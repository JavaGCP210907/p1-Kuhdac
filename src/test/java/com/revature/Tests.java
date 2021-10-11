package com.revature;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Tests {
	
	LoginService ls = new LoginService();
	ReimbursementService rs = new ReimbursementService();
	UserService us = new UserService();
	
	@Test
	public void getUserbyUsernameTest() {
		User testUser = us.getUserByUsername("dblair");
		Assertions.assertFalse(testUser.getEmail().isEmpty());
		System.out.println(testUser.getF_name());
		Assertions.assertEquals("Doctor", testUser.getF_name());
	}
	
	@Test
	public void getReimbursementsTest() {
		List<Reimbursement> testReimbList = rs.getReimbursements();
		Assertions.assertFalse(testReimbList.isEmpty());
	}
	
	@Test
	public void getReimbursementsByIdTest() {
		List<Reimbursement> testReimbList = rs.getReimbursementsByAuthor(5);
		Assertions.assertFalse(testReimbList.isEmpty());
		Assertions.assertNotEquals(1, testReimbList.size());
		for (Reimbursement reimb: testReimbList) {
			Assertions.assertEquals("dblair", reimb.getAuthor().getUsername());
		}
	}
	
	@Test
	public void getReimbursementsByStatusTest() {
		List<Reimbursement> testReimbList = rs.getReimbursementByStatus("4", "PENDING");
		Assertions.assertFalse(testReimbList.isEmpty());
		for (Reimbursement reimb: testReimbList) {
			Assertions.assertEquals("kchilds", reimb.getAuthor().getUsername());
			Assertions.assertEquals("PENDING", reimb.getStatus().getStatus());
		}
		
	}
	
	@Test
	public void LoginTest() {
		Assertions.assertTrue(ls.login("dblair", "password"));
		Assertions.assertFalse(ls.login("DummyUser", "password"));
	}
}
