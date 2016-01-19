package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.ProvinceDao;
import com.metasoft.ibilling.model.Province;

@Repository("provinceDao")
public class ProvinceDaoImpl extends AbstractDaoImpl<Province, Integer> implements ProvinceDao {
	public ProvinceDaoImpl() {
		super(Province.class);
	}
}
