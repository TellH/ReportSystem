package com.tlh.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.tlh.model.UserInfo;

public class UserUtils {
	public static UserInfo addUserIntoSession(String id,int identity) {
		if(Utils.isEmptyText(id))
			return null;
		UserInfo user=new UserInfo(id,identity);
		Utils.getRequest().getSession(true).setAttribute(Constant.USER_INFO,user);
		return user;
	}
	public static UserInfo getUserFromSession() {
		HttpServletRequest req=ServletActionContext.getRequest();
		return (UserInfo) req.getSession(true).getAttribute(Constant.USER_INFO);
	}
	public static void removeUserFromSession() {
		Utils.getRequest().getSession(true).removeAttribute(Constant.USER_INFO);
	}
	public static boolean hasLogin() {
		UserInfo user =getUserFromSession();
		return user!=null? true:false ;
	}
	
	//密码格式或者长度校验
	public static boolean verifyPassword(String password) {
		if(Utils.isEmptyText(password)){
			return false;
		}
		return true;
	}
}
