package com.tlh.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tlh.model.BaseModel;
import com.tlh.model.ReportForStudentModel;
import com.tlh.model.ReportForStudentModel.ReportForStudentEntity;
import com.tlh.service.AccoutService;
import com.tlh.service.Report4StudentService;
import com.tlh.utils.Constant;
import com.tlh.utils.Utils;

public class Report4StudentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String userId;
	private int page;
	private int itemNum;
	private String reportId;
	private int status;
	private String term;
	private String password;
	private String advice;
	private String docUrl;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JSON(serialize=false)
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	@JSON(serialize=false)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@JSON(serialize=false)
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	public String listAll(){
		ReportForStudentModel model=(ReportForStudentModel) getModel();
		Report4StudentService service = new Report4StudentService();
		List<ReportForStudentEntity> data;
		if(Utils.checkHasNull(userId)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listAllReport(page, itemNum, userId);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg("failed to list");
		}
		return SUCCESS;
	}
	public String listByTerm(){
		ReportForStudentModel model=(ReportForStudentModel) getModel();
		Report4StudentService service = new Report4StudentService();
		List<ReportForStudentEntity> data;
		if(Utils.checkHasNull(userId,term)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listByTermReport(page, itemNum, userId,term);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg("failed to list");
		}
		return SUCCESS;
	}
	
	public String listByStatus(){
		ReportForStudentModel model=(ReportForStudentModel) getModel();
		Report4StudentService service = new Report4StudentService();
		List<ReportForStudentEntity> data;
		if(Utils.checkHasNull(userId,status)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listByStatusReport(page, itemNum, userId,status);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg("failed to list");
		}
		return SUCCESS;
	}
	public String detail(){
		ReportForStudentModel model=(ReportForStudentModel) getModel();
		Report4StudentService service = new Report4StudentService();
		if(Utils.checkHasNull(userId,reportId)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			List<ReportForStudentEntity> data = service.getDetailOfReport(
					userId, reportId);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			model.setResult("failed");
			model.setMsg("failed to get");
		}
		return SUCCESS;
	}
	public String update(){
		ReportForStudentModel model=(ReportForStudentModel) getModel();
		Report4StudentService service = new Report4StudentService();
		if(Utils.checkHasNull(userId,reportId,password)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}

		if(!new AccoutService().hasCorrectPassword(userId, password, Constant.INDENTITY_STUDENT)){
			model.setResult("failed");
			model.setMsg("wrong password");
			return SUCCESS;
		}
		try {
			service.update(userId, reportId, advice, docUrl);
			model.setResult("success");
			model.setMsg("update successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg(e.getMessage());
			return SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg("there is something wrong while getting info");
			return SUCCESS;
		}
		return SUCCESS;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	@Override
	public BaseModel getModel() {
		if(model==null){
			model=new ReportForStudentModel();
			return model;
		}
		return super.getModel();
	}
	
}
