/**
 * 
 */
package com.metasoft.ibilling.controller.ajax;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.ibilling.bean.paging.PaySurveyClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.PaySurveySearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.PaySurveyClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.PaySurveySearchResultVo;
import com.metasoft.ibilling.bean.vo.ResultVo;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.PaySurveyService;

@Controller
public class PaySurveyAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private PaySurveyService paySurveyService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/paysurvey/searchClaim", method = RequestMethod.POST)
	public @ResponseBody
	String searchClaim(Model model,
			@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd,
			@RequestParam(required = true) Integer selEmployee,			
			@RequestParam(required = false) String firstTime,
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		PaySurveyClaimSearchResultVoPaging resultPaging = new PaySurveyClaimSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(firstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<PaySurveyClaimSearchResultVo>());
		}else{
			resultPaging = claimService.searchPaySurveyClaimPaging(txtDispatchDateStart, txtDispatchDateEnd, selEmployee, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/paysurvey/save", method = RequestMethod.POST,headers = { "Content-type=application/x-www-form-urlencoded;charset=UTF-8" },produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String save(Model model, @RequestParam(required = true) String claimIds,
			HttpSession session) throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		
		User loginUser = (User) session.getAttribute("loginUser");

		PaySurvey paySurvey = paySurveyService.save(claimIds, loginUser.getId());
		
		resultVo.setMessage("บันทึกข้อมูลเรียบร้อย\n เลขที่จ่าย : " + paySurvey.getCode());
			
		String json2 = gson.toJson(resultVo);
		return json2;
	}
	
	@RequestMapping(value = "/paysurvey/search", method = RequestMethod.POST)
	public @ResponseBody
	String searchPaySurvey(Model model,
			@RequestParam(required = false) String txtPaySurveyDateStart,
			@RequestParam(required = false) String txtPaySurveyDateEnd,
			@RequestParam(required = false) String txtPaySurveyCode,	
			@RequestParam(required = false) Integer selEmployee,		
			@RequestParam(required = false) String firstTime,
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		PaySurveySearchResultVoPaging resultPaging = new PaySurveySearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(firstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<PaySurveySearchResultVo>());
		}else{
			resultPaging = paySurveyService.searchPaging(txtPaySurveyDateStart, txtPaySurveyDateEnd, txtPaySurveyCode,selEmployee, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
}
