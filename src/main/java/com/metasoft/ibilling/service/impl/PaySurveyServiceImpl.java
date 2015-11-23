/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.PaySurveyDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.PaySurveyService;

@Service("paySurveyService")
public class PaySurveyServiceImpl extends ModelBasedServiceImpl<PaySurveyDao, PaySurvey, Integer> implements PaySurveyService {
	
	private PaySurveyDao paySurveyDao;
	
	@Autowired
	private ClaimDao claimDao;
	
	@Autowired
	private UserDao userDao;

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
		User user = userDao.findById(createBy);
		
		PaySurvey paySurvey = new PaySurvey();
		paySurvey.setCreateBy(user);
		paySurvey.setCreateDate(new Date());
		paySurveyDao.save(paySurvey);
		
		paySurvey.setCode(String.format("%08d", paySurvey.getId()));
		paySurveyDao.saveOrUpdate(paySurvey);
		
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
}
