package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.ClaimLoadLogDao;
import com.metasoft.ibilling.model.ClaimLoadLog;

@Repository("claimLoadLogDao")
public class ClaimLoadLogDaoImpl extends AbstractDaoImpl<ClaimLoadLog, Integer> implements ClaimLoadLogDao {
	
	public ClaimLoadLogDaoImpl() {
		super(ClaimLoadLog.class);
	}

}
