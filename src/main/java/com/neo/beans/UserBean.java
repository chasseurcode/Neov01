package com.neo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import com.neo.dao.RoleDAO;
import com.neo.dao.UtilisateurDAO;
import com.neo.daoImpl.RoleDAOImpl;
import com.neo.daoImpl.UtilisateurDAOImpl;
import com.neo.domaine.Role;
import com.neo.domaine.Utilisateur;

@ManagedBean
@SessionScoped
public class UserBean {
	private Utilisateur user;
	private UtilisateurDAO userDAO;
	private String motPasse,confirme;
	private List<Role> roleDipo;
	private List<String> selectRole;
	private List<Utilisateur> users;
	private RoleDAO roleDAO;

	
	@PostConstruct
	public void init() {
		roleDAO=new RoleDAOImpl();
		userDAO=new UtilisateurDAOImpl();
		selectRole=new ArrayList<String>();
		roleDipo=roleDAO.lister();
		user=new Utilisateur();
		refreshListe();	
	}


	private void refreshListe() {
		setUsers(userDAO.lister());	
	}

	public String  addUser() {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();
		String hashPass = new Sha256Hash(motPasse, salt,1024).toBase64();
		user.setMotDePasse(hashPass);
		user.setSaltMotDePasse(salt.toString());
		userDAO.creer(user);
		List<Role> mRoles=new ArrayList<Role>();
		for(String roleId: selectRole){
			mRoles.add(roleDAO.findById(new Long(roleId)));
		}
		user.setRoles(mRoles);
		userDAO.modifier(user);
		user=new Utilisateur();
		selectRole.clear();
		refreshListe();
		return "pretty:userslist";
	}

	public String desactiverCompte(Utilisateur user) {
		if(user.isActif()){
			user.setActif(false);	
		}else {
			user.setActif(true);
		}
		userDAO.modifier(user);
		refreshListe();
		return null;
	}

	public String editUser(Utilisateur user) {
		setUser(user);
		return "pretty:edituser";
	}
	public List<Role> getRoleDipo() {
		return roleDipo;
	}

	public void setRoleDipo(List<Role> roleDipo) {
		this.roleDipo = roleDipo;
	}

	public List<String> getSelectRole() {
		return selectRole;
	}

	public void setSelectRole(List<String> selectRole) {
		this.selectRole = selectRole;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public UtilisateurDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UtilisateurDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getConfirme() {
		return confirme;
	}

	public void setConfirme(String confirme) {
		this.confirme = confirme;
	}

	public List<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}









}
