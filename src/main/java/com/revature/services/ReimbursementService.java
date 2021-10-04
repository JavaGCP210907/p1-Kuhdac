package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public class ReimbursementService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	
	public List<Reimbursement> getReimbursements() {
		return rDao.getAllReimbursements();
	}
	
	public void createReimbursements(Reimbursement reimbursement) {
		rDao.createReimbursement(reimbursement);
	}
	
	public List<Reimbursement> getReimbursementByStatus(Status status){
		return rDao.getReimbursementsByStatus(status);
	}
	
	public List<Reimbursement> getReimbursementsByAuthor(User user){
		return rDao.getReimbursementsByAuthor(user);
	}
	
	public void updateReimbursementByStatus(Reimbursement reimbursement) {
		rDao.updateReimbursement(reimbursement);
	}

}
