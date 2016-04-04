package com.tlh.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class Utils {
	public static boolean isEmptyText(String text){
		return text==null||text.isEmpty()?true:false;
	}
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	public static boolean checkHasNull(Object ...objs){
		for (Object object : objs) {
			if(object==null)
				return true;
			if(object instanceof String &&isEmptyText((String) object))
				return true;
		}
		return false;
	}
	public static Object[][] to2DArrary(List<Object[]> list){
		 Object[] s = (Object[])list.toArray();
		 Object[][] aa = new Object[s.length][];
		 for(int i=0;i<s.length;i++){
		 aa[i] = (Object[]) s[i];
		}
		return aa;
	}
}
