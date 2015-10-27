package com.metasoft.ibilling.dao.claim;

import java.util.Date;
import java.util.List;

import com.metasoft.ibilling.bean.paging.ReportPaging;
import com.metasoft.ibilling.dao.AbstractDao;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.JobStatus;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.model.StdInsurance;
import com.metasoft.ibilling.model.TblClaimRecovery;

public interface ReportDao extends AbstractDao<TblClaimRecovery, Integer>{
	public ReportPaging searchPaging(Date jobDateStart,Date jobDateEnd,StdInsurance partyInsurance,
			ClaimType claimType,int start,int length,String pageName);
	
	public ReportPaging searchPaging(Date jobDateStart,Date jobDateEnd,int agent,
			ClaimType claimType);
	
	public ReportPaging searchPaging(Date jobDateStart,Date jobDateEnd,SecUser agent,
			ClaimType claimType,int start,int length);
	
	public List<TblClaimRecovery> searchExport(Date jobDateStart,Date jobDateEnd,int agent,
			ClaimType claimType);
	
	
	public List<TblClaimRecovery> searchExportTracking(Date jobDateStart,Date jobDateEnd,StdInsurance partyInsurance,
			ClaimType claimType,String pageName);
	   
}