package com.tlh.service;

import java.util.List;

import com.tlh.dao.LessonDao;
import com.tlh.model.LessonForStudentModel.LessonForStudentEntity;
import com.tlh.model.LessonForTeacherModel.LessonForTeacherEntity;

public class LessonService {
	private LessonDao dao;
	public LessonService() {
		this.dao=new LessonDao();
	}
	public List<LessonForStudentEntity> listAllForStudent(String userId){
		return dao.listAllForStudent(userId);
	}
	public List<LessonForStudentEntity> listByTermForStudent(String userId,String term){
		return dao.listByTermForStudent(userId, term);
	}
	public List<LessonForTeacherEntity> listAllForTeacher(String userId){
		return dao.listAllForTeacher(userId);
	}
	public List<LessonForTeacherEntity> listByTermForTeacher(String userId,String term){
		return dao.listByTermForTeacher(userId, term);
	}
}
