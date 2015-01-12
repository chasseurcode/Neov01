package com.neo.securite;

import java.util.Date;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Utilisateur;

public class APIRealm extends AuthorizingRealm{
	private UtilisateurDAO UtilisateurDAO;
	
	public APIRealm() {
		setUtilisateurDAO(new UtilisateurDAOImpl());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
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
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(utilisateur.getCompte(), utilisateur.getMotDePasse(), getName());
		return info;
	}

	public UtilisateurDAO getUtilisateurDAO() {
		return UtilisateurDAO;
	}

	public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
		UtilisateurDAO = utilisateurDAO;
	}

}
