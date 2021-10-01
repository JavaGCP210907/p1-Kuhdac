package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.controllers.ReimbursementController;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

import io.javalin.Javalin;

public class Launcher {
	
	public static void main(String[] args) {
		
		
						
		ReimbursementController rc = new ReimbursementController();
						
		Javalin app = Javalin.create(
				config ->{ config.enableCorsForAllOrigins();
				}
				).start(8090);
		
		app.get("/reimbursements", rc.getAllReimbursementsHandler);
		
		app.post("/reimbursements", rc.createReimbursementHandler);
		
		ReimbursementDao rDao= new ReimbursementDao();
		
		List<Reimbursement> reimb = rDao.getAllReimbursements();
		
		for (Reimbursement r: reimb) {
			System.out.println("Amount: " + r.getReimb_Amnt() + "Date Submitted: " + r.getDateSubmitted());
			
		}
		
		
		
		
		
		
				
	}

}
