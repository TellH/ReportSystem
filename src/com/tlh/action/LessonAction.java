package com.tlh.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tlh.model.LessonForStudentModel;
import com.tlh.model.LessonForStudentModel.LessonForStudentEntity;
import com.tlh.model.LessonForTeacherModel;
import com.tlh.model.LessonForTeacherModel.LessonForTeacherEntity;
import com.tlh.service.LessonService;
import com.tlh.utils.Utils;


public class LessonAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String term;
	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String listAll4Student(){
		LessonForStudentModel model=new LessonForStudentModel();
		setModel(model);
		LessonService service = new LessonService();
		List<LessonForStudentEntity> data;
		if(Utils.checkHasNull(userId)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listAllForStudent(userId);
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
	public String listByTerm4Student(){
		LessonForStudentModel model=new LessonForStudentModel();
		setModel(model);
		LessonService service = new LessonService();
		List<LessonForStudentEntity> data;
		if(Utils.checkHasNull(userId,term)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listByTermForStudent(userId,term);
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
	public String listAll4Teacher(){
		LessonForTeacherModel model=new LessonForTeacherModel();
		setModel(model);
		LessonService service = new LessonService();
		List<LessonForTeacherEntity> data;
		if(Utils.checkHasNull(userId)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listAllForTeacher(userId);
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
	public String listByTerm4Teacher(){
		LessonForTeacherModel model=new LessonForTeacherModel();
		setModel(model);
		LessonService service = new LessonService();
		List<LessonForTeacherEntity> data;
		if(Utils.checkHasNull(userId,term)){
			model.setResult("failed");
			model.setMsg("parameter error");
			return SUCCESS;
		}
		try {
			data = service.listByTermForTeacher(userId,term);
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
}
