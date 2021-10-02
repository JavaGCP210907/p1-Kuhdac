package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Status;

public interface ReimbursementDaoInterface {
	
	public List<Reimbursement> getAllReimbursements();
	
	public Reimbursement getReimbursementById(int id);
	
	public List<Reimbursement> getReimbursementsByAmount(double min, double max);
	
	public List<Reimbursement> getReimbursementsByDate(Date start, Date end);
	
	public List<Reimbursement> getReimbursementsByType(int type_id);
	
	public List<Reimbursement> getReimbursementsByStatus(Status status_id);
	
	public List<Reimbursement> getReimbursementsByAuthor(int author_id);
	
	public List<Reimbursement> getReimbursementsByResolver(int resolver_id);
	
	public void createReimbursement(Reimbursement reimbursement);
	
	public void updateReimbursement(double amount, Date rDate, String description, int status, int type, byte[] receipt);

}
