/**
 * 
 */
package com.metasoft.ibilling.service.claim;



import java.util.List;

import com.metasoft.ibilling.bean.paging.TrackingSearchResultVoPaging;
import com.metasoft.ibilling.bean.report.BillingExportResult;
import com.metasoft.ibilling.controller.vo.LaborResultVo;
import com.metasoft.ibilling.controller.vo.TrackingSearchResultVo;
import com.metasoft.ibilling.dao.claim.ReportDao;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.service.ModelBasedService;

public interface ReportService extends ModelBasedService<ReportDao, TblClaimRecovery, Integer> {
	public TrackingSearchResultVoPaging searchPaging(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length,String pageName);
	
	public TrackingSearchResultVoPaging searchPagingLabor(String paramJobDateStart, String paramJobDateEnd, String agentName,
			String paramClaimTypeId, int start, int length);
	
	public List<TrackingSearchResultVo> trackingPrint(String paramJobDateStart, String paramJobDateEnd, String paramPartyInsuranceId,
			//String paramClaimTypeId, int start, int length,String pageName);
			String paramClaimTypeId,String pageName);
	
	public List<TrackingSearchResultVo> laborPrint(String paramJobDateStart, String paramJobDateEnd, String agentName,
			String paramClaimTypeId);
	
	public List<TrackingSearchResultVo> searchExport(Integer[] ids); 
	
	public List<LaborResultVo> searchExportLabor(Integer[] ids);
	
//	public List<TrackingSearchResultVo> searchExportLabor(Integer[] ids);
	

	 
}
