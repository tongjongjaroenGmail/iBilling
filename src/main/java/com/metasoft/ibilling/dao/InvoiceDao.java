package com.metasoft.ibilling.dao;

import java.util.Date;

import com.metasoft.ibilling.bean.paging.InvoicePaging;
import com.metasoft.ibilling.model.Invoice;

public interface InvoiceDao extends AbstractDao<Invoice, Integer>{
	public InvoicePaging searchPaging(Date createDateStart,Date createDateEnd,String invoiceCode,
			int start,int length);
	
	public Invoice findByCode(String code);
}