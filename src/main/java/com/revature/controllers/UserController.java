package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	UserService us = new UserService();
	Role role = new Role();
	
	public Handler isManagerHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			User user = gson.fromJson(body, User.class);
			
			String userJSON;
			
			// Set role on the returning JSON object
			if (us.isManager(user)) {
				role.setRole_id(1);
				user.setRole(role);
			} else {
				role.setRole_id(2);
				user.setRole(role);
			}
			
			userJSON = gson.toJson(user);
			ctx.result(userJSON);
			ctx.status(200);
			
		} else {
			ctx.status(403);
			
			ctx.result("Unable to access list of Users");
		}
		
	};
}
