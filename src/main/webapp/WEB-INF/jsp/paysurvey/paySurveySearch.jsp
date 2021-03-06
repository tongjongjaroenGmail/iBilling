<%@page import="com.metasoft.ibilling.model.ClaimStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>ค้นหาข้อมูลใบสำรวจ</h1>
	</div>
	<!-- /.page-header -->

<div id="divParamSearch">
	<!-- PAGE CONTENT BEGINS -->
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>วันที่สร้างใบจ่ายเงิน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtPaySurveyDateStart" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
						<input class="form-control date-picker" id="txtPaySurveyDateEnd" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
						<b>ศูนย์ : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selBranch" onchange="getEmployeeFromBranch($(this).val(),'selEmployee',false);">
							<option value=""></option>
							<c:forEach var="branchData" items="${branchs}" varStatus="index">		
								<option value="${branchData.id}">${branchData.name}</option>						
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>พนักงาน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selEmployee" title="พนักงาน">						
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
						<b>เลขที่จ่าย : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input type="text" id="txtPaySurveyCode">
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
					<button class="btn btn-info" type="button" id="btnSearch" onclick="searchPaySurvey();">
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
		<table id="tblPaySurvey" class="table table-striped table-bordered table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th><label><input name="chkAll" class="ace" type="checkbox" onclick="checkSelect(this,document.getElementsByName('chk'));"><span class="lbl"></span></label></th>
					<th>เลขที่จ่าย</th>
					<th>วันที่สร้างใบจ่ายเงิน</th>
					<th>พนักงาน</th>
					<th>ค่าพาหนะ</th>
					<th>ค่ารูป</th>
					<th>ค่าโทรศัพท์</th>
					<th>ค่าเรียกร้อง</th>
					<th>ค่าประจำวัน</th>
					<th>ยอดรวม</th>
					<th>รายละเอียด</th>
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
			<div class="table-responsive">
				<div class="col-sm-12">
					<button class="btn btn-info" type="button" id="btnPaysurveyPrint" onclick="printPaySurvey();">
						<i class="icon-file"></i> พิมพ์ใบจ่ายค่าสำรวจ
					</button>
				</div>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
	</div>
</div>
<!-- /.page-content -->

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

var tblPaySurveyDt;
var firstTime = true;
var isSearch = false;

$(document).ready(function() {
	tblPaySurveyDt = $("#tblPaySurvey").dataTable({
		"lengthMenu": [[10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000], [10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000]],
		"aoColumns" : [ 
		    {   "mData" : "paySurveyId",
				"bSortable": false,
				'sWidth': '30px',
				"mRender" : function (data, type, full) {
					return '<input name="chk" class="ace" type="checkbox" value="' + data + '"><span class="lbl"></span></label>';
				}	
			},	
			{ "mData" : "paySurveyCode"  },
			{ "mData" : "paySurveyDate" },
			{ "mData" : "employeeCode" },
			{ "mData" : "surveyTrans"},
			{ "mData" : "surveyPhoto"},
			{ "mData" : "surveyTel"},
			{ "mData" : "surveyClaim"},
			{ "mData" : "surveyDaily"},
			{ "mData" : "surveyTotal"},
			{ "mData" : "paySurveyId",
				"bSortable": false,
				"mRender" : function (data, type, full) {
					return '<button id="btnPaySurveyDetail" class="btn-info" type="button" onclick="openPaySurveyDetailModal(' + data + ');">รายละเอียด</button>';
				}	
			}
		   ],
			columnDefs: [
			    { type: 'date-dd/mm/yyyy', targets:  [2]}
			],
			"processing": true,
            "serverSide": true,
            "bSort" : false,
            "bFilter": false,
            "ajax": {
                "url": '${pageContext.request.contextPath}/paysurvey/search',
                "type": "POST",
                "data": function ( d ) {
                     d.txtPaySurveyDateStart =  $("#divParamSearch").find("#txtPaySurveyDateStart").val(),  
                     d.txtPaySurveyDateEnd   =  $("#divParamSearch").find("#txtPaySurveyDateEnd").val(),  
                     d.selEmployee           =  $("#divParamSearch").find("#selEmployee").val(),
                     d.txtPaySurveyCode      =  $("#divParamSearch").find("#txtPaySurveyCode").val(),
                     d.firstTime             =  firstTime
                }
            },
            "fnDrawCallback" : function() {
            	firstTime = false;
            }
	});
});

function searchPaySurvey(){
	if(!validate("divParamSearch")){
		return;
	}
	
	delay(function(){
		tblPaySurveyDt.fnDraw();
	}, 500 );
	
	isSearch = true;
}

function openPaySurveyDetailModal(paySurveyId){
	setPageForDetailPaySurvey(paySurveyId);
	$('#modalPaySurveyDetail').modal(
		{
			backdrop:'static'
		}
	);
}

function printPaySurvey(){
	if($("[name='chk']:checked").size() == 0){
		alert("กรุณาเลือกรายการใบจ่ายค่าสำรวจ");
		return false;
	}
	download();
}

function exportFile(token){	
	var paySurveyIds = "";
	$("[name='chk']:checked").each(function() {
		paySurveyIds += "," + $(this).val();
	});
	
	var param = "token=" + token + "&paySurveyIds=" + paySurveyIds.substring(1);
	window.location = '${pageContext.request.contextPath}/report/paySurvey?' + param;
}
</script>

<div id='msgbox' title='' style='display:none'></div>

<jsp:include page = "modalPaySurveyDetail.jsp" flush="false"/>