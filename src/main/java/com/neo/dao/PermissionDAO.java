package com.neo.dao;

import java.util.List;

import com.neo.domaine.Permission;

public interface PermissionDAO {
	public Permission parId(int id);
	public void creer(Permission permission);
	public void modifier(Permission permission);
	public void supprimer(Permission permission);
	public List<Permission> lister();
}
