package com.tlh.dao;
import java.util.List;

public class Page {
	private int pageSize;//һҳչʾ�ļ�¼��
	private int currentPage;//��ǰҳ��
	private int totalRecord;//��¼��
	private int totalPage;//��ҳ��
	private int startIndex;//��Ӧ���ݿ��п�ʼȡ��������ֵ
	private List<?> dataList;
	public Page(int currentPage, int totalRecord,int pageSize) {
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		totalPage=(int) Math.ceil((double)totalRecord/(double)currentPage);
		startIndex=(currentPage-1)*pageSize;
	}
	public Page(int currentPage, int totalRecord) {
		this(currentPage,totalRecord,15);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
}