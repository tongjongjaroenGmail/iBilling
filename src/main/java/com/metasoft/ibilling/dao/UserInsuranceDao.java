package com.metasoft.ibilling.dao;

import java.util.List;

import com.metasoft.ibilling.model.TblUserInsurance;

public interface UserInsuranceDao extends AbstractDao<TblUserInsurance, Integer>{
	public List<TblUserInsurance> searchByInsuranceId(int insuranceId);
}
