package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.PaySurveyDao;
import com.metasoft.ibilling.model.PaySurvey;

@Repository("paySurveyDao")
@Transactional
public class PaySurveyDaoImpl extends AbstractDaoImpl<PaySurvey, Integer> implements PaySurveyDao {
	
	public PaySurveyDaoImpl() {
		super(PaySurvey.class);
	}
	
	
}
