package com.metasoft.ibilling.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class InvoiceReport {
	private static final long serialVersionUID = 1L;

	private String claimNo;
	private Date dispatchDate;
	private String claimType;
	private Float surInvest;
	private Float surTrans;
	private Float surDaily;
	private Float surPhoto;
	private Float surClaim;
	private Float surTel;
	private Float surInsure;
	private Float surTowcar;
	private Float surOther;
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public Date getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
