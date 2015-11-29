/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.bean.paging.CheckClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveyClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.CheckClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.PaySurveyClaimSearchResultVo;
import com.metasoft.ibilling.dao.AmphurDao;
import com.metasoft.ibilling.dao.BranchDao;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.ClaimLoadLogDao;
import com.metasoft.ibilling.dao.SubBranchDao;
import com.metasoft.ibilling.dao.SurveyEmployeeDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Amphur;
import com.metasoft.ibilling.model.AreaType;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.ClaimLoadLog;
import com.metasoft.ibilling.model.ClaimLoadLogErrorDetail;
import com.metasoft.ibilling.model.ClaimStatus;
import com.metasoft.ibilling.model.ClaimTp;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.DispatchType;
import com.metasoft.ibilling.model.ServiceType;
import com.metasoft.ibilling.model.SubBranch;
import com.metasoft.ibilling.model.SurveyEmployee;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.model.WorkTime;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.util.DateToolsUtil;
import com.metasoft.ibilling.util.NumberToolsUtil;
import com.metasoft.ibilling.ws.bean.json.ClaimRs;
import com.metasoft.ibilling.ws.bean.json.RptData;

@Service("claimService")
public class ClaimServiceImpl extends ModelBasedServiceImpl<ClaimDao, Claim, Integer> implements ClaimService {

	private ClaimDao claimDao;

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private SurveyEmployeeDao surveyEmployeeDao;

	@Autowired
	private ClaimLoadLogDao claimLoadLogDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SubBranchDao subBranchDao;

	@Autowired
	private AmphurDao amphurDao;

	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public ClaimServiceImpl(ClaimDao dao) {
		super(dao);
		this.claimDao = dao;
	}

	@Override
	public ClaimSearchResultVoPaging searchGroupClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selBranch,
			int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		Branch branch = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (selBranch != null && selBranch != 0) {
			branch = branchDao.findById(selBranch);
		}

		ClaimPaging claimPaging = claimDao.searchPaging(dispatchDateStart, dispatchDateEnd, branch, start, length);

		ClaimSearchResultVoPaging voPaging = new ClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<ClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				ClaimSearchResultVo vo = new ClaimSearchResultVo();
				vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
				vo.setClaimId(claim.getId().intValue());
				if (claim.getDispatchDate() != null) {
					vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
				}
				if (claim.getBranch() != null) {
					vo.setBranchName(claim.getBranch().getName());
				}

				vo.setSurInvest(NumberToolsUtil.nullToFloat(claim.getSurInvest()));
				vo.setSurTrans(NumberToolsUtil.nullToFloat(claim.getSurTrans()));
				vo.setSurDaily(NumberToolsUtil.nullToFloat(claim.getSurDaily()));
				vo.setSurPhoto(NumberToolsUtil.nullToFloat(claim.getSurPhoto()));
				vo.setSurClaim(NumberToolsUtil.nullToFloat(claim.getSurClaim()));
				vo.setSurTel(NumberToolsUtil.nullToFloat(claim.getSurTel()));
				vo.setSurInsure(NumberToolsUtil.nullToFloat(claim.getSurInsure()));
				vo.setSurTowcar(NumberToolsUtil.nullToFloat(claim.getSurTowcar()));
				vo.setSurOther(NumberToolsUtil.nullToFloat(claim.getSurOther()));

				vo.setSurTotal(calcTotalSur(claim));
				
				vo.setSurveyPrice(vo.getSurTotal());

				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}

	@Override
	public CheckClaimSearchResultVoPaging searchCheckClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd, String claimNo,
			Integer employeeId, Integer claimStatus, int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		SurveyEmployee surveyEmployee = null;
		ClaimStatus claimStatusEnum = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (employeeId != null && employeeId != 0) {
			surveyEmployee = surveyEmployeeDao.findById(employeeId);
		}

		if (claimStatus != null) {
			claimStatusEnum = ClaimStatus.getById(claimStatus);
		}

		ClaimPaging claimPaging = claimDao.searchCheckClaimPaging(dispatchDateStart, dispatchDateEnd, claimNo, surveyEmployee,
				claimStatusEnum, start, length);

		CheckClaimSearchResultVoPaging voPaging = new CheckClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<CheckClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				CheckClaimSearchResultVo vo = new CheckClaimSearchResultVo();
				vo.setClaimId(claim.getId().intValue());
				vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));

				if (claim.getSurveyEmployee() != null) {
					vo.setEmployeeCode(StringUtils.trimToEmpty(claim.getSurveyEmployee().getCode()));
				}

				vo.setCenter(StringUtils.trimToEmpty(claim.getCenter()));

				if (claim.getDispatchDate() != null) {
					vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
				}

				if (claim.getClaimType() != null) {
					vo.setClaimType(claim.getClaimType().getName());
				}

				vo.setSurveyTip(calcTotalSur(claim));
				
				vo.setSurveyEmp(calcTotalSurvey(claim));

				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}
	
	private float calcTotalSur(Claim claim){
		float surTotal = 
				NumberToolsUtil.nullToFloat(claim.getSurInvest()) + 
				NumberToolsUtil.nullToFloat(claim.getSurTrans()) + 
				NumberToolsUtil.nullToFloat(claim.getSurDaily()) + 
				NumberToolsUtil.nullToFloat(claim.getSurPhoto()) + 
				NumberToolsUtil.nullToFloat(claim.getSurClaim()) + 
				NumberToolsUtil.nullToFloat(claim.getSurTel()) + 
				NumberToolsUtil.nullToFloat(claim.getSurInsure()) + 
				NumberToolsUtil.nullToFloat(claim.getSurTowcar()) + 
				NumberToolsUtil.nullToFloat(claim.getSurOther());

			surTotal = (surTotal * (100 + NumberToolsUtil.nullToFloat(claim.getSurTax()))) / 100;
		return surTotal;
	}

	@Override
	public PaySurveyClaimSearchResultVoPaging searchPaySurveyClaimPaging(String txtDispatchDateStart, String txtDispatchDateEnd,
			Integer employeeId, int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		SurveyEmployee surveyEmployee = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (employeeId != null && employeeId != 0) {
			surveyEmployee = surveyEmployeeDao.findById(employeeId);
		}

		ClaimPaging claimPaging = claimDao.searchPaySurveyClaimPaging(dispatchDateStart, dispatchDateEnd, surveyEmployee, start, length);

		PaySurveyClaimSearchResultVoPaging voPaging = new PaySurveyClaimSearchResultVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<PaySurveyClaimSearchResultVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				PaySurveyClaimSearchResultVo vo = new PaySurveyClaimSearchResultVo();
				vo.setClaimId(claim.getId().intValue());
				vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
				if (claim.getDispatchDate() != null) {
					vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
				}

				vo.setSurveyTrans(NumberToolsUtil.nullToFloat(claim.getSurveyTrans()));
				vo.setSurveyDaily(NumberToolsUtil.nullToFloat(claim.getSurveyDaily()));
				vo.setSurveyPhoto(NumberToolsUtil.nullToFloat(claim.getSurveyPhoto()));
				vo.setSurveyClaim(NumberToolsUtil.nullToFloat(claim.getSurveyClaim()));
				vo.setSurveyTel(NumberToolsUtil.nullToFloat(claim.getSurveyTel()));
				vo.setSurveyFine(NumberToolsUtil.nullToFloat(claim.getSurveyFine()));
				vo.setSurveyOther(NumberToolsUtil.nullToFloat(claim.getSurveyOther()));

				vo.setSurveyTotal(calcTotalSurvey(claim));

				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}
	
	@Override
	public float calcTotalSurvey(Claim claim){
		return
				NumberToolsUtil.nullToFloat(claim.getSurveyInvest()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyTrans()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyDaily()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyPhoto()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyClaim()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyTel()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyConditionRight()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyOther()) - 
				NumberToolsUtil.nullToFloat(claim.getSurveyFine());
	}

	@Override
	public void loadClaimsFromWs(ClaimRs claimRs) {
		int totalSuccess = 0;
		int totalError = 0;
		List<ClaimLoadLogErrorDetail> claimLoadLogErrorDetails = new ArrayList<ClaimLoadLogErrorDetail>();
		ClaimLoadLog claimLoadLog = new ClaimLoadLog();
		for (RptData rptData : claimRs.getRptDatas()) {
			try {
				Claim claim = claimDao.findByClaimNo(rptData.getClaimNo());
				User admin = userDao.findById(1);
				if (claim != null) {
					claim.setUpdateDate(new Date());
					claim.setUpdateBy(admin);
				} else {
					claim = new Claim();
					claim.setCreateDate(new Date());
					claim.setCreateBy(admin);
				}

				PropertyDescriptor[] rptDataPD = BeanUtils.getPropertyDescriptors(RptData.class);
				Map<String, String> rptDataMap = new HashMap<String, String>();
				for (PropertyDescriptor propertyDescriptor : rptDataPD) {
					rptDataMap.put(propertyDescriptor.getName(), propertyDescriptor.getName());
				}

				PropertyDescriptor[] claimPD = BeanUtils.getPropertyDescriptors(Claim.class);
				List<String> claimPDs = new ArrayList<String>();
				for (PropertyDescriptor propertyDescriptor : claimPD) {
					if (rptDataMap.get(propertyDescriptor.getName()) == null) {
						claimPDs.add(propertyDescriptor.getName());
					}
				}

				BeanUtils.copyProperties(rptData, claim, claimPDs.toArray(new String[claimPDs.size()]));

				if (StringUtils.isNotBlank(rptData.getEmpcode())) {
					SurveyEmployee surveyEmployee = surveyEmployeeDao.findByCode(rptData.getEmpcode().trim());
					if (surveyEmployee == null) {
						surveyEmployee = new SurveyEmployee();
						surveyEmployee.setCode(rptData.getEmpcode());
						surveyEmployee.setFullname(rptData.getSurveyor());
						surveyEmployeeDao.save(surveyEmployee);
					}

					claim.setSurveyEmployee(surveyEmployee);
				}

				if (StringUtils.isNotBlank(rptData.getBranchCode())) {
					claim.setBranch(branchDao.findById(Integer.parseInt(rptData.getBranchCode())));
				}

				if (StringUtils.isNotBlank(rptData.getWrkTimeCode())) {
					for (WorkTime workTime : WorkTime.values()) {
						if (rptData.getWrkTimeCode().equalsIgnoreCase(workTime.getName())) {
							claim.setWrkTime(workTime);
						}
					}
				}

				if (StringUtils.isNotBlank(rptData.getServiceTypeCode())) {
					for (ServiceType serviceType : ServiceType.values()) {
						if (rptData.getServiceTypeCode().equalsIgnoreCase(serviceType.getName())) {
							claim.setServiceType(serviceType);
						}
					}
				}

				if (StringUtils.isNotBlank(rptData.getClaimTpCode())) {
					for (ClaimTp bean : ClaimTp.values()) {
						if (rptData.getClaimTpCode().equalsIgnoreCase(bean.getName())) {
							claim.setClaimTp(bean);
						}
					}
				}

				if (StringUtils.isNotBlank(rptData.getcStatusCode())) {
					for (ClaimStatus bean : ClaimStatus.values()) {
						if (rptData.getcStatusCode().equalsIgnoreCase(bean.getName())) {
							claim.setClaimStatus(bean);
						}
					}
				}

				if (StringUtils.isNotBlank(rptData.getClaimTypeCode())) {
					for (ClaimType bean : ClaimType.values()) {
						if (rptData.getClaimTypeCode().equalsIgnoreCase(bean.getName())) {
							claim.setClaimType(bean);
						}
					}
				}

				if (StringUtils.isNotBlank(rptData.getDispatchTypeCode())) {
					for (DispatchType bean : DispatchType.values()) {
						if (rptData.getDispatchTypeCode().equalsIgnoreCase(bean.getName())) {
							claim.setDispatchType(bean);
						}
					}
				}

				if (StringUtils.isNotBlank(rptData.getAccZoneCode())) {
					for (AreaType bean : AreaType.values()) {
						if (rptData.getAccZoneCode().equalsIgnoreCase(bean.getName())) {
							claim.setAreaType(bean);
						}
					}
				}

				if (claim.getClaimStatus() != null && (claim.getClaimStatus().getId() == 2 || claim.getClaimStatus().getId() == 3)) {
					calcEmployeeSurveyPrice(claim);
				}

				claimDao.saveOrUpdate(claim);
				totalSuccess++;
			} catch (Exception e) {	
				ClaimLoadLogErrorDetail claimLoadLogErrorDetail = new ClaimLoadLogErrorDetail();
				claimLoadLogErrorDetail.setClaimLoadLog(claimLoadLog);
				claimLoadLogErrorDetail.setRemark("Error ClaimNo = " + rptData.getClaimNo() + " : " + e.toString());

				claimLoadLogErrorDetails.add(claimLoadLogErrorDetail);
				totalError++;
			}
		}

		claimLoadLog.setClaimLoadLogErrorDetails(claimLoadLogErrorDetails);
		claimLoadLog.setTotalErrorData(totalError);
		claimLoadLog.setTotalInsertData(totalSuccess);
		claimLoadLog.setCreateDate(new Date());
		claimLoadLog.setWsSuccess(true);

		claimLoadLogDao.save(claimLoadLog);
		
		
	}

	private void calcEmployeeSurveyPrice(Claim claim) throws Exception {
		int accZone = claim.getAreaType() != null ? claim.getAreaType().getId() : -1;
		int serviceType = claim.getServiceType() != null ? claim.getServiceType().getId() : -1;
		int workTime = claim.getWrkTime() != null ? claim.getWrkTime().getId() : -1;
		int claimType = claim.getClaimType() != null ? claim.getClaimType().getId() : -1;
		int claimTp = claim.getClaimTp() != null ? claim.getClaimTp().getId() : -1;
		int policeRptNum = claim.getPoliceRptNum() != null ? claim.getPoliceRptNum(): 0;
		int photoNum = claim.getPhotoNum() != null ? claim.getPhotoNum(): 0;
		float surClaim = claim.getSurClaim() != null ? claim.getSurClaim(): 0;
		String empCode = claim.getSurveyEmployee() != null ? claim.getSurveyEmployee().getCode() : "";
		
		// 1. ค่าบริการ Survey_invest
		if (accZone == 0 || accZone == 1) {
			if (serviceType == 0) {
				claim.setSurveyInvest(100f);
			} else if (serviceType == 2 || serviceType == 1) {
				claim.setSurveyInvest(50f);
			} else
				claim.setSurveyInvest(0f);
		} else if (accZone == 2) {
			claim.setSurveyInvest(0f);
		}
		claim.setSurveyInvest(NumberToolsUtil.nullToFloat(claim.getSurveyInvest()));

		// 2. ค่าพาหนะ Survey_trans
		if(claim.getSurveyInvest() == 0){
		
			float surveyTrans = 0f;
			if (accZone == 0 || accZone == 1) {
				if (empCode.startsWith("L")) // ถ้าอักษรตัวแรกขึ้นต้นด้วย "L"
				{
					if (workTime == 1)
						surveyTrans = 400f;
					else
						surveyTrans = 300f;
				} else if (empCode.startsWith("D")) // ถ้าอักษรตัวแรกขึ้นต้นด้วย "D"
				{
					if (workTime == 1)
						surveyTrans = 300f;
					else
						surveyTrans = 200f;
				}
				// ******************************************
				if (claim.getCoArea()) {
					surveyTrans = surveyTrans + 100;
				}
	
				if (claim.getW7() != null && claim.getW7()) {
					surveyTrans = surveyTrans + 50;
				}
			} else if (accZone == 2) {
				if (serviceType == 3) {
					surveyTrans = 300f;
				} else {
					// คำนวนหาค่าพาหนะ จากrate ที่พนักงานศูนย์ที่พนักงานสังกัด ไปยัง
					// อำเภอที่ตรวจสอบ ***select sur_pay from sub_branch***
					if (StringUtils.isNotBlank(claim.getSurveyAmphur())) {
						Amphur surveyAmphur = amphurDao.findById(Integer.parseInt(claim.getSurveyAmphur()));
						if(surveyAmphur != null && claim.getBranch() != null){
							SubBranch subBranch = subBranchDao.findByAmphurAndBranch(surveyAmphur,claim.getBranch());
							if(subBranch != null){
								surveyTrans = subBranch.getSurPay();
							}
						}
					}
				}
				if (claim.getDisperse() != null && claim.getDisperse()) {
					surveyTrans = surveyTrans / 2;
				} else if (workTime == 1) {
					surveyTrans = surveyTrans + 100;
				} else if (claimType == 0) {
					surveyTrans = surveyTrans - 100;
				}
			}
			claim.setSurveyTrans(surveyTrans);
		}else{
			claim.setSurveyTrans(0f);
		}

		// 3. ค่าประจำวัน survey_daily
		if (accZone == 0 || accZone == 1) {
			if (policeRptNum == 1) {
				claim.setSurveyDaily(80f);
			} else if (policeRptNum == 2) {
				claim.setSurveyDaily(150f);
			} else if (policeRptNum == 3) {
				claim.setSurveyDaily(200f);
			} else if (policeRptNum == 4) {
				claim.setSurveyDaily(250f);
			} else if (policeRptNum > 4) {
				claim.setSurveyDaily(300f);
			}
		} else if (accZone == 2) {
			if (policeRptNum == 1) {
				claim.setSurveyDaily(80f);
			} else if (policeRptNum > 1) {
				claim.setSurveyDaily(150f);
			}
		}
		claim.setSurveyDaily(NumberToolsUtil.nullToFloat(claim.getSurveyDaily()));

		// 4. ค่ารูป survey_photo
		if (accZone == 0 || accZone == 1) {
			if (photoNum > 0) {
				claim.setSurveyPhoto(15f);
			}
		} else if (accZone == 2) {
			if (photoNum == 1) {
				claim.setSurveyPhoto(8f);
			} else if (photoNum > 1) {
				claim.setSurveyPhoto(15f);
			}
		}
		claim.setSurveyPhoto(NumberToolsUtil.nullToFloat(claim.getSurveyPhoto()));

		// 5. ค่าเรียกร้อง survey_claim
		claim.setSurveyClaim((float) (surClaim * 0.15)); // ค่าเรียกร้อง15%

		// 6. ค่าโทรศัพท์ survey_tel
		if (accZone == 0 || accZone == 1) {
			claim.setSurveyTel(0f);
		} else if (accZone == 2) {
			if (claimType == 0) {
				claim.setSurveyTel(0f);
			} else {
				claim.setSurveyTel(30f);
			}
		}
		claim.setSurveyTel(NumberToolsUtil.nullToFloat(claim.getSurveyTel()));

		// 7. ค่าเงื่อนไขฝายถูก survey_condition_right
		if (accZone == 0 || accZone == 1) {
			if (claimTp == 1) {
				claim.setSurveyConditionRight(100f);
			} else if (claimTp == 0) {
				claim.setSurveyConditionRight(30f);
			} else if (claimTp == 2) {
				claim.setSurveyConditionRight(0f);
			}
			claim.setSurveyConditionRight(NumberToolsUtil.nullToFloat(claim.getSurveyConditionRight()));
		}
	}
}
