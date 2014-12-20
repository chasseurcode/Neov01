package com.neo.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@ManagedBean(name="auth")
@SessionScoped
public class AuthBean {
	private boolean desable=false;
	private String username;
	private String password;
	private boolean remember=false;

	/*
	 * connexion de l'utilisateur
	 */
	public String login() {
		System.out.println("appel de la page");
		desable=true;
		Subject currentUser=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username, password,remember);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Veillez saisir des idenfiants corrects."));
			desable=false;
			return "pretty:login";
		}
		return "pretty:userhome";
	}
	
	public void verifyLogin() {
		System.out.println("Appel de methode");
	}

	/*
	 * deconnection de l'utilisateur
	 */
	public String  logout() {
		Subject currentUser=SecurityUtils.getSubject();
		try {
			currentUser.logout();
			desable=false;
			return "pretty:login";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public boolean isDesable() {
		return desable;
	}

	public void setDesable(boolean desable) {
		this.desable = desable;
	}
}
