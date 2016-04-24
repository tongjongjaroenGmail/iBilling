package com.metasoft.ibilling.bean.vo;

public class InvoiceReportVo {
	private String claimNo = "";
	private String branchDhipName = "";
	private String dispatchDate = "";
	private String type = "";
	private float surveyTotal = 0f;
	private String claimStatus = "";
	private String invoiceNo = "";
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getBranchDhipName() {
		return branchDhipName;
	}
	public void setBranchDhipName(String branchDhipName) {
		this.branchDhipName = branchDhipName;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getSurveyTotal() {
		return surveyTotal;
	}
	public void setSurveyTotal(float surveyTotal) {
		this.surveyTotal = surveyTotal;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
}
