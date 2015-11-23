/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.bean.paging.CheckClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveyClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.CheckClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.PaySurveyClaimSearchResultVo;
import com.metasoft.ibilling.dao.BranchDao;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.SurveyEmployeeDao;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.ClaimStatus;
import com.metasoft.ibilling.model.SurveyEmployee;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.util.DateToolsUtil;
import com.metasoft.ibilling.util.NumberToolsUtil;

@Service("claimService")
public class ClaimServiceImpl extends ModelBasedServiceImpl<ClaimDao, Claim, Integer> implements ClaimService {
	
	private ClaimDao claimDao;
	
	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private SurveyEmployeeDao surveyEmployeeDao;

	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public ClaimServiceImpl(ClaimDao dao) {
		super(dao);
		this.claimDao = dao;
	}

	@Override
	public ClaimSearchResultVoPaging searchGroupClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranch,int start, int length) {	
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		Branch branch = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (selBranch != null && selBranch != 0) {
			branch = branchDao.findById(selBranch);
		}
		
		ClaimPaging claimPaging = claimDao.searchPaging(dispatchDateStart, dispatchDateEnd, branch, start, length);

		ClaimSearchResultVoPaging voPaging = new ClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<ClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				ClaimSearchResultVo vo = new ClaimSearchResultVo();
				vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
				vo.setClaimId(claim.getId().intValue());
				if (claim.getDispatchDate() != null) {
					vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
				}
				if (claim.getBranch() != null) {
					vo.setBranchName(claim.getBranch().getName());
				}
				
			// TODO ค่าสำรวจ รอสูตร
				vo.setSurveyPrice(0f);
				
				vo.setSurInvest(NumberToolsUtil.nullToFloat(claim.getSurInvest()));
				vo.setSurTrans(NumberToolsUtil.nullToFloat(claim.getSurTrans()));
				vo.setSurDaily(NumberToolsUtil.nullToFloat(claim.getSurDaily()));
				vo.setSurPhoto(NumberToolsUtil.nullToFloat(claim.getSurPhoto()));
				vo.setSurClaim(NumberToolsUtil.nullToFloat(claim.getSurClaim()));
				vo.setSurTel(NumberToolsUtil.nullToFloat(claim.getSurTel()));
				vo.setSurInsure(NumberToolsUtil.nullToFloat(claim.getSurInsure()));
				vo.setSurTowcar(NumberToolsUtil.nullToFloat(claim.getSurTowcar()));
				vo.setSurOther(NumberToolsUtil.nullToFloat(claim.getSurOther()));
				
				float surTotal = vo.getSurInvest() + vo.getSurTrans() + vo.getSurDaily() + vo.getSurPhoto() + vo.getSurClaim() + 
						vo.getSurTel() + vo.getSurInsure() + vo.getSurTowcar() + vo.getSurOther();
				surTotal = (surTotal * (100 + NumberToolsUtil.nullToFloat(claim.getSurTax())))/100;
				vo.setSurTotal(surTotal);
				
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}

	@Override
	public CheckClaimSearchResultVoPaging searchCheckClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, String claimNo,
			Integer employeeId, Integer claimStatus, int start, int length) {	
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		SurveyEmployee surveyEmployee = null;
		ClaimStatus claimStatusEnum = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (employeeId != null && employeeId != 0) {
			surveyEmployee = surveyEmployeeDao.findById(employeeId);
		}
		
		if (claimStatus != null) {
			claimStatusEnum = ClaimStatus.getById(claimStatus);
		}
		
		ClaimPaging claimPaging = claimDao.searchCheckClaimPaging(dispatchDateStart, dispatchDateEnd, claimNo,surveyEmployee,claimStatusEnum, start, length);

		CheckClaimSearchResultVoPaging voPaging = new CheckClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<CheckClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				CheckClaimSearchResultVo vo = new CheckClaimSearchResultVo();
				vo.setClaimId(claim.getId().intValue());
				vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
				
				if (claim.getSurveyEmployee() != null) {
					vo.setEmployeeCode(StringUtils.trimToEmpty(claim.getSurveyEmployee().getCode()));
				}
				
				vo.setCenter(StringUtils.trimToEmpty(claim.getCenter()));
				
				if (claim.getDispatchDate() != null) {
					vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}
				
			// TODO ค่าสำรวจ รอสูตร
				vo.setSurveyTip(0f);
				vo.setSurveyEmp(0f);

				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}

	@Override
	public PaySurveyClaimSearchResultVoPaging searchPaySurveyClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd,
			Integer employeeId, int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		SurveyEmployee surveyEmployee = null;
		ClaimStatus claimStatusEnum = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (employeeId != null && employeeId != 0) {
			surveyEmployee = surveyEmployeeDao.findById(employeeId);
		}
		
		ClaimPaging claimPaging = claimDao.searchPaySurveyClaimPaging(dispatchDateStart, dispatchDateEnd, surveyEmployee, start, length);

		PaySurveyClaimSearchResultVoPaging voPaging = new PaySurveyClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<PaySurveyClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				PaySurveyClaimSearchResultVo vo = new PaySurveyClaimSearchResultVo();
				vo.setClaimId(claim.getId().intValue());
				vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
				if (claim.getDispatchDate() != null) {
					vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
				}

				vo.setSurveyTrans(NumberToolsUtil.nullToFloat(claim.getSurveyTrans()));
				vo.setSurveyDaily(NumberToolsUtil.nullToFloat(claim.getSurveyDaily()));
				vo.setSurveyPhoto(NumberToolsUtil.nullToFloat(claim.getSurveyPhoto()));
				vo.setSurveyClaim(NumberToolsUtil.nullToFloat(claim.getSurveyClaim()));
				vo.setSurveyTel(NumberToolsUtil.nullToFloat(claim.getSurveyTel()));
				vo.setSurveyFine(NumberToolsUtil.nullToFloat(claim.getSurveyFine()));
				vo.setSurveyOther(NumberToolsUtil.nullToFloat(claim.getSurveyOther()));
				
				float total = vo.getSurveyTrans() + vo.getSurveyDaily() + vo.getSurveyPhoto() + vo.getSurveyClaim() + vo.getSurveyTel() - 
						vo.getSurveyFine() + vo.getSurveyOther();
				
				vo.setSurveyTotal(total);
				
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}

}
