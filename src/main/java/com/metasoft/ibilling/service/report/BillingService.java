/**
 * 
 */
package com.metasoft.ibilling.service.report;

import java.util.List;

import com.metasoft.ibilling.bean.paging.BillingSearchResultVoPaging;
import com.metasoft.ibilling.bean.report.BillingExportResult;
import com.metasoft.ibilling.dao.claim.ClaimDao;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.service.ModelBasedService;

public interface BillingService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer> {
	public BillingSearchResultVoPaging searchPaging(String paramCloseDateStart, String paramCloseDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length);

	public List<BillingExportResult> searchExport(Integer[] ids);
}
