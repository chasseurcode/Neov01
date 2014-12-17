package com.neo.securite;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class SaltAuthInfo implements SaltedAuthenticationInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String compte;
	private String motDePasse;
	private String saltMotDePasse;
	
	

	public SaltAuthInfo(String compte, String motDePasse, String saltMotDePasse) {
		super();
		this.compte = compte;
		this.motDePasse = motDePasse;
		this.saltMotDePasse = saltMotDePasse;
	}

	@Override
	public PrincipalCollection getPrincipals() {
		SimplePrincipalCollection principal=new SimplePrincipalCollection(compte, compte);
		return principal;
	}

	@Override
	public Object getCredentials() {
		return motDePasse;
	}

	@Override
	public ByteSource getCredentialsSalt() {
		ByteSource byteSource=new SimpleByteSource(Base64.decode(saltMotDePasse));
		System.out.println(Base64.decode(saltMotDePasse));
		return byteSource;
	}


	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getSaltMotDePasse() {
		return saltMotDePasse;
	}

	public void setSaltMotDePasse(String saltMotDePasse) {
		this.saltMotDePasse = saltMotDePasse;
	}
	
}
