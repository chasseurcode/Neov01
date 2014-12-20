package com.neo.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neo.dao.PermissionDAO;
import com.neo.dao.RoleDAO;
import com.neo.daoImpl.PermissionDAOImpl;
import com.neo.daoImpl.RoleDAOImpl;
import com.neo.domaine.Permission;
import com.neo.domaine.Role;

@ManagedBean
@SessionScoped
public class RoleBean {
	private List<Permission> perms;
	private PermissionDAO permDAO;
	private Role role;
	private RoleDAO roleDAO;
	
	@PostConstruct
	public void init() {
		permDAO=new PermissionDAOImpl();
		roleDAO=new RoleDAOImpl();
		perms=permDAO.lister();
		System.out.println("size: "+perms.size());
	}
	
	public List<Permission> getPerms() {
		return perms;
	}
	public void setPerms(List<Permission> perms) {
		this.perms = perms;
	}
	public PermissionDAO getPermDAO() {
		return permDAO;
	}
	public void setPermDAO(PermissionDAO permDAO) {
		this.permDAO = permDAO;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
}
