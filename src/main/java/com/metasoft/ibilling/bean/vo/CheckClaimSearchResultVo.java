package com.metasoft.ibilling.bean.vo;



public class CheckClaimSearchResultVo {
	private int claimId;
	private String claimNo = "";
	private String employeeCode = "";
	private String center = "";
	private String dispatchDate = "";
	private String claimType = "";

	private float surveyTip;
	private float surveyEmp;
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public float getSurveyTip() {
		return surveyTip;
	}
	public void setSurveyTip(float surveyTip) {
		this.surveyTip = surveyTip;
	}
	public float getSurveyEmp() {
		return surveyEmp;
	}
	public void setSurveyEmp(float surveyEmp) {
		this.surveyEmp = surveyEmp;
	}
	
	
	
}
