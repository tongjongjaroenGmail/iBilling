<%@page import="com.metasoft.ibilling.model.SurveyEmployee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%
	//System.out.println("Query for Amphur...");
	List<SurveyEmployee> dataList = new ArrayList<SurveyEmployee>();
	Integer branchId = null;
	if (request.getParameter("branchId") != null && !request.getParameter("branchId").equals("")) {
		branchId = Integer.parseInt( request.getParameter("branchId") );
		List<SurveyEmployee> surveyEmployees = (List<SurveyEmployee>)request.getSession().getAttribute("surveyEmployees");
		for(SurveyEmployee surveyEmployee : surveyEmployees){
			if(surveyEmployee.getBranch() != null && surveyEmployee.getBranch().getId() == branchId){
				dataList.add(surveyEmployee);
			}		
		}
	}
%>

<%for(SurveyEmployee bean : dataList){%>
<option value="<%=bean.getId()%>"><%=bean.getCode() %></option>
<%}%>