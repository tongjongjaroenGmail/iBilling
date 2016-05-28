package com.metasoft.ibilling.bean.vo;



public class PaySurveyReportSearchResultVo {
	private String claimNo = "";
	private String dispatchDate = "";
	private String branchName = "";
	private String claimStatus = "";
	private String paySurveyCode = "";
	private String employeeName = "";	
	private float surveyTotal;
	
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getPaySurveyCode() {
		return paySurveyCode;
	}
	public void setPaySurveyCode(String paySurveyCode) {
		this.paySurveyCode = paySurveyCode;
	}
	public float getSurveyTotal() {
		return surveyTotal;
	}
	public void setSurveyTotal(float surveyTotal) {
		this.surveyTotal = surveyTotal;
	}

	
}
