/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.paging.PaySurveyPaging;
import com.metasoft.ibilling.bean.paging.PaySurveySearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.PaySurveySearchResultVo;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.PaySurveyDao;
import com.metasoft.ibilling.dao.SurveyEmployeeDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.SurveyEmployee;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.PaySurveyService;
import com.metasoft.ibilling.util.DateToolsUtil;
import com.metasoft.ibilling.util.NumberToolsUtil;

@Service("paySurveyService")
public class PaySurveyServiceImpl extends ModelBasedServiceImpl<PaySurveyDao, PaySurvey, Integer> implements PaySurveyService {
	
	private PaySurveyDao paySurveyDao;
	
	@Autowired
	private ClaimDao claimDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SurveyEmployeeDao surveyEmployeeDao;

	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public PaySurveyServiceImpl(PaySurveyDao dao) {
		super(dao);
		this.paySurveyDao = dao;
	}

	@Override
	@Transactional
	public PaySurvey save(String claimIds, int createBy) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM",DateToolsUtil.LOCALE_TH);
		
		User user = userDao.findById(createBy);
		
		PaySurvey paySurvey = new PaySurvey();
		paySurvey.setCreateBy(user);
		paySurvey.setCreateDate(new Date());
		paySurvey.setCode(paySurveyDao.genCode(sdf.format(new Date())));
		paySurveyDao.save(paySurvey);
		
		String[] arrClaimIds = claimIds.split(",");
		for (String claimId : arrClaimIds) {
			Claim claim = claimDao.findById(Integer.parseInt(claimId.trim()));
			claim.setPaySurvey(paySurvey);
			claim.setUpdateBy(user);
			claim.setUpdateDate(new Date());
			claimDao.saveOrUpdate(claim);
		}
		
		return paySurvey;
	}
	
	@Override
	public PaySurveySearchResultVoPaging searchPaging(String txtPaySurveyDateStart, String txtPaySurveyDateEnd, String paySurveyCode,Integer employeeId,int start, int length) {	
		Date paySurveyDateStart = null;
		Date paySurveyDateEnd = null;

		if (StringUtils.isNotBlank(txtPaySurveyDateStart)) {
			paySurveyDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtPaySurveyDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtPaySurveyDateEnd)) {
			paySurveyDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtPaySurveyDateEnd, DateToolsUtil.LOCALE_TH);
		}
		
		SurveyEmployee employee = null;
		if(employeeId != null && employeeId != 0){
			employee = surveyEmployeeDao.findById(employeeId);
		}
		
		PaySurveyPaging paging = paySurveyDao.searchPaging(paySurveyDateStart, paySurveyDateEnd, paySurveyCode,employee, start, length);

		PaySurveySearchResultVoPaging voPaging = new PaySurveySearchResultVoPaging();
		voPaging.setDraw(paging.getDraw());
		voPaging.setRecordsFiltered(paging.getRecordsFiltered());
		voPaging.setRecordsTotal(paging.getRecordsTotal());
		voPaging.setData(new ArrayList<PaySurveySearchResultVo>());
		if (paging != null && paging.getDataObjects() != null) {
			for (Object[] bean : paging.getDataObjects()) {
				PaySurveySearchResultVo vo = new PaySurveySearchResultVo();
				
                int i = 0;
                vo.setPaySurveyId((int) bean[i++]);
                
                vo.setPaySurveyCode((String) bean[i++]);
                
				if (bean[i] != null) {
					vo.setPaySurveyDate(DateToolsUtil.convertToString((Date)bean[i++], DateToolsUtil.LOCALE_TH));
				}
				if (bean[i] != null) {
					vo.setEmployeeCode(StringUtils.trimToEmpty(((SurveyEmployee)bean[i++]).getCode()));
				}
				
				PaySurvey paySurvey = paySurveyDao.findById(vo.getPaySurveyId());
				float surTotal = 0;
				for (Claim claim : paySurvey.getClaims()) {
					vo.setSurveyTrans(NumberToolsUtil.nullToFloat(claim.getSurveyTrans()) + vo.getSurveyTrans());
					vo.setSurveyPhoto(NumberToolsUtil.nullToFloat(claim.getSurveyPhoto()) + vo.getSurveyPhoto());
					vo.setSurveyTel(NumberToolsUtil.nullToFloat(claim.getSurveyTel()) + vo.getSurveyTel());
					vo.setSurveyClaim(NumberToolsUtil.nullToFloat(claim.getSurveyClaim()) + vo.getSurveyClaim());
					vo.setSurveyDaily(NumberToolsUtil.nullToFloat(claim.getSurveyDaily()) + vo.getSurveyDaily());
					
					surTotal += ClaimServiceImpl.calcTotalSurvey(claim);
				}

				vo.setSurveyTotal(surTotal);
				
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}
}
