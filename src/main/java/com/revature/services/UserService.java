package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class UserService {
	
	UserDao uDao = new UserDao();
	
	public boolean isManager(User user) {
		return (uDao.isManager(user));
	}

}
