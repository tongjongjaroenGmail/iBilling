package com.metasoft.ibilling.model;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "claim")
@NamedQuery(name = "Claim.findAll", query = "SELECT s FROM Claim s")
public class Claim extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	// Id อ้างอิง
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "claim_no")
	// เลขเคลม
	private String claimNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "acc_date")
	// วันเวลาที่เกิดเหตุ
	private Date accDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "noti_date")
	// วันเวลาที่รับแจ้ง
	private Date notiDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dispatch_date")
	// วันเวลาที่จ่ายงาน
	private Date dispatchDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "confirm_date")
	// วันเวลาที่รับงาน
	private Date confirmDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrive_date")
	// วันเวลาที่ถึงที่เกิดเหตุ
	private Date arriveDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "finish_date")
	// วันเวลาที่เสร็จงาน
	private Date finishDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sendrpt_date")
	// วันเวลาที่ส่งงาน
	private Date sendrptDate;

	@Column(name = "notified_name")
	// ชื่อผุ้รับแจ้ง
	private String notifiedName;

	@Column(name = "dispatch_by")
	// ชื่อผู้จ่ายงาน
	private String dispatchBy;

	// ชื่อพนักงานสำรวจ
	private String surveyor;

	// รหัสพนักงานสำรวจ
	private String empcode;

	// @Column(name = "") // ศูนย์

	@ManyToOne
	@JoinColumn(name = "branch_id")
	// สาขา
	private Branch branch;

	@Column(name = "policy_type")
	// ประเภทกธ.
	private String policyType;

	// @Column(name = "") // เวร (ใน/นอก)

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "claim_type")
	// ประเภทเคลม (เคลมสด,เคลมแห้ง,ติดตาม)
	private ClaimType claimType;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "dispatch_type")
	// ประเภทการจ่าย (ว.4,นัดหมาย)
	private DispatchType dispatchType;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "acc_zone")
	// พื้นที่ (กทม./ปริมณฑล/ตจว.)
	private AreaType areaType;

	@Column(name = "co_area", nullable = true, columnDefinition = "boolean DEFAULT null")
	// นอกพื้นที่ (Y/N)
	private Boolean coArea;

	@Column(name = "disperse", nullable = true, columnDefinition = "boolean DEFAULT null")
	// แยกย้าย (Y/N)
	private Boolean disperse;

	@Column(name = "wrkTime", nullable = true, columnDefinition = "boolean DEFAULT null")
	// เวร (ใน/นอก)
	private Boolean wrkTime;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "service_type")
	// ประเภทบริการอื่นๆ (บริการ, ต่อเนื่อง, หน้าร้าน,พื้นที่เดียวกัน)
	private ServiceType serviceType;

	@Column(name = "service_amphur")
	// อำเภอที่ตรวจสอบ
	private String serviceAmphur;

	@Column(name = "survice_province")
	// จังหวัดที่ตรวจสอบ
	private String surviceProvince;

	@Column(name = "noti_result")
	// ผลคดี(รับแจ้ง)
	private String notiResult;

	@Column(name = "acc_result")
	// ผลคดี(สำรวจ)
	private String accResult;

	@Column(name = "has_tp", nullable = true, columnDefinition = "boolean DEFAULT null")
	// คู่กรณี (Y/N)
	private Boolean hasTp;

	// @Column(name = "") // ประเภทรถคู่กรณี กะบะ, จยย,
	// รถตู้,รถเก๋ง,รถบรรทุก,อื่นๆ

	@Column(name = "tp_veh", nullable = true, columnDefinition = "boolean DEFAULT null")
	// คู่กรณีรถ (Y/N)
	private Boolean tpVeh;

	@Column(name = "tp_person", nullable = true, columnDefinition = "boolean DEFAULT null")
	// คู่กรณีบุคคล (Y/N)
	private Boolean tpPerson;

	@Column(name = "tp_asst", nullable = true, columnDefinition = "boolean DEFAULT null")
	// คู่กรณีทรัพย์สิน (Y/N)
	private Boolean tpAsst;

	@Column(name = "photo_num")
	// จำนวนรูปถ่าย
	private Integer photoNum;

	@Column(name = "police_rpt_num")
	// จำนวนข้อประจำวัน
	private Integer policeRptNum;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "claim_tp")
	// เงื่อนไขฝ่ายถูก (ประกันภัย, บุคคล, ไม่มี)
	private ClaimTp claimTp;

	@Column(name = "review_by")
	// ชื่อผู้ตรวจงาน
	private String reviewBy;

	@Column(name = "approve_by")
	// ชื่อผุ้อนุมัติ
	private String approveBy;

	@Column(name = "c_status")
	// สถานะ จ่ายงาน,รอการตรวจสอบ,ปิดการตรวจสอบ,สินไหมอนุมัติ,ยกเลิก
	private ClaimStatus claimStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cancel_date")
	// วันเวลาที่ยกเลิก
	private Date cancelDate;

	@Column(name = "sur_invest")
	// ค่าบริการที่เสนอ
	private Float surInvest;

	@Column(name = "sur_trans")
	// ค่าพาหนะที่เสนอ
	private Float surTrans;

	@Column(name = "sur_daily")
	// ค่าประจำวันที่เสนอ
	private Float surDaily;

	@Column(name = "sur_photo")
	// ค่ารูปที่เสนอ
	private Float surPhoto;

	@Column(name = "sur_claim")
	// ค่าเรียกร้องที่เสนอ
	private Float surClaim;

	@Column(name = "sur_tel")
	// ค่าโทรศัพท์ที่เสนอ
	private Float surTel;

	@Column(name = "sur_insure")
	// ค่าประกันตัวที่เสนอ
	private Float surInsure;

	@Column(name = "sur_towcar")
	// ค่ารถยกที่เสนอ
	private Float surTowcar;

	@Column(name = "sur_other")
	// ค่าใช้จ่ายอื่นๆที่เสนอ
	private Float surOther;

//	@Column(name = "total_sum")
//	// ยอดรวมก่อนภาษีที่เสนอ
//	private Float totalSum;

	@Column(name = "sur_tax") // ภาษีที่เสนอ
	private Float surTax;
	// @Column(name = "") // ยอดรวมหลังภาษีที่เสนอ

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sr_send_date")
	// วันเวลาที่ตรวจงาน
	private Date srSendDate;

	@Column(name = "ins_inverst")
	// ค่าบริการที่อนุมัติ
	private Float insInverst;

	@Column(name = "ins_trans")
	// ค่าพาหนะที่อนุมัติ
	private Float insTrans;

	@Column(name = "ins_daily")
	// ค่าประจำวันที่อนุมัติ
	private Float insDaily;

	@Column(name = "ins_photo")
	// ค่ารูปที่อนุมัติ
	private Float insPhoto;

	@Column(name = "ins_claim")
	// ค่าเรียกร้องที่อนุมัติ
	private Float insClaim;

	@Column(name = "ins_tel")
	// ค่าโทรศัพท์ที่อนุมัติ
	private Float insTel;

	@Column(name = "ins_insure")
	// ค่าประกันตัวที่อนุมัติ
	private Float insInsure;

	@Column(name = "ins_toecar")
	// ค่ารถยกที่อนุมัติ
	private Float insToecar;

	@Column(name = "ins_other")
	// ค่าใช้จ่ายอื่นๆที่อนุมัติ
	private Float insOther;

	// @Column(name = "") // ยอดรวมก่อนภาษีที่อนุมัติ
	// @Column(name = "") // ภาษีที่อนุมัติ
	// @Column(name = "") // ยอดรวมหลังภาษีที่อนุมัติ

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sr_approve_date")
	// วันเวลาที่อนุมัติ
	private Date srApproveDate;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name = "create_by")
	private User createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "update_by")
	private User updateBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
	}

	public DispatchType getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(DispatchType dispatchType) {
		this.dispatchType = dispatchType;
	}

	public AreaType getAreaType() {
		return areaType;
	}

	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
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

	public Boolean getWrkTime() {
		return wrkTime;
	}

	public void setWrkTime(Boolean wrkTime) {
		this.wrkTime = wrkTime;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceAmphur() {
		return serviceAmphur;
	}

	public void setServiceAmphur(String serviceAmphur) {
		this.serviceAmphur = serviceAmphur;
	}

	public String getSurviceProvince() {
		return surviceProvince;
	}

	public void setSurviceProvince(String surviceProvince) {
		this.surviceProvince = surviceProvince;
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

	public Boolean getHasTp() {
		return hasTp;
	}

	public void setHasTp(Boolean hasTp) {
		this.hasTp = hasTp;
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

	public ClaimTp getClaimTp() {
		return claimTp;
	}

	public void setClaimTp(ClaimTp claimTp) {
		this.claimTp = claimTp;
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

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
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

//	public Float getTotalSum() {
//		return totalSum;
//	}
//
//	public void setTotalSum(Float totalSum) {
//		this.totalSum = totalSum;
//	}

	public Date getSrSendDate() {
		return srSendDate;
	}

	public void setSrSendDate(Date srSendDate) {
		this.srSendDate = srSendDate;
	}

	public Float getInsInverst() {
		return insInverst;
	}

	public void setInsInverst(Float insInverst) {
		this.insInverst = insInverst;
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

	public Float getInsToecar() {
		return insToecar;
	}

	public void setInsToecar(Float insToecar) {
		this.insToecar = insToecar;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Float getSurTax() {
		return surTax;
	}

	public void setSurTax(Float surTax) {
		this.surTax = surTax;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	

}
