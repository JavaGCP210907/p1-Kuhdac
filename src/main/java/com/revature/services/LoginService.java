package com.revature.services;

public class LoginService {
	
	public boolean login(String username, String password) {
		
		if(username.equals("admin") && password.equals("password")) {
			return true;
		}
		
		return false;
	}

}
