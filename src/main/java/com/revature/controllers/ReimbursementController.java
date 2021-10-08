package com.revature.controllers;

import java.util.Enumeration;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	ReimbursementService rs = new ReimbursementService();
	Status s = new Status();
	
	public Handler getAllReimbursementsHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
			List<Reimbursement> allReimbursements = rs.getReimbursements();
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(allReimbursements);
			
			ctx.result(JSONReimbursements);
			
			ctx.status(200);
		
		} else {
			ctx.status(403);
			
			ctx.result("Something is up");
		}
	};
	
	public Handler createReimbursementHandler = (ctx) -> {
		
		if (ctx.req.getSession(false) != null) {
			
			String body = ctx.body();
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
			
			rs.createReimbursements(reimbursement);
			
			ctx.status(200);
			
			ctx.result("Reimbursement Created");
			
		} else {
			ctx.status(403);
			
			ctx.result("Unable to add Reimbursement");
		}
	};
		
	public Handler getReimbursementsByStatus = (ctx) -> {
		
		if (ctx.req.getSession(false) != null) {
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			Status status = gson.fromJson(body, Status.class);			
		
			List <Reimbursement> reimByStatus = rs.getReimbursementByStatus(status);
			
			String JSONReimByStatus = gson.toJson(reimByStatus);
			
			ctx.result(JSONReimByStatus);
			
			ctx.status(200);
			
		} else {
			
			ctx.status(403);
			
			ctx.result("Unable to get Reimbursements");
		}
		
		
	};
	
	public Handler getReimbursementByUser = (ctx) -> {
		
		if (ctx.req.getSession(false) != null) {
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			User user = gson.fromJson(body, User.class);
			
			List<Reimbursement> reimByAuthor = rs.getReimbursementsByAuthor(user);
			
			String JSONReimByAuthor = gson.toJson(reimByAuthor);
			
			ctx.result(JSONReimByAuthor);
			
			ctx.status(200);
			
		} else {
			
			ctx.status(403);
			
			ctx.result("Unable to get Reimbursement by User");
		}
	};
	
	public Handler updateReimbursementStatus = (ctx) ->{
		
		if (ctx.req.getSession(false) != null) {
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
			
			rs.updateReimbursementByStatus(reimbursement);
			
			ctx.result("Status Updated");
			
			ctx.status(200);
		} else {
			
			ctx.status(403);
			
			ctx.result("Unable to update status");
		}
	};

}
