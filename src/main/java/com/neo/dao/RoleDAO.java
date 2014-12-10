package com.neo.dao;

import java.util.List;

import com.neo.domaine.Role;

public interface RoleDAO {
	public Role parId(int id);
	public void creer(Role role);
	public void modifier(Role role);
	public void supprimer(Role role);
	public List<Role> lister();
}
