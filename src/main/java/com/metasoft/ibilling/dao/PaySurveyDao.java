package com.metasoft.ibilling.dao;

import java.util.Date;

import com.metasoft.ibilling.bean.paging.PaySurveyPaging;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.SurveyEmployee;

public interface PaySurveyDao extends AbstractDao<PaySurvey, Integer>{
	public PaySurveyPaging searchPaging(Date createDateStart,Date createDateEnd,String paySurveyCode,SurveyEmployee surveyEmployee
			,int start,int length);
	
	public String genCode(String startCode);
}