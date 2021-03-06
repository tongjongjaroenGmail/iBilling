/**
 * 
 */
package com.metasoft.ibilling.controller.ajax;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

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
import com.metasoft.ibilling.bean.SaveResult;
import com.metasoft.ibilling.bean.paging.ClaimSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.InvoiceReportVoPaging;
import com.metasoft.ibilling.bean.paging.InvoiceSearchResultVoPaging;
import com.metasoft.ibilling.bean.paging.ReportStatisticsSurveyVoPaging;
import com.metasoft.ibilling.bean.vo.ClaimSearchResultVo;
import com.metasoft.ibilling.bean.vo.InvoiceDetailVo;
import com.metasoft.ibilling.bean.vo.InvoiceReportVo;
import com.metasoft.ibilling.bean.vo.InvoiceSearchResultVo;
import com.metasoft.ibilling.bean.vo.ResultVo;
import com.metasoft.ibilling.controller.vo.ReportStatisticsSurveyVo;
import com.metasoft.ibilling.dao.InvoiceDao;
import com.metasoft.ibilling.dao.UserDao;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.User;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.InvoiceService;
import com.metasoft.ibilling.service.impl.ClaimServiceImpl;
import com.metasoft.ibilling.service.impl.report.DownloadService;
import com.metasoft.ibilling.service.impl.report.ExporterService;
import com.metasoft.ibilling.service.impl.report.TokenService;
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
	
	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/invoiceGroup/search", method = RequestMethod.POST)
	public @ResponseBody
	String searchClaim(Model model,
			@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd,
			@RequestParam(required = false) Integer selBranch,	
			@RequestParam(required = true) String selClaimStatus,
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
			resultPaging = claimService.searchGroupClaimPaging(txtDispatchDateStart, txtDispatchDateEnd, selBranch,selClaimStatus, start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/invoice/save", method = RequestMethod.GET,headers = { "Content-type=application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody
	String save(Model model, @RequestParam(required = true) String claimIds,
			@RequestParam(required = true) String invoiceNo, HttpSession session) throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ResultVo resultVo = new ResultVo();
		
		User loginUser = (User) session.getAttribute("loginUser");

		SaveResult saveResult = invoiceService.save(claimIds, invoiceNo, loginUser.getId());
		
		if(saveResult.isSeccess()){
			resultVo.setMessage("บันทึกข้อมูลเรียบร้อย");
			resultVo.setData(saveResult.getId());
		}else{
			resultVo.setMessage(saveResult.getErrorDesc());
			resultVo.setError(true);
		}
			
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

			vo.setSurTotal(ClaimServiceImpl.calcTotalSur(claim));
			vo.setSurTax(ClaimServiceImpl.calcVat(vo.getSurTotal()));
			vo.setSurTotal(vo.getSurTotal() + vo.getSurTax());
			
			invoiceDetailVo.getClaims().add(vo);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(invoiceDetailVo);
		return json;
	}
	
	@RequestMapping(value = "/invoice/report/search", method = RequestMethod.POST)
	public @ResponseBody String searchInvoiceReport(Model model, 
			
			@RequestParam(required = false) String paramDispatchDateStart,
			@RequestParam(required = false) String paramDispatchDateEnd, 
			@RequestParam(required = false) Integer paramBranchDhip, 
			@RequestParam(required = false) Integer paramType, 
			@RequestParam(required = true) String paramFirstTime,

			@RequestParam(required = true) Integer draw, @RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {

		InvoiceReportVoPaging resultPaging = new InvoiceReportVoPaging();
		resultPaging.setDraw(++draw);
		if (new Boolean(paramFirstTime)) {
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<InvoiceReportVo>());
		} else {
			resultPaging = claimService.searchInvoiceReportPaging(paramDispatchDateStart, paramDispatchDateEnd, paramBranchDhip, paramType,
					start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}
	
	@RequestMapping(value = "/invoice/report/export", method = RequestMethod.POST)
	public void export(@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd, 
			@RequestParam(required = false) Integer selBranchDhip, 
			@RequestParam(required = false) Integer selType, 
			@RequestParam(required = false) String token, HttpSession session, HttpServletResponse response) throws ServletException,
			IOException, JRException, Exception {

		InvoiceReportVoPaging results = claimService.searchInvoiceReportPaging(txtDispatchDateStart, txtDispatchDateEnd, selBranchDhip, selType,0,0);
		
		HashMap param =new HashMap();
		downloadService.download(ExporterService.EXTENSION_TYPE_EXCEL, "invoiceReport",
				session.getServletContext().getRealPath("/report/invoiceReport"),
				param, results.getData(), token, response);		
	}
}
