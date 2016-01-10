package com.metasoft.ibilling.ws.bean.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class RptData {
	@JsonProperty("ID")
	private String refWsId;

	@JsonProperty("claim_no")
	private String claimNo;

	@JsonProperty("acc_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date accDate;

	@JsonProperty("noti_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date notiDate;

	@JsonProperty("dispatch_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date dispatchDate;

	@JsonProperty("confirm_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date confirmDate;

	@JsonProperty("arrive_ddt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date arriveDate;

	@JsonProperty("finish_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date finishDate;

	@JsonProperty("sendrpt_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date sendrptDate;

	@JsonProperty("notified_name")
	private String notifiedName;

	@JsonProperty("dispatch_By")
	private String dispatchBy;

	@JsonProperty("surveyor")
	private String surveyor;

	@JsonProperty("empcode")
	private String empcode;
	
	@JsonProperty("center")
	private String center;

	@JsonProperty("branch")
	private String branchCode;

	@JsonProperty("policy_Type")
	private String policyType;

	@JsonProperty("claim_type")
	private String claimTypeCode;

	@JsonProperty("dispatch_type")
	private String dispatchTypeCode;

	@JsonProperty("acc_zone")
	private String accZoneCode;

	@JsonProperty("COArea")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean coArea;

	@JsonProperty("disperse")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean disperse;
	
	@JsonProperty("W7")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean w7;

	@JsonProperty("wrkTime")
	private String wrkTimeCode;

	@JsonProperty("service_type")
	private String serviceTypeCode;

	@JsonProperty("survey_amphur")
	private String surveyAmphur;

	@JsonProperty("survey_province")
	private String surveyProvince;

	@JsonProperty("noti_result")
	private String notiResult;

	@JsonProperty("acc_result")
	private String accResult;

	@JsonProperty("HAS_TP")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean hasTp;
	
	@JsonProperty("TP_TYPE")
	private String tpType;

	@JsonProperty("TP_VEH")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean tpVeh;

	@JsonProperty("TP_PERSON")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean tpPerson;

	@JsonProperty("TP_ASST")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean tpAsst;

	@JsonProperty("PHOTO_NUM")
	private Integer photoNum;

	@JsonProperty("POLICE_RPT_NUM")
	private Integer policeRptNum;

	@JsonProperty("CLAIM_TP")
	private String claimTpCode;

	@JsonProperty("REVIEW_BY")
	private String reviewBy;

	@JsonProperty("APPROVE_BY")
	private String approveBy;

	@JsonProperty("C_STASTUS")
	private String cStatusCode;

	@JsonProperty("cancel_dt")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date cancelDate;

	@JsonProperty("SUR_INVEST")
	private Float surInvest;

	@JsonProperty("SUR_TRANS")
	private Float surTrans;

	@JsonProperty("SUR_DAILY")
	private Float surDaily;

	@JsonProperty("SUR_PHOTO")
	private Float surPhoto;

	@JsonProperty("SUR_CLAIM")
	private Float surClaim;

	@JsonProperty("SUR_TEL")
	private Float surTel;

	@JsonProperty("SUR_INSURE")
	private Float surInsure;

	@JsonProperty("SUR_TOWCAR")
	private Float surTowcar;

	@JsonProperty("SUR_OTHER")
	private Float surOther;

	@JsonProperty("TOTAL_SUM")
	private Float totlSum;

	@JsonProperty("SR_SEND_DATE")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date srSendDate;

	@JsonProperty("INS_INVEST")
	private Float insInvest;

	@JsonProperty("INS_TRANS")
	private Float insTrans;

	@JsonProperty("INS_DAILY")
	private Float insDaily;

	@JsonProperty("INS_PHOTO")
	private Float insPhoto;

	@JsonProperty("INS_CLAIM")
	private Float insClaim;

	@JsonProperty("INS_TEL")
	private Float insTel;

	@JsonProperty("INS_INSURE")
	private Float insInsure;

	@JsonProperty("INS_TOWCAR")
	private Float insTowcar;

	@JsonProperty("INS_OTHER")
	private Float insOther;

	@JsonProperty("SR_APPROVE_DATE")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date srApproveDate;

	public String getSurveyProvince() {
		return surveyProvince;
	}

	public void setSurveyProvince(String surveyProvince) {
		this.surveyProvince = surveyProvince;
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

	public Boolean getTpVeh() {
		return tpVeh;
	}

	public void setTpVeh(Boolean tpVeh) {
		this.tpVeh = tpVeh;
	}

	public Boolean getTpPerson() {
		return tpPerson;
	}

	public void setTpPerson(Boolean tpPerson) {
		this.tpPerson = tpPerson;
	}

	public Boolean getTpAsst() {
		return tpAsst;
	}

	public void setTpAsst(Boolean tpAsst) {
		this.tpAsst = tpAsst;
	}

	public String getReviewBy() {
		return reviewBy;
	}

	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}

	public String getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}

	public String getWrkTimeCode() {
		return wrkTimeCode;
	}

	public void setWrkTimeCode(String wrkTimeCode) {
		this.wrkTimeCode = wrkTimeCode;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	public Boolean getHasTp() {
		return hasTp;
	}

	public void setHasTp(Boolean hasTp) {
		this.hasTp = hasTp;
	}

	public String getClaimTpCode() {
		return claimTpCode;
	}

	public void setClaimTpCode(String claimTpCode) {
		this.claimTpCode = claimTpCode;
	}

	public String getcStatusCode() {
		return cStatusCode;
	}

	public void setcStatusCode(String cStatusCode) {
		this.cStatusCode = cStatusCode;
	}

	public String getRefWsId() {
		return refWsId;
	}

	public void setRefWsId(String refWsId) {
		this.refWsId = refWsId;
	}

	public Integer getPhotoNum() {
		return photoNum;
	}

	public void setPhotoNum(Integer photoNum) {
		this.photoNum = photoNum;
	}

	public Integer getPoliceRptNum() {
		return policeRptNum;
	}

	public void setPoliceRptNum(Integer policeRptNum) {
		this.policeRptNum = policeRptNum;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
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

	public Float getTotlSum() {
		return totlSum;
	}

	public void setTotlSum(Float totlSum) {
		this.totlSum = totlSum;
	}

	public Date getSrSendDate() {
		return srSendDate;
	}

	public void setSrSendDate(Date srSendDate) {
		this.srSendDate = srSendDate;
	}

	public Float getInsInvest() {
		return insInvest;
	}

	public void setInsInvest(Float insInvest) {
		this.insInvest = insInvest;
	}

	public Float getInsTrans() {
		return insTrans;
	}

	public void setInsTrans(Float insTrans) {
		this.insTrans = insTrans;
	}

	public Float getInsDaily() {
		return insDaily;
	}

	public void setInsDaily(Float insDaily) {
		this.insDaily = insDaily;
	}

	public Float getInsPhoto() {
		return insPhoto;
	}

	public void setInsPhoto(Float insPhoto) {
		this.insPhoto = insPhoto;
	}

	public Float getInsClaim() {
		return insClaim;
	}

	public void setInsClaim(Float insClaim) {
		this.insClaim = insClaim;
	}

	public Float getInsTel() {
		return insTel;
	}

	public void setInsTel(Float insTel) {
		this.insTel = insTel;
	}

	public Float getInsInsure() {
		return insInsure;
	}

	public void setInsInsure(Float insInsure) {
		this.insInsure = insInsure;
	}

	public Float getInsTowcar() {
		return insTowcar;
	}

	public void setInsTowcar(Float insTowcar) {
		this.insTowcar = insTowcar;
	}

	public String getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getSendrptDate() {
		return sendrptDate;
	}

	public void setSendrptDate(Date sendrptDate) {
		this.sendrptDate = sendrptDate;
	}

	public String getNotifiedName() {
		return notifiedName;
	}

	public void setNotifiedName(String notifiedName) {
		this.notifiedName = notifiedName;
	}

	public String getDispatchBy() {
		return dispatchBy;
	}

	public void setDispatchBy(String dispatchBy) {
		this.dispatchBy = dispatchBy;
	}

	public String getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	
	
	public String getClaimTypeCode() {
		return claimTypeCode;
	}

	public void setClaimTypeCode(String claimTypeCode) {
		this.claimTypeCode = claimTypeCode;
	}

	public String getDispatchTypeCode() {
		return dispatchTypeCode;
	}

	public void setDispatchTypeCode(String dispatchTypeCode) {
		this.dispatchTypeCode = dispatchTypeCode;
	}

	public String getAccZoneCode() {
		return accZoneCode;
	}

	public void setAccZoneCode(String accZoneCode) {
		this.accZoneCode = accZoneCode;
	}

	public Boolean getCoArea() {
		return coArea;
	}

	public void setCoArea(Boolean coArea) {
		this.coArea = coArea;
	}

	public Boolean getDisperse() {
		return disperse;
	}

	public void setDisperse(Boolean disperse) {
		this.disperse = disperse;
	}

	public String getSurveyAmphur() {
		return surveyAmphur;
	}

	public void setSurveyAmphur(String surveyAmphur) {
		this.surveyAmphur = surveyAmphur;
	}

	public Float getInsOther() {
		return insOther;
	}

	public void setInsOther(Float insOther) {
		this.insOther = insOther;
	}

	public Date getSrApproveDate() {
		return srApproveDate;
	}

	public void setSrApproveDate(Date srApproveDate) {
		this.srApproveDate = srApproveDate;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Boolean getW7() {
		return w7;
	}

	public void setW7(Boolean w7) {
		this.w7 = w7;
	}

	public String getTpType() {
		return tpType;
	}

	public void setTpType(String tpType) {
		this.tpType = tpType;
	}

	
}
