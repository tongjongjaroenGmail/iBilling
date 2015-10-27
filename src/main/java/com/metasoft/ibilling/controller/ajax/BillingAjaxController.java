/**
 * 
 */
package com.metasoft.ibilling.controller.ajax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.ibilling.bean.paging.BillingSearchResultVoPaging;
import com.metasoft.ibilling.bean.report.BillingExportResult;
import com.metasoft.ibilling.controller.vo.BillingSearchResultVo;
import com.metasoft.ibilling.service.impl.DownloadService;
import com.metasoft.ibilling.service.impl.ExporterService;
import com.metasoft.ibilling.service.impl.TokenService;
import com.metasoft.ibilling.service.report.BillingService;
import com.metasoft.ibilling.util.NumberToolsUtil;
import com.metasoft.ibilling.util.ThaiBaht;

@Controller
@RequestMapping("/report/billing")
public class BillingAjaxController extends BaseAjaxController {
	@Autowired
	private BillingService billingService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody String search(Model model, @RequestParam(required = false) String paramCloseDateStart,
			@RequestParam(required = false) String paramCloseDateEnd, @RequestParam(required = false) String paramPartyInsuranceId,
			@RequestParam(required = false) String paramClaimTypeId, @RequestParam(required = true) String paramFirstTime,

			@RequestParam(required = true) Integer draw, @RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {

		BillingSearchResultVoPaging resultPaging = new BillingSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if (new Boolean(paramFirstTime)) {
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<BillingSearchResultVo>());
		} else {
			resultPaging = billingService.searchPaging(paramCloseDateStart, paramCloseDateEnd, paramPartyInsuranceId, paramClaimTypeId,
					start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@RequestParam(required = true) Integer[] chk,
			@RequestParam(required = false) String token, HttpSession session, HttpServletResponse response) throws ServletException,
			IOException, JRException, Exception {

		List<BillingExportResult> results = billingService.searchExport(chk);

		if (!results.isEmpty()) {
			List<String> fileList = new ArrayList<String>();
			List<InputStream> inputStreams = new ArrayList<InputStream>();

			float totalWage = 0;
			List<BillingExportResult> exports = new ArrayList<BillingExportResult>();
			
			int i = 1;
			HashMap<String,Object> param = new HashMap<String,Object>();
			
			ThaiBaht thaiBaht = new ThaiBaht();
			boolean isContinue = false;
			for (BillingExportResult result : results) {
				isContinue = false;
				totalWage += result.getWage();
				if (totalWage > 30000) {
					param = new HashMap<String,Object>();
					if(exports.size() == 0){
						param.put("totalWage", totalWage);
						param.put("totalWageThai", thaiBaht.getText(totalWage));
						exports.add(result);
						totalWage = 0;
						isContinue = true;
					}else{
						param.put("totalWage", totalWage - result.getWage());
						param.put("totalWageThai", thaiBaht.getText(totalWage - result.getWage()));
						totalWage = result.getWage();
					}
					param.put("totalClaim", NumberToolsUtil.integerFormat(exports.size()));
					ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
							session.getServletContext().getRealPath("/report/billing"), ExporterService.EXTENSION_TYPE_EXCEL,
							param, "billing", exports);
					exports = new ArrayList<BillingExportResult>();
					
					if (reportOut != null) {
						InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
						inputStreams.add(in);
						fileList.add("billing" + i++ + ".xls");
					}
					if(isContinue){
						continue;
					}
				}

				exports.add(result);
			}

			if (!exports.isEmpty()) {
				param = new HashMap<String,Object>();
				param.put("totalClaim", NumberToolsUtil.integerFormat(exports.size()));
				param.put("totalWage", totalWage);
				param.put("totalWageThai", thaiBaht.getText(totalWage));
				ByteArrayOutputStream reportOut = downloadService.generateReportXLS(null,
						session.getServletContext().getRealPath("/report/billing"), ExporterService.EXTENSION_TYPE_EXCEL, param,
						"billing", exports);
				exports = new ArrayList<BillingExportResult>();

				if (reportOut != null) {
					InputStream in = new ByteArrayInputStream(reportOut.toByteArray());
					inputStreams.add(in);
					fileList.add("billing" + i++ + ".xls");
				}
			}
			String header = "";

			OutputStream outs = null;	
			outs = response.getOutputStream();

			if(fileList.size() > 1){
				header = "attachment; filename=billing.zip";
			}else{
				header = "attachment; filename=billing.xls";
			}		
			
			header = new String(header.getBytes("UTF-8"), "ISO8859_1");
			response.setHeader("Content-Disposition", header);
			response.setContentType("application/ms-excel");
			
			if(fileList.size() > 1){
				downloadService.writeZipFile(fileList, inputStreams, outs, token);
			}else{		
				byte[] bytes = IOUtils.toByteArray(inputStreams.get(0));
				ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
				baos.write(bytes, 0, bytes.length);
				downloadService.write(token, response, baos);
				baos.close();
			}		
		}
	}
}
