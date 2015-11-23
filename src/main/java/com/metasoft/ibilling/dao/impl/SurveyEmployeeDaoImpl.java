/**
 * 
 */
package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.SurveyEmployeeDao;
import com.metasoft.ibilling.model.SurveyEmployee;

/**
 * @author
 * 
 */
@Repository("surveyEmployeeDao")
@Transactional
public class SurveyEmployeeDaoImpl extends AbstractDaoImpl<SurveyEmployee, Integer> implements SurveyEmployeeDao {

	public SurveyEmployeeDaoImpl() {
		super(SurveyEmployee.class);
	}

}
