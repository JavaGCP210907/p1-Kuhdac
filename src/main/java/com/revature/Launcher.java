package com.revature;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.controllers.ReimbursementController;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Type;
import com.revature.models.Status;
import com.revature.utils.HibernateUtil;

import io.javalin.Javalin;

public class Launcher {
	
	public static void main(String[] args) {
		
						
		ReimbursementController rc = new ReimbursementController();
						
		Javalin app = Javalin.create(
				config ->{ config.enableCorsForAllOrigins();
				}
				).start(8090);
		
		app.get("/reimbursements", rc.getAllReimbursementsHandler);
				
	}

}
