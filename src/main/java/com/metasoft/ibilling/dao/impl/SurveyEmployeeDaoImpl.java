/**
 * 
 */
package com.metasoft.ibilling.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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

	@Override
	public List<SurveyEmployee> findAllOrderByBranch() {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.addOrder(Order.asc("code"));
		return criteria.list();
	}

	@Override
	public SurveyEmployee findByCodeAndName(String code, String fullname) {
		return (SurveyEmployee) getCurrentSession().createCriteria(entityClass)
				.add(Restrictions.eq("code", code))
				.add(Restrictions.eqOrIsNull("fullname", fullname))
				.uniqueResult();
	}

}
