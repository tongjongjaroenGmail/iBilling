/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.SaveResult;
import com.metasoft.ibilling.bean.paging.InvoicePaging;
import com.metasoft.ibilling.bean.paging.InvoiceSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.InvoiceSearchResultVo;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.InvoiceService;
import com.metasoft.ibilling.util.DateToolsUtil;

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
	public SaveResult save(String claimIds, String invoiceNo, int createBy) {
		SaveResult saveResult = new SaveResult();
		User user = userDao.findById(createBy);
		
		Invoice invoice = invoiceDao.findByCode(invoiceNo);
		
		if(invoice == null){
			invoice = new Invoice();
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
			
			saveResult.setSeccess(true);
			saveResult.setId(invoice.getId());
		}else{
			saveResult.setErrorDesc("เลขที่วางบิล เลขที่ " + invoiceNo  + " ซ้ำ");
		}

		return saveResult;
	}
	
	@Override
	public InvoiceSearchResultVoPaging searchPaging(String txtCreateDateStart, String txtCreateDateEnd, String invoiceCode,int start, int length) {	
		Date createDateStart = null;
		Date createDateEnd = null;

		if (StringUtils.isNotBlank(txtCreateDateStart)) {
			createDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtCreateDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtCreateDateEnd)) {
			createDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtCreateDateEnd, DateToolsUtil.LOCALE_TH);
		}
		
		InvoicePaging invoicePaging = invoiceDao.searchPaging(createDateStart, createDateEnd, invoiceCode, start, length);

		InvoiceSearchResultVoPaging voPaging = new InvoiceSearchResultVoPaging();
		voPaging.setDraw(invoicePaging.getDraw());
		voPaging.setRecordsFiltered(invoicePaging.getRecordsFiltered());
		voPaging.setRecordsTotal(invoicePaging.getRecordsTotal());
		voPaging.setData(new ArrayList<InvoiceSearchResultVo>());
		if (invoicePaging != null && invoicePaging.getData() != null) {
			for (Invoice invoice : invoicePaging.getData()) {
				InvoiceSearchResultVo vo = new InvoiceSearchResultVo();
				vo.setInvoiceCode(StringUtils.trimToEmpty(invoice.getCode()));
				vo.setInvoiceId(invoice.getId().intValue());
				if (invoice.getCreateDate() != null) {
					vo.setCreateDate(DateToolsUtil.convertToString(invoice.getCreateDate(), DateToolsUtil.LOCALE_TH));
				}
				float total = 0f;
				for (Claim claim : invoice.getClaims()) {
					float totalNoVat = ClaimServiceImpl.calcTotalSur(claim);
					total += totalNoVat + ClaimServiceImpl.calcVat(totalNoVat);
				}
				vo.setTotal(total);
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}
}
