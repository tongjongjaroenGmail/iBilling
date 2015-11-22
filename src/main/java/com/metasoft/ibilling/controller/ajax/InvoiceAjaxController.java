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
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.ResultVo;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.InvoiceService;

@Controller
public class InvoiceAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/invoice/search", method = RequestMethod.POST)
	public @ResponseBody
	String searchClaim(Model model,
			@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd,
			@RequestParam(required = false) Integer selBranch,			
			@RequestParam(required = false) String firstTime,
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		ClaimSearchResultVoPaging resultPaging = new ClaimSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(firstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<ClaimSearchResultVo>());
		}else{
			resultPaging = claimService.searchPaging(txtDispatchDateStart, txtDispatchDateEnd, selBranch, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/invoice/save", method = RequestMethod.GET,headers = { "Content-type=application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String updateLeaveStatus(Model model, @RequestParam(required = true) String claimIds,
			@RequestParam(required = true) String invoiceNo, HttpSession session) throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		
		User loginUser = (User) session.getAttribute("loginUser");

		int invoiceId = invoiceService.save(claimIds, invoiceNo, loginUser.getId());
		
		resultVo.setMessage("บันทึกข้อมูลเรียบร้อย");
		resultVo.setData(invoiceId);
			
		String json2 = gson.toJson(resultVo);
		return json2;
	}
}
