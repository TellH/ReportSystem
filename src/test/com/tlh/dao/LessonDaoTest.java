package test.com.tlh.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tlh.dao.LessonDao;
import com.tlh.utils.DaoUtils;

public class LessonDaoTest {
	static LessonDao dao;
	@BeforeClass
	public static void before(){
		dao=new LessonDao();
	}

	@Test
	public void testListAllForStudent() {
		List list=dao.listAllForStudent("123789");
	}

	@Test
	public void testListAllForTeacher() {
		List list=dao.listAllForTeacher("3914");
	}

	@Test
	public void testListByTermForStudent() {
		List list=dao.listByTermForStudent("123789", "2015-1");
	}

	@Test
	public void testListByTermForTeacher() {
		List list=dao.listByTermForTeacher("3914", "2015-2");
	}

	@Test
	public void testAdd() throws SQLException{
		try {
			DaoUtils.startTransaction();
//			dao.add("", "", "", null);
			dao.add("2016-1", "逗比养成记", "5626", null);
			DaoUtils.commit();
		} catch (SQLException e) {
			DaoUtils.rollback();
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void getNumbersOf1(){
		int sum=0;
		int i=4;
		while(i>0){
			sum++;
			i = i&(i - 1);
		}
		System.out.println(sum);
		String s;
	}
	@Test
	public void getCountingBit(){
		int num=11;
		List<Integer> list=new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		for(int i=2;i<=num;i++){
			int result=(int) (Math.log(i)/Math.log(2));
			int left=(int) (i-Math.pow(2, result));
			list.add(1+list.get(left));
		}
		System.out.println(list.toArray());
	}
	
	@AfterClass
	public static void after(){
		DaoUtils.release();
	}
}
