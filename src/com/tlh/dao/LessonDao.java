package com.tlh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.tlh.model.LessonForStudentModel.LessonForStudentEntity;
import com.tlh.model.LessonForTeacherModel.LessonForTeacherEntity;
import com.tlh.model.Student;
import com.tlh.model.Teacher;
import com.tlh.utils.DaoUtils;
import com.tlh.utils.Utils;

public class LessonDao {
	private Connection conn;
	public LessonDao() {
		super();
		try {
			this.conn=DaoUtils.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public LessonDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<LessonForStudentEntity> listAllForStudent(String userId){
		String sql1="select l.* from lesson l ,student_lesson s_l where s_l.studentId=? and l.id=s_l.lessonId order by l.id";
		String sql2="select t.* from lesson l ,teacher t,student_lesson s_l where s_l.studentId=? and l.id=s_l.lessonId and t.id=l.teacherId order by l.id";
		QueryRunner qr=new QueryRunner();
		try {
			List<LessonForStudentEntity> lessons=qr.query(conn,sql1,new BeanListHandler<LessonForStudentEntity>(LessonForStudentEntity.class),userId);
			List<Teacher> teachers=qr.query(conn,sql2,new BeanListHandler<Teacher>(Teacher.class),userId);
			int index=0;
			for (LessonForStudentEntity lesson : lessons) {
				lesson.setTeacher(teachers.get(index));
				index++;
			}
			return lessons;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<LessonForTeacherEntity> listAllForTeacher(String userId){
		String sql="select l.* from lesson l where l.teacherId=?";
		QueryRunner qr=new QueryRunner();
		try {
			List<LessonForTeacherEntity> lessons=qr.query(conn,sql,new BeanListHandler<LessonForTeacherEntity>(LessonForTeacherEntity.class),userId);
			for (LessonForTeacherEntity lesson : lessons) {
				lesson.setStudents(getStudentsByLesson(lesson.getId()));
			}
			return lessons;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<LessonForStudentEntity> listByTermForStudent(String userId,String term){
		String sql1="select l.* from lesson l ,student_lesson s_l where s_l.studentId=? and l.id=s_l.lessonId and l.term=? order by l.id";
		String sql2="select t.* from lesson l ,teacher t,student_lesson s_l where s_l.studentId=? and l.id=s_l.lessonId and t.id=l.teacherId and l.term=? order by l.id";
		QueryRunner qr=new QueryRunner();
		try {
			List<LessonForStudentEntity> lessons=qr.query(conn,sql1,new BeanListHandler<LessonForStudentEntity>(LessonForStudentEntity.class),userId,term);
			List<Teacher> teachers=qr.query(conn,sql2,new BeanListHandler<Teacher>(Teacher.class),userId,term);
			int index=0;
			for (LessonForStudentEntity lesson : lessons) {
				lesson.setTeacher(teachers.get(index));
				index++;
			}
			return lessons;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<LessonForTeacherEntity> listByTermForTeacher(String userId,String term){
		String sql="select l.* from lesson l where l.teacherId=? and l.term=?";
		QueryRunner qr=new QueryRunner();
		try {
			List<LessonForTeacherEntity> lessons=qr.query(conn,sql,new BeanListHandler<LessonForTeacherEntity>(LessonForTeacherEntity.class),userId,term);
			for (LessonForTeacherEntity lesson : lessons) {
				lesson.setStudents(getStudentsByLesson(lesson.getId()));
			}
			return lessons;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String add(String term,String name,String teacherId,String grade) throws SQLException,RuntimeException{
		String id=teacherId+System.currentTimeMillis();
		if(Utils.checkHasNull(term,name,teacherId))
			throw new RuntimeException("add failed");
		StringBuilder builder_keys=new StringBuilder(" id,term,name,teacherId");
		StringBuilder builder_values=new StringBuilder("?,?,?,?");
		List<Object> values=new ArrayList<Object>();
		values.add(id);
		values.add(term);
		values.add(name);
		values.add(teacherId);
		if(Utils.isEmptyText(grade)){
			builder_keys.append(",grade ");
			builder_values.append(",? ");	
			values.add(grade);
		}
		String sql_insert_into_lesson = "insert into lesson ("+builder_keys.toString()+") values ("+builder_values.toString()+")";
		DaoUtils.startTransaction();
		QueryRunner qr=new QueryRunner();
		qr.update(conn, sql_insert_into_lesson,values.toArray());
		return id;
	}
	
	public void addStudentToLesson(String lessonId,List<Student> students) throws SQLException {
		if(Utils.checkHasNull(lessonId,students)||students.size()<=0)
			return;
		List<String> studentIds =new ArrayList<String>();
		for(Student s:students){
			studentIds.add(s.getId());
		}
		addStudentToLesson(studentIds, lessonId);
	}
	
	public void addStudentToLesson(List<String> studentIds,String lessonId) throws SQLException {
		if(Utils.checkHasNull(lessonId,studentIds)||studentIds.size()<=0)
			return;
		String sql="insert into student_lesson (studentId,lessonId) values (?,?)";
		List<Object[]> batchList=new ArrayList<Object[]>();
		QueryRunner qr=new QueryRunner();
		for (String studentId : studentIds) {
			batchList.add(new Object[] {studentId,lessonId});
		}
		qr.batch(conn,sql, Utils.to2DArrary(batchList));
	}
	
	public List<Student> getStudentsByLesson(String lessonId) {
		String sql="select s.* from student_lesson s_l,student s where lessonId=? and s.id=studentId";
		QueryRunner qr=new QueryRunner();
		try {
			return qr.query(conn,sql,new BeanListHandler<Student>(Student.class),lessonId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getStudentsIdByLesson(String lessonId) {
		String sql="select s_l.studentId from student_lesson s_l  where lessonId=?";
		QueryRunner qr=new QueryRunner();
		try {
			return qr.query(conn,sql,new ColumnListHandler<String>(),lessonId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
