package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public interface ReimbursementDaoInterface {
	
	public List<Reimbursement> getAllReimbursements();
	
	public Reimbursement getReimbursementById(int id);
	
	public List<Reimbursement> getActiveUserReq (User user);
	
	public List<Reimbursement> getInactiveUserReq (User user);
	
	public List<Reimbursement> getReimbursementsByStatus(Status status_id);
	
	public List<Reimbursement> getReimbursementsByAuthor(User user);
	
	public void createReimbursement(Reimbursement reimbursement);
	
	public void updateReimbursement(Reimbursement reimbursement);

}
