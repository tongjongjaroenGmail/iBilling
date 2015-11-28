package com.metasoft.ibilling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "claim_load_log_error_detail")
@NamedQuery(name = "ClaimLoadLogErrorDetail.findAll", query = "SELECT s FROM ClaimLoadLogErrorDetail s")
public class ClaimLoadLogErrorDetail extends BaseModel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	// Id อ้างอิง
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String remark;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "claim_load_log_id", nullable = false)
    private ClaimLoadLog claimLoadLog;

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

	public ClaimLoadLog getClaimLoadLog() {
		return claimLoadLog;
	}

	public void setClaimLoadLog(ClaimLoadLog claimLoadLog) {
		this.claimLoadLog = claimLoadLog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
