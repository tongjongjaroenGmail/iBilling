package com.metasoft.ibilling.controller.ajax;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metasoft.ibilling.bean.vo.StatusResponse;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.service.InvoiceService;
import com.metasoft.ibilling.service.impl.report.DownloadService;
import com.metasoft.ibilling.service.impl.report.ExporterService;
import com.metasoft.ibilling.service.impl.report.TokenService;
import com.metasoft.ibilling.util.NumberToolsUtil;
import com.metasoft.ibilling.util.ThaiBaht;

@Controller
@RequestMapping("/report")
public class ReportAjaxController {

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private InvoiceService invoiceService;
	


	@RequestMapping(value = "/download/progress")
	public @ResponseBody StatusResponse checkDownloadProgress(
			@RequestParam String token) {
		return new StatusResponse(true, tokenService.check(token));
	}

	@RequestMapping(value = "/download/token")
	public @ResponseBody StatusResponse getDownloadToken() {
		return new StatusResponse(true, tokenService.generate());
	}
	
	@RequestMapping(value = "/invoice")
	public void work(
			@RequestParam(required = true) int invoiceId,
			@RequestParam(required = false) String token, HttpSession session,
			HttpServletResponse response) throws ParseException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException {

		Invoice invoice = invoiceService.findById(invoiceId);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		ThaiBaht thaiBaht = new ThaiBaht();
		
		float surTotal = 0;
		for (Claim claim : invoice.getClaims()) {
			surTotal += 
					NumberToolsUtil.nullToFloat(claim.getSurInvest()) + 
					NumberToolsUtil.nullToFloat(claim.getSurTrans()) + 
					NumberToolsUtil.nullToFloat(claim.getSurDaily()) + 
					NumberToolsUtil.nullToFloat(claim.getSurPhoto()) + 
					NumberToolsUtil.nullToFloat(claim.getSurClaim()) + 
					NumberToolsUtil.nullToFloat(claim.getSurTel()) + 
					NumberToolsUtil.nullToFloat(claim.getSurOther());
		}

		
		param.put("invoiceNo", invoice.getCode());
		param.put("total", surTotal);
		param.put("totalThai", "(" + thaiBaht.getText(surTotal) + ")");

		downloadService.download(ExporterService.EXTENSION_TYPE_EXCEL, "invoice",
				session.getServletContext().getRealPath("/jasperreport/invoice"),
				param, invoice.getClaims(), token, response);
	}
}