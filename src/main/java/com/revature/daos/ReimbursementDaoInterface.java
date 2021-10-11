package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public interface ReimbursementDaoInterface {
	
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getActiveUserReq (User user);
	
	public List<Reimbursement> getAllReimbursementsByStatus(int status_id);
	
	public List<Reimbursement> getReimbursementsByStatus(String user_id, String status);
	
	public List<Reimbursement> getReimbursementsByAuthor(int user_id);
	
	public void createReimbursement(Reimbursement reimbursement);
	
	public void updateReimbursement(Reimbursement reimbursement);

}
