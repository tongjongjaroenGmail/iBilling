package com.metasoft.ibilling.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.SubBranchDao;
import com.metasoft.ibilling.model.Amphur;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.SubBranch;

@Repository("subBranchDao")
@Transactional
public class SubBranchDaoImpl extends AbstractDaoImpl<SubBranch, Integer> implements SubBranchDao {
	public SubBranchDaoImpl() {
		super(SubBranch.class);
	}
	
	@Override
	public SubBranch findByAmphurAndBranch(Amphur amphur,Branch branch) {
		return (SubBranch) getCurrentSession().createCriteria(entityClass)
				.add(Restrictions.eq("amphur", amphur))
				.add(Restrictions.eq("branch", branch))
				.uniqueResult();
	}
}
