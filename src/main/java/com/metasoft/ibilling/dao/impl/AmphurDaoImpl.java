package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.AmphurDao;
import com.metasoft.ibilling.model.Amphur;

@Repository("amphurDao")
public class AmphurDaoImpl extends AbstractDaoImpl<Amphur, Integer> implements AmphurDao {
	public AmphurDaoImpl() {
		super(Amphur.class);
	}
}
