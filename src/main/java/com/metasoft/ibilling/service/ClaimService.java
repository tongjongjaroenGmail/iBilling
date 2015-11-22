/**
 * 
 */
package com.metasoft.ibilling.service;

import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.model.Claim;

public interface ClaimService extends ModelBasedService<ClaimDao, Claim, Integer> {
	public ClaimSearchResultVoPaging searchPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranch, int start,
			int length);
}
