package com.tlh.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tlh.dao.Page;
import com.tlh.dao.ReportForTeacherDao;
import com.tlh.model.ReportForTeacherModel.ReportForTeacherEntity;
import com.tlh.utils.DaoUtils;
import com.tlh.utils.Utils;

public class Report4TeacherService {
	private ReportForTeacherDao dao;
	public Report4TeacherService() {
		this.dao=new ReportForTeacherDao();
	}
	public List<ReportForTeacherEntity> listAllReport(int pageIndex, int itemNum,String id){
		if(itemNum==0){
			throw new RuntimeException();
		}
		if(pageIndex==0)
			pageIndex=1;
		try {
			Page page = new Page(pageIndex, dao.getListAllItemCount(id),
					itemNum);
			return dao.listAll(id, page.getStartIndex(), itemNum);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<ReportForTeacherEntity> listByTermReport(int pageIndex,
			int itemNum, String id,String term) {
		if(itemNum==0){
			throw new RuntimeException();
		}
		if(pageIndex==0)
			pageIndex=1;
		try {
			Page page = new Page(pageIndex, dao.getListByTermCount(id, term),
					itemNum);
			return dao.listByTerm(id,term, page.getStartIndex(), itemNum);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<ReportForTeacherEntity> getDetailOfReport(String id,String reportId){
		try {
			return dao.getDetail(id, reportId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<ReportForTeacherEntity>listByLesson(String userId,String lessonId,int pageIndex,int itemNum){
		return null;
	}
	public void addReport(String userId,String reportName,String content,String lessonId,int location,
			String deadline,String college,String major) throws SQLException{
		try {
			DaoUtils.startTransaction();
			Map<String, Object> parms=new HashMap<String, Object>();
			if(!Utils.isEmptyText(deadline))
				parms.put("date_to", deadline);
			if(!Utils.isEmptyText(college))
				parms.put("college", college);
			if(!Utils.isEmptyText(major))
				parms.put("major", major);
			dao.add(userId, reportName, content, lessonId, location, parms);
			DaoUtils.commit();
		} catch (SQLException e) {
			DaoUtils.rollback();
			throw new RuntimeException(e);
		}
	}
	public void updateReport(String userId,String reportId,String reportName,String deadline,String content,
			String note,String templateUrl) throws SQLException{
		try {
			DaoUtils.startTransaction();
			Map<String, Object> parms=new HashMap<String, Object>();
			if(!Utils.isEmptyText(deadline))
				parms.put("date_to", deadline);
			if(!Utils.isEmptyText(reportName))
				parms.put("name", reportName);
			if(!Utils.isEmptyText(content))
				parms.put("content", content);
			if(!Utils.isEmptyText(note))
				parms.put("note", note);
			if(!Utils.isEmptyText(templateUrl))
				parms.put("templateUrl", templateUrl);
			dao.update(userId, reportId, parms);
			DaoUtils.commit();
		} catch (SQLException e) {
			DaoUtils.rollback();
			throw new RuntimeException(e);
		}
	}
	public void deleteReport(String userId,String reportId) throws SQLException{
		try {
			DaoUtils.startTransaction();
			dao.delete(userId, reportId);
			DaoUtils.commit();
		} catch (SQLException e) {
			DaoUtils.rollback();
			throw new RuntimeException(e);
		}
	}
	public void scoreStudentReport(String userId,String reportId,
			String studentId,float score,String comment) throws SQLException{
		try {
			DaoUtils.startTransaction();
			Map<String, Object> parms=new HashMap<String, Object>();
			parms.put("score", score);
			if(!Utils.isEmptyText(comment))
				parms.put("comment", comment);
			dao.update(userId, reportId, parms);
			DaoUtils.commit();
		} catch (SQLException e) {
			DaoUtils.rollback();
			throw new RuntimeException(e);
		}
	}
}
