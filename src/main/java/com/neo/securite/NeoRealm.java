package com.neo.securite;

import java.util.Date;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Permission;
import com.neo.domaine.Role;
import com.neo.domaine.Utilisateur;

public class NeoRealm extends AuthorizingRealm {
	private UtilisateurDAO UtilisateurDAO;
	
	public NeoRealm() {
		setUtilisateurDAO(new UtilisateurDAOImpl());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String compte=principals.getPrimaryPrincipal().toString();
		Utilisateur utilisateur=UtilisateurDAO.findByCompte(compte);
		if(utilisateur!=null){
			SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
			
			for(Role role: utilisateur.getRoles()){
				info.addRole(role.getNom());
				for(Permission permission: role.getPermissions()){
					info.addStringPermission(permission.getNom());
				}
			}
			
			return info;
		}
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken mToken=(UsernamePasswordToken) token;
		
		Utilisateur utilisateur=UtilisateurDAO.findByCompte(mToken.getUsername());
		if(utilisateur==null){
			throw new AuthenticationException("Ce compte n'existe pas");
		}
		utilisateur.setUpdated(new Date());
		UtilisateurDAO.modifier(utilisateur);
		SaltAuthInfo info=new SaltAuthInfo(utilisateur.getCompte(), utilisateur.getMotDePasse(), utilisateur.getSaltMotDePasse());
		return info;
	}

	public UtilisateurDAO getUtilisateurDAO() {
		return UtilisateurDAO;
	}

	public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
		UtilisateurDAO = utilisateurDAO;
	}

}
