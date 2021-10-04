package com.revature.models;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int reimb_Id;
	
	@Column(name = "amount")
	private double reimb_Amnt;
	
	@Column(name = "date_submitted")
	private Date dateSubmitted;
	
	@Column(name = "date_resolved")
	private Date dateResolved;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User author;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "")
	private User resolver;
	
	@JoinColumn(name = "status_id")
	@ManyToOne(targetEntity = Status.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Status status;
	
	@ManyToOne(targetEntity = Type.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id")
	private Type type;
	
	@Column(name = "receipt")
	private byte[] receipt;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimb_Id, double reimb_Amnt, Date dateSubmitted, Date dateResolved, String description,
			User author, User resolver, Status status, Type type, byte[] receipt) {
		super();
		this.reimb_Id = reimb_Id;
		this.reimb_Amnt = reimb_Amnt;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		this.receipt = receipt;
	}

	public Reimbursement(double reimb_Amnt, Date dateSubmitted, Date dateResolved, String description, User author,
			User resolver, Status status, Type type, byte[] receipt) {
		super();
		this.reimb_Amnt = reimb_Amnt;
		this.dateSubmitted = dateSubmitted;
		this.dateResolved = dateResolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((dateResolved == null) ? 0 : dateResolved.hashCode());
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + Arrays.hashCode(receipt);
		long temp;
		temp = Double.doubleToLongBits(reimb_Amnt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimb_Id;
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
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
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_Id=" + reimb_Id + ", reimb_Amnt=" + reimb_Amnt + ", dateSubmitted=" + dateSubmitted
				+ ", dateResolved=" + dateResolved + ", description=" + description + ", author=" + author
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + ", receipt="
				+ Arrays.toString(receipt) + "]";
	}
	
}