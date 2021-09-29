package com.revature.daos;

import java.sql.Date;
import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDaoInterface {
	
	public List<Reimbursement> getAllReimbursements();
	
	public Reimbursement getReimbursementById(int id);
	
	public List<Reimbursement> getReimbursementsByAmount(double min, double max);
	
	public List<Reimbursement> getReimbursementsByDate(Date start, Date end);
	
	public List<Reimbursement> getReimbursementsByType(int type_id);
	
	public List<Reimbursement> getReimbursementsByStatus(int status_id);
	
	public List<Reimbursement> getReimbursementsByAuthor(int author_id);
	
	public List<Reimbursement> getReimbursementsByResolver(int resolver_id);
	
	public void createReimbursement(double amount, Date sDate, Date rDate, String description,
									int author, int resolver, int status, int type, byte[] receipt);
	
	public void updateReimbursement(double amount, Date rDate, String description, int status, int type, byte[] receipt);

}
