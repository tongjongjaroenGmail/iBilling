package com.metasoft.ibilling.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.UserInsuranceDao;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.JobStatus;
import com.metasoft.ibilling.model.StdInsurance;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.model.TblUserInsurance;

@Repository("userInsuranceDao")
@Transactional
public class UserInsuranceDaoImpl extends AbstractDaoImpl<TblUserInsurance, Integer> implements UserInsuranceDao {
	public UserInsuranceDaoImpl() {
		super(TblUserInsurance.class);
	}

	@Override
	public List<TblUserInsurance> searchByInsuranceId(int insuranceId) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("id.insuranceId", insuranceId));
		return criteria.list();
	}
}
