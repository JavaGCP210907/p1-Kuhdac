package com.revature.controllers;

import com.auth0.jwt.JWT;
import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;
import com.revature.services.UserService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {
	
	LoginService ls = new LoginService();
	
	UserService us = new UserService();
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
		
		String username = LDTO.getUsername();
		String password = LDTO.getPassword();
		
		if(ls.login(username, password)) {
			
			String jwt = JwtUtil.generate(username, password);
			
			ctx.req.getSession().setAttribute(username, us.getUserByUsername(username));
			
			ctx.status(200);
			
			ctx.result("Login Succesful! JWT is: " + jwt);
			
			
		} else {
			ctx.status(401);
			ctx.result("Login Failed");
		}
	};

}
