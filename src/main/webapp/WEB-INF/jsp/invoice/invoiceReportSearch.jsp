<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/report/download/token" var="downloadTokenUrl"/>
<c:url value="/report/download/progress" var="downloadProgressUrl"/>

<form action="${pageContext.request.contextPath}/invoice/report/export" method="post">
<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>รายงานการจัดชุดวางบิล
		</h1>
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
						<input class="form-control date-picker" name="txtDispatchDateStart" id="txtDispatchDateStart" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
						<input class="form-control date-picker" name="txtDispatchDateEnd" id="txtDispatchDateEnd" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
						<b>สาขาทิพย : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selBranchDhip" name="selBranchDhip">
							<option value="">ทั้งหมด</option>
							<c:forEach var="branchDhip" items="${branchDhips}" varStatus="index">		
								<option value="${branchDhip.id}">${branchDhip.name}</option>					
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>ประเภท : </b> 
					</div>
				</div>
				
				<div class="col-sm-3">	
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selType" name="selType">
							<option value="">ทั้งหมด</option>
							<option value="1">ยังไม่ได้จัดชุด</option>
							<option value="2">จัดชุดแล้ว</option>
						</select>
					</div>
				</div>
				
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>

</div>	
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
			<div class="table-responsive">
				<div class="col-sm-12">
					<button class="btn btn-info" type="button" id="btnSearch" onclick="search();">
						<i class="icon-search"></i> ค้นหา
					</button>
					
					<button class="btn btn-success" type="button" id="btnExport" onclick="download();">
						<i class="icon-file"></i> พิมพ์
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
					<th>สาขาทิพย</th>	
					<th>วันที่จ่ายงาน</th>
					<th>ประเภท</th>
					<th>ค่าสำรวจรวม vat</th>
					<th>สถานะ</th>
					<th>เลขที่จัดชุด</th>				
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>
	</div>
	
	<div class="space-4"></div>

</div>
<!-- /.page-content -->

<div class="modal fade" id="modalDownload" tabindex="-1" role="dialog" aria-labelledby="modalDownload"
	aria-hidden="true" style="overflow-x: hidden; overflow-y: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				
			</div>
			<div class="modal-body">
				Processing download...
			</div>
			<div class="modal-footer">
				
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

var tblClaim;
var firstTime = true;
var pageName = "billing"

$(document).ready(function() {
	tblClaim = $("#tblClaim").dataTable(
				{
					"lengthMenu": [[10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000], [10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000]],
					'bAutoWidth': true , 
					"aoColumns" : [
								{ "mData" : "claimNo"  },	
								{ "mData" : "branchDhipName"},	
								{ "mData" : "dispatchDate"},	
								{ "mData" : "type" },
								{ "mData" : "surveyTotal",
									"mRender" : function (data, type, full) {
										return addCommas(data);
									}	
								},
								{ "mData" : "claimStatus" },
								{ "mData" : "invoiceNo" }
							   ],
				columnDefs: [{ type: 'date-dd/mm/yyyy', targets: 2 }],
				"processing": true,
                "serverSide": true,
                "bSort" : false,
                "bFilter": false,
                "scrollX": true,
                "ajax": {
                	"url": '${pageContext.request.contextPath}/invoice/report/search',
                    "type": "POST",
                    "data": function ( d ) {
                         d.paramDispatchDateStart   =  $("#divParamSearch").find("#txtDispatchDateStart").val(),  
                         d.paramDispatchDateEnd     =  $("#divParamSearch").find("#txtDispatchDateEnd").val(),  
                         d.paramBranchDhip       	=  $("#divParamSearch").find("#selBranchDhip").val(),
                         d.paramType       			=  $("#divParamSearch").find("#selType").val(),
                         d.paramFirstTime           =  firstTime
                    }
                },
                "fnDrawCallback" : function() {
                	firstTime = false;
                	$("[name='chkAll']").prop( "checked", false );
                	countTotalSelect();
                }
	});
});

function countTotalSelect()
{
	$("#lblTotalSelect").html($("[name='chk']:checked").size());
}
	
function search(){
	delay(function(){
		tblClaim.fnDraw();
	}, 1000 );
}


function download() {
	// Retrieve download token
	// When token is received, proceed with download
	$.get('${downloadTokenUrl}', function(response) {
		// Store token
		var token = response.message[0];
		// Show progress dialog
		$('#modalDownload').modal('show');
		
		// Start download
		exportFile(token);

		// Check periodically if download has started
		var frequency = 1000;
		var timer = setInterval(function() {
			$.get('${downloadProgressUrl}', {token: token}, 
					function(response) {
						// If token is not returned, download has started
						// Close progress dialog if started
						if (response.message[0] != token) {
							$('#modalDownload').modal('hide');
							clearInterval(timer);
						}
				});
		}, frequency);
		
	});
}

function exportFile(token){
	$("#token").val(token);
	
	var form = document.forms[0];
	form.submit();
}


</script>

<div id='msgbox' title='' style='display:none'></div>
<input type="hidden" id="token" name="token">
</form>