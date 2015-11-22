/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.dao.BranchDao;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.util.DateToolsUtil;
import com.metasoft.ibilling.util.NumberToolsUtil;

@Service("claimService")
public class ClaimServiceImpl extends ModelBasedServiceImpl<ClaimDao, Claim, Integer> implements ClaimService {
	
	private ClaimDao claimDao;
	
	@Autowired
	private BranchDao branchDao;

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
	public ClaimSearchResultVoPaging searchPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranch,int start, int length) {	
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		Branch branch = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDate(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDate(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
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


}
