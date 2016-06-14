package com.metasoft.ibilling.bean.vo;

import javax.persistence.Column;



public class PaySurveyReportSearchResultVo {
	private String claimNo = "";
	private String dispatchDate = "";
	private String branchName = "";
	private String claimStatus = "";
	private String paySurveyCode = "";
	private String employeeName = "";	
	private float surveyTotal;
	private Float surveyInvest;
	private Float surveyTrans;
	private Float surveyDaily;
	private Float surveyPhoto;
	private Float surveyClaim;
	private Float surveyTel;
	private Float surveyConditionRight;
	private Float surveyOther;
	private Float surveyFine;
	
	public Float getSurveyInvest() {
		return surveyInvest;
	}
	public void setSurveyInvest(Float surveyInvest) {
		this.surveyInvest = surveyInvest;
	}
	public Float getSurveyTrans() {
		return surveyTrans;
	}
	public void setSurveyTrans(Float surveyTrans) {
		this.surveyTrans = surveyTrans;
	}
	public Float getSurveyDaily() {
		return surveyDaily;
	}
	public void setSurveyDaily(Float surveyDaily) {
		this.surveyDaily = surveyDaily;
	}
	public Float getSurveyPhoto() {
		return surveyPhoto;
	}
	public void setSurveyPhoto(Float surveyPhoto) {
		this.surveyPhoto = surveyPhoto;
	}
	public Float getSurveyClaim() {
		return surveyClaim;
	}
	public void setSurveyClaim(Float surveyClaim) {
		this.surveyClaim = surveyClaim;
	}
	public Float getSurveyTel() {
		return surveyTel;
	}
	public void setSurveyTel(Float surveyTel) {
		this.surveyTel = surveyTel;
	}
	public Float getSurveyConditionRight() {
		return surveyConditionRight;
	}
	public void setSurveyConditionRight(Float surveyConditionRight) {
		this.surveyConditionRight = surveyConditionRight;
	}
	public Float getSurveyOther() {
		return surveyOther;
	}
	public void setSurveyOther(Float surveyOther) {
		this.surveyOther = surveyOther;
	}
	public Float getSurveyFine() {
		return surveyFine;
	}
	public void setSurveyFine(Float surveyFine) {
		this.surveyFine = surveyFine;
	}
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
