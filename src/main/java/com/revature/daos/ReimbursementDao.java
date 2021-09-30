package com.revature.daos;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;

public class ReimbursementDao implements ReimbursementDaoInterface {

	@Override
	public List<Reimbursement> getAllReimbursements() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> reimList = ses.createQuery("FROM Reimbursement").list();
		
		HibernateUtil.closeSession();
		
		return reimList;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAmount(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByDate(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByType(int type_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(int status_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthor(int author_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByResolver(int resolver_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReimbursement(double amount, Date sDate, Date rDate, String description, int author, int resolver,
			int status, int type, byte[] receipt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReimbursement(double amount, Date rDate, String description, int status, int type,
			byte[] receipt) {
		// TODO Auto-generated method stub

	}

}
