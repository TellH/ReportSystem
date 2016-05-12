package com.tlh.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoUtils {
	private static DataSource ds=null;
	private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
	static{
		ds=new ComboPooledDataSource();
	}
	public static DataSource getDataSource() {
		return ds;
	}
	public static Connection getConnection() throws SQLException{
		Connection conn=threadLocal.get();
		if(conn==null){
			conn=ds.getConnection();
			threadLocal.set(conn);
		}
		conn=threadLocal.get();
		return conn;
	}
	
	public static void startTransaction() throws SQLException{
		Connection conn=threadLocal.get();
		if(conn==null){
			conn=getConnection();
			threadLocal.set(conn);
		}
		conn.setAutoCommit(false);
	}
	public static void  rollback(Savepoint sp) throws SQLException {
		Connection conn=threadLocal.get();
		if(conn!=null&&sp!=null){
			conn.rollback(sp);
		}
	}
	public static void  rollback() throws SQLException {
		Connection conn=threadLocal.get();
		if(conn!=null){
			conn.rollback();
		}
	}
	
	public static void  commit() throws SQLException {
		Connection conn=threadLocal.get();
		if(conn!=null){
			conn.commit();
		}
	}
	
	public static void release(){
		Connection conn=threadLocal.get();
		if(conn!=null){
			threadLocal.remove();//解除线程的绑定
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
