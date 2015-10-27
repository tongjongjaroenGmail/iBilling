/**
 * 
 */
package com.metasoft.ibilling.service.impl.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.bean.paging.BillingSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.bean.report.BillingExportResult;
import com.metasoft.ibilling.controller.vo.BillingSearchResultVo;
import com.metasoft.ibilling.dao.claim.ClaimDao;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.StdInsurance;
import com.metasoft.ibilling.model.TblClaimRecovery;
import com.metasoft.ibilling.service.impl.ModelBasedServiceImpl;
import com.metasoft.ibilling.service.report.BillingService;
import com.metasoft.ibilling.service.standard.InsuranceService;
import com.metasoft.ibilling.util.DateToolsUtil;

@Service("billingService")
public class BillingServiceImpl extends ModelBasedServiceImpl<ClaimDao, TblClaimRecovery, Integer> implements BillingService {
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private ClaimDao claimDao;

	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public BillingServiceImpl(ClaimDao dao) {
		super(dao);
		this.claimDao = dao;
	}

	@Override
	public BillingSearchResultVoPaging searchPaging(String paramCloseDateStart, String paramCloseDateEnd, String paramPartyInsuranceId,
			String paramClaimTypeId, int start, int length) {
		Date closeDateStart = null;
		Date closeDateEnd = null;
		StdInsurance partyInsurance = null;
		ClaimType claimType = null;
		
		if (StringUtils.isNotBlank(paramCloseDateStart)) {
			closeDateStart = DateToolsUtil.convertStringToDate(paramCloseDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramCloseDateEnd)) {
			closeDateEnd = DateToolsUtil.convertStringToDate(paramCloseDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(paramPartyInsuranceId)) {
			partyInsurance = insuranceService.findById(Integer.parseInt(paramPartyInsuranceId));
		}
		
		if (StringUtils.isNotBlank(paramClaimTypeId)) {
			claimType = ClaimType.getById(Integer.parseInt(paramClaimTypeId));
		}

		ClaimPaging paging = claimDao.searchBillingPaging(closeDateStart, closeDateEnd, partyInsurance, claimType, start, length);
		BillingSearchResultVoPaging voPaging = new BillingSearchResultVoPaging();
		
		voPaging.setDraw(paging.getDraw());
		voPaging.setRecordsFiltered(paging.getRecordsFiltered());
		voPaging.setRecordsTotal(paging.getRecordsTotal());
		voPaging.setData(new ArrayList<BillingSearchResultVo>());
		
		if(paging != null && paging.getData() != null){
			for (TblClaimRecovery claim : paging.getData()) {
				BillingSearchResultVo  vo = new BillingSearchResultVo();
				
				if (claim.getCloseDate() != null) {
					vo.setCloseDate(DateToolsUtil.convertToString(claim.getCloseDate(), DateToolsUtil.LOCALE_TH));
				}
				vo.setJobNo(StringUtils.trimToEmpty(claim.getJobNo()));
				vo.setClaimNumber(StringUtils.trimToEmpty(claim.getClaimNumber()));
				vo.setLicenseNumber(StringUtils.trimToEmpty(claim.getLicenseNumber()));
				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}
				if (claim.getPartyInsurance() != null) {
					vo.setInsuranceName(claim.getPartyInsurance().getName());
				}

				if(claim.getLicenseNumber() != null) {
					vo.setLicenseNumber(claim.getLicenseNumber());
				}		
				
				if(ClaimType.FAST_TRACK.equals(claim.getClaimType())){
					vo.setWage("300");
				}else if(ClaimType.KFK.equals(claim.getClaimType())){
					vo.setWage("300");
				}else if(ClaimType.REQUEST.equals(claim.getClaimType())){
					vo.setWage("300");
				}
				
				vo.setClaimId(claim.getId().toString());
				
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}


	@Override
	public List<BillingExportResult> searchExport(Integer[] ids) {
		List<TblClaimRecovery> results = claimDao.findByIds(ids);

		List<BillingExportResult> vos = new ArrayList<BillingExportResult>();
		
		if(results != null ){
			int i = 1;
			for (TblClaimRecovery claim : results) {
				BillingExportResult  export = new BillingExportResult();
				
				export.setNo(i++);
				if (claim.getCloseDate() != null) {
					export.setCloseDate(claim.getCloseDate());
				}
				if (claim.getAccidentDate() != null) {
					export.setAccidentDate(claim.getAccidentDate());
				}
				export.setJobNo(StringUtils.trimToEmpty(claim.getJobNo()));
				export.setClaimNumber(StringUtils.trimToEmpty(claim.getClaimNumber()));
				export.setLicenseNumber(StringUtils.trimToEmpty(claim.getLicenseNumber()));
				if (claim.getClaimType() != null) {
					export.setClaimType(claim.getClaimType().getId());
				}
				if (claim.getPartyInsurance() != null) {
					export.setInsuranceName(claim.getPartyInsurance().getName());
				}

				if(claim.getLicenseNumber() != null) {
					export.setLicenseNumber(claim.getLicenseNumber());
				}		
				
				if(ClaimType.FAST_TRACK.equals(claim.getClaimType())){
					export.setWage(300F);
				}else if(ClaimType.KFK.equals(claim.getClaimType())){
					export.setWage(300F);
				}else if(ClaimType.REQUEST.equals(claim.getClaimType())){
					export.setWage(300F);
				}else {
					export.setWage(0F);
				}
				export.setClaimAmount(claim.getClaimAmount());
				
				if (claim.getReceiveMoneyType() != null) {
					export.setReceiveMoneyType(claim.getReceiveMoneyType().getName());
				}
				vos.add(export);
			}
		}

		return vos;
	}
		


}
