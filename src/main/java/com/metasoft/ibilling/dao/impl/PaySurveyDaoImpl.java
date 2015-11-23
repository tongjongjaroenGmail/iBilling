package com.metasoft.ibilling.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.paging.PaySurveyPaging;
import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.PaySurveyDao;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.PaySurveyStatus;
import com.metasoft.ibilling.model.SurveyEmployee;

@Repository("paySurveyDao")
@Transactional
public class PaySurveyDaoImpl extends AbstractDaoImpl<PaySurvey, Integer> implements PaySurveyDao {
	
	public PaySurveyDaoImpl() {
		super(PaySurvey.class);
	}
	
	@Override
	public PaySurveyPaging searchPaging(Date createDateStart,Date createDateEnd,String paySurveyCode,SurveyEmployee surveyEmployee ,
			int start,int length) {
		PaySurveyPaging resultPaging = new PaySurveyPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
	
		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		criteriaCount.createAlias("claims", "claim");
		
		if (createDateStart != null && createDateEnd != null) {
			criteriaCount.add(Restrictions.between("createDate", createDateStart, createDateEnd));
		} else if (createDateStart != null) {
			criteriaCount.add(Restrictions.ge("createDate", createDateStart));
		} else if (createDateEnd != null) {
			criteriaCount.add(Restrictions.le("createDate", createDateEnd));
		}

		if (StringUtils.isNotBlank(paySurveyCode)) {
			criteriaCount.add(Restrictions.ilike("code", paySurveyCode + "%"));
		}
		
		if (surveyEmployee != null) {
			criteriaCount.add(Restrictions.eq("claim.surveyEmployee", surveyEmployee));
		}
		
		criteriaCount.add(Restrictions.eq("status",PaySurveyStatus.active));
		
		criteriaCount.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("id"))
                		);   

		resultPaging.setRecordsFiltered((long) criteriaCount.list().size());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.createAlias("claims", "claim");
			
			if (createDateStart != null && createDateEnd != null) {
				criteria.add(Restrictions.between("createDate", createDateStart, createDateEnd));
			} else if (createDateStart != null) {
				criteria.add(Restrictions.ge("createDate", createDateStart));
			} else if (createDateEnd != null) {
				criteria.add(Restrictions.le("createDate", createDateEnd));
			}

			if (StringUtils.isNotBlank(paySurveyCode)) {
				criteria.add(Restrictions.ilike("code", paySurveyCode + "%"));
			}
			
			if (surveyEmployee != null) {
				
				criteria.add(Restrictions.eq("claim.surveyEmployee", surveyEmployee));
			}
			
			criteria.add(Restrictions.eq("status",PaySurveyStatus.active));
			
			criteria.setProjection(Projections.projectionList()
                    .add(Projections.groupProperty("id"))
                    .add(Projections.groupProperty("code"))
                    .add(Projections.groupProperty("createDate"))
                    .add(Projections.groupProperty("claim.surveyEmployee"))
                    .add(Projections.sum("claim.surveyTrans"))
                    .add(Projections.sum("claim.surveyPhoto"))
                    .add(Projections.sum("claim.surveyTel"))
                    .add(Projections.sum("claim.surveyClaim"))
                    .add(Projections.sum("claim.surveyDaily"))
                    		);   

			criteria.addOrder(Order.asc("code"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setDataObjects(criteria.list());
		}

		if (resultPaging.getDataObjects() == null) {
			resultPaging.setDataObjects(new ArrayList<Object[]>());
		}
		return resultPaging;
	}
}
