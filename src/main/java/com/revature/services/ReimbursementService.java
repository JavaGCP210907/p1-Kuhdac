package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	ReimbursementDao rDao = new ReimbursementDao();
	
	public List<Reimbursement> getReimbursements() {
		return rDao.getAllReimbursements();
	}
	
	public void createReimbursements(Reimbursement reimbursement) {
		rDao.createReimbursement(reimbursement);
	}

}
