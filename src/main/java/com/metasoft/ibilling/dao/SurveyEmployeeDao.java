package com.metasoft.ibilling.dao;

import java.util.List;

import com.metasoft.ibilling.model.SurveyEmployee;


public interface SurveyEmployeeDao extends AbstractDao<SurveyEmployee, Integer>{
	public SurveyEmployee findByCode(String code);
	
	public SurveyEmployee findByCodeAndName(String code, String fullname);
	
	public List<SurveyEmployee> findAllOrderByBranch();
}