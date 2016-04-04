package com.tlh.service;

import java.sql.SQLException;

import com.tlh.dao.AccoutDao;
import com.tlh.model.User;
import com.tlh.utils.Constant;
import com.tlh.utils.Utils;

public class AccoutService {

	private AccoutDao dao;

	public AccoutService() {
		super();
		this.dao=new AccoutDao();
	}

	public boolean hasCorrectPassword(String userId,String password,int identity){
		String correctPassword=null;
		switch(identity){
		case Constant.INDENTITY_STUDENT:
			correctPassword=dao.getStudentPassword(userId);
			break;
		case Constant.INDENTITY_TEACHER:
			correctPassword=dao.getTeacherPassword(userId);
			break;
		case Constant.INDENTITY_ADMINISTRATOR:
			break;
		default:
			return false;
		}
		if(password.equals(correctPassword)&&!Utils.isEmptyText(correctPassword)){
			return true;
		}else{
			return false;
		}
	}
	public void updatePassword(String id,int identity,String password) throws RuntimeException, SQLException{
		dao.updatePassword(id, identity, password);
	}
	public User getInfo(String id,int identity){
		switch(identity){
		case Constant.INDENTITY_STUDENT:
			return dao.getStudent(id);
		case Constant.INDENTITY_TEACHER:
			return dao.getTeacher(id);
		default:
			throw new RuntimeException("parameter error");
		}
	}
}
