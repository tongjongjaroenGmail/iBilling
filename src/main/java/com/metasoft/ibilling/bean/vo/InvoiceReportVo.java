package com.metasoft.ibilling.bean.vo;

import javax.persistence.Column;

public class InvoiceReportVo {
	private String claimNo = "";
	private String branchDhipName = "";
	private String dispatchDate = "";
	private String type = "";
	private Float surInvest;
	private Float surTrans;
	private Float surDaily;
	private Float surPhoto;
	private Float surClaim;
	private Float surTel;
	private Float surInsure;
	private Float surTowcar;
	private Float surOther;
	private float surveyTotal = 0f;
	private String claimStatus = "";
	private String invoiceNo = "";
		
	public Float getSurInvest() {
		return surInvest;
	}
	public void setSurInvest(Float surInvest) {
		this.surInvest = surInvest;
	}
	public Float getSurTrans() {
		return surTrans;
	}
	public void setSurTrans(Float surTrans) {
		this.surTrans = surTrans;
	}
	public Float getSurDaily() {
		return surDaily;
	}
	public void setSurDaily(Float surDaily) {
		this.surDaily = surDaily;
	}
	public Float getSurPhoto() {
		return surPhoto;
	}
	public void setSurPhoto(Float surPhoto) {
		this.surPhoto = surPhoto;
	}
	public Float getSurClaim() {
		return surClaim;
	}
	public void setSurClaim(Float surClaim) {
		this.surClaim = surClaim;
	}
	public Float getSurTel() {
		return surTel;
	}
	public void setSurTel(Float surTel) {
		this.surTel = surTel;
	}
	public Float getSurInsure() {
		return surInsure;
	}
	public void setSurInsure(Float surInsure) {
		this.surInsure = surInsure;
	}
	public Float getSurTowcar() {
		return surTowcar;
	}
	public void setSurTowcar(Float surTowcar) {
		this.surTowcar = surTowcar;
	}
	public Float getSurOther() {
		return surOther;
	}
	public void setSurOther(Float surOther) {
		this.surOther = surOther;
	}
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
