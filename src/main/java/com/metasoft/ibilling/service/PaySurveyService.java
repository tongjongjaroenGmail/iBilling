package com.metasoft.ibilling.service;

import com.metasoft.ibilling.bean.paging.PaySurveySearchResultVoPaging;
import com.metasoft.ibilling.dao.PaySurveyDao;
import com.metasoft.ibilling.model.PaySurvey;

public interface PaySurveyService extends ModelBasedService<PaySurveyDao, PaySurvey, Integer> {
	public PaySurvey save(String claimIds, int createBy);
	
	public PaySurveySearchResultVoPaging searchPaging(String txtCreateDateStart, String txtCreateDateEnd, String invoiceCode,Integer employeeId, int start,
			int length);
}
