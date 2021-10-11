package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	UserService us = new UserService();
	Role role = new Role();
	
	public Handler getUserByUsername = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			String username = gson.fromJson(body, String.class);
			
			User user = us.getUserByUsername(username);
			
			String JSONUser = gson.toJson(user);
			
			ctx.result(JSONUser);
			ctx.status(200);
		} else {
			ctx.result("Unable to retrieve User");
			ctx.status(403);
		}
	};
	
}
