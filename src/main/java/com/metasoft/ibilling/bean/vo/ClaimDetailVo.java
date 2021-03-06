package com.metasoft.ibilling.bean.vo;


public class ClaimDetailVo {
	private int claimId;
	private String claimNo = "";
	private String dispatchDate = "";
	private String sendrptDate = "";
	private String claimStatus = "";
	
	private String employeeCode = "";
	private String center = "";
	private String claimType = "";
	private String dispatchType = "";
	private String areaType = "";
	private String workTime = "";
	private boolean coArea;
	private boolean disperse;
	private String serviceType = "";
	private String serviceAmphur = "";
	private String serviceProvince = "";
	private Integer photoNum;
	private Integer policeRptNum;
	private String claimTp = "";
	private String reviewBy = "";
	private String srSendDate = "";
	private Float surClaimMain;
	private String approveBy = "";
	private String srApproveDate = "";
	
	private int invoiceId;
	private String invoiceCode = "";
	private Float surInvest;
	private Float surTrans;
	private Float surDaily;
	private Float surPhoto;
	private Float surClaim;
	private Float surTel;
	private Float surInsure;
	private Float surTowcar;
	private Float surOther;
	private Float surTotalNoTax;
	private Float surTax;
	private Float surTotalWithTax;
	
	private Float insInvest;
	private Float insTrans;
	private Float insDaily;
	private Float insPhoto;
	private Float insClaim;
	private Float insTel;
	private Float insInsure;
	private Float insTowcar;
	private Float insOther;
	private Float insTotalNoTax;
	private Float insTax;
	private Float insTotalWithTax;
	
	private int paySurveyId;
	private String paySurveyCode = "";
	private Float surveyInvest;
	private Float surveyTrans;
	private Float surveyDaily;
	private Float surveyPhoto;
	private Float surveyClaim;
	private Float surveyTel;
	private Float surveyConditionRight;
	private Float surveyOther;
	private Float surveyFine;
	private Float surveyTotal;
	
	private String remark = "";
	private String createBy = "";
	private String createDate = "";
	
	private boolean w7;

	public boolean isW7() {
		return w7;
	}
	public void setW7(boolean w7) {
		this.w7 = w7;
	}
	public String getApproveBy() {
		return approveBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	public String getSrApproveDate() {
		return srApproveDate;
	}
	public void setSrApproveDate(String srApproveDate) {
		this.srApproveDate = srApproveDate;
	}
	public Float getSurClaimMain() {
		return surClaimMain;
	}
	public void setSurClaimMain(Float surClaimMain) {
		this.surClaimMain = surClaimMain;
	}
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
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getSendrptDate() {
		return sendrptDate;
	}
	public void setSendrptDate(String sendrptDate) {
		this.sendrptDate = sendrptDate;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
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
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getDispatchType() {
		return dispatchType;
	}
	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public boolean isCoArea() {
		return coArea;
	}
	public void setCoArea(boolean coArea) {
		this.coArea = coArea;
	}
	public boolean isDisperse() {
		return disperse;
	}
	public void setDisperse(boolean disperse) {
		this.disperse = disperse;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceAmphur() {
		return serviceAmphur;
	}
	public void setServiceAmphur(String serviceAmphur) {
		this.serviceAmphur = serviceAmphur;
	}
	public String getServiceProvince() {
		return serviceProvince;
	}
	public void setServiceProvince(String serviceProvince) {
		this.serviceProvince = serviceProvince;
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
	public String getClaimTp() {
		return claimTp;
	}
	public void setClaimTp(String claimTp) {
		this.claimTp = claimTp;
	}
	public String getReviewBy() {
		return reviewBy;
	}
	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}
	public String getSrSendDate() {
		return srSendDate;
	}
	public void setSrSendDate(String srSendDate) {
		this.srSendDate = srSendDate;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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
	public Float getSurTotalNoTax() {
		return surTotalNoTax;
	}
	public void setSurTotalNoTax(Float surTotalNoTax) {
		this.surTotalNoTax = surTotalNoTax;
	}
	public Float getSurTax() {
		return surTax;
	}
	public void setSurTax(Float surTax) {
		this.surTax = surTax;
	}
	public Float getSurTotalWithTax() {
		return surTotalWithTax;
	}
	public void setSurTotalWithTax(Float surTotalWithTax) {
		this.surTotalWithTax = surTotalWithTax;
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
	public Float getInsOther() {
		return insOther;
	}
	public void setInsOther(Float insOther) {
		this.insOther = insOther;
	}
	public Float getInsTotalNoTax() {
		return insTotalNoTax;
	}
	public void setInsTotalNoTax(Float insTotalNoTax) {
		this.insTotalNoTax = insTotalNoTax;
	}
	public Float getInsTax() {
		return insTax;
	}
	public void setInsTax(Float insTax) {
		this.insTax = insTax;
	}
	public Float getInsTotalWithTax() {
		return insTotalWithTax;
	}
	public void setInsTotalWithTax(Float insTotalWithTax) {
		this.insTotalWithTax = insTotalWithTax;
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
	public Float getSurveyTotal() {
		return surveyTotal;
	}
	public void setSurveyTotal(Float surveyTotal) {
		this.surveyTotal = surveyTotal;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
