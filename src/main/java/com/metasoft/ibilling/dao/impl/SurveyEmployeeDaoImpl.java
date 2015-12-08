/**
 * 
 */
package com.metasoft.ibilling.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.SurveyEmployeeDao;
import com.metasoft.ibilling.model.SurveyEmployee;
import com.metasoft.ibilling.model.User;

/**
 * @author
 * 
 */
@Repository("surveyEmployeeDao")
public class SurveyEmployeeDaoImpl extends AbstractDaoImpl<SurveyEmployee, Integer> implements SurveyEmployeeDao {

	public SurveyEmployeeDaoImpl() {
		super(SurveyEmployee.class);
	}
	
	@Override
	public SurveyEmployee findByCode(String code) {
		return (SurveyEmployee) getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("code", code)).uniqueResult();
	}

}
