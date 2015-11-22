package com.metasoft.ibilling.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "invoice")
@NamedQuery(name = "Invoice.findAll", query = "SELECT s FROM Invoice s")
public class Invoice extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id") // Id อ้างอิง
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String code;
	
//	// ค่าบริการ
//	private Float invest;
//	
//	// ค่าพาหนะ
//	private Float trans;
//	
//	// ค่าประจำวัน
//	private Float daily;
//	
//	// ค่ารูป
//	private Float photo;
//	
//	// ค่าเรียกร้อง
//	private Float claim;
//	
//	// ค่าโทรศัพท์
//	private Float tel;
//	
//	// ค่าประกันตัว
//	private Float insure;
//	
//	// ค่ารถยก
//	private Float towcar;
//	
//	// ค่าใช้จ่ายอื่นๆ
//	private Float other;
	
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
	
	private String remark;
	
	private Float tax; //	ภาษี
	// จำนวนเคลม
//	ยอดรวมก่อนภาษี
//	ยอดรวมหลังภาษี
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	private List<Claim> claims;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public Integer getId() {
		return id;
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

	public Float getTax() {
		return tax;
	}

	public void setTax(Float tax) {
		this.tax = tax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
