/**
 * 
 */
package com.metasoft.ibilling.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.metasoft.ibilling.service.UserService;

/**
 * @author 
 * 
 */
@Controller
public class SiteController extends BaseController
{
	 @Autowired
	 private UserService userService;
	 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}else if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;
	}
    
    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String mainPage()
    {	
		return "mainPage";
    }
    
    @RequestMapping(value = "/invoiceGroupPage", method = RequestMethod.GET)
    public String invoiceGroupPage()
    {	
		return "invoiceGroupPage";
    }
    
    @RequestMapping(value = "/invoiceGroupClosePage", method = RequestMethod.GET)
    public String invoiceGroupClosePage()
    {	
		return "invoiceGroupClosePage";
    }
    
    @RequestMapping(value = "/invoiceSearchPage", method = RequestMethod.GET)
    public String invoiceSearchPage()
    {	
		return "invoiceSearchPage";
    }
    
    @RequestMapping(value = "/claimSearchPage", method = RequestMethod.GET)
    public String claimSearchPage()
    {	
		return "claimSearchPage";
    }
    
    @RequestMapping(value = "/paySurveyAddPage", method = RequestMethod.GET)
    public String paySurveyAddPage()
    {	
		return "paySurveyAddPage";
    }
    
    @RequestMapping(value = "/paySurveySearchPage", method = RequestMethod.GET)
    public String paySurveySearchPage()
    {	
		return "paySurveySearchPage";
    }
    
    @RequestMapping(value = "/reportStatisticsSurveyPage", method = RequestMethod.GET)
    public String reportStatisticsSurveyPage()
    {	
		return "reportStatisticsSurveyPage";
    }
    
    @RequestMapping(value = "/invoiceReportPage", method = RequestMethod.GET)
    public String invoiceReportPage()
    {	
		return "invoiceReportPage";
    }
    
    @RequestMapping(value = "/claimLoadWSPage", method = RequestMethod.GET)
    public String claimLoadWSPage()
    {	
		return "claimLoadWSPage";
    }
    
    @RequestMapping(value = "/paySurveyReportPage", method = RequestMethod.GET)
    public String paySurveyReportPage()
    {	
		return "paySurveyReportPage";
    }
}
