package com.revature.daos;

import java.util.List;

import com.revature.models.Role;

public interface RoleDaoInterface {
	
	public List<Role> getRoles();
	
	public Role getRoleById(int id);

}
