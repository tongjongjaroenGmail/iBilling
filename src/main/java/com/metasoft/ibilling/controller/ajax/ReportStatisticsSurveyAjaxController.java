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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metasoft.ibilling.bean.paging.ReportStatisticsSurveyVoPaging;
import com.metasoft.ibilling.controller.vo.ReportStatisticsSurveyVo;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.impl.report.DownloadService;
import com.metasoft.ibilling.service.impl.report.ExporterService;
import com.metasoft.ibilling.service.impl.report.TokenService;

@Controller
@RequestMapping("/report/statisticsSurvey")
public class ReportStatisticsSurveyAjaxController extends BaseAjaxController {
	@Autowired
	private ClaimService claimService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody String search(Model model, 
			
			@RequestParam(required = false) String paramDispatchDateStart,
			@RequestParam(required = false) String paramDispatchDateEnd, 
			@RequestParam(required = false) Integer paramAreaType,
			@RequestParam(required = false) Integer paramBranch, 
			@RequestParam(required = false) Integer paramClaimStatus, 
			@RequestParam(required = true) String paramFirstTime,

			@RequestParam(required = true) Integer draw, @RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {

		ReportStatisticsSurveyVoPaging resultPaging = new ReportStatisticsSurveyVoPaging();
		resultPaging.setDraw(++draw);
		if (new Boolean(paramFirstTime)) {
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<ReportStatisticsSurveyVo>());
		} else {
			resultPaging = claimService.searchReportStatisticsSurveyPaging(paramDispatchDateStart, paramDispatchDateEnd, paramAreaType, paramBranch,paramClaimStatus,
					start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd, 
			@RequestParam(required = false) Integer selAreaType,
			@RequestParam(required = false) Integer selBranch, 
			@RequestParam(required = false) Integer selClaimStatus, 
			@RequestParam(required = false) String token, HttpSession session, HttpServletResponse response) throws ServletException,
			IOException, JRException, Exception {

		List<ReportStatisticsSurveyVo> results = claimService.searchReportStatisticsSurvey(txtDispatchDateStart, txtDispatchDateEnd, selAreaType, selBranch,selClaimStatus);
		
		HashMap param =new HashMap();
		downloadService.download(ExporterService.EXTENSION_TYPE_EXCEL, "statisticsSurvey",
				session.getServletContext().getRealPath("/report/statisticsSurvey"),
				param, results, token, response);		
	}
}
