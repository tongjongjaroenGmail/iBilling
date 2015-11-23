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
//	
//	// ค่าเงื่อนไขฝ่ายถูก
//	private Float right;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_date")
	// วันที่วางบิล
	private Date payDate;

//	private Float tax; // ภาษี

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

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	

}
