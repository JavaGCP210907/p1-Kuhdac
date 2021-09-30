package com.revature.daos;

import java.util.List;

import com.revature.models.Status;

public interface StatusDaoInterface {
	
	public List<Status> getStatus();
	
	public Status getStatusById();

}
