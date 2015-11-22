package com.metasoft.ibilling.dao;

import java.util.Date;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Claim;

public interface ClaimDao extends AbstractDao<Claim, Integer>{
	public ClaimPaging searchPaging(Date dispatchDateStart,Date dispatchDateEnd,Branch branch,
			int start,int length);
}