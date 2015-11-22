package com.metasoft.ibilling.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.model.Invoice;

@Repository("invoiceDao")
@Transactional
public class InvoiceDaoImpl extends AbstractDaoImpl<Invoice, Integer> implements InvoiceDao {
	
	public InvoiceDaoImpl() {
		super(Invoice.class);
	}
}
