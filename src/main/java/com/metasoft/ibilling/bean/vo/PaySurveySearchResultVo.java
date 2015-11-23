package com.metasoft.ibilling.bean.vo;



public class PaySurveySearchResultVo {
	private int paySurveyId;
	private String paySurveyCode = "";
	private String paySurveyDate = "";
	private String employeeCode = "";
	private float surveyTrans;
	private float surveyPhoto;
	private float surveyTel;
	private float surveyClaim;
	private float surveyDaily;
	private float surveyOther;
	private float surveyFine;
	private float surveyTotal;
	
	
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public int getPaySurveyId() {
		return paySurveyId;
	}
	public void setPaySurveyId(int paySurveyId) {
		this.paySurveyId = paySurveyId;
	}
	public String getPaySurveyCode() {
		return paySurveyCode;
	}
	public void setPaySurveyCode(String paySurveyCode) {
		this.paySurveyCode = paySurveyCode;
	}
	public String getPaySurveyDate() {
		return paySurveyDate;
	}
	public void setPaySurveyDate(String paySurveyDate) {
		this.paySurveyDate = paySurveyDate;
	}
	public float getSurveyTrans() {
		return surveyTrans;
	}
	public void setSurveyTrans(float surveyTrans) {
		this.surveyTrans = surveyTrans;
	}
	public float getSurveyPhoto() {
		return surveyPhoto;
	}
	public void setSurveyPhoto(float surveyPhoto) {
		this.surveyPhoto = surveyPhoto;
	}
	public float getSurveyTel() {
		return surveyTel;
	}
	public void setSurveyTel(float surveyTel) {
		this.surveyTel = surveyTel;
	}
	public float getSurveyClaim() {
		return surveyClaim;
	}
	public void setSurveyClaim(float surveyClaim) {
		this.surveyClaim = surveyClaim;
	}
	public float getSurveyDaily() {
		return surveyDaily;
	}
	public void setSurveyDaily(float surveyDaily) {
		this.surveyDaily = surveyDaily;
	}
	public float getSurveyOther() {
		return surveyOther;
	}
	public void setSurveyOther(float surveyOther) {
		this.surveyOther = surveyOther;
	}
	public float getSurveyFine() {
		return surveyFine;
	}
	public void setSurveyFine(float surveyFine) {
		this.surveyFine = surveyFine;
	}
	public float getSurveyTotal() {
		return surveyTotal;
	}
	public void setSurveyTotal(float surveyTotal) {
		this.surveyTotal = surveyTotal;
	}

	
	
}
