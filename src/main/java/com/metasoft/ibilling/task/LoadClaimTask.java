package com.metasoft.ibilling.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.metasoft.ibilling.dao.ClaimLoadLogDao;
import com.metasoft.ibilling.model.ClaimLoadLog;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.ws.bean.json.ClaimRs;
import com.metasoft.ibilling.ws.rest.client.ClaimRsClient;

public class LoadClaimTask {
	@Autowired
	private ClaimService claimService;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private ClaimLoadLogDao claimLoadLogDao;

	public void loadClaim() {
		ClaimRsClient client = (ClaimRsClient) appContext.getBean("loadClaimClient");
		ClaimRs claimRs = null;
		ClaimLoadLog claimLoadLog = new ClaimLoadLog();
		try {
			claimRs = client.loadClaims();
		} catch (Exception e) {
			claimLoadLog.setRemark(e.toString());
			claimLoadLogDao.save(claimLoadLog);
		}
		claimLoadLog.setWsSuccess(true);
		if (claimLoadLog.isWsSuccess()) {
			if (claimRs == null) {
				claimLoadLog.setRemark("ไม่พบข้อมูลใน web service");
				claimLoadLogDao.save(claimLoadLog);
			} else if (claimRs.getSuccess() != 1) {
				claimLoadLog.setRemark("ข้อมูลใน web service : SUCCESS != 1");
				claimLoadLogDao.save(claimLoadLog);
			}else{
				claimService.loadClaimsFromWs(claimRs);
			}
		}
	}
}