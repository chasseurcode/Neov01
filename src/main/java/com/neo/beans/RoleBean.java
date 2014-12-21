package com.neo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import com.neo.dao.PermissionDAO;
import com.neo.dao.RoleDAO;
import com.neo.daoImpl.PermissionDAOImpl;
import com.neo.daoImpl.RoleDAOImpl;
import com.neo.domaine.Permission;
import com.neo.domaine.Role;

@ManagedBean
@RequestScoped
public class RoleBean {
	private List<Permission> perms;
	private List<String> selectPerm;
	private PermissionDAO permDAO;
	private List<Role> roles;
	private Role role;
	private RoleDAO roleDAO;
	
	@PostConstruct
	public void init() {
		permDAO=new PermissionDAOImpl();
		roleDAO=new RoleDAOImpl();
		perms=permDAO.lister();
		setRoles(roleDAO.lister());
		role=new Role();
		selectPerm=new ArrayList<String>();
	}
	
	public String addRole() {
		List<Permission> listP=new ArrayList<Permission>();
		for(String permId: selectPerm){
			listP.add(permDAO.findById(Integer.parseInt(permId)));
		}
		role.setPermissions(listP);
		roleDAO.creer(role);
		return "pretty:roles";
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

	public List<String> getSelectPerm() {
		return selectPerm;
	}

	public void setSelectPerm(List<String> selectPerm) {
		this.selectPerm = selectPerm;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
