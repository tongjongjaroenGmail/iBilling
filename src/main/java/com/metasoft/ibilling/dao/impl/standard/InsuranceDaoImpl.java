package com.metasoft.ibilling.dao.impl.standard;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.standard.InsuranceDao;
import com.metasoft.ibilling.model.StdInsurance;

@Repository("insuranceDao")
@Transactional
public class InsuranceDaoImpl extends AbstractDaoImpl<StdInsurance, Integer> implements InsuranceDao {
	public InsuranceDaoImpl() {
		super(StdInsurance.class);
	}
	
	@Override
	 public List<StdInsurance> findAllOrder()
	    {
		 	Criteria criteria = getCurrentSession().createCriteria(entityClass);
		 	criteria.addOrder(Order.asc("name"));
	        return criteria.list();
	    }

	@Override
	public StdInsurance findByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("name", name));
		if(criteria.list() != null && !criteria.list().isEmpty()){
			return (StdInsurance) criteria.list().get(0);
		}else{
			return null;
		}
        
	}
}
