package com.tlh.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tlh.model.BaseModel;
import com.tlh.model.ReportForTeacherModel;
import com.tlh.model.ReportForTeacherModel.ReportForTeacherEntity;
import com.tlh.service.AccoutService;
import com.tlh.service.Report4TeacherService;
import com.tlh.utils.Constant;
import com.tlh.utils.Utils;

public class Report4TeacherAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String userId;
	private int page;
	private int itemNum;
	private String term;
	private String lessonId;
	private String reportId;
	private String reportName;
	private String content;
	private int location;
	private String college;
	private String major;
	private String note;
	private String templateUrl;
	private String studentId;
	private float score;
	private String comment;
	private String deadline;
	private String password;
	
	@Override
	public BaseModel getModel() {
		if(model==null){
			model=new ReportForTeacherModel();
			return model;
		}
		return super.getModel();
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	@JSON(serialize=false)
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	@JSON(serialize=false)
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	@JSON(serialize=false)
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	@JSON(serialize=false)
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@JSON(serialize=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JSON(serialize=false)
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	@JSON(serialize=false)
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@JSON(serialize=false)
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@JSON(serialize=false)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@JSON(serialize=false)
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	@JSON(serialize=false)
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	@JSON(serialize=false)
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	@JSON(serialize=false)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@JSON(serialize=false)
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public String listAll(){
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		List<ReportForTeacherEntity> data;
		try {
			data = service.listAllReport(page, itemNum, userId);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			model.setResult("failed");
			model.setMsg("failed to list");
		}
		return SUCCESS;
	}
	public String listByTerm(){
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		List<ReportForTeacherEntity> data;
		try {
			data = service.listByTermReport(page, itemNum, userId,term);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			model.setResult("failed");
			model.setMsg("failed to list");
		}
		return SUCCESS;
	}
	public String listByLesson(){
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		List<ReportForTeacherEntity> data;
		try {
			data = service.listByLesson(userId,lessonId,page,itemNum);
			model.setData(data);
			model.setResult("success");
			model.setMsg("succeed to get info");
		} catch (Exception e) {
			model.setResult("failed");
			model.setMsg("failed to list");
		}
		return SUCCESS;
	}
	public String detail(){
		if(Utils.checkHasNull(userId,reportId)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		try {
			List<ReportForTeacherEntity> data = service.getDetailOfReport(
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
	public String add(){
		if(Utils.checkHasNull(userId,reportName,password,content,lessonId,location)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		if(!new AccoutService().hasCorrectPassword(userId, password, Constant.INDENTITY_TEACHER)){
			model.setResult("failed");
			model.setMsg("wrong password");
			return SUCCESS;
		}
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		try {
			service.addReport(userId, reportName, content, lessonId, location, deadline, college, major);
			model.setResult("success");
			model.setMsg("update successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg("failed to add report");
			return SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			model.setResult("failed");
			model.setMsg("there is something wrong");
			return SUCCESS;
		}
		return SUCCESS;
	}
	public String update(){
		if(Utils.checkHasNull(userId,reportId,password)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		if(!new AccoutService().hasCorrectPassword(userId, password, Constant.INDENTITY_TEACHER)){
			model.setResult("failed");
			model.setMsg("wrong password");
			return SUCCESS;
		}
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		try {
			service.updateReport(userId, reportId, reportName, deadline, content, note, templateUrl);
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
	public String delete(){
		if(Utils.checkHasNull(userId,reportId,password)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		if(!new AccoutService().hasCorrectPassword(userId, password, Constant.INDENTITY_TEACHER)){
			model.setResult("failed");
			model.setMsg("wrong password");
			return SUCCESS;
		}
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		try {
			service.deleteReport(userId, reportId);
			model.setResult("success");
			model.setMsg("delete report successfully");
		} catch (SQLException e) {
			model.setResult("failed");
			model.setMsg("there is something wrong while getting info");
			return SUCCESS;
		}
		return SUCCESS;
	}
	public String perStudent(){
		if(Utils.checkHasNull(userId,reportId,password)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		if(!new AccoutService().hasCorrectPassword(userId, password, Constant.INDENTITY_TEACHER)){
			model.setResult("failed");
			model.setMsg("wrong password");
			return SUCCESS;
		}
		ReportForTeacherModel model=(ReportForTeacherModel) getModel();
		Report4TeacherService service = new Report4TeacherService();
		try {
			service.scoreStudentReport(userId, reportId, studentId, score, comment);
			model.setResult("success");
			model.setMsg("update report successfully");
		} catch (SQLException e) {
			model.setResult("failed");
			model.setMsg("there is something wrong while getting info");
			return SUCCESS;
		}
		return SUCCESS;
	}
	
}
