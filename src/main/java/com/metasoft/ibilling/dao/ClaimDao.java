package com.metasoft.ibilling.dao;

import java.util.Date;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.ClaimStatus;
import com.metasoft.ibilling.model.SurveyEmployee;

public interface ClaimDao extends AbstractDao<Claim, Integer> {
	public ClaimPaging searchPaging(Date dispatchDateStart, Date dispatchDateEnd, Branch branch, int start, int length);

	public ClaimPaging searchCheckClaimPaging(Date dispatchDateStart, Date dispatchDateEnd, String claimNo, SurveyEmployee surveyEmployee,
			ClaimStatus claimStatus, int start, int length);
	
	public ClaimPaging searchPaySurveyClaimPaging(Date dispatchDateStart, Date dispatchDateEnd, SurveyEmployee surveyEmployee, int start, int length);

	public Claim findByClaimNo(String claimNo);
}