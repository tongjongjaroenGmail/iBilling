/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.vo.ResultVo;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.InvoiceService;

@Service("invoiceService")
public class InvoiceServiceImpl extends ModelBasedServiceImpl<InvoiceDao, Invoice, Integer> implements InvoiceService {
	
	private InvoiceDao invoiceDao;
	
	@Autowired
	private ClaimDao claimDao;
	
	@Autowired
	private UserDao userDao;

	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public InvoiceServiceImpl(InvoiceDao dao) {
		super(dao);
		this.invoiceDao = dao;
	}

	@Override
	@Transactional
	public int save(String claimIds, String invoiceNo, int createBy) {
		User user = userDao.findById(createBy);
		
		Invoice invoice = new Invoice();
		invoice.setCode(invoiceNo);
		invoice.setCreateBy(user);
		invoice.setCreateDate(new Date());
		invoiceDao.save(invoice);
		
		String[] arrClaimIds = claimIds.split(",");
		for (String claimId : arrClaimIds) {
			Claim claim = claimDao.findById(Integer.parseInt(claimId.trim()));
			claim.setInvoice(invoice);
			claim.setUpdateBy(user);
			claim.setUpdateDate(new Date());
			claimDao.saveOrUpdate(claim);
		}
		
		return invoice.getId();
	}
}
