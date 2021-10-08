package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDaoInterface {
	
	public boolean verifyUserLogin(String username, String password);
	
	public boolean isManager(User user);
	
	public User getUserByUsername(String username);
	

}
