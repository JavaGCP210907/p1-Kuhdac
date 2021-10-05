package com.revature.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDao implements UserDaoInterface {

	@Override
	public boolean verifyUserLogin(String username, String password) {
		
		Session ses = HibernateUtil.getSession();
		
		String hql = "FROM User U WHERE U.username = :username AND U.password = :password";
		
		Query query = ses.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		if (query.uniqueResult() != null) {
			
			HibernateUtil.closeSession();
			return true;
		}
		
		HibernateUtil.closeSession();
		return false;
		
	}
}
