package com.metasoft.ibilling.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "claim_load_log")
@NamedQuery(name = "ClaimLoadLog.findAll", query = "SELECT s FROM ClaimLoadLog s")
public class ClaimLoadLog extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	// Id อ้างอิง
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "ws_success")
	boolean wsSuccess;

	private String remark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "total_insert_data")
	private int totalInsertData;

	@Column(name = "total_error_data")
	private int totalErrorData;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "claimLoadLog", cascade = { CascadeType.ALL })
	private List<ClaimLoadLogErrorDetail> claimLoadLogErrorDetails;

	public boolean isWsSuccess() {
		return wsSuccess;
	}

	public void setWsSuccess(boolean wsSuccess) {
		this.wsSuccess = wsSuccess;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTotalInsertData() {
		return totalInsertData;
	}

	public void setTotalInsertData(int totalInsertData) {
		this.totalInsertData = totalInsertData;
	}

	public int getTotalErrorData() {
		return totalErrorData;
	}

	public void setTotalErrorData(int totalErrorData) {
		this.totalErrorData = totalErrorData;
	}

	public List<ClaimLoadLogErrorDetail> getClaimLoadLogErrorDetails() {
		return claimLoadLogErrorDetails;
	}

	public void setClaimLoadLogErrorDetails(List<ClaimLoadLogErrorDetail> claimLoadLogErrorDetails) {
		this.claimLoadLogErrorDetails = claimLoadLogErrorDetails;
	}

}
