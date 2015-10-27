package com.metasoft.ibilling.dao.standard;

import java.util.List;

import com.metasoft.ibilling.dao.AbstractDao;
import com.metasoft.ibilling.model.StdInsurance;

public interface InsuranceDao extends AbstractDao<StdInsurance, Integer>{
	public List<StdInsurance> findAllOrder();
	public StdInsurance findByName(String name);
}
