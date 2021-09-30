package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import io.javalin.http.Handler;

public class ReimbursementController {
	
	ReimbursementService rs = new ReimbursementService();
	
	public Handler getAllReimbursementsHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
			List<Reimbursement> allReimbursements = rs.getReimbursements();
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(allReimbursements);
			
			ctx.result(JSONReimbursements);
			
			ctx.status(200);
			
		} else {
			ctx.status(403);
		}
	};

}
