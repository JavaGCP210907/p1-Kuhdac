package com.revature.daos;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDao implements UserDaoInterface {
	
	Logger logger = Logger.getLogger(UserDao.class);

	@Override
	public boolean verifyUserLogin(String username, String password) {
		
		try(Session ses = HibernateUtil.getSession()){
			
		
			String hql = "FROM User U WHERE U.username = :username AND U.password = :password";
			
			Query query = ses.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			if (query.uniqueResult() != null) {
				
				HibernateUtil.closeSession();
				logger.info(username + "logged in");
				return true;
			}
			
			HibernateUtil.closeSession();
			return false;
		
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to access database for login");
		}
		return false;
	}

	@Override
	public boolean isManager(User user) {
		
		Session ses = HibernateUtil.getSession();
		
		String hql = "FROM User U WHERE U.username = :username AND U.role.role_id = :role";
		
		Query query = ses.createQuery(hql);
		query.setParameter("username", user.getUsername());
		query.setParameter("role", 1);
		
		if (query.uniqueResult() != null) {
			
			HibernateUtil.closeSession();
			return true;
		}
		
		HibernateUtil.closeSession();
		return false;
	}

	@Override
	public User getUserByUsername(String username) {
		try (Session ses = HibernateUtil.getSession()){
		
			String hql = "FROM User U WHERE U.username = :username";
			TypedQuery<User> query = ses.createQuery(hql);
			query.setParameter("username", username);
			User user = query.getSingleResult();
			
			
			HibernateUtil.closeSession();
		
			logger.info("User retrieved user info for user" + username);
			
			return user;
	
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to retrieve user info for " + username);
		}
		
		return null;
	}
}