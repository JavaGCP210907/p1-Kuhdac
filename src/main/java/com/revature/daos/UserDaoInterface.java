package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDaoInterface {
	
	public List<User> getUsers();
	
	public User getUserById(int id);
	
	public List<User> getUsersByRoleId(int role_id);
	

}
