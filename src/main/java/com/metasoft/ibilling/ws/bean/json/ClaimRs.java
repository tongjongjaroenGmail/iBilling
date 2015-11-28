package com.metasoft.ibilling.ws.bean.json;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClaimRs {
	@JsonProperty("SUCCESS")
	private int success;
	
	@JsonProperty("MESSAGE")
	private String message;
	
	@JsonProperty("RPT_DATA")
	private List<RptData> rptDatas;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<RptData> getRptDatas() {
		return rptDatas;
	}
	public void setRptDatas(List<RptData> rptDatas) {
		this.rptDatas = rptDatas;
	}


	
}
