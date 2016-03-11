package com.metasoft.ibilling.dao;

import com.metasoft.ibilling.model.BranchDhip;

public interface BranchDhipDao extends AbstractDao<BranchDhip, Integer>{
	public BranchDhip findByCode(String code);
	
	public BranchDhip findByName(String name);
}
