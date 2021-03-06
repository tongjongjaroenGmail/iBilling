/**
 * 
 */
package com.metasoft.ibilling.service;

import java.util.Date;
import java.util.List;

import com.metasoft.ibilling.bean.paging.CheckClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.InvoiceReportVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveyClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveyReportSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ReportStatisticsSurveyVoPaging;
import com.metasoft.ibilling.controller.vo.ReportStatisticsSurveyVo;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.model.Claim;

public interface ClaimService extends ModelBasedService<ClaimDao, Claim, Integer> {
	public ClaimSearchResultVoPaging searchGroupClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranch,
			String selClaimStatus,int start, int length);

	public CheckClaimSearchResultVoPaging searchCheckClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, String claimNo,
			Integer employeeId, Integer claimStatus, int start, int length);
	
	public PaySurveyClaimSearchResultVoPaging searchPaySurveyClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer employeeId,
			int start, int length);
	
	public PaySurveyReportSearchResultVoPaging searchPaySurveyReportPaging(String txtDispatchDateStart, String txtDispatchDateEnd, String hasPaySurvey,
			Integer branchId,Integer employeeId,int start, int length);
	
	public void loadClaimsFromWs(Date loadDate);
	
	public ReportStatisticsSurveyVoPaging searchReportStatisticsSurveyPaging(String dispatchDateStart, String dispatchDateEnd, Integer areaType,
			Integer branch,Integer claimStatus,int start, int length);
	
	public List<ReportStatisticsSurveyVo> searchReportStatisticsSurveyExport(Integer[] ids);
	
	public List<ReportStatisticsSurveyVo> searchReportStatisticsSurvey(String dispatchDateStart, String dispatchDateEnd, Integer areaType,
			Integer branch, Integer claimStatus);
	
	public void calcClaim(String claimNo);
	
	public InvoiceReportVoPaging searchInvoiceReportPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranchDhip,
			Integer selType,int start, int length);
}
