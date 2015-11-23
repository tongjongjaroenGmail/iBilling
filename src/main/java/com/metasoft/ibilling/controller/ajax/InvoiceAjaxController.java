/**
 * 
 */
package com.metasoft.ibilling.controller.ajax;

import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.QueryHint;
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
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.InvoiceSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.InvoiceDetailVo;
import com.metasoft.ibilling.bean.vo.InvoiceSearchResultVo;
import com.metasoft.ibilling.bean.vo.ResultVo;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.InvoiceService;
import com.metasoft.ibilling.util.DateToolsUtil;
import com.metasoft.ibilling.util.NumberToolsUtil;

@Controller
public class InvoiceAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/invoiceGroup/search", method = RequestMethod.POST)
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
			resultPaging = claimService.searchGroupClaimPaging(txtDispatchDateStart, txtDispatchDateEnd, selBranch, start, length);
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
	
	@RequestMapping(value = "/invoice/search", method = RequestMethod.POST)
	public @ResponseBody
	String searchInvoice(Model model,
			@RequestParam(required = false) String txtCreateDateStart,
			@RequestParam(required = false) String txtCreateDateEnd,
			@RequestParam(required = false) String txtInvoiceCode,			
			@RequestParam(required = false) String firstTime,
			@RequestParam(required = true) Integer draw,
			@RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {
		
		InvoiceSearchResultVoPaging resultPaging = new InvoiceSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if(new Boolean(firstTime)){		
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<InvoiceSearchResultVo>());
		}else{
			resultPaging = invoiceService.searchPaging(txtCreateDateStart, txtCreateDateEnd, txtInvoiceCode, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/invoice/find", method = RequestMethod.GET)
	public @ResponseBody
	String find(Model model,@RequestParam(required = false) int id) throws ParseException {
		Invoice invoice = invoiceDao.findById(id);
		
		InvoiceDetailVo invoiceDetailVo = new InvoiceDetailVo();
		invoiceDetailVo.setInvoiceId(invoice.getId());
		invoiceDetailVo.setInvoiceCode(invoice.getCode());
		invoiceDetailVo.setInvoiceCreateDate(DateToolsUtil.convertToString(invoice.getCreateDate(), DateToolsUtil.LOCALE_TH));
		for (Claim claim : invoice.getClaims()) {
			ClaimSearchResultVo vo = new ClaimSearchResultVo();
			vo.setClaimNo(StringUtils.trimToEmpty(claim.getClaimNo()));
			vo.setClaimId(claim.getId().intValue());

			vo.setSurInvest(NumberToolsUtil.nullToFloat(claim.getSurInvest()));
			vo.setSurTrans(NumberToolsUtil.nullToFloat(claim.getSurTrans()));
			vo.setSurDaily(NumberToolsUtil.nullToFloat(claim.getSurDaily()));
			vo.setSurPhoto(NumberToolsUtil.nullToFloat(claim.getSurPhoto()));
			vo.setSurClaim(NumberToolsUtil.nullToFloat(claim.getSurClaim()));
			vo.setSurTel(NumberToolsUtil.nullToFloat(claim.getSurTel()));
			vo.setSurInsure(NumberToolsUtil.nullToFloat(claim.getSurInsure()));
			vo.setSurTowcar(NumberToolsUtil.nullToFloat(claim.getSurTowcar()));
			vo.setSurOther(NumberToolsUtil.nullToFloat(claim.getSurOther()));
			
			float surTotal = vo.getSurInvest() + vo.getSurTrans() + vo.getSurDaily() + vo.getSurPhoto() + vo.getSurClaim() + 
					vo.getSurTel() + vo.getSurInsure() + vo.getSurTowcar() + vo.getSurOther();
			surTotal = (surTotal * (100 + NumberToolsUtil.nullToFloat(claim.getSurTax())))/100;
			vo.setSurTotal(surTotal);
			
			invoiceDetailVo.getClaims().add(vo);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(invoiceDetailVo);
		return json;
	}
}
