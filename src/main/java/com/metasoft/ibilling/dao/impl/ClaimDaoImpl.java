package com.metasoft.ibilling.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Claim;

@Repository("claimDao")
@Transactional
public class ClaimDaoImpl extends AbstractDaoImpl<Claim, Integer> implements ClaimDao {
	@Autowired
	private UserDao userDao;

	public ClaimDaoImpl() {
		super(Claim.class);
	}

	@Override
	public ClaimPaging searchPaging(Date dispatchDateStart,Date dispatchDateEnd,Branch branch,
			int start,int length) {
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
	

		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (dispatchDateStart != null && dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
		} else if (dispatchDateStart != null) {
			criteriaCount.add(Restrictions.ge("dispatchDate", dispatchDateStart));
		} else if (dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.le("dispatchDate", dispatchDateEnd));
		}

		if (branch != null) {
			criteriaCount.add(Restrictions.eq("branch", branch));
		}
		
		criteriaCount.add(Restrictions.isNull("invoice"));

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (dispatchDateStart != null && dispatchDateEnd != null) {
				criteria.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
			} else if (dispatchDateStart != null) {
				criteria.add(Restrictions.ge("dispatchDate", dispatchDateStart));
			} else if (dispatchDateEnd != null) {
				criteria.add(Restrictions.le("dispatchDate", dispatchDateEnd));
			}

			if (branch != null) {
				criteria.add(Restrictions.eq("branch", branch));
			}
			
			criteria.add(Restrictions.isNull("invoice"));

			criteria.addOrder(Order.asc("branch"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<Claim>());
		}
		return resultPaging;
	}
}