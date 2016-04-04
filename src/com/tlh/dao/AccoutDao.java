package com.tlh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.tlh.model.Student;
import com.tlh.model.Teacher;
import com.tlh.utils.Constant;
import com.tlh.utils.DaoUtils;
import com.tlh.utils.UserUtils;

public class AccoutDao {
	private Connection conn;
	public AccoutDao() {
		super();
		try {
			this.conn=DaoUtils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Student getStudent(String id) {
		try {
			QueryRunner qr = new QueryRunner(); 
			return qr.query(conn,"select * from student where id = ?",new BeanHandler<Student>(Student.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public String getStudentPassword(String id){
		try {
			QueryRunner qr = new QueryRunner(); 
			return qr.query(conn,"select password from student where id = ?",new ResultSetHandler<String>() {
				@Override
				public String handle(ResultSet rs) throws SQLException {
					if(rs.next()){
						return rs.getString(1);
					}
					return null;
				}
			},id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public String getTeacherPassword(String id){
		try {
			QueryRunner qr = new QueryRunner(); 
			return qr.query(conn,"select password from teacher where id = ?",new ResultSetHandler<String>() {
				@Override
				public String handle(ResultSet rs) throws SQLException {
					if(rs.next()){
						return rs.getString(1);
					}
					return null;
				}
			},id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Teacher getTeacher(String id) {
		try {
			QueryRunner qr = new QueryRunner(); 
			return qr.query(conn,"select * from teacher where id = ?",new BeanHandler<Teacher>(Teacher.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void updatePassword(String id,int identity,String password) throws RuntimeException, SQLException{
		if(!UserUtils.verifyPassword(password)){
			throw new RuntimeException("new password is illegal");
		}
		String table=null;
		switch(identity){
		case Constant.INDENTITY_STUDENT:
			table="student";
			break;
		case Constant.INDENTITY_TEACHER:
			table="teacher";
			break;
		case Constant.INDENTITY_ADMINISTRATOR:
			table="administrator";
			break;
		default:
			throw new RuntimeException("parameter error");
		}
		QueryRunner qr = new QueryRunner(); 
		qr.update(conn,"update "+table+ " set password=? where id =?",password,id);
	}

}
