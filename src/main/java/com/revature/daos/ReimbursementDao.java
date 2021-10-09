package com.revature.daos;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	public List<Reimbursement> getReimbursementsByStatus(String user_id, String status) {

		Session ses = HibernateUtil.getSession();
		
		String hql = "FROM Reimbursement R WHERE R.status.status = :status AND R.author.user_id = " +  user_id;
		
		TypedQuery<Reimbursement> query = ses.createQuery(hql);
		query.setParameter("status", status);
		
		List<Reimbursement> rList = query.getResultList();
		
		HibernateUtil.closeSession();
		
		return rList;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthor(int user_id) {
		
		Session ses = HibernateUtil.getSession();
		
		Query query = ses.createQuery("FROM Reimbursement R WHERE R.author.user_id = :user_id");
		query.setParameter("user_id", user_id);
		
		List<Reimbursement> rList = query.getResultList();
		
		HibernateUtil.closeSession();
		
		return rList;
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

	@Override
	public List<Reimbursement> getActiveUserReq(User user) {
		
		Session ses = HibernateUtil.getSession();
		
		List <Reimbursement> rList = ses.createQuery("SELECT * FROM Reimbursement WHERE status_id = 3 AND author = " + user.getUser_id()).list();
		
		HibernateUtil.closeSession();
		
		return rList;
	}

	@Override
	public List<Reimbursement> getInactiveUserReq(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
