/**
 * 
 */
package com.metasoft.ibilling.controller.ajax;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.ibilling.bean.paging.CheckClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.CheckClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.ClaimDetailVo;
import com.metasoft.ibilling.bean.vo.ResultVo;
import com.metasoft.ibilling.dao.ClaimDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.impl.ClaimServiceImpl;
import com.metasoft.ibilling.util.DateToolsUtil;

@Controller
public class ClaimAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private ClaimDao claimDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/claim/search", method = RequestMethod.POST)
	public @ResponseBody
	String searchClaim(Model model,
			@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd,
			@RequestParam(required = false) String txtClaimNo,		
			@RequestParam(required = true) Integer selEmployee,			
			@RequestParam(required = false) Integer selClaimStatus,			
			@RequestParam(required = false) String firstTime,
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		CheckClaimSearchResultVoPaging resultPaging = new CheckClaimSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(firstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<CheckClaimSearchResultVo>());
		}else{
			resultPaging = claimService.searchCheckClaimPaging(txtDispatchDateStart, txtDispatchDateEnd, txtClaimNo,selEmployee,selClaimStatus, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/claim/find", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String find(Model model,@RequestParam(required = false) int id) throws ParseException {
		Claim claim = claimDao.findById(id);
		
		ClaimDetailVo vo = new ClaimDetailVo();

		vo.setClaimId(claim.getId());
		vo.setClaimNo(claim.getClaimNo());
		vo.setDispatchDate(DateToolsUtil.convertToString(claim.getDispatchDate(), DateToolsUtil.LOCALE_TH));
		vo.setSendrptDate(DateToolsUtil.convertToString(claim.getSendrptDate(), DateToolsUtil.LOCALE_TH));
		vo.setClaimStatus(claim.getClaimStatus() != null?claim.getClaimStatus().getName():"");
		
		vo.setEmployeeCode(claim.getSurveyEmployee() != null?claim.getSurveyEmployee().getCode():"");
		
		if(claim.getBranch() != null){
			vo.setCenter(StringUtils.trimToEmpty(claim.getBranch().getName()));
		}
		
		vo.setClaimType(claim.getClaimType() != null?claim.getClaimType().getName():"");
		vo.setDispatchType(claim.getDispatchType() != null?claim.getDispatchType().getName():"");
		vo.setAreaType(claim.getAreaType() != null?claim.getAreaType().getName():"");
		vo.setWorkTime(claim.getWrkTime() != null?claim.getWrkTime().getName():"");
		vo.setCoArea(claim.getCoArea() == null?false:claim.getCoArea());
		vo.setDisperse(claim.getDisperse() == null?false:claim.getDisperse());
		vo.setServiceType(claim.getServiceType() != null?claim.getServiceType().getName():"");
		if(claim.getSurveyAmphur() != null){
			vo.setServiceAmphur(StringUtils.trimToEmpty(claim.getSurveyAmphur().getName()));
		}
		if(claim.getSurveyProvince() != null){
			vo.setServiceProvince(StringUtils.trimToEmpty(claim.getSurveyProvince().getName()));
		}
		vo.setPhotoNum(claim.getPhotoNum() != null?claim.getPhotoNum():0);
		vo.setPoliceRptNum(claim.getPoliceRptNum() != null?claim.getPoliceRptNum():0);
		vo.setClaimTp(claim.getClaimTp() != null?claim.getClaimTp().getName():"");
		vo.setReviewBy(StringUtils.trimToEmpty(claim.getReviewBy()));
		vo.setSrSendDate(DateToolsUtil.convertToString(claim.getSrSendDate(), DateToolsUtil.LOCALE_TH));
		vo.setSurClaimMain(claim.getSurClaim());
		vo.setApproveBy(StringUtils.trimToEmpty(claim.getApproveBy()));
		vo.setSrApproveDate(DateToolsUtil.convertToString(claim.getSrApproveDate(), DateToolsUtil.LOCALE_TH));
		
		if(claim.getInvoice() != null){
			Invoice invoice = claim.getInvoice();
			vo.setInvoiceId(invoice.getId());
			vo.setInvoiceCode(invoice.getCode());
		}	
			
		vo.setSurInvest(claim.getSurInvest());
		vo.setSurTrans(claim.getSurTrans());
		vo.setSurDaily(claim.getSurDaily());
		vo.setSurPhoto(claim.getSurPhoto());
		vo.setSurClaim(claim.getSurClaim());
		vo.setSurTel(claim.getSurTel());
		vo.setSurInsure(claim.getSurInsure());
		vo.setSurTowcar(claim.getSurTowcar());
		vo.setSurOther(claim.getSurOther());
			
		vo.setSurTotalNoTax(ClaimServiceImpl.calcTotalSur(claim));
		vo.setSurTax(ClaimServiceImpl.calcVat(vo.getSurTotalNoTax()));
		
		vo.setSurTotalWithTax(vo.getSurTotalNoTax() + vo.getSurTax());
		
		vo.setInsInvest(claim.getInsInvest());
		vo.setInsTrans(claim.getInsTrans());
		vo.setInsDaily(claim.getInsDaily());
		vo.setInsPhoto(claim.getInsPhoto());
		vo.setInsClaim(claim.getInsClaim());
		vo.setInsTel(claim.getInsTel());
		vo.setInsInsure(claim.getInsInsure());
		vo.setInsTowcar(claim.getInsTowcar());
		vo.setInsOther(claim.getInsOther());
			
		vo.setInsTotalNoTax(ClaimServiceImpl.calcTotalIns(claim));	
		vo.setInsTax(ClaimServiceImpl.calcVat(vo.getInsTotalNoTax()));
		
		vo.setInsTotalWithTax(vo.getInsTotalNoTax() + vo.getInsTax());

		if(claim.getPaySurvey() != null){
			PaySurvey paySurvey = claim.getPaySurvey();
			vo.setPaySurveyId(paySurvey.getId());
			vo.setPaySurveyCode(paySurvey.getCode());
		}	
		
		vo.setSurveyInvest(claim.getSurveyInvest());
		vo.setSurveyTrans(claim.getSurveyTrans());
		vo.setSurveyDaily(claim.getSurveyDaily());
		vo.setSurveyPhoto(claim.getSurveyPhoto());
		vo.setSurveyClaim(claim.getSurveyClaim());
		vo.setSurveyTel(claim.getSurveyTel());
		vo.setSurveyConditionRight(claim.getSurveyConditionRight());
		vo.setSurveyOther(claim.getSurveyOther());
		vo.setSurveyFine(claim.getSurveyFine());
		
		vo.setSurveyTotal(ClaimServiceImpl.calcTotalSurvey(claim));
		
		vo.setRemark(StringUtils.trimToEmpty(claim.getRemark()));
		if(claim.getUpdateBy() != null){
			vo.setCreateBy(StringUtils.trimToEmpty(claim.getUpdateBy().getName()));
			vo.setCreateDate(DateToolsUtil.convertToString(claim.getUpdateDate(), DateToolsUtil.LOCALE_TH));
		}else{
			vo.setCreateBy(StringUtils.trimToEmpty(claim.getCreateBy().getName()));
			vo.setCreateDate(DateToolsUtil.convertToString(claim.getCreateDate(), DateToolsUtil.LOCALE_TH));
		}
		
		vo.setW7(claim.getW7() == null?false:claim.getW7());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(vo);
		return json;
	}
	
	@RequestMapping(value = "/claim/save", method = RequestMethod.POST,headers = { "Content-type=application/x-www-form-urlencoded;charset=UTF-8" },produces = { "application/json;charset=UTF-8" })
    public @ResponseBody String save(Model model
    		,@RequestParam(required = true) Integer claimId
    		,@RequestParam(required = false) String remark
    		,@RequestParam(required = false) Float surveyOther
    		,@RequestParam(required = false) Float surveyFine
    		,HttpSession session) throws ParseException
    {
		ResultVo resultVo = new ResultVo();
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	if(claimId != 0){
    		Claim claim = claimService.findById(claimId);
    		claim.setRemark(remark);
    		claim.setSurveyOther(surveyOther);
    		claim.setSurveyFine(surveyFine);
    		
    		User user = (User)session.getAttribute("loginUser");
    		claim.setUpdateBy(userDao.findById(user.getId()));
    		claim.setUpdateDate(new Date());
    		
    		claimDao.saveOrUpdate(claim);

        	resultVo.setMessage("บันทึกข้อมูลเรียบร้อยแล้ว");
    	}

    	String json = gson.toJson(resultVo);
		return json;
    }
	
	@RequestMapping(value = "/claim/calcClaim", method = RequestMethod.GET,produces = { "application/json;charset=UTF-8" })
    public @ResponseBody String calcClaim(Model model,@RequestParam(required = false) String tempClaimNo) throws ParseException
    {
		ResultVo resultVo = new ResultVo();
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	claimService.calcClaim(tempClaimNo);

    	String json = gson.toJson(resultVo);
		return json;
    }
}
