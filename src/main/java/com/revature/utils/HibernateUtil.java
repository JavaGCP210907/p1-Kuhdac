package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	private static Session s;
	
	
	public static Session getSession() {
		if (s == null) {
			s = sf.openSession();
		}
		return s;
	}
	
	public static void closeSession() {
		s.close();
		s = null;
	}

}
