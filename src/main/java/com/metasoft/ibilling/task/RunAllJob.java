package com.metasoft.ibilling.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class RunAllJob extends QuartzJobBean {
	private LoadClaimTask loadClaimTask;

	public void setLoadClaimTask(LoadClaimTask loadClaimTask) {
		this.loadClaimTask = loadClaimTask;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

			try {
				loadClaimTask.loadClaim();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}