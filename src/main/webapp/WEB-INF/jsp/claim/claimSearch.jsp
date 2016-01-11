<%@page import="com.metasoft.ibilling.model.ClaimStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
pageContext.setAttribute("claimStatuses", ClaimStatus.values());
%>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>ตรวจสอบเคลม</h1>
	</div>
	<!-- /.page-header -->

<div id="divParamSearch">
	<!-- PAGE CONTENT BEGINS -->
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>วันที่จ่ายงาน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtDispatchDateStart" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>
				</div>
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>ถึงวันที่ : </b> 
					</div>
				</div>
				
				<div class="col-sm-3">	
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtDispatchDateEnd" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>

	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>เลขเคลม : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input type="text" id="txtClaimNo">
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>พนักงาน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selEmployee" title="พนักงาน">
							<option value=""></option>
							<c:forEach var="surveyEmployee" items="${surveyEmployees}" varStatus="index">		
								<c:if test="${empty surveyEmployee.branch}">
									<option value="${surveyEmployee.id}">${surveyEmployee.code}</option>	
								</c:if>
								<c:if test="${not empty surveyEmployee.branch}">
									<option value="${surveyEmployee.id}">${surveyEmployee.branch.name} : ${surveyEmployee.code}</option>	
								</c:if>				
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>สถานะของงาน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selClaimStatus">
							<option value=""></option>
							<c:forEach var="claimStatus" items="${claimStatuses}" varStatus="index">		
								<option value="${claimStatus.id}">${claimStatus.name}</option>					
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>
</div>	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
			<div class="table-responsive">
				<div class="col-sm-12">
					<button class="btn btn-info" type="button" id="btnSearch" onclick="searchClaim();">
						<i class="icon-search"></i> ค้นหา
					</button>
				</div>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
	</div>
	<!-- /row -->

	<div class="table-responsive">
		<br> <br>
		<table id="tblClaim" class="table table-striped table-bordered table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th>เลขเคลม</th>
					<th>พนักงาน</th>
					<th>ศูนย์</th>
					<th>วันที่จ่ายงาน</th>
					<th>ประเภทเคลม</th>
					<th>ค่าสำรวจทิพยฯ</th>
					<th>ค่าสำรวจพนักงาน</th>
					<th>รายละเอียด</th>
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>
	</div>
	
	<div class="space-4"></div>
	
<%-- 	<jsp:include page = "modalClaimSave.jsp" flush="false"/> --%>
</div>
<!-- /.page-content -->

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

var tblClaimDt;
var firstTime = true;
var isSearch = false;

$(document).ready(function() {
	tblClaimDt = $("#tblClaim").dataTable({
		"lengthMenu": [[10,15,20, 25, 50, 100], [10,15,20, 25, 50, 100]],
		"aoColumns" : [ 
			{ "mData" : "claimNo"  },
			{ "mData" : "employeeCode" },
			{ "mData" : "center" },
			{ "mData" : "dispatchDate" },
			{ "mData" : "claimType" },
			{ "mData" : "surveyTip"},
			{ "mData" : "surveyEmp"},
			{ "mData" : "claimId",
				"bSortable": false,
				"mRender" : function (data, type, full) {
					return '<button id="btnClaimDetail" class="btn-info" type="button" onclick="openclaimDetailModal(' + data + ');">รายละเอียด</button>';
				}	
			}
		   ],
			columnDefs: [
			    { type: 'date-dd/mm/yyyy', targets:  [1]}
			],
			"processing": true,
            "serverSide": true,
            "bSort" : false,
            "bFilter": false,
            "ajax": {
                "url": '${pageContext.request.contextPath}/claim/search',
                "type": "POST",
                "data": function ( d ) {
                     d.txtDispatchDateStart =  $("#divParamSearch").find("#txtDispatchDateStart").val(),  
                     d.txtDispatchDateEnd   =  $("#divParamSearch").find("#txtDispatchDateEnd").val(),  
                     d.txtClaimNo           =  $("#divParamSearch").find("#txtClaimNo").val(),  
                     d.selEmployee          =  $("#divParamSearch").find("#selEmployee").val(), 
                     d.selClaimStatus       =  $("#divParamSearch").find("#selClaimStatus").val(), 
                     d.firstTime            =  firstTime
                }
            },
            "fnDrawCallback" : function() {
            	firstTime = false;
            }
	});
});

function searchClaim(){
	if(!validate("divParamSearch")){
		return;
	}
	
	delay(function(){
		tblClaimDt.fnDraw();
	}, 500 );
	
	isSearch = true;
}

function openclaimDetailModal(claimId){
	setPageForClaimDetail(claimId);
	$('#modalClaimDetail').modal(
		{
			backdrop:'static'
		}
	);
}
</script>

<div id='msgbox' title='' style='display:none'></div>

<jsp:include page = "modalClaimDetail.jsp" flush="false"/>