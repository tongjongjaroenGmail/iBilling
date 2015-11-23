package com.metasoft.ibilling.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pay_survey")
@NamedQuery(name = "PaySurvey.findAll", query = "SELECT s FROM PaySurvey s")
public class PaySurvey extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	// Id อ้างอิง
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String code;

	// // ค่าบริการ
	// private Float invest;
	//
	// // ค่าพาหนะ
	// private Float trans;
	//
	// // ค่าประจำวัน
	// private Float daily;
	//
	// // ค่ารูป
	// private Float photo;
	//
	// // ค่าเรียกร้อง
	// private Float claim;
	//
	// // ค่าโทรศัพท์
	// private Float tel;
	//
	// // ค่าประกันตัว
	// private Float insure;
	//
	// // ค่ารถยก
	// private Float towcar;
	//
	// // ค่าใช้จ่ายอื่นๆ
	// private Float other;
	//
	// // ค่าเงื่อนไขฝ่ายถูก
	// private Float right;

	// @Temporal(TemporalType.TIMESTAMP)
	// @Column(name = "pay_date")
	// // วันที่วางบิล
	// private Date payDate;

	// private Float tax; // ภาษี

	// จำนวนเคลม
	// ยอดรวมก่อนภาษี
	// ยอดรวมหลังภาษี

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paySurvey")
	private List<Claim> claims;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private PaySurveyStatus status = PaySurveyStatus.active;

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public PaySurveyStatus getStatus() {
		return status;
	}

	public void setStatus(PaySurveyStatus status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Integer id) {
		this.id = id;
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
