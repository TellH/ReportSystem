package com.tlh.action;

import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;
import com.tlh.model.BaseModel;
import com.tlh.model.UserInfo;
import com.tlh.utils.UserUtils;
import com.tlh.utils.Utils;

public class BaseAction extends ActionSupport{
	protected static final long serialVersionUID = 1L;
	public BaseModel model; 
	public BaseModel getModel() {
		return model;
	}
	public void setModel(BaseModel model) {
		this.model = model;
	}

	@Override
	public void validate() {
		super.validate();
		if (!(this instanceof AccountAction)) {
			UserInfo user = UserUtils.getUserFromSession();
			if (user == null) {
				try {
					Utils.getResponse()
							.sendRedirect(
									Utils.getRequest().getContextPath()
											+ "/login.html");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				//Ö±½Ó·µ»Ø
				addFieldError("", "");
			}
		}
	}
	
}
