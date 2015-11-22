/**
 * 
 */
package com.metasoft.ibilling.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.BranchDao;
import com.metasoft.ibilling.model.Branch;

/**
 * @author
 * 
 */
@Repository("branchDao")
@Transactional
public class BranchDaoImpl extends AbstractDaoImpl<Branch, Integer> implements BranchDao {
	
	public BranchDaoImpl() {
		super(Branch.class);
	}
	
	@Override
	public Branch findByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("name", name));	
		return (Branch) criteria.uniqueResult();	
	}
}
