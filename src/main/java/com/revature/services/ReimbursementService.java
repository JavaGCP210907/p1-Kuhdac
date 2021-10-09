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
	
	public List<Reimbursement> getReimbursementByStatus(String user_id, String status){
		return rDao.getReimbursementsByStatus(user_id, status);
	}
	
	public List<Reimbursement> getReimbursementsByAuthor(int user_id){
		return rDao.getReimbursementsByAuthor(user_id);
	}
	
	public void updateReimbursementByStatus(Reimbursement reimbursement) {
		rDao.updateReimbursement(reimbursement);
	}

}
