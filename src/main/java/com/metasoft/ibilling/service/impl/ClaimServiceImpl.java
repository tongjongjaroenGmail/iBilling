/**
 * 
 */
package com.metasoft.ibilling.service.impl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metasoft.ibilling.bean.paging.CheckClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ClaimPaging;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.InvoiceReportVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveyClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ReportStatisticsSurveyVoPaging;
import com.metasoft.ibilling.bean.vo.CheckClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.InvoiceReportVo;
import com.metasoft.ibilling.bean.vo.PaySurveyClaimSearchResultVo;
import com.metasoft.ibilling.constant.SystemConstant;
import com.metasoft.ibilling.controller.vo.ReportStatisticsSurveyVo;
import com.metasoft.ibilling.dao.AmphurDao;
import com.metasoft.ibilling.dao.BranchDao;
import com.metasoft.ibilling.dao.BranchDhipDao;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.ClaimLoadLogDao;
import com.metasoft.ibilling.dao.ProvinceDao;
import com.metasoft.ibilling.dao.SubBranchDao;
import com.metasoft.ibilling.dao.SurveyEmployeeDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Amphur;
import com.metasoft.ibilling.model.AreaType;
import com.metasoft.ibilling.model.Branch;
import com.metasoft.ibilling.model.BranchDhip;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.ClaimLoadLog;
import com.metasoft.ibilling.model.ClaimLoadLogErrorDetail;
import com.metasoft.ibilling.model.ClaimStatus;
import com.metasoft.ibilling.model.ClaimTp;
import com.metasoft.ibilling.model.ClaimType;
import com.metasoft.ibilling.model.DispatchType;
import com.metasoft.ibilling.model.Province;
import com.metasoft.ibilling.model.ServiceType;
import com.metasoft.ibilling.model.SubBranch;
import com.metasoft.ibilling.model.SurveyEmployee;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.model.WorkTime;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.util.DateToolsUtil;
import com.metasoft.ibilling.util.NumberToolsUtil;
import com.metasoft.ibilling.ws.bean.dprptdata.DpRptDataLocator;
import com.metasoft.ibilling.ws.bean.dprptdata.DpRptDataPortType;
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
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private BranchDhipDao branchDhipDao;

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
			String claimStatus,int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		BranchDhip branchDhip = null;
		List<ClaimStatus> claimStatusList = new ArrayList<ClaimStatus>();
		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (selBranch != null && selBranch != 0) {
			branchDhip = branchDhipDao.findById(selBranch);
		}
		
		if(StringUtils.isNotBlank(claimStatus)){
			String[] claimStatusArray = claimStatus.split(",");
			for (String claimStatusStr : claimStatusArray) {
				claimStatusList.add(ClaimStatus.getById(Integer.parseInt(claimStatusStr.trim())));
			}
 		}

		ClaimPaging claimPaging = claimDao.searchPaging(dispatchDateStart, dispatchDateEnd, branchDhip,claimStatusList, start, length);

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
				if (claim.getBranchDhip() != null) {
					vo.setBranchName(claim.getBranchDhip().getName());
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
				vo.setSurTax(calcVat(vo.getSurTotal()));
				
				vo.setSurveyPrice(vo.getSurTotal() + vo.getSurTax());

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

				if(claim.getBranch() != null){
					vo.setCenter(StringUtils.trimToEmpty(claim.getBranch().getName()));
				}

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
	
	public static float calcTotalSur(Claim claim){
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
		return surTotal;
	}
	
	public static float calcTotalIns(Claim claim){
		float insTotal = NumberToolsUtil.nullToFloat(claim.getInsInvest())  + 
				NumberToolsUtil.nullToFloat(claim.getInsTrans()) + 
				NumberToolsUtil.nullToFloat(claim.getInsDaily()) + 
				NumberToolsUtil.nullToFloat(claim.getInsPhoto()) + 
				NumberToolsUtil.nullToFloat(claim.getInsClaim()) + 
				NumberToolsUtil.nullToFloat(claim.getInsTel()) + 
				NumberToolsUtil.nullToFloat(claim.getInsInsure()) + 
				NumberToolsUtil.nullToFloat(claim.getInsTowcar()) + 
				NumberToolsUtil.nullToFloat(claim.getInsOther());
		return insTotal;
	}
	
	public static float calcTotalSurvey(Claim claim){
		float surTotal = NumberToolsUtil.nullToFloat(claim.getSurveyInvest()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyTrans()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyDaily()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyPhoto()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyClaim()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyTel()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyConditionRight()) + 
				NumberToolsUtil.nullToFloat(claim.getSurveyOther()) - 
				NumberToolsUtil.nullToFloat(claim.getSurveyFine());
		return surTotal;
	}
	
	public static float calcVat(float total){
		return total * SystemConstant.VAT;
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
	public void loadClaimsFromWs(Date loadDate) {
		boolean isInsert = false;
		boolean isUpdate = false;
		int totalInsertSuccess = 0;
		int totalUpdateSuccess = 0;
		int totalError = 0;
		List<ClaimLoadLogErrorDetail> claimLoadLogErrorDetails = new ArrayList<ClaimLoadLogErrorDetail>();
		ClaimLoadLog claimLoadLog = new ClaimLoadLog();
		claimLoadLog.setWsSuccess(true);
		
		DpRptDataLocator dpRptData = new DpRptDataLocator();
		DpRptDataPortType dpRptDataPortType = null;
		ClaimRs claimRs = null;
		String rptDatastr = "";
		try {
			if(loadDate == null){
				Date today = new Date(); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.DATE, -1);
				loadDate = cal.getTime();
			}
			dpRptDataPortType = dpRptData.getdpRptDataPort();
			rptDatastr = dpRptDataPortType.getRptData("", "", DateToolsUtil.convertToString(loadDate, "yyyy-MM-dd", DateToolsUtil.LOCALE_EN));
			claimRs = new ObjectMapper().readValue(rptDatastr, ClaimRs.class);
		} catch (Exception e1) {
			ClaimLoadLogErrorDetail claimLoadLogErrorDetail = new ClaimLoadLogErrorDetail();
			claimLoadLogErrorDetail.setClaimLoadLog(claimLoadLog);
			claimLoadLogErrorDetail.setRemark("web service error getRptData method : " + e1.toString());

			claimLoadLogErrorDetails.add(claimLoadLogErrorDetail);
			claimLoadLog.setWsSuccess(false);
		}
		if(claimLoadLog.isWsSuccess()){
			for (RptData rptData : claimRs.getRptDatas()) {
				isInsert = false;
				isUpdate = false;
				try {
					Claim claim = claimDao.findByRefWsId(rptData.getRefWsId());
					User admin = userDao.findById(1);
					if (claim != null) {
						claim.setUpdateDate(new Date());
						claim.setUpdateBy(admin);
						
						isUpdate = true;
					} else {
						claim = new Claim();
						claim.setCreateDate(new Date());
						claim.setCreateBy(admin);
						
						isInsert = true;
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
						
					Branch branch = null;
					if (StringUtils.isNotBlank(rptData.getCenter())) {
						branch = branchDao.findByName(rptData.getCenter().trim());
					}
					claim.setBranch(branch);
					
					if (StringUtils.isNotBlank(rptData.getSurveyAmphurId())) {
						Amphur surveyAmphur = amphurDao.findById(Integer.parseInt(rptData.getSurveyAmphurId().trim()));
						claim.setSurveyAmphur(surveyAmphur);
					}
					
					if (StringUtils.isNotBlank(rptData.getSurveyProvinceId())) {
						Province surveyProvince = provinceDao.findById(Integer.parseInt(rptData.getSurveyProvinceId().trim()));
						claim.setSurveyProvince(surveyProvince);
					}
					
					if (StringUtils.isNotBlank(rptData.getEmpcode())) {
						SurveyEmployee surveyEmployee = surveyEmployeeDao.findByCodeAndName(rptData.getEmpcode().trim(),rptData.getSurveyor().trim());
						if (surveyEmployee == null) {
							surveyEmployee = new SurveyEmployee();
							surveyEmployee.setCode(rptData.getEmpcode());
							surveyEmployee.setFullname(rptData.getSurveyor());	
						}
						surveyEmployee.setBranch(branch);
						surveyEmployeeDao.saveOrUpdate(surveyEmployee);
						
						claim.setSurveyEmployee(surveyEmployee);
					}

					if (StringUtils.isNotBlank(rptData.getBranchCode())) {
						claim.setBranchDhip(branchDhipDao.findByName(rptData.getBranchCode().trim()));
					}
					
					if(claim.getBranchDhip() == null){
						throw new Exception("BranchDhip is null.");
					}
	
					if (StringUtils.isNotBlank(rptData.getWrkTimeCode())) {
						for (WorkTime workTime : WorkTime.values()) {
							if (rptData.getWrkTimeCode().trim().equalsIgnoreCase(workTime.getName())) {
								claim.setWrkTime(workTime);
							}
						}
					}
	
					if (StringUtils.isNotBlank(rptData.getServiceTypeCode())) {
						for (ServiceType serviceType : ServiceType.values()) {
							if (rptData.getServiceTypeCode().trim().equalsIgnoreCase(serviceType.getName())) {
								claim.setServiceType(serviceType);
							}
						}
					}
	
					if (StringUtils.isNotBlank(rptData.getClaimTpCode())) {
						for (ClaimTp bean : ClaimTp.values()) {
							if (rptData.getClaimTpCode().trim().equalsIgnoreCase(bean.getName())) {
								claim.setClaimTp(bean);
							}
						}
					}
	
					if (StringUtils.isNotBlank(rptData.getcStatusCode())) {
						for (ClaimStatus bean : ClaimStatus.values()) {
							if (rptData.getcStatusCode().trim().equalsIgnoreCase(bean.getName())) {
								claim.setClaimStatus(bean);
							}
						}
					}
					
					// ข้อมูลจาก loxbit
					// ประเภทเคลม (เคลมสด,เคลมแห้ง,ติดตาม) 01,02,03	
//					--------------
//					follow(0, "ติดตาม"),
//					party(1, "เคลมสด"),
//					noParty(2, "เคลมแห้ง");
					if (StringUtils.isNotBlank(rptData.getClaimTypeCode())) {
						if("01".equals(rptData.getClaimTypeCode().trim())){
							claim.setClaimType(ClaimType.getById(1));	
						}else if("02".equals(rptData.getClaimTypeCode().trim())){
							claim.setClaimType(ClaimType.getById(2));	
						}else if("03".equals(rptData.getClaimTypeCode().trim())){
							claim.setClaimType(ClaimType.getById(0));	
						}								
					}
	
					// ข้อมูลจาก loxbit
					// ประเภทการจ่าย (ว.4,นัดหมาย)
//					--------------
//					w4(0, "ว.4"),
//					meet(1, "นัดหมาย");
					if (StringUtils.isNotBlank(rptData.getDispatchTypeCode())) {
						if("01".equals(rptData.getDispatchTypeCode().trim())){
							claim.setDispatchType(DispatchType.getById(0));	
						}else if("02".equals(rptData.getDispatchTypeCode().trim())){
							claim.setDispatchType(DispatchType.getById(1));	
						}
					}
	
					if (StringUtils.isNotBlank(rptData.getAccZoneCode())) {
						for (AreaType bean : AreaType.values()) {
							if (rptData.getAccZoneCode().trim().equalsIgnoreCase(bean.getName())) {
								claim.setAreaType(bean);
							}
						}
					}
	
					if (claim.getClaimStatus() != null && (claim.getClaimStatus().getId() == 2 || claim.getClaimStatus().getId() == 3 || claim.getClaimStatus().getId() == 5)) {
						calcEmployeeSurveyPrice(claim);
					}
	
					claimDao.saveOrUpdate(claim);
					if(isInsert){
						totalInsertSuccess++;
					}else if(isUpdate){
						totalUpdateSuccess++;
					}

					dpRptDataPortType.confirmReceiveRpt("", "", claim.getRefWsId(), claim.getClaimNo());
				} catch (Exception e) {	
					ClaimLoadLogErrorDetail claimLoadLogErrorDetail = new ClaimLoadLogErrorDetail();
					claimLoadLogErrorDetail.setClaimLoadLog(claimLoadLog);
					claimLoadLogErrorDetail.setRemark("Error ClaimNo = " + rptData.getClaimNo() + " : " + e.toString());
	
					claimLoadLogErrorDetails.add(claimLoadLogErrorDetail);
					totalError++;
				}
			}
		}

		claimLoadLog.setClaimLoadLogErrorDetails(claimLoadLogErrorDetails);
		claimLoadLog.setTotalErrorData(totalError);
		claimLoadLog.setTotalInsertData(totalInsertSuccess);
		claimLoadLog.setTotalUpdateData(totalUpdateSuccess);
		claimLoadLog.setEndDate(new Date());

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
		float surveyInvest = 0;
		if (accZone == 0 || accZone == 1) {
			if (serviceType == 0) {
				surveyInvest = 100f;
			} else if (serviceType == 2 || serviceType == 1) {
				surveyInvest =50f;
			} else
				surveyInvest =0f;
		} else if (accZone == 2) {
			surveyInvest =0f;
		}
		claim.setSurveyInvest(surveyInvest);

		// 2. ค่าพาหนะ Survey_trans
		if(claim.getSurveyInvest() == 0){
		
			float surveyTrans = 0f;
			if (accZone == 0 || accZone == 1) {
				if (empCode.startsWith("L") || empCode.startsWith("l")) // ถ้าอักษรตัวแรกขึ้นต้นด้วย "L"
				{
					if (workTime == 1)
						surveyTrans = 400f;
					else
						surveyTrans = 300f;
				} else if (empCode.startsWith("D") || empCode.startsWith("d")) // ถ้าอักษรตัวแรกขึ้นต้นด้วย "D"
				{
					if (workTime == 1)
						surveyTrans = 300f;
					else
						surveyTrans = 200f;
				}
				// ******************************************
				if (claim.getCoArea() != null && claim.getCoArea()) {
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
					if (claim.getSurveyAmphur() != null) {
						Amphur surveyAmphur = claim.getSurveyAmphur();
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
				} else if (claimType == 0) {
					surveyTrans = surveyTrans - 100;
				} else if (workTime == 1) {
					surveyTrans = surveyTrans + 100;
				}
			}
			surveyTrans = surveyTrans < 0?0:surveyTrans;
			claim.setSurveyTrans(surveyTrans);
		}else{
			claim.setSurveyTrans(0f);
		}

		// 3. ค่าประจำวัน survey_daily
		float surveyDaily = 0f;
		if (accZone == 0 || accZone == 1) {
			if (policeRptNum == 1) {
				surveyDaily =80f;
			} else if (policeRptNum == 2) {
				surveyDaily =150f;
			} else if (policeRptNum == 3) {
				surveyDaily =200f;
			} else if (policeRptNum == 4) {
				surveyDaily =250f;
			} else if (policeRptNum > 4) {
				surveyDaily =300f;
			}
		} else if (accZone == 2) {
			if (policeRptNum == 1) {
				surveyDaily =80f;
			} else if (policeRptNum > 1) {
				surveyDaily =150f;
			}
		}
		claim.setSurveyDaily(surveyDaily);

		// 4. ค่ารูป survey_photo
		float surveyPhoto = 0f;
		if (accZone == 0 || accZone == 1) {
			if (photoNum > 0) {
				surveyPhoto = 15f;
			}
		} else if (accZone == 2) {
			if (photoNum == 1) {
				surveyPhoto = 8f;
			} else if (photoNum > 1) {
				surveyPhoto = 15f;
			}
		}
		claim.setSurveyPhoto(surveyPhoto);

		// 5. ค่าเรียกร้อง survey_claim
		claim.setSurveyClaim((float) (surClaim * 5 * 0.15)); // ค่าเรียกร้อง15%

		// 6. ค่าโทรศัพท์ survey_tel
		float surveyTel = 0f;
		if (accZone == 0 || accZone == 1) {
			surveyTel = 0f;
		} else if (accZone == 2) {
			if (claimType == 0) {
				surveyTel = 0f;
			} else {
				surveyTel = 30f;
			}
		}
		claim.setSurveyTel(surveyTel);

		// 7. ค่าเงื่อนไขฝายถูก survey_condition_right
		float surveyConditionRight = 0f;
		if (accZone == 0 || accZone == 1) {
			if (claimTp == 1) {
				surveyConditionRight = 100f;
			} else if (claimTp == 0) {
				surveyConditionRight = 30f;
			} else if (claimTp == 2) {
				surveyConditionRight = 0f;
			}
			claim.setSurveyConditionRight(surveyConditionRight);
		}
	}

	private ReportStatisticsSurveyVo setReportStatisticsSurveyVo(Claim claim){
		ReportStatisticsSurveyVo vo = new ReportStatisticsSurveyVo();
		vo.setClaimId(claim.getId());
		vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
		if (claim.getDispatchDate() != null) {
			vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
		}
		
		if(claim.getSurveyEmployee() != null){
			vo.setEmployeeName(StringUtils.trimToEmpty(claim.getSurveyEmployee().getFullname()));
			vo.setEmployeeCode(StringUtils.trimToEmpty(claim.getSurveyEmployee().getCode()));
		}

		if (claim.getBranch() != null) {
			vo.setBranchName(claim.getBranch().getName());
		}
		
		vo.setPolicyType(StringUtils.trimToEmpty(claim.getPolicyType()));
		
		if (claim.getHasTp() != null && claim.getHasTp()) {
			vo.setHasTp("มี");
		}else{
			vo.setHasTp("ไม่มี");
		}

		vo.setNotiResult(StringUtils.trimToEmpty(claim.getNotiResult()));
		vo.setAccResult(StringUtils.trimToEmpty(claim.getAccResult()));
		
		if (claim.getTpVeh() != null && claim.getTpVeh()) {
			vo.setTpVeh("มี");
		}else{
			vo.setTpVeh("ไม่มี");
		}
		
		vo.setTpType(StringUtils.trimToEmpty(claim.getTpType()));
		
		if (claim.getDisperse() != null && claim.getDisperse()) {
			vo.setDisperse("Y");
		}else{
			vo.setDisperse("N");
		}

		if (claim.getWrkTime() != null) {
			vo.setWrkTime(claim.getWrkTime().getName());
		}
		
		vo.setSurClaim(NumberToolsUtil.nullToFloat(claim.getSurClaim()));
		
		if (claim.getClaimType() != null) {
			vo.setClaimType(claim.getClaimType().getName());
		}
		
		if (claim.getAreaType() != null) {
			vo.setAccZone(claim.getAreaType().getName());
		}
		
		if (claim.getClaimStatus() != null) {
			vo.setClaimStatus(claim.getClaimStatus().getName());
		}
		
		if (claim.getReviewBy() != null) {
			vo.setReviewBy(claim.getReviewBy());
		}
		
		if (claim.getApproveBy() != null) {
			vo.setApproveBy(claim.getApproveBy());
		}	

		float surTotalNoTax = ClaimServiceImpl.calcTotalSur(claim);
		vo.setSurTotalWithTax(surTotalNoTax + ClaimServiceImpl.calcVat(surTotalNoTax));
		float insTotalNoTax = ClaimServiceImpl.calcTotalIns(claim);
		vo.setInsTotalWithTax(insTotalNoTax + ClaimServiceImpl.calcVat(insTotalNoTax));
		vo.setSurveyTotal(ClaimServiceImpl.calcTotalSurvey(claim));
		
		return vo;
	}
	
	@Override
	public ReportStatisticsSurveyVoPaging searchReportStatisticsSurveyPaging(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selAreaType,
			Integer selBranch,Integer selClaimStatus, int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		Branch branch = null;
		AreaType areaType = null;
		ClaimStatus claimStatus = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (selBranch != null && selBranch != 0) {
			branch = branchDao.findById(selBranch);
		}
		
		if(selAreaType != null) {
			areaType = AreaType.getById(selAreaType);
		}
		
		if(selClaimStatus != null) {
			claimStatus = ClaimStatus.getById(selClaimStatus);
		}

		ClaimPaging claimPaging = claimDao.searchReportStatisticsSurveyPaging(dispatchDateStart, dispatchDateEnd, areaType, branch,claimStatus, start, length);

		ReportStatisticsSurveyVoPaging voPaging = new ReportStatisticsSurveyVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<ReportStatisticsSurveyVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				ReportStatisticsSurveyVo vo = setReportStatisticsSurveyVo(claim);
				voPaging.getData().add(vo);
			}
		}

		return voPaging;
	}
	
	@Override
	public List<ReportStatisticsSurveyVo> searchReportStatisticsSurvey(String txtDispatchDateStart, String txtDispatchDateEnd, Integer selAreaType,
			Integer selBranch, Integer selClaimStatus) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		Branch branch = null;
		AreaType areaType = null;
		ClaimStatus claimStatus = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (selBranch != null && selBranch != 0) {
			branch = branchDao.findById(selBranch);
		}
		
		if(selAreaType != null) {
			areaType = AreaType.getById(selAreaType);
		}
		
		if(selClaimStatus != null) {
			claimStatus = ClaimStatus.getById(selClaimStatus);
		}

		List<Claim> claims = claimDao.searchReportStatisticsSurvey(dispatchDateStart, dispatchDateEnd, areaType, branch,claimStatus);

		List<ReportStatisticsSurveyVo> reportStatisticsSurveyVos = new ArrayList<ReportStatisticsSurveyVo>();
		
		if (claims != null) {
			for (Claim claim : claims) {
				ReportStatisticsSurveyVo vo = setReportStatisticsSurveyVo(claim);
				reportStatisticsSurveyVos.add(vo);
			}
		}

		return reportStatisticsSurveyVos;
	}

	@Override
	public List<ReportStatisticsSurveyVo> searchReportStatisticsSurveyExport(Integer[] ids) {
		List<Claim> results = claimDao.findByIds(ids);

		List<ReportStatisticsSurveyVo> vos = new ArrayList<ReportStatisticsSurveyVo>();
		
		if(results != null ){
			for (Claim claim : results) {
				ReportStatisticsSurveyVo vo = setReportStatisticsSurveyVo(claim);
				vos.add(vo);
			}
		}

		return vos;
	}

	@Override
	public void calcClaim(String claimNo) {
		Claim claim = claimDao.findByClaimNo(claimNo);
		try {
			calcEmployeeSurveyPrice(claim);
			
			claimDao.saveOrUpdate(claim);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public InvoiceReportVoPaging searchInvoiceReportPaging(String txtDispatchDateStart, String txtDispatchDateEnd,
			Integer selBranchDhip, Integer selType, int start, int length) {
		Date dispatchDateStart = null;
		Date dispatchDateEnd = null;
		BranchDhip branchDhip = null;
		Boolean groupInvoice = null;

		if (StringUtils.isNotBlank(txtDispatchDateStart)) {
			dispatchDateStart = DateToolsUtil.convertStringToDateWithStartTime(txtDispatchDateStart, DateToolsUtil.LOCALE_TH);
		}

		if (StringUtils.isNotBlank(txtDispatchDateEnd)) {
			dispatchDateEnd = DateToolsUtil.convertStringToDateWithEndTime(txtDispatchDateEnd, DateToolsUtil.LOCALE_TH);
		}

		if (selBranchDhip != null && selBranchDhip != 0) {
			branchDhip = branchDhipDao.findById(selBranchDhip);
		}
		
		if(selType != null) {
			if(selType == 1){
				groupInvoice = false;
			}else{
				groupInvoice = true;
			}
		}

		ClaimPaging claimPaging = claimDao.searchInvoiceReportPaging(dispatchDateStart, dispatchDateEnd, branchDhip, groupInvoice, start, length);

		InvoiceReportVoPaging voPaging = new InvoiceReportVoPaging();
		voPaging.setDraw(claimPaging.getDraw());
		voPaging.setRecordsFiltered(claimPaging.getRecordsFiltered());
		voPaging.setRecordsTotal(claimPaging.getRecordsTotal());
		voPaging.setData(new ArrayList<InvoiceReportVo>());
		if (claimPaging != null && claimPaging.getData() != null) {
			for (Claim claim : claimPaging.getData()) {
				InvoiceReportVo vo = setInvoiceReportVo(claim);
				voPaging.getData().add(vo);
			}
		}
		return voPaging;
	}
	
	private InvoiceReportVo setInvoiceReportVo(Claim claim){
		InvoiceReportVo vo = new InvoiceReportVo();
		vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
		if (claim.getDispatchDate() != null) {
			vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
		}
	
		if (claim.getBranchDhip() != null) {
			vo.setBranchDhipName(claim.getBranchDhip().getName());
		}
		
		float totalSur = calcTotalSur(claim);		
		vo.setSurveyTotal(totalSur + calcVat(totalSur));
		
		if (claim.getClaimStatus() != null) {
			vo.setClaimStatus(claim.getClaimStatus().getName());
		}
		
		if (claim.getInvoice() != null) {
			vo.setInvoiceNo(claim.getInvoice().getCode());
			vo.setType("จัดชุดแล้ว");
		}else{
			vo.setType("ยังไม่จัดชุด");
		}
		
		return vo;
	}
}
