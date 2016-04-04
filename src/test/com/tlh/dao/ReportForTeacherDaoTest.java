package test.com.tlh.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tlh.dao.ReportForTeacherDao;
import com.tlh.model.ReportForTeacherModel.ReportForTeacherEntity;
import com.tlh.utils.Constant;
import com.tlh.utils.DaoUtils;

public class ReportForTeacherDaoTest {

	ReportForTeacherDao dao;
	@Before
	public void init(){
		dao=new ReportForTeacherDao();
	}
	@Test
	public void testListAll() {
		List<ReportForTeacherEntity> list=dao.listAll("5626", 0, 3);
	}

	@Test
	public void testGetListAllItemCount() {
		assertEquals(1, dao.getListAllItemCount("3914"));
	}

	@Test
	public void testListByTerm() {
		List<ReportForTeacherEntity> list=dao.listByTerm("5626", "2015-1", 0,3);
	}

	@Test
	public void testGetListByTermCount() {
		assertEquals(2, dao.getListByTermCount("5626", "2015-1"));
	}

	@Test
	public void testGetDetail() throws SQLException, RuntimeException {
		List<ReportForTeacherEntity> list=dao.getDetail("5626", "5603112");
	}

	@Test
	public void testUpdate() throws SQLException {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", "合法啊");
		map.put("note", "嗷嗷嗷");
		dao.update("5626", "5603112", map);
	}

	@Test
	public void testUpdatePerStudent() throws SQLException {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("score", 91);
		map.put("comment","159");
		dao.updatePerStudent("5603111", "123789", map);
	}

	@Test
	public void testAdd() throws SQLException {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("college", "政治与行政学院");
		map.put("major", "自我管理");
		try {
			dao.add("5626", "逗比的养成秘籍", "你猜", "5401", Constant.LOCATION_YU, map);
			DaoUtils.commit();
		} catch (SQLException e) {
			DaoUtils.rollback();
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() throws SQLException {
		dao.delete("5626", "54011459642768763");
	}

	@AfterClass
	public static void after(){
		DaoUtils.release();
	}
}
