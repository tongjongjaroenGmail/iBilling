package com.metasoft.ibilling.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.paging.InvoicePaging;
import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.InvoiceStatus;

@Repository("invoiceDao")
@Transactional
public class InvoiceDaoImpl extends AbstractDaoImpl<Invoice, Integer> implements InvoiceDao {
	
	public InvoiceDaoImpl() {
		super(Invoice.class);
	}
	
	@Override
	public InvoicePaging searchPaging(Date createDateStart,Date createDateEnd,String invoiceCode,
			int start,int length) {
		InvoicePaging resultPaging = new InvoicePaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
	

		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (createDateStart != null && createDateEnd != null) {
			criteriaCount.add(Restrictions.between("createDate", createDateStart, createDateEnd));
		} else if (createDateStart != null) {
			criteriaCount.add(Restrictions.ge("createDate", createDateStart));
		} else if (createDateEnd != null) {
			criteriaCount.add(Restrictions.le("createDate", createDateEnd));
		}

		if (StringUtils.isNotBlank(invoiceCode)) {
			criteriaCount.add(Restrictions.ilike("code", invoiceCode + "%"));
		}
		
		criteriaCount.add(Restrictions.eq("invoiceStatus",InvoiceStatus.active));

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (createDateStart != null && createDateEnd != null) {
				criteria.add(Restrictions.between("createDate", createDateStart, createDateEnd));
			} else if (createDateStart != null) {
				criteria.add(Restrictions.ge("createDate", createDateStart));
			} else if (createDateEnd != null) {
				criteria.add(Restrictions.le("createDate", createDateEnd));
			}

			if (StringUtils.isNotBlank(invoiceCode)) {
				criteria.add(Restrictions.ilike("code", invoiceCode + "%"));
			}
			
			criteria.add(Restrictions.eq("invoiceStatus",InvoiceStatus.active));

			criteria.addOrder(Order.asc("code"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<Invoice>());
		}
		return resultPaging;
	}
}
