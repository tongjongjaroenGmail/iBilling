/**
 * 
 */
package com.metasoft.ibilling.service.impl.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.dao.standard.InsuranceDao;
import com.metasoft.ibilling.model.StdInsurance;
import com.metasoft.ibilling.service.impl.ModelBasedServiceImpl;
import com.metasoft.ibilling.service.standard.InsuranceService;

@Service("insuranceService")
public class InsuranceServiceImpl extends ModelBasedServiceImpl<InsuranceDao, StdInsurance, Integer> implements InsuranceService
{
    private InsuranceDao insuranceDao;
    /**
     * 
     * @param entityClass
     */
    @Autowired
    public InsuranceServiceImpl(InsuranceDao dao)
    {
        super(dao);
        this.insuranceDao = dao;
    }
	
	
}
