package com.metasoft.ibilling.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.metasoft.ibilling.dao.ClaimLoadLogDao;
import com.metasoft.ibilling.service.ClaimService;

public class LoadClaimTask {
	@Autowired
	private ClaimService claimService;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private ClaimLoadLogDao claimLoadLogDao;

	public void loadClaim() {
		claimService.loadClaimsFromWs(null);
	}
}