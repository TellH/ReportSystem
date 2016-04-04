package test.com.tlh.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tlh.dao.AccoutDao;
import com.tlh.model.Student;

public class AccoutDaoTest {

	static AccoutDao dao=null ;
	@BeforeClass
	public static void init(){
		dao=new AccoutDao();
	}
	
	@Test
	public void testGetStudent() {
		try {
			Student student = dao.getStudent("12378");
			if (student == null) {
				System.out.println("null");
			}
			//		System.out.println(student.getName());
			//		System.out.println(dao.getStudent("12378").getCollege());
		} catch (Exception e) {
			System.out.println("e");
		}
	}

	@Test
	public void testGetTeacher() {
//		System.out.println(AccoutDao.getTeacher("3914").getName());
//		System.out.println(AccoutDao.getTeacher("3914").getCollege());
	}

	@Test
	public void testUpdatePassword() {
//		AccoutDao.updatePassword("3914", Constant.INDENTITY_TEACHER, "123456");
	}
}
