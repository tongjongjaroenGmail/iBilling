package com.metasoft.ibilling.dao;

import com.metasoft.ibilling.model.Branch;

public interface BranchDao extends AbstractDao<Branch, Integer>{
	
	public Branch findByName(String name);
}
