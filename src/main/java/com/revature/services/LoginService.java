package com.revature.services;

import org.hibernate.Session;

import com.revature.daos.UserDao;
import com.revature.utils.HibernateUtil;

public class LoginService {
	
	public boolean login(String username, String password) {
		
		UserDao uDao = new UserDao();
				
		if(uDao.verifyUserLogin(username, password)) {
			return true;
		}
		
		return false;
	}

}
