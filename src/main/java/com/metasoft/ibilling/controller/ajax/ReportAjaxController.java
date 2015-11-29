package com.metasoft.ibilling.controller.ajax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metasoft.ibilling.bean.vo.StatusResponse;
import com.metasoft.ibilling.model.Claim;
import com.metasoft.ibilling.model.Invoice;
import com.metasoft.ibilling.model.PaySurvey;
import com.metasoft.ibilling.model.SurveyEmployee;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.InvoiceService;
import com.metasoft.ibilling.service.PaySurveyService;
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
	
	@Autowired
	private PaySurveyService paySurveyService;
	
	@Autowired
	private ClaimService claimService;
	
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
	public void invoice(
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
	
	@RequestMapping(value = "/paySurvey")
	public void paySurvey(
			@RequestParam(required = true) String paySurveyIds,
			@RequestParam(required = false) String token, HttpSession session,
			HttpServletResponse response) throws ServletException, IOException, JRException, Exception {

		List<String> fileList = new ArrayList<String>();
		List<InputStream> inputStreams = new ArrayList<InputStream>();
		HashMap<String,Object> param;
		ThaiBaht thaiBaht = new ThaiBaht();

		String[] arrPaySurveyId = paySurveyIds.split(",");
		for (String paySurveyId : arrPaySurveyId) {
			param = new HashMap<String,Object>();
			
			PaySurvey paySurvey = paySurveyService.findById(Integer.parseInt(paySurveyId));
			
			float sumSurveyTrans = 0;
			float sumSurveyInvest = 0;
			float sumSurveyDaily = 0;
			float sumSurveyPhoto = 0;
			float sumSurveyTel = 0;
			float sumSurveyClaim = 0;
			float sumSurveyConditionRight = 0;
			float sumSurveyOther = 0;
			float sumSurveyFine = 0;		
			float sumSurveyTotal = 0; 
			for (Claim claim : paySurvey.getClaims()) {
				sumSurveyTrans += NumberToolsUtil.nullToFloat(claim.getSurveyTrans());
				sumSurveyInvest += NumberToolsUtil.nullToFloat(claim.getSurveyInvest());
				sumSurveyDaily += NumberToolsUtil.nullToFloat(claim.getSurveyDaily());
				sumSurveyPhoto += NumberToolsUtil.nullToFloat(claim.getSurveyPhoto());
				sumSurveyTel += NumberToolsUtil.nullToFloat(claim.getSurveyTel());
				sumSurveyClaim += NumberToolsUtil.nullToFloat(claim.getSurveyClaim());
				sumSurveyConditionRight += NumberToolsUtil.nullToFloat(claim.getSurveyConditionRight());
				sumSurveyOther += NumberToolsUtil.nullToFloat(claim.getSurveyOther());
				sumSurveyFine += NumberToolsUtil.nullToFloat(claim.getSurveyFine());		
				sumSurveyTotal += claimService.calcTotalSurvey(claim); 
			}
			
			param.put("sumSurveyTrans", sumSurveyTrans);
			param.put("sumSurveyInvest", sumSurveyInvest);
			param.put("sumSurveyDaily", sumSurveyDaily);
			param.put("sumSurveyPhoto", sumSurveyPhoto);
			param.put("sumSurveyTel", sumSurveyTel);
			param.put("sumSurveyClaim", sumSurveyClaim);
			param.put("sumSurveyConditionRight", sumSurveyConditionRight);
			param.put("sumSurveyOther", sumSurveyOther);
			param.put("sumSurveyFine", sumSurveyFine);
			param.put("totalThai", "=" + thaiBaht.getText(sumSurveyTotal) + "=");
			param.put("total", sumSurveyTotal);
			
			SurveyEmployee surveyEmployee = paySurvey.getClaims().get(0).getSurveyEmployee();
			if(surveyEmployee != null){
				param.put("surveyEmployeeCode", surveyEmployee.getCode());
				param.put("surveyEmployeeName", surveyEmployee.getFullname());
			}
			
			param.put("paySurveyNo", paySurvey.getCode());
			
			ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
					session.getServletContext().getRealPath("/jasperreport/paySurvey"), ExporterService.EXTENSION_TYPE_EXCEL,
					param, "paySurvey", paySurvey.getClaims());
			
			if (reportOut != null) {
				InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
				inputStreams.add(in);
				fileList.add("ใบสำคัญจ่าย_" + paySurvey.getCode() + ".xls");
			}
			
			//-----------------------------------------------------
			
			param = new HashMap<String,Object>();
			
			param.put("paySurveyNo", paySurvey.getCode());
			if(surveyEmployee != null){
				param.put("surveyEmployeeCode", surveyEmployee.getCode());
				param.put("surveyEmployeeName", surveyEmployee.getFullname());
			}
			param.put("total", sumSurveyTotal);
			param.put("totalThai", "(" + thaiBaht.getText(sumSurveyTotal) + ")");
			
			reportOut = downloadService.generateReportXLS(null,
					session.getServletContext().getRealPath("/jasperreport/paySurveyApprove"), ExporterService.EXTENSION_TYPE_EXCEL,
					param, "paySurveyApprove", paySurvey.getClaims());
			
			if (reportOut != null) {
				InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
				inputStreams.add(in);
				fileList.add("แบบขออนุมัติ_" + paySurvey.getCode() + ".xls");
			}
		}
		
		OutputStream outs = null;	
		outs = response.getOutputStream();

		String header = "attachment; filename=billing.zip";
	
		header = new String(header.getBytes("UTF-8"), "ISO8859_1");
		response.setHeader("Content-Disposition", header);
		response.setContentType("application/ms-excel");
		
		downloadService.writeZipFile(fileList, inputStreams, outs, token);
	}
}