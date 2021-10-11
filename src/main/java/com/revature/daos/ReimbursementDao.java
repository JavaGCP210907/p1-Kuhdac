package com.revature.daos;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDao implements ReimbursementDaoInterface {
	
	Logger logger = Logger.getLogger(ReimbursementDao.class);

	@Override
	public List<Reimbursement> getAllReimbursements() {
		try (Session ses = HibernateUtil.getSession();){
		
			List<Reimbursement> reimList = ses.createQuery("FROM Reimbursement").list();
			
			HibernateUtil.closeSession();
			
			logger.info("User retrieved a list of all employees");
			
			return reimList;
		
		
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to retrieve Reimbursements");
		}
		
		return null;
	}
	

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String user_id, String status) {

		try(Session ses = HibernateUtil.getSession()){
		
			String hql = "FROM Reimbursement R WHERE R.status.status = :status AND R.author.user_id = " +  user_id;
			
			TypedQuery<Reimbursement> query = ses.createQuery(hql);
			query.setParameter("status", status);
			
			List<Reimbursement> rList = query.getResultList();
			
			HibernateUtil.closeSession();
			
			logger.info("Employee " + user_id + " retrieved a list of " + status + " reimbursment requests");
			
			return rList;
		
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to retrieve reimbursements by status");
		}
		
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthor(int user_id) {
		
		try(Session ses = HibernateUtil.getSession()){
		
			Query query = ses.createQuery("FROM Reimbursement R WHERE R.author.user_id = :user_id");
			query.setParameter("user_id", user_id);
			
			List<Reimbursement> rList = query.getResultList();
			
			HibernateUtil.closeSession();
			
			logger.info(user_id + " retrieved a list of their reimbursement requests");
			
			return rList;
		
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("User " + user_id + " was unable to retireve a list of their reimbursement requests");
		}
		
		return null;
	}

	@Override
	public void createReimbursement(Reimbursement reimbursement) {
		
		try (Session ses = HibernateUtil.getSession()){
		
			ses.save(reimbursement);
		
			HibernateUtil.closeSession();
			
			logger.info("Employee #" + reimbursement.getAuthor().getUser_id()+ " created a new reimbursement request");
		
		}catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to create reimbursement");
		}
	}

	@Override
	public void updateReimbursement(Reimbursement reimbursement) {
		
		try(Session ses = HibernateUtil.getSession()){
			
			Transaction tran = ses.beginTransaction();
			
			Date date = reimbursement.getDateResolved();
			String dateChar = date.toString();

			
			String HQL1 = "UPDATE Reimbursement SET status = " + reimbursement.getStatus().getStatus_id() + " WHERE id = " + reimbursement.getReimb_Id();
			String HQL2 = "UPDATE Reimbursement SET resolver  = " + reimbursement.getResolver().getUser_id() + " WHERE id = " + reimbursement.getReimb_Id();
			String HQL3 = "UPDATE Reimbursement SET date_resolved = :date WHERE id = " + reimbursement.getReimb_Id(); 
			
			Query q = ses.createQuery(HQL1);
			Query r = ses.createQuery(HQL2);
			Query s = ses.createQuery(HQL3);
			s.setParameter("date", date);
			
			q.executeUpdate();
			r.executeUpdate();
			s.executeUpdate();
			
			tran.commit();
			HibernateUtil.closeSession();
			
			logger.info("Reimbursement Request: " + reimbursement.getReimb_Id() + " was updated");
		
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to update reimbursement status");
		}
	}

	@Override
	public List<Reimbursement> getActiveUserReq(User user) {
		
		try(Session ses = HibernateUtil.getSession()){
		
			List <Reimbursement> rList = ses.createQuery("SELECT * FROM Reimbursement WHERE status_id = 3 AND author = " + user.getUser_id()).list();
			
			HibernateUtil.closeSession();
			
			return rList;
		
		} catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(int status_id) {

		try(Session ses = HibernateUtil.getSession()){
		
			String hql = "FROM Reimbursement R WHERE R.status.status_id = :status_id";
			
			TypedQuery<Reimbursement> query = ses.createQuery(hql);
			query.setParameter("status_id", status_id);
			
			List<Reimbursement> rList = query.getResultList();
			
			HibernateUtil.closeSession();
			
			logger.info("Manager retrieved a list of all requests with the status id: " + status_id);
			
			return rList;
		
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Unable to retrieve reimbursements by status");
		}
		
		return null;

	}
}
