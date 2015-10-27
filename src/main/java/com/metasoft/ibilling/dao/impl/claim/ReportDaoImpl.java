package com.metasoft.ibilling.dao.impl.claim;

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
import org.springframework.transaction.annotation.Transactional;

import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ReportPaging;
import com.metasoft.ibilling.controller.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.dao.AbstractDaoImpl;
import com.metasoft.ibilling.dao.claim.ClaimDao;
import com.metasoft.ibilling.dao.claim.ReportDao;
import com.metasoft.ibilling.dao.security.UserDao;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.JobStatus;
import com.metasoft.ibilling.model.SecUser;
import com.metasoft.ibilling.model.StdInsurance;
import com.metasoft.ibilling.model.TblClaimRecovery;

@Repository("reportDao")
@Transactional
public class ReportDaoImpl extends AbstractDaoImpl<TblClaimRecovery, Integer>
		implements ReportDao {
	@Autowired
	private UserDao userDao;

	public ReportDaoImpl() {
		super(TblClaimRecovery.class);
	}

	@Override
	public ReportPaging searchPaging(Date jobDateStart, Date jobDateEnd,
			StdInsurance partyInsurance, ClaimType claimType, int start,
			int length, String pageName) {
		System.out.println(">>>>>> pageName = " + pageName);

		ReportPaging resultPaging = new ReportPaging();
		try {
			Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(
					entityClass);

			criteriaRecordsTotal.setProjection(Projections.rowCount());
			resultPaging.setRecordsTotal((Long) criteriaRecordsTotal
					.uniqueResult());

			Criteria criteriaCount = getCurrentSession().createCriteria(
					entityClass);

			if (partyInsurance != null) {
				criteriaCount.add(Restrictions.eq("partyInsurance",
						partyInsurance));
			}

			if (claimType != null) {
				criteriaCount.add(Restrictions.eq("claimType", claimType));
			}

			if (pageName.equals("tracking")) {
				if (jobDateStart != null && jobDateEnd != null) {
					criteriaCount.add(Restrictions.between("followDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteriaCount.add(Restrictions.ge("followDate",
							jobDateStart));
				} else if (jobDateEnd != null) {
					criteriaCount
							.add(Restrictions.le("followDate", jobDateEnd));
				} 
				 criteriaCount.add(Restrictions.eq("jobStatus", JobStatus.FOLLOWED));
			}
			if (pageName.equals("billing")) {
				System.out.println(">>>>>> billing");
				if (jobDateStart != null && jobDateEnd != null) {
					criteriaCount.add(Restrictions.between("closeDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteriaCount.add(Restrictions
							.ge("closeDate", jobDateStart));
				} else if (jobDateEnd != null) {
					criteriaCount.add(Restrictions.le("closeDate", jobDateEnd));
				}
				criteriaCount.add(Restrictions.eq("jobStatus",JobStatus.CLOSED));
			}

			criteriaCount.setProjection(Projections.rowCount());
			resultPaging
					.setRecordsFiltered((Long) criteriaCount.uniqueResult());

			if (resultPaging.getRecordsFiltered() != 0) {
				Criteria criteria = getCurrentSession().createCriteria(
						entityClass);
				if (partyInsurance != null) {
					criteria.add(Restrictions.eq("partyInsurance",
							partyInsurance));
				}

				if (claimType != null) {
					criteria.add(Restrictions.eq("claimType", claimType));
				}

				if (pageName.equals("tracking")) {
					if (jobDateStart != null && jobDateEnd != null) {
						criteria.add(Restrictions.between("followDate",
								jobDateStart, jobDateEnd));
					} else if (jobDateStart != null) {
						criteria.add(Restrictions
								.ge("followDate", jobDateStart));
					} else if (jobDateEnd != null) {
						criteria.add(Restrictions.le("followDate", jobDateEnd));
					}
					criteria.add(Restrictions.eq("jobStatus", JobStatus.FOLLOWED));
				}
				if (pageName.equals("billing")) {
					if (jobDateStart != null && jobDateEnd != null) {
						criteria.add(Restrictions.between("closeDate",
								jobDateStart, jobDateEnd));
					} else if (jobDateStart != null) {
						criteria.add(Restrictions.ge("closeDate", jobDateStart));
					} else if (jobDateEnd != null) {
						criteria.add(Restrictions.le("closeDate", jobDateEnd));
					}
					criteria.add(Restrictions.eq("jobStatus", JobStatus.CLOSED));
				}

				criteria.setFirstResult(start);
				criteria.setMaxResults(length);
				resultPaging.setData(criteria.list());
			}

			if (resultPaging.getData() == null) {
				resultPaging.setData(new ArrayList<TblClaimRecovery>());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultPaging;
	}

	@Override
	public ReportPaging searchPaging(Date jobDateStart, Date jobDateEnd,
			SecUser agent, ClaimType claimType, int start, int length) {
		System.out.println(">>>>> labor");
		ReportPaging resultPaging = new ReportPaging();
		
			Criteria criteriaRecordsTotal = getCurrentSession().createCriteria(
					entityClass);

			criteriaRecordsTotal.setProjection(Projections.rowCount());
			resultPaging.setRecordsTotal((Long) criteriaRecordsTotal
					.uniqueResult());

			Criteria criteriaCount = getCurrentSession().createCriteria(
					entityClass);

			if (agent != null) {
				criteriaCount.add(Restrictions.eq("agent",agent));
				System.out.println(">>>> agent"+agent);
			}

			if (claimType != null) {
				criteriaCount.add(Restrictions.eq("claimType", claimType));
			}

			
			if (jobDateStart != null && jobDateEnd != null) {
					criteriaCount.add(Restrictions.between("closeDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteriaCount.add(Restrictions.ge("closeDate",
							jobDateStart));
				} else if (jobDateEnd != null) {
					criteriaCount
							.add(Restrictions.le("closeDate", jobDateEnd));
				
			}
			
			criteriaCount.setProjection(Projections.rowCount());
			resultPaging.setRecordsFiltered((Long) criteriaCount.uniqueResult());

			if (resultPaging.getRecordsFiltered() != 0) {
				Criteria criteria = getCurrentSession().createCriteria(entityClass);
				if (agent != null) {
					criteria.add(Restrictions.eq("agent",agent));
				}

				if (claimType != null) {
					criteria.add(Restrictions.eq("claimType", claimType));
				}

				if (jobDateStart != null && jobDateEnd != null) {
						criteria.add(Restrictions.between("closeDate",jobDateStart, jobDateEnd));
					} else if (jobDateStart != null) {
						criteria.add(Restrictions.ge("closeDate", jobDateStart));
					} else if (jobDateEnd != null) {
						criteria.add(Restrictions.le("closeDate", jobDateEnd));
					}
					criteria.add(Restrictions.eq("jobStatus", JobStatus.CLOSED));
				

				criteria.setFirstResult(start);
				criteria.setMaxResults(length);
				resultPaging.setData(criteria.list());
				}

			if (resultPaging.getData() == null) {
				resultPaging.setData(new ArrayList<TblClaimRecovery>());
			}

		
		return resultPaging;
		
	}

	@Override
	public ReportPaging searchPaging(Date jobDateStart, Date jobDateEnd,
			int agent, ClaimType claimType) {
		
		return null;
	}

	@Override
	public List<TblClaimRecovery> searchExport(Date jobDateStart,
			Date jobDateEnd, int agent, ClaimType claimType) {
		
		List<TblClaimRecovery> results = new ArrayList<TblClaimRecovery>();

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		if (agent != 0) {
			criteria.add(Restrictions.eq("agent",agent));}

		if (claimType != null) {
			criteria.add(Restrictions.eq("claimType", claimType));
		}

		if (jobDateStart != null && jobDateEnd != null) {
				criteria.add(Restrictions.between("closeDate",
						jobDateStart, jobDateEnd));
			} else if (jobDateStart != null) {
				criteria.add(Restrictions.ge("closeDate", jobDateStart));
			} else if (jobDateEnd != null) {
				criteria.add(Restrictions.le("closeDate", jobDateEnd));
			}
			criteria.add(Restrictions.eq("jobStatus", JobStatus.CLOSED));
		

			results = criteria.list();
			if (results == null) {
				results = new ArrayList<TblClaimRecovery>();
			}
			return results;

	}

	@Override
	public List<TblClaimRecovery> searchExportTracking(Date jobDateStart,
			Date jobDateEnd, StdInsurance partyInsurance, ClaimType claimType,
			String pageName) {
		List<TblClaimRecovery> results = new ArrayList<TblClaimRecovery>();

		Criteria criteria = getCurrentSession().createCriteria(entityClass);

			if (partyInsurance != null) {
				criteria.add(Restrictions.eq("partyInsurance",
						partyInsurance));
			}

			if (claimType != null) {
				criteria.add(Restrictions.eq("claimType", claimType));
			}

			if (pageName.equals("tracking")) {
				if (jobDateStart != null && jobDateEnd != null) {
					criteria.add(Restrictions.between("followDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteria.add(Restrictions
							.ge("followDate", jobDateStart));
				} else if (jobDateEnd != null) {
					criteria.add(Restrictions.le("followDate", jobDateEnd));
				}
				criteria.add(Restrictions.eq("jobStatus", JobStatus.FOLLOWED));
				
			} else if (pageName.equals("billing")) {
				if (jobDateStart != null && jobDateEnd != null) {
					criteria.add(Restrictions.between("closeDate",
							jobDateStart, jobDateEnd));
				} else if (jobDateStart != null) {
					criteria.add(Restrictions.ge("closeDate", jobDateStart));
				} else if (jobDateEnd != null) {
					criteria.add(Restrictions.le("closeDate", jobDateEnd));
				}
				criteria.add(Restrictions.eq("jobStatus", JobStatus.CLOSED));
			}
			results = criteria.list();
			if (results == null) {
				results = new ArrayList<TblClaimRecovery>();
			}
			return results;
		}

}
