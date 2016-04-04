package com.tlh.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tlh.dao.Page;
import com.tlh.dao.ReportForStudentDao;
import com.tlh.model.ReportForStudentModel.ReportForStudentEntity;
import com.tlh.utils.Utils;

public class Report4StudentService {

	private ReportForStudentDao dao;

	public Report4StudentService() {
		this.dao=new ReportForStudentDao();
	}
	public List<ReportForStudentEntity> listAllReport(int pageIndex, int itemNum,String id){
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
	public List<ReportForStudentEntity> listByTermReport(int pageIndex,
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
	public List<ReportForStudentEntity> listByStatusReport(int pageIndex,
			int itemNum, String id, int status) {
		if(itemNum==0){
			throw new RuntimeException();
		}
		if(pageIndex==0)
			pageIndex=1;
		try {
			Page page = new Page(pageIndex, dao.getListByStatusItemCount(id, status),
					itemNum);
			return dao.listByStatus(id, status,page.getStartIndex(), itemNum);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<ReportForStudentEntity> getDetailOfReport(String id,String reportId){
		try {
			return dao.getDetail(id, reportId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void update(String userId,String reportId,String advice,String docUrl) throws RuntimeException, SQLException{
		Map<String, Object> params=new HashMap<String, Object>();
		if(!Utils.isEmptyText(advice))
			params.put("advice",advice);
		if(!Utils.isEmptyText(docUrl))
			params.put("docUrl", docUrl);
		dao.update(userId, reportId, params);
	}
}
