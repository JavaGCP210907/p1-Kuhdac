package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDaoInterface {
	
	public boolean verifyUserLogin(String username, String password);
	

}
