package com.metasoft.ibilling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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

	// ค่าบริการ
	private Float invest;

	// ค่าพาหนะ
	private Float trans;

	// ค่าประจำวัน
	private Float daily;

	// ค่ารูป
	private Float photo;

	// ค่าเรียกร้อง
	private Float claim;

	// ค่าโทรศัพท์
	private Float tel;

	// ค่าประกันตัว
	private Float insure;

	// ค่ารถยก
	private Float towcar;

	// ค่าใช้จ่ายอื่นๆ
	private Float other;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_date")
	// วันที่วางบิล
	private Date payDate;

	private Float tax; // ภาษี

	// จำนวนเคลม
	// ยอดรวมก่อนภาษี
	// ยอดรวมหลังภาษี

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getInvest() {
		return invest;
	}

	public void setInvest(Float invest) {
		this.invest = invest;
	}

	public Float getTrans() {
		return trans;
	}

	public void setTrans(Float trans) {
		this.trans = trans;
	}

	public Float getDaily() {
		return daily;
	}

	public void setDaily(Float daily) {
		this.daily = daily;
	}

	public Float getPhoto() {
		return photo;
	}

	public void setPhoto(Float photo) {
		this.photo = photo;
	}

	public Float getClaim() {
		return claim;
	}

	public void setClaim(Float claim) {
		this.claim = claim;
	}

	public Float getTel() {
		return tel;
	}

	public void setTel(Float tel) {
		this.tel = tel;
	}

	public Float getInsure() {
		return insure;
	}

	public void setInsure(Float insure) {
		this.insure = insure;
	}

	public Float getTowcar() {
		return towcar;
	}

	public void setTowcar(Float towcar) {
		this.towcar = towcar;
	}

	public Float getOther() {
		return other;
	}

	public void setOther(Float other) {
		this.other = other;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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

	public void setId(Integer id) {
		this.id = id;
	}

}
