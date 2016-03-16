package com.metasoft.ibilling.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.ClaimLoadLogDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.AreaType;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.BranchDhip;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.ClaimStatus;
import com.metasoft.ibilling.model.SurveyEmployee;

@Repository("claimDao")
public class ClaimDaoImpl extends AbstractDaoImpl<Claim, Integer> implements ClaimDao {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ClaimLoadLogDao claimLoadLogDao;

	public ClaimDaoImpl() {
		super(Claim.class);
	}
	
	@Override
	public Claim findByClaimNo(String claimNo) {
		return (Claim) getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("claimNo", claimNo)).uniqueResult();
	}

	@Override
	public ClaimPaging searchPaging(Date dispatchDateStart,Date dispatchDateEnd,BranchDhip branchDhip,
			int start,int length) {
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
	

		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (dispatchDateStart != null && dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
		} else if (dispatchDateStart != null) {
			criteriaCount.add(Restrictions.ge("dispatchDate", dispatchDateStart));
		} else if (dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.le("dispatchDate", dispatchDateEnd));
		}

		if (branchDhip != null) {
			criteriaCount.add(Restrictions.eq("branchDhip", branchDhip));
		}
		
		criteriaCount.add(Restrictions.isNull("invoice"));
		criteriaCount.add(Restrictions.eq("claimStatus", ClaimStatus.approve));

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (dispatchDateStart != null && dispatchDateEnd != null) {
				criteria.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
			} else if (dispatchDateStart != null) {
				criteria.add(Restrictions.ge("dispatchDate", dispatchDateStart));
			} else if (dispatchDateEnd != null) {
				criteria.add(Restrictions.le("dispatchDate", dispatchDateEnd));
			}

			if (branchDhip != null) {
				criteria.add(Restrictions.eq("branchDhip", branchDhip));
			}
			
			criteria.add(Restrictions.isNull("invoice"));
			criteria.add(Restrictions.eq("claimStatus", ClaimStatus.approve));

			criteria.addOrder(Order.asc("branch"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<Claim>());
		}
		return resultPaging;
	}

	@Override
	public ClaimPaging searchCheckClaimPaging(Date dispatchDateStart, Date dispatchDateEnd, String claimNo, SurveyEmployee surveyEmployee,
			ClaimStatus claimStatus, int start, int length) {
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
	

		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (dispatchDateStart != null && dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
		} else if (dispatchDateStart != null) {
			criteriaCount.add(Restrictions.ge("dispatchDate", dispatchDateStart));
		} else if (dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.le("dispatchDate", dispatchDateEnd));
		}

		if (StringUtils.isNotBlank(claimNo)) {
			criteriaCount.add(Restrictions.ilike("claimNo", claimNo + "%"));
		}
		
		if (surveyEmployee != null) {
			criteriaCount.add(Restrictions.eq("surveyEmployee", surveyEmployee));
		}
		
		if (claimStatus != null) {
			criteriaCount.add(Restrictions.eq("claimStatus", claimStatus));
		}

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			
			if (dispatchDateStart != null && dispatchDateEnd != null) {
				criteria.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
			} else if (dispatchDateStart != null) {
				criteria.add(Restrictions.ge("dispatchDate", dispatchDateStart));
			} else if (dispatchDateEnd != null) {
				criteria.add(Restrictions.le("dispatchDate", dispatchDateEnd));
			}

			if (StringUtils.isNotBlank(claimNo)) {
				criteria.add(Restrictions.ilike("claimNo", claimNo + "%"));
			}
			
			if (surveyEmployee != null) {
				criteria.add(Restrictions.eq("surveyEmployee", surveyEmployee));
			}
			
			if (claimStatus != null) {
				criteria.add(Restrictions.eq("claimStatus", claimStatus));
			}

			criteria.addOrder(Order.asc("claimNo"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<Claim>());
		}
		return resultPaging;
	}

	@Override
	public ClaimPaging searchPaySurveyClaimPaging(Date dispatchDateStart, Date dispatchDateEnd, SurveyEmployee surveyEmployee, int start,
			int length) {
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
		
		criteriaRecordsTotal.add(Restrictions.isNull("paySurvey"));
		criteriaRecordsTotal.add(Restrictions.eq("claimStatus",ClaimStatus.closeCheck));
	
		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (dispatchDateStart != null && dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
		} else if (dispatchDateStart != null) {
			criteriaCount.add(Restrictions.ge("dispatchDate", dispatchDateStart));
		} else if (dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.le("dispatchDate", dispatchDateEnd));
		}

		if (surveyEmployee != null) {
			criteriaCount.add(Restrictions.eq("surveyEmployee", surveyEmployee));
		}
		
		criteriaCount.add(Restrictions.isNull("paySurvey"));
		criteriaCount.add(Restrictions.eq("claimStatus",ClaimStatus.closeCheck));

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			
			if (dispatchDateStart != null && dispatchDateEnd != null) {
				criteria.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
			} else if (dispatchDateStart != null) {
				criteria.add(Restrictions.ge("dispatchDate", dispatchDateStart));
			} else if (dispatchDateEnd != null) {
				criteria.add(Restrictions.le("dispatchDate", dispatchDateEnd));
			}

			if (surveyEmployee != null) {
				criteria.add(Restrictions.eq("surveyEmployee", surveyEmployee));
			}
			
			criteria.add(Restrictions.isNull("paySurvey"));
			criteria.add(Restrictions.eq("claimStatus",ClaimStatus.closeCheck));

			criteria.addOrder(Order.asc("claimNo"));
			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<Claim>());
		}
		return resultPaging;
	}

	@Override
	public ClaimPaging searchReportStatisticsSurveyPaging(Date dispatchDateStart, Date dispatchDateEnd, AreaType areaType, Branch branch,
			int start, int length) {
		ClaimPaging resultPaging = new ClaimPaging();

		Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(entityClass);
	

		criteriaRecordsTotal.setProjection(Projections.rowCount());
		resultPaging.setRecordsTotal((Long) criteriaRecordsTotal.uniqueResult());

		Criteria criteriaCount = getCurrentSession().createCriteria(entityClass);
		if (dispatchDateStart != null && dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
		} else if (dispatchDateStart != null) {
			criteriaCount.add(Restrictions.ge("dispatchDate", dispatchDateStart));
		} else if (dispatchDateEnd != null) {
			criteriaCount.add(Restrictions.le("dispatchDate", dispatchDateEnd));
		}

		if (branch != null) {
			criteriaCount.add(Restrictions.eq("branch", branch));
		}
		
		if (areaType != null) {
			criteriaCount.add(Restrictions.eq("areaType", areaType));
		}

		criteriaCount.setProjection(Projections.rowCount());
		resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

		if (resultPaging.getRecordsFiltered() != 0) {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			if (dispatchDateStart != null && dispatchDateEnd != null) {
				criteria.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
			} else if (dispatchDateStart != null) {
				criteria.add(Restrictions.ge("dispatchDate", dispatchDateStart));
			} else if (dispatchDateEnd != null) {
				criteria.add(Restrictions.le("dispatchDate", dispatchDateEnd));
			}

			if (branch != null) {
				criteria.add(Restrictions.eq("branch", branch));
			}
			
			if (areaType != null) {
				criteria.add(Restrictions.eq("areaType", areaType));
			}

			criteria.setFirstResult(start);
			criteria.setMaxResults(length);
			resultPaging.setData(criteria.list());
		}

		if (resultPaging.getData() == null) {
			resultPaging.setData(new ArrayList<Claim>());
		}
		return resultPaging;
	}
	
	@Override
	public List<Claim> searchReportStatisticsSurvey(Date dispatchDateStart, Date dispatchDateEnd, AreaType areaType, Branch branch) {
	
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (dispatchDateStart != null && dispatchDateEnd != null) {
			criteria.add(Restrictions.between("dispatchDate", dispatchDateStart, dispatchDateEnd));
		} else if (dispatchDateStart != null) {
			criteria.add(Restrictions.ge("dispatchDate", dispatchDateStart));
		} else if (dispatchDateEnd != null) {
			criteria.add(Restrictions.le("dispatchDate", dispatchDateEnd));
		}

		if (branch != null) {
			criteria.add(Restrictions.eq("branch", branch));
		}
		
		if (areaType != null) {
			criteria.add(Restrictions.eq("areaType", areaType));
		}

		return criteria.list();
	}

	@Override
	public Claim findByRefWsId(String refWsId) {
		return (Claim) getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("refWsId", refWsId)).uniqueResult();
	}
}
