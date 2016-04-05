package com.tlh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.tlh.model.Lesson;
import com.tlh.model.ReportForTeacherModel.ReportForTeacherEntity;
import com.tlh.model.StudentWithReport;
import com.tlh.utils.Constant;
import com.tlh.utils.DaoUtils;
import com.tlh.utils.Utils;

public class ReportForTeacherDao implements IReportDao {
	private Connection conn;

	@Override
	public List<ReportForTeacherEntity> listAll(String userId, int startIndex, int itemNum) {
		String sql1="select r.* from report r  "
				+ "where r.teacherId=? order by r.id limit ?,? ";
		String sql2="select l.* from report r , lesson l "
				+ "where r.teacherId=? and l.id=r.lessonId order by r.id limit ?,? ";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForTeacherEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForTeacherEntity>(ReportForTeacherEntity.class),userId,startIndex,itemNum);
			List<Lesson> lessons=qr.query(conn,sql2, new BeanListHandler<Lesson>(Lesson.class),userId,startIndex,itemNum);
			int index=0;
			for (ReportForTeacherEntity entity : list) {
				entity.setLesson(lessons.get(index));
				index++;
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public int getListAllItemCount(String userId) {
		String sql="select count(*) from report r  "
				+ "where r.teacherId=?";
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

	public List<ReportForTeacherEntity> listByLesson(String userId, String lessonId, int startIndex,
			int itemNum) {
		String sql1="select r.* from report r"
				+ " where r.teacherId=? and r.lessonId=? order by r.id limit ?,? ";
		String sql2="select l.* from report r , lesson l "
				+ "where r.teacherId=? and l.id=r.lessonId and r.lessonId=? order by r.id limit ?,? ";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForTeacherEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForTeacherEntity>(ReportForTeacherEntity.class),userId,lessonId,startIndex,itemNum);
			List<Lesson> lessons=qr.query(conn,sql2, new BeanListHandler<Lesson>(Lesson.class),userId,lessonId,startIndex,itemNum);
			System.out.println(lessons.size());
			int index=0;
			for (ReportForTeacherEntity entity : list) {
				entity.setLesson(lessons.get(index));
				index++;
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int getListByLessonCount(String userId, String lessonId) {
		String sql="select count(*) from report r"
				+ " where r.teacherId=? and r.lessonId=? ";
		QueryRunner qr=new QueryRunner();
		try {
			return qr.query(conn, sql,new ResultSetHandler<Integer>() {
				@Override
				public Integer handle(ResultSet rs) throws SQLException {
					if(rs.next())
						return rs.getInt(1);
					return 0;
				}
			},userId,lessonId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<ReportForTeacherEntity> listByTerm(String userId, String term, int startIndex,
			int itemNum) {
		String sql1="select r.* from report r, lesson l "
				+ " where r.teacherId=? and l.id=r.lessonId and l.term=? order by r.id limit ?,? ";
		String sql2="select l.* from report r , lesson l "
				+ "where r.teacherId=? and l.id=r.lessonId and l.term=? order by r.id limit ?,? ";
		QueryRunner qr=new QueryRunner();
		try {
			List<ReportForTeacherEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForTeacherEntity>(ReportForTeacherEntity.class),userId,term,startIndex,itemNum);
			List<Lesson> lessons=qr.query(conn,sql2, new BeanListHandler<Lesson>(Lesson.class),userId,term,startIndex,itemNum);
			System.out.println(lessons.size());
			int index=0;
			for (ReportForTeacherEntity entity : list) {
				entity.setLesson(lessons.get(index));
				index++;
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getListByTermCount(String userId, String term) {
		String sql="select count(*) from report r, lesson l "
				+ " where r.teacherId=? and l.id=r.lessonId and l.term=? ";
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

	public List<ReportForTeacherEntity> getDetail(String userId,String reportId) throws SQLException,RuntimeException{
		String sql1="select r.* from report r  "
				+ "where r.teacherId=? and r.id=?";
		String sql2="select l.* from lesson l "
				+ "where l.id=? ";
		String sql3="select r_s.*, s.* from student s, report_student r_s "
				+ "where r_s.reportId=? and s.id=r_s.studentId";
		QueryRunner qr=new QueryRunner();
		List<ReportForTeacherEntity> list=qr.query(conn, sql1, new BeanListHandler<ReportForTeacherEntity>(ReportForTeacherEntity.class),userId,reportId);
		if(list==null||list.size()!=1)
			throw new RuntimeException("refuse to give information");
		Lesson lesson=qr.query(conn,sql2, new BeanHandler<Lesson>(Lesson.class),list.get(0).getLessonId());
		List<StudentWithReport> students=qr.query(conn, sql3,new BeanListHandler<StudentWithReport>(StudentWithReport.class),reportId);
		list.get(0).setLesson(lesson);
		list.get(0).setStudents(students);
		return list;
	}

	public int update(String userId,String reportId,Map<String, Object> parms) throws SQLException {
		if(parms==null||parms.isEmpty())
			throw new RuntimeException("update failed");
		Set<String> keySet=parms.keySet();
		StringBuilder builder=new StringBuilder();
		List<Object>list=new ArrayList<Object>();
		for(String k:keySet){  
			builder.append(" ").append(k).append("=").append("?").append(" ,");
			list.add(parms.get(k));
		}
		builder.deleteCharAt(builder.lastIndexOf(","));
		list.add(userId);
		list.add(reportId);
		String sql="update report set"+builder.toString() +"where teacherId=? and id=?";
		QueryRunner qr=new QueryRunner();
		return qr.update(conn,sql,list.toArray());
	}

	public int updatePerStudent(String reportId,String studentId,Map<String, Object> parms) throws SQLException {
		if(parms==null||parms.isEmpty())
			throw new RuntimeException("update failed");
		Set<String> keySet=parms.keySet();
		StringBuilder builder=new StringBuilder();
		List<Object>list=new ArrayList<Object>();
		for(String k:keySet){  
			builder.append(" ").append(k).append("=").append("?").append(" ,");
			list.add(parms.get(k));
		}
		if(parms.containsKey("score")){
			builder.append(" status=? , ");
			list.add(Constant.STATUS_HAS_SCORE);
		}
		list.add(studentId);
		list.add(reportId);
		builder.deleteCharAt(builder.lastIndexOf(","));
		String sql="update report_student set"+builder.toString() +"where studentId =? and reportId=?";
		QueryRunner qr=new QueryRunner();
		return qr.update(conn,sql,list.toArray());
	}

	public void add(String userId,String reportName,String content,String lessonId,int location ,Map<String, Object> parms ) throws SQLException,RuntimeException {
		if(Utils.checkHasNull(userId,reportName,content,lessonId))
			throw new RuntimeException("add failed");
		if(location!=Constant.LOCATION_YU&&location!=Constant.LOCATION_MA)
			throw new RuntimeException("add failed");
		DaoUtils.startTransaction();
		Timestamp date_from =new Timestamp(System.currentTimeMillis());
		Set<String> keySet=parms.keySet();
		StringBuilder builder_keys=new StringBuilder();
		StringBuilder builder_values=new StringBuilder();
		List<Object> values=new ArrayList<Object>();
		for(String k:keySet){  
			builder_keys.append(" ").append(k).append(" ,");
			builder_values.append("?,");
			values.add(parms.get(k));
		}
		String reportId= userId+System.currentTimeMillis();
		builder_keys.append(" id,teacherId,name,content,lessonId,location,date_from ");
		builder_values.append("?,?,?,?,?,?,?");
		values.add(reportId);
		values.add(userId);
		values.add(reportName);
		values.add(content);
		values.add(lessonId);
		values.add(location);
		values.add(date_from);
		String sql_insert_into_report="insert into report ("+builder_keys.toString()+") values ("+builder_values.toString()+")";
		String sql_insert_into_report_student = "insert into report_student (studentId,reportId,status ) values(?,?,?)";
		QueryRunner qr=new QueryRunner();
		qr.update(conn, sql_insert_into_report,values.toArray());
		LessonDao dao=new LessonDao(conn);
		List<String> StudentIds=dao.getStudentsIdByLesson(lessonId);
		List<Object[]> batchList=new ArrayList<Object[]>();
		for (String id : StudentIds) {
			batchList.add(new Object[] {id,reportId,Constant.STATUS_NOT_SUBMIT});
		}
		qr.batch(conn,sql_insert_into_report_student, Utils.to2DArrary(batchList));
	}

	public ReportForTeacherDao() {
		super();
		try {
			this.conn=DaoUtils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int delete(String userId,String reportId) throws SQLException {
		String sql="delete from report where teacherId=? and id=? ";
		QueryRunner qr=new QueryRunner();
		return qr.update(conn, sql,userId,reportId);
	}


}
