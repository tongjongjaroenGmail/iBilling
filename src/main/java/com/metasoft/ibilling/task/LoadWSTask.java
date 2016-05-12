package com.metasoft.ibilling.task;

import java.util.Date;

import com.metasoft.ibilling.service.ClaimService;

public class LoadWSTask implements Runnable{
	private ClaimService claimService;
	
	private Date loadDate;
	
	public LoadWSTask(ClaimService claimService,Date loadDate){
		this.claimService = claimService;
		this.loadDate = loadDate;
	}
	
	@Override
	public void run() {	
		claimService.loadClaimsFromWs(loadDate);
	}

}