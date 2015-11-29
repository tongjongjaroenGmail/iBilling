/**
 * 
 */
package com.metasoft.ibilling.service;

import com.metasoft.ibilling.bean.paging.CheckClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveyClaimSearchResultVoPaging;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.ws.bean.json.ClaimRs;

public interface ClaimService extends ModelBasedService<ClaimDao, Claim, Integer> {
	public ClaimSearchResultVoPaging searchGroupClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranch,
			int start, int length);

	public CheckClaimSearchResultVoPaging searchCheckClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, String claimNo,
			Integer employeeId, Integer claimStatus, int start, int length);
	
	public PaySurveyClaimSearchResultVoPaging searchPaySurveyClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer employeeId,
			int start, int length);
	
	public void loadClaimsFromWs(ClaimRs claimRs);
	
	public float calcTotalSurvey(Claim claim);
}
