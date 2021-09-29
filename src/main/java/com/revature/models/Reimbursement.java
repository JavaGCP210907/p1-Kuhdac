package com.revature.models;

import java.sql.Date;
import java.util.Arrays;

public class Reimbursement {
	
	private int reimb_Id;
	private double reimb_Amnt;
	private Date dateSubmitted;
	private Date dateResolved;
	private String description;
	private int reimb_author;
	private int reimb_resolver;
	private int reimb_status;
	private int reimb_type;
	private byte[] receipt;
	
	
	
	public Reimbursement() {
		super();
	}



	public Reimbursement(int reimb_Id, double reimb_Amnt, Date dateSubmitted, Date dateResolved, String description,
			int reimb_author, int reimb_resolver, int reimb_status, int reimb_type, byte[] receipt) {
		super();
		this.reimb_Id = reimb_Id;
		this.reimb_Amnt = reimb_Amnt;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
		this.receipt = receipt;
	}



	public Reimbursement(double reimb_Amnt, Date dateSubmitted, Date dateResolved, String description, int reimb_author,
			int reimb_resolver, int reimb_status, int reimb_type, byte[] receipt) {
		super();
		this.reimb_Amnt = reimb_Amnt;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
		this.receipt = receipt;
	}



	public int getReimb_Id() {
		return reimb_Id;
	}



	public void setReimb_Id(int reimb_Id) {
		this.reimb_Id = reimb_Id;
	}



	public double getReimb_Amnt() {
		return reimb_Amnt;
	}



	public void setReimb_Amnt(double reimb_Amnt) {
		this.reimb_Amnt = reimb_Amnt;
	}



	public Date getDateSubmitted() {
		return dateSubmitted;
	}



	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}



	public Date getDateResolved() {
		return dateResolved;
	}



	public void setDateResolved(Date dateResolved) {
		this.dateResolved = dateResolved;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getReimb_author() {
		return reimb_author;
	}



	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}



	public int getReimb_resolver() {
		return reimb_resolver;
	}



	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}



	public int getReimb_status() {
		return reimb_status;
	}



	public void setReimb_status(int reimb_status) {
		this.reimb_status = reimb_status;
	}



	public int getReimb_type() {
		return reimb_type;
	}



	public void setReimb_type(int reimb_type) {
		this.reimb_type = reimb_type;
	}



	public byte[] getReceipt() {
		return receipt;
	}



	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}



	@Override
	public String toString() {
		return "Reimbursement [reimb_Id=" + reimb_Id + ", reimb_Amnt=" + reimb_Amnt + ", dateSubmitted=" + dateSubmitted
				+ ", dateResolved=" + dateResolved + ", description=" + description + ", reimb_author=" + reimb_author
				+ ", reimb_resolver=" + reimb_resolver + ", reimb_status=" + reimb_status + ", reimb_type=" + reimb_type
				+ ", receipt=" + Arrays.toString(receipt) + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateResolved == null) ? 0 : dateResolved.hashCode());
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(receipt);
		long temp;
		temp = Double.doubleToLongBits(reimb_Amnt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimb_Id;
		result = prime * result + reimb_author;
		result = prime * result + reimb_resolver;
		result = prime * result + reimb_status;
		result = prime * result + reimb_type;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (dateResolved == null) {
			if (other.dateResolved != null)
				return false;
		} else if (!dateResolved.equals(other.dateResolved))
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (Double.doubleToLongBits(reimb_Amnt) != Double.doubleToLongBits(other.reimb_Amnt))
			return false;
		if (reimb_Id != other.reimb_Id)
			return false;
		if (reimb_author != other.reimb_author)
			return false;
		if (reimb_resolver != other.reimb_resolver)
			return false;
		if (reimb_status != other.reimb_status)
			return false;
		if (reimb_type != other.reimb_type)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
