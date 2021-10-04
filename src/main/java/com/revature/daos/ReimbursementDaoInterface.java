package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

public interface ReimbursementDaoInterface {
	
	public List<Reimbursement> getAllReimbursements();
	
	public Reimbursement getReimbursementById(int id);
	
	public List<Reimbursement> getReimbursementsByAmount(double min, double max);
	
	public List<Reimbursement> getReimbursementsByDate(Date start, Date end);
	
	public List<Reimbursement> getReimbursementsByType(int type_id);
	
	public List<Reimbursement> getReimbursementsByStatus(Status status_id);
	
	public List<Reimbursement> getReimbursementsByAuthor(User user);
	
	public List<Reimbursement> getReimbursementsByResolver(int resolver_id);
	
	public void createReimbursement(Reimbursement reimbursement);
	
	public void updateReimbursement(Reimbursement reimbursement);

}
