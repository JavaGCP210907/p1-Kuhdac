package com.revature.models;

public class BaseEntity {
	
	private int employeeId;
	private String fName;
	private String lName;
	private String department;
	private boolean isManager;
	
	
	
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BaseEntity(int employeeId, String fName, String lName, String department, boolean isManager) {
		super();
		this.employeeId = employeeId;
		this.fName = fName;
		this.lName = lName;
		this.department = department;
		this.isManager = isManager;
	}



	public BaseEntity(String fName, String lName, String department, boolean isManager) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.department = department;
		this.isManager = isManager;
	}



	@Override
	public String toString() {
		return "BaseEntity [employeeId=" + employeeId + ", fName=" + fName + ", lName=" + lName + ", department="
				+ department + ", isManager=" + isManager + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + (isManager ? 1231 : 1237);
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
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
		BaseEntity other = (BaseEntity) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (isManager != other.isManager)
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}



	public int getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}



	public String getfName() {
		return fName;
	}



	public void setfName(String fName) {
		this.fName = fName;
	}



	public String getlName() {
		return lName;
	}



	public void setlName(String lName) {
		this.lName = lName;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public boolean isManager() {
		return isManager;
	}



	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	
	
	

}
