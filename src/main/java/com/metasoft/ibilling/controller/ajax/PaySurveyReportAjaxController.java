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
import com.metasoft.ibilling.bean.paging.PaySurveyReportSearchResultVoPaging;
import com.metasoft.ibilling.bean.vo.PaySurveyReportSearchResultVo;
import com.metasoft.ibilling.controller.vo.ReportStatisticsSurveyVo;
import com.metasoft.ibilling.service.ClaimService;
import com.metasoft.ibilling.service.impl.report.DownloadService;
import com.metasoft.ibilling.service.impl.report.ExporterService;
import com.metasoft.ibilling.service.impl.report.TokenService;

@Controller
@RequestMapping("paySurvey/report")
public class PaySurveyReportAjaxController extends BaseAjaxController {
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
			@RequestParam(required = false) String paramHasPaySurvey,
			@RequestParam(required = false) Integer paramBranch, 
			@RequestParam(required = false) Integer paramEmployee, 
			@RequestParam(required = true) String paramFirstTime,

			@RequestParam(required = true) Integer draw, @RequestParam(required = true) Integer start,
			@RequestParam(required = true) Integer length) throws ParseException {

		PaySurveyReportSearchResultVoPaging resultPaging = new PaySurveyReportSearchResultVoPaging();
		resultPaging.setDraw(++draw);
		if (new Boolean(paramFirstTime)) {
			resultPaging.setRecordsFiltered(0L);
			resultPaging.setRecordsTotal(0L);
			resultPaging.setData(new ArrayList<PaySurveyReportSearchResultVo>());
		} else {
			resultPaging = claimService.searchPaySurveyReportPaging(paramDispatchDateStart, paramDispatchDateEnd, paramHasPaySurvey, paramBranch,paramEmployee,
					start, length);
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(resultPaging);
		return json;
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(
			@RequestParam(required = false) String txtDispatchDateStart,
			@RequestParam(required = false) String txtDispatchDateEnd, 
			@RequestParam(required = false) String selHasPaySurvey,
			@RequestParam(required = false) Integer selBranch, 
			@RequestParam(required = false) Integer selEmployee, 
			@RequestParam(required = false) String token, HttpSession session, HttpServletResponse response) throws ServletException,
			IOException, JRException, Exception {

		PaySurveyReportSearchResultVoPaging result = claimService.searchPaySurveyReportPaging(txtDispatchDateStart, txtDispatchDateEnd, selHasPaySurvey, selBranch,selEmployee,
				0, 0);
		
		HashMap param =new HashMap();
		downloadService.download(ExporterService.EXTENSION_TYPE_EXCEL, "paySurveyReport",
				session.getServletContext().getRealPath("/report/paySurveyReport"),
				param, result.getData(), token, response);		
	}
}
