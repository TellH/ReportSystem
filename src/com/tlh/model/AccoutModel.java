package com.tlh.model;

public class AccoutModel extends BaseModel {
	private User user;
	private int identity;
	
	public AccoutModel(int identity) {
		super();
		this.identity = identity;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
