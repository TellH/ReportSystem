package com.tlh.dao;

import java.util.List;

public interface IReportDao{
	List<?> listAll(String userId,int startIndex,int itemNum);
	List<?> listByTerm(String userId,String term,int startIndex,int itemNum);
}
