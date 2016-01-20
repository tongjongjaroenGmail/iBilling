package com.metasoft.ibilling.service;

import com.metasoft.ibilling.bean.SaveResult;
import com.metasoft.ibilling.bean.paging.InvoiceSearchResultVoPaging;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.model.Invoice;

public interface InvoiceService extends ModelBasedService<InvoiceDao, Invoice, Integer> {
	public SaveResult save(String claimIds,String invoiceNo, int createBy);
	
	public InvoiceSearchResultVoPaging searchPaging(String txtCreateDateStart, String txtCreateDateEnd, String invoiceCode, int start,
			int length);
}
