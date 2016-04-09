package com.metasoft.ibilling.controller.vo;

public class ReportStatisticsSurveyVo {
	private Integer claimId;
	private String claimNo = "";
	private String dispatchDate = "";
	private String employeeName = "";
	private String employeeCode = "";
	private String branchName = "";
	private String policyType = "";
	private String hasTp = "";
	private String notiResult = "";
	private String accResult = "";
	private String tpVeh = "";
	private String tpType = "";
	private String disperse = "";
	private String wrkTime = "";
	private Float surClaim = 0f;
	private String claimType = "";
	private String accZone = "";
	
//	แก้ i-billing ครั้งที่4‏
//	 "ผู้ตรวจสอบ","ยอดวางบิลทิพย","ยอดทิพยอนุมัติ","ยอดจ่ายค่าสำรวจ พนักงาน","สถานะของงาน","ผู้อนุมัติ"
	private String reviewBy = "";
	private String approveBy = "";
	private Float surTotalWithTax = 0f;
	private Float insTotalWithTax = 0f;
	private Float surveyTotal = 0f;
	private String claimStatus = "";

	public String getApproveBy() {
		return approveBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	public String getReviewBy() {
		return reviewBy;
	}
	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}
	public Float getSurTotalWithTax() {
		return surTotalWithTax;
	}
	public void setSurTotalWithTax(Float surTotalWithTax) {
		this.surTotalWithTax = surTotalWithTax;
	}
	public Float getInsTotalWithTax() {
		return insTotalWithTax;
	}
	public void setInsTotalWithTax(Float insTotalWithTax) {
		this.insTotalWithTax = insTotalWithTax;
	}
	public Float getSurveyTotal() {
		return surveyTotal;
	}
	public void setSurveyTotal(Float surveyTotal) {
		this.surveyTotal = surveyTotal;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getHasTp() {
		return hasTp;
	}
	public void setHasTp(String hasTp) {
		this.hasTp = hasTp;
	}
	public String getNotiResult() {
		return notiResult;
	}
	public void setNotiResult(String notiResult) {
		this.notiResult = notiResult;
	}
	public String getAccResult() {
		return accResult;
	}
	public void setAccResult(String accResult) {
		this.accResult = accResult;
	}
	public String getTpVeh() {
		return tpVeh;
	}
	public void setTpVeh(String tpVeh) {
		this.tpVeh = tpVeh;
	}
	public String getTpType() {
		return tpType;
	}
	public void setTpType(String tpType) {
		this.tpType = tpType;
	}
	public String getDisperse() {
		return disperse;
	}
	public void setDisperse(String disperse) {
		this.disperse = disperse;
	}
	public String getWrkTime() {
		return wrkTime;
	}
	public void setWrkTime(String wrkTime) {
		this.wrkTime = wrkTime;
	}
	
	public Float getSurClaim() {
		return surClaim;
	}
	public void setSurClaim(Float surClaim) {
		this.surClaim = surClaim;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getAccZone() {
		return accZone;
	}
	public void setAccZone(String accZone) {
		this.accZone = accZone;
	}
	
	
}
