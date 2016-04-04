package test.com.tlh.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;

import com.tlh.dao.ReportForStudentDao;
import com.tlh.model.ReportForStudentModel.ReportForStudentEntity;
import com.tlh.utils.DaoUtils;

public class ReportForStudentDaoTest {

	@Test
	public void testListAll() {
		ReportForStudentDao dao=new ReportForStudentDao();
		List<ReportForStudentEntity> list=dao.listAll("123789", 0, 3);
		
	}

	@Test
	public void testGetListAllItemCount() {
		ReportForStudentDao dao=new ReportForStudentDao();
		System.out.println(dao.getListAllItemCount("123789"));
	}

	@Test
	public void testListByTerm() {
		ReportForStudentDao dao=new ReportForStudentDao();
		List<ReportForStudentEntity> list=dao.listByTerm("123789", "2015-1", 0, 3);
	}

	@Test
	public void testListByStatus() {
		ReportForStudentDao dao=new ReportForStudentDao();
		List<ReportForStudentEntity> list=dao.listByStatus("123789", 0, 0, 3);
	}

	@Test
	public void testGetListByTermItemCount() {
		ReportForStudentDao dao=new ReportForStudentDao();
		System.out.println(dao.getListByTermCount("123789", "2015-1"));
	}
	@Test
	public void testGetListByStatusItemCount() {
		ReportForStudentDao dao=new ReportForStudentDao();
		System.out.println(dao.getListByStatusItemCount("123789", 0));
	}
	@Test
	public void testGetDetail() {
		ReportForStudentDao dao=new ReportForStudentDao();
		List<ReportForStudentEntity> list=dao.getDetail("123789", "5603112");
	}
	
	@Test
	public void testUpdate() throws SQLException {
		ReportForStudentDao dao=new ReportForStudentDao();
		Map map=new HashMap<String, Object>();
//		map.put("advice", "呵呵");
		map.put("docUrl", "逗比");
		try {
			dao.update("123789", "5401115", map);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public static void after(){
		DaoUtils.release();
	}
}
