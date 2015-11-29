package com.metasoft.ibilling.bean.vo;

import java.util.ArrayList;
import java.util.List;

public class PaySurveyDetailVo {
	private int id;
	private String code = "";
	private String createDate = "";
	
	private List<ClaimSearchResultVo> claims = new ArrayList<ClaimSearchResultVo>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<ClaimSearchResultVo> getClaims() {
		return claims;
	}

	public void setClaims(List<ClaimSearchResultVo> claims) {
		this.claims = claims;
	}


}
