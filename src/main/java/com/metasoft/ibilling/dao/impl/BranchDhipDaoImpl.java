/**
 * 
 */
package com.metasoft.ibilling.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.BranchDhipDao;
import com.metasoft.ibilling.model.BranchDhip;

/**
 * @author
 * 
 */
@Repository("branchDhipDao")
public class BranchDhipDaoImpl extends AbstractDaoImpl<BranchDhip, Integer> implements BranchDhipDao {
	
	public BranchDhipDaoImpl() {
		super(BranchDhip.class);
	}
	
	@Override
	public BranchDhip findByCode(String code) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("code", code));	
		return (BranchDhip) criteria.uniqueResult();	
	}
}
