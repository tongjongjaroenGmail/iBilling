package com.metasoft.ibilling.dao;

import com.metasoft.ibilling.model.Amphur;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.SubBranch;

public interface SubBranchDao extends AbstractDao<SubBranch, Integer>{
	SubBranch findByAmphurAndBranch(Amphur amphur, Branch branch);
}
