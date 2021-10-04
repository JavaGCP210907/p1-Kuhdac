package com.revature.daos;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
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
	public List<Reimbursement> getReimbursementsByStatus(Status status_id) {

		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> rList = ses.createQuery("FROM Reimbursement R WHERE R.status = " + status_id.getStatus_id()).list();
		
		HibernateUtil.closeSession();
		
		return rList;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthor(User user) {
		
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> rList = ses.createQuery("FROM Reimbursement R WHERE R.author = " + user.getUser_id()).list();
		
		HibernateUtil.closeSession();
		
		return rList;
	}

	@Override
	public List<Reimbursement> getReimbursementsByResolver(int resolver_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReimbursement(Reimbursement reimbursement) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(reimbursement);
		
		HibernateUtil.closeSession();

	}

	@Override
	public void updateReimbursement(Reimbursement reimbursement) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction();
		
		String HQL = "UPDATE Reimbursement SET status = " + reimbursement.getStatus().getStatus_id() + " WHERE id = " + reimbursement.getReimb_Id();
		
		Query q = ses.createQuery(HQL);
		
		q.executeUpdate();
		
		tran.commit();
		HibernateUtil.closeSession();
		

	}

}
