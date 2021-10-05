package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;

import io.javalin.Javalin;

public class Launcher {
	
	public static void main(String[] args) {
						
		ReimbursementController rc = new ReimbursementController();
		
		LoginController lc = new LoginController();
						
		Javalin app = Javalin.create(
				config ->{ config.enableCorsForAllOrigins();
				}
				).start(8090);
		
		app.post("/login", lc.loginHandler);
		
		app.get("/reimbursements", rc.getAllReimbursementsHandler);
		
		app.post("/reimbursements", rc.createReimbursementHandler);
		
		app.post("/status", rc.getReimbursementsByStatus);
			
		}
		
		
		
		
		
		
				
	}

