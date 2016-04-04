package com.tlh.model;
import java.io.Serializable;
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	public UserInfo(String id, int identity) {
		super();
		this.id = id;
		this.identity=identity;
	}
	private String id;
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	private int identity;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
