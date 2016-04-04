package com.tlh.action;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.struts2.json.annotations.JSON;

import com.tlh.model.AccoutModel;
import com.tlh.model.BaseModel;
import com.tlh.model.User;
import com.tlh.model.UserInfo;
import com.tlh.service.AccoutService;
import com.tlh.utils.Constant;
import com.tlh.utils.UserUtils;
import com.tlh.utils.Utils;
public class AccountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String password;
	private int identity;
	private String oldPassword;
	private String newPassword;

	
	
	@Override
	public BaseModel getModel() {
		if(model==null){
			model=new AccoutModel(identity);
			return model;
		}
		return super.getModel();
	}

	@JSON(serialize=false)
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@JSON(serialize=false)
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String login(){
		AccoutService service=new AccoutService();
		AccoutModel model=(AccoutModel) getModel();
		if(Utils.checkHasNull(userId,password,identity)){
			model.setMsg("parameter error");
			model.setResult("failed");
			return SUCCESS;
		}
		if(UserUtils.getUserFromSession()!=null){
			UserUtils.removeUserFromSession();
		}
		if(service.hasCorrectPassword(userId, password, identity)){
			UserUtils.addUserIntoSession(userId, identity);
			model.setMsg("succeed to login");
			model.setResult("success");
		}else{
			model.setMsg("wrong password or accout number");
			model.setResult("failed");
		}
		return SUCCESS;
	}
	
	public String logout(){
		UserInfo user=UserUtils.getUserFromSession();
		if(user!=null){
			UserUtils.removeUserFromSession();
			try {
				Utils.getResponse().sendRedirect(Utils.getRequest().getContextPath()+"/login.html");
				return NONE;
			} catch (IOException e) {
				
			}
		}
		return SUCCESS;
	}
	
	public String update(){
		AccoutService service=new AccoutService();
		AccoutModel model=(AccoutModel) getModel();
		try {
			if(service.hasCorrectPassword(userId, oldPassword, identity)){
				service.updatePassword(userId, identity, newPassword);
				model.setMsg("success");
				model.setResult("update successfully");
			}else{
				model.setResult("failed");
				model.setMsg("wrong password");
			}
		} catch (RuntimeException e) {
			model.setMsg(e.getMessage());
			model.setResult("failed");
		} catch (SQLException e) {
			model.setResult("failed");
			model.setMsg("update failed");
		}
		return SUCCESS;
	}
	
	public String getInfo(){
		AccoutModel model=(AccoutModel) getModel();
		if(Utils.checkHasNull(userId,identity)){
			model.setMsg("parameter error");
			model.setResult("failed");
		}
		AccoutService service=new AccoutService();
		UserInfo userInfo=UserUtils.getUserFromSession();
		if(userInfo==null){
			try {
				Utils.getResponse().sendRedirect(Utils.getRequest().getContextPath()+"/login.html");
				return NONE;
			} catch (IOException e) {
				
			}
		}
		try{
		User user=service.getInfo(userId, identity);
		if(user==null){
			if(identity==Constant.INDENTITY_STUDENT)
				model.setMsg("Student not found");
			if(identity==Constant.INDENTITY_TEACHER)
				model.setMsg("Teacher not found");
			model.setResult("failed");
		}else{
			((AccoutModel)model).setUser(user);
			model.setMsg("succeed to get");
			model.setResult("success");
		}
		}catch(RuntimeException e){
			System.out.println(e);
			model.setMsg(e.getMessage());
			model.setResult("failed");
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	
	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

}
