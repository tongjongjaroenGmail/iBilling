package com.metasoft.ibilling.bean.vo;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailVo {
	private int invoiceId;
	private String invoiceCode = "";
	private String invoiceCreateDate = "";
	
	private List<ClaimSearchResultVo> claims = new ArrayList<ClaimSearchResultVo>();

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceCreateDate() {
		return invoiceCreateDate;
	}

	public void setInvoiceCreateDate(String invoiceCreateDate) {
		this.invoiceCreateDate = invoiceCreateDate;
	}

	public List<ClaimSearchResultVo> getClaims() {
		return claims;
	}

	public void setClaims(List<ClaimSearchResultVo> claims) {
		this.claims = claims;
	}
}
