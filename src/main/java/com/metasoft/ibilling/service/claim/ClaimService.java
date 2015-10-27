/**
 * 
 */
package com.metasoft.ibilling.service.claim;

import java.util.Date;
import java.util.List;

import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.controller.vo.ClaimSaveVo;
import com.metasoft.ibilling.controller.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.dao.claim.ClaimDao;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.JobStatus;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.model.StdInsurance;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.service.ModelBasedService;

public interface ClaimService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer> {
	public ClaimSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramTotalDayOfMaturity, String paramClaimTypeId, String paramClaimNumber, String paramJobStatusId, int start, int length,SecUser user);
	
	public List<ClaimSearchResultVo> searchExport(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramTotalDayOfMaturity, String paramClaimTypeId, String paramClaimNumber, String paramJobStatusId);

	public List<ClaimSearchResultVo> searchExport(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus);
	
	public ClaimSearchResultVoPaging searchPaging(Date jobDateStart, Date jobDateEnd, StdInsurance partyInsurance, Date maturityDate,
			ClaimType claimType, String claimNumber, JobStatus jobStatus, int start, int length,SecUser user);
	
	public TblClaimRecovery save(ClaimSaveVo claimSaveVo,SecUser user);
	
	public ClaimSaveVo findById(Integer id,SecUser user);
}
