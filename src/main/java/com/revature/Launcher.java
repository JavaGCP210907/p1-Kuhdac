package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Type;
import com.revature.models.Status;
import com.revature.utils.HibernateUtil;

public class Launcher {
	
	public static void main(String[] args) {
		
		Reimbursement reimbursement = new Reimbursement();
		Type type = new Type("lodging");
		Role role = new Role();
		Status status = new Status();
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(status);
		ses.save(type);
		ses.save(role);
		ses.save(reimbursement);
		
		
		HibernateUtil.closeSession();
	}

}
