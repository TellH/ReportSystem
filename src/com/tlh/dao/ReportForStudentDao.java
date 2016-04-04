package com.tlh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tlh.model.Lesson;
import com.tlh.model.ReportForStudentModel.ReportForStudentEntity;
import com.tlh.model.Teacher;
import com.tlh.utils.Constant;
import com.tlh.utils.DaoUtils;

public class ReportForStudentDao implements IReportDao{
	private Connection conn;

	@Override
	public List<ReportForStudentEntity> listAll(String userId, int startIndex, int itemNum) {
		String sql1="select r.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId order by r.id limit ?,? ";
		String sql2="select t.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId order by r.id limit ?,?";
		String sql3="select l.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId order by r.id limit ?,?";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForStudentEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForStudentEntity>(ReportForStudentEntity.class),userId,startIndex,itemNum);
			List<Teacher> teachers=qr.query(conn,sql2, new BeanListHandler<Teacher>(Teacher.class),userId,startIndex,itemNum);
			List<Lesson> lessons=qr.query(conn,sql3, new BeanListHandler<Lesson>(Lesson.class),userId,startIndex,itemNum);
			int index=0;
			for (ReportForStudentEntity entity : list) {
				entity.setTeacher(teachers.get(index));
				entity.setLesson(lessons.get(index));
				index++;
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ReportForStudentDao() {
		super();
		try {
			this.conn=DaoUtils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public int getListAllItemCount(String userId) {
		String sql="select count(*) from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId";
		QueryRunner qr=new QueryRunner();
		try {
			return qr.query(conn, sql,new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet rs) throws SQLException {
					if(rs.next())
						return rs.getInt(1);
					return 0;
				}
			},userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<ReportForStudentEntity> listByTerm(String userId, String term, int startIndex, int itemNum) {
		String sql1="select r.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and l.term=? order by r.id limit ?,? ";
		String sql2="select t.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and l.term=? order by r.id limit ?,?";
		String sql3="select l.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and l.term=? order by r.id limit ?,?";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForStudentEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForStudentEntity>(ReportForStudentEntity.class),userId,term,startIndex,itemNum);
			List<Teacher> teachers=qr.query(conn,sql2, new BeanListHandler<Teacher>(Teacher.class),userId,term,startIndex,itemNum);
			List<Lesson> lessons=qr.query(conn,sql3, new BeanListHandler<Lesson>(Lesson.class),userId,term,startIndex,itemNum);
			int index=0;
			for (ReportForStudentEntity entity : list) {
				entity.setTeacher(teachers.get(index));
				entity.setLesson(lessons.get(index));
				index++;
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public int getListByTermCount(String userId, String term) {
		String sql="select count(*) from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and  l.term=?";
		QueryRunner qr=new QueryRunner();
		try {
			return qr.query(conn, sql,new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet rs) throws SQLException {
					if(rs.next())
						return rs.getInt(1);
					return 0;
				}
			},userId,term);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<ReportForStudentEntity> listByStatus(String userId, int status, int startIndex,
			int itemNum) {
		String sql1="select r.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and r_s.status=? order by r.id limit ?,? ";
		String sql2="select t.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and r_s.status=? order by r.id limit ?,?";
		String sql3="select l.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and r_s.status=? order by r.id limit ?,?";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForStudentEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForStudentEntity>(ReportForStudentEntity.class),userId,status,startIndex,itemNum);
			List<Teacher> teachers=qr.query(conn,sql2, new BeanListHandler<Teacher>(Teacher.class),userId,status,startIndex,itemNum);
			List<Lesson> lessons=qr.query(conn,sql3, new BeanListHandler<Lesson>(Lesson.class),userId,status,startIndex,itemNum);
			int index=0;
			for (ReportForStudentEntity entity : list) {
				entity.setTeacher(teachers.get(index));
				entity.setLesson(lessons.get(index));
				index++;
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getListByStatusItemCount(String userId, int status) {
		String sql="select count(*) from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and r_s.status=?";
		QueryRunner qr=new QueryRunner();
		try {
			return qr.query(conn, sql,new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet rs) throws SQLException {
					if(rs.next())
						return rs.getInt(1);
					return 0;
				}
			},userId,status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ReportForStudentEntity> getDetail(String userId,String reportId) {
		String sql1="select r.*,r_s.* from report r ,report_student r_s, lesson l, teacher t  "
				+ "where studentId=? and r_s.reportId=r.id and l.id=r.lessonId and t.id=r.teacherId and r.id=?";
		String sql2="select t.* from teacher t  "
				+ "where t.id=?";
		String sql3="select l.* from lesson l  "
				+ "where l.id=?";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForStudentEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForStudentEntity>(ReportForStudentEntity.class),userId,reportId);
			if(list==null||list.size()!=1)
				throw new RuntimeException("refuse to give information");
			Teacher teacher=qr.query(conn,sql2, new BeanHandler<Teacher>(Teacher.class),list.get(0).getTeacherId());
			Lesson lesson=qr.query(conn,sql3, new BeanHandler<Lesson>(Lesson.class),list.get(0).getLessonId());
			list.get(0).setTeacher(teacher);
			list.get(0).setLesson(lesson);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int update(String userId,String reportId,Map<String, Object> parms) throws RuntimeException, SQLException{
		if(parms==null||parms.isEmpty())
			throw new RuntimeException("update failed");
		Set<String> keySet=parms.keySet();
		StringBuilder builder=new StringBuilder();
		List<Object>list=new ArrayList<Object>();
		for(String k:keySet){  
			builder.append(" ").append(k).append("=").append("?").append(" ,");
			list.add(parms.get(k));
		}
		if(parms.containsKey("docUrl")){
			builder.append(" status=? , ");
			list.add(Constant.STATUS_HAS_SUBMIT);
		}
		list.add(userId);
		list.add(reportId);
		builder.deleteCharAt(builder.lastIndexOf(","));
		String sql="update report_student set"+builder.toString() +"where studentId=? and reportId=?";
		QueryRunner qr=new QueryRunner();
		return qr.update(conn,sql,list.toArray());
	}

}
