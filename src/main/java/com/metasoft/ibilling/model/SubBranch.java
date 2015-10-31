package com.metasoft.ibilling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the sec_user database table.
 * 
 */
@Entity
@Table(name = "sub_branch")
@NamedQuery(name = "SubBranch.findAll", query = "SELECT s FROM SubBranch s")
public class SubBranch extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToOne
	@JoinColumn(name = "amphur_id", nullable = false)
	private Amphur amphur;

	@ManyToOne
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;

	@Column(name = "sur_tran", nullable = false)
	private Float surTran;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Amphur getAmphur() {
		return amphur;
	}

	public void setAmphur(Amphur amphur) {
		this.amphur = amphur;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Float getSurTran() {
		return surTran;
	}

	public void setSurTran(Float surTran) {
		this.surTran = surTran;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}