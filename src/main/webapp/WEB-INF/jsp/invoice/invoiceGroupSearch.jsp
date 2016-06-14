<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>จัดชุดวางบิล (ทิพยอนุมัติ)</h1>
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
						<b>สาขา : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selBranch">
							<option value=""></option>
							<c:forEach var="branch" items="${branchDhips}" varStatus="index">		
								<option value="${branch.id}">${branch.name}</option>					
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
					<th><label><input name="chkAll" class="ace" type="checkbox" onclick="checkSelect(this,document.getElementsByName('chk'));countTotalSelect();"><span class="lbl"></span></label></th>
					<th>เลขเคลม</th>
					<th>สาขา</th>
					<th>วันที่จ่ายงาน</th>
					<th>ค่าสำรวจ</th>
					<th>ค่าสำรวจรวม Vat</th>
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
					<b>จำนวนเคลมที่เลือก 
						<label id="lblTotalSelect" style="font-size: 20px">0</label> 
						เคลม 
						ค่าสำรวจรวม 
						<label id="lblTotalSurveySelect" style="font-size: 20px">0</label>
						บาท
					</b>
				</div>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
			<div class="table-responsive">
				<div class="col-sm-12">
					<button class="btn btn-success" type="button" id="btnGroupInvoice">
						<i class="icon-file"></i> จัดชุดวางบิล (ทิพยอนุมัติ)
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

var tblClaimDt;
var firstTime = true;
var isSearch = false;

function countTotalSelect()
{
	$("#lblTotalSelect").html($("[name='chk']:checked").size());
	var totalSurvey = 0;
	
	$("#tblClaim tbody").find('tr').each(function() {
		 if($(this).find("[name='chk']").is( ":checked" )){
			 // get the position of the current data from the node
	        var aPos = tblClaimDt.fnGetPosition( this );

	        // get the data array
	        var aData = tblClaimDt.fnGetData( aPos[0] );

	        // get departmentID for the row
	        var surveyPrice = aData[aPos].surveyPrice;
	        totalSurvey += parseFloat(surveyPrice);
		 }
	});                          

	$("#lblTotalSurveySelect").html(addCommas(totalSurvey.toFixed(2)));
}

$(document).ready(function() {
	tblClaimDt = $("#tblClaim").dataTable({
			"lengthMenu": [[10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000], [10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000]],
			"aoColumns" : [ 
			    {   "mData" : "claimId",
					"bSortable": false,
					'sWidth': '30px',
					"mRender" : function (data, type, full) {
						return '<input name="chk" class="ace" type="checkbox" onclick="countTotalSelect();" value="' + data + '"><span class="lbl"></span></label>';
					}	
				},
				{ 	"mData" : "claimNo"  ,
					'sWidth': '10%'
				},
				{ 	"mData" : "branchName"  ,
					'sWidth': '20%'
				},
				{ "mData" : "dispatchDate" },
				{ "mData" : "surTotal"},
				{ "mData" : "surveyPrice",
				  "mRender" : function (data, type, full) {
						return addCommas(data.toFixed(2)) + "<input type='hidden' name='hdnSurveyPrice' value='" + data + "'/>";
					}					
				},
				
				{ "mData" : "surInvest"},
				{ "mData" : "surTrans"},
				{ "mData" : "surDaily"},
				{ "mData" : "surPhoto"},
				{ "mData" : "surClaim"},
				{ "mData" : "surTel"},
				{ "mData" : "surInsure"},
				{ "mData" : "surTowcar"},
				{ "mData" : "surOther"}
			   ],
				columnDefs: [
				    { type: 'date-dd/mm/yyyy', targets:  [5]},
		            {
		                "targets": [ 6,7,8,9,10,11,12,13,14 ],
		                "visible": false
		            }
				],
				"processing": true,
                "serverSide": true,
                "bSort" : false,
                "bFilter": false,
                "ajax": {
                    "url": '${pageContext.request.contextPath}/invoiceGroup/search',
                    "type": "POST",
                    "data": function ( d ) {
                         d.txtDispatchDateStart       =  $("#divParamSearch").find("#txtDispatchDateStart").val(),  
                         d.txtDispatchDateEnd         =  $("#divParamSearch").find("#txtDispatchDateEnd").val(),  
                         d.selBranch   =  $("#divParamSearch").find("#selBranch").val(),  
                         d.selClaimStatus   =  '3',  
                         d.firstTime          =  firstTime
                    }
                },
                "fnDrawCallback" : function() {
                	firstTime = false;

            		countTotalSelect();
                }
	});
	
	$( "#btnGroupInvoice" ).click(function() {	
		if(groupInvoice()){		
			setPageForGroupInvoice();
			
			$('#modalGroupInvoice').modal(
				{
					backdrop:'static'
				}
			);
		}
	});
});

function searchClaim(){
	if($("#selBranch").val() == ""){
		alert("กรุณาเลือก สาขา");
		$("#selBranch").focus();
		return;
	}
	
	delay(function(){
		tblClaimDt.fnDraw();
	}, 500 );
	
	isSearch = true;
}


function groupInvoice(){
	if($("[name='chk']:checked").size() == 0){
		alert("กรุณาเลือกรายการเคลม");
		return false;
	}
	/*
	var totalSurveySelect = parseFloat(delCommas($("#lblTotalSurveySelect").html()));
	if(totalSurveySelect > 30000){
		alert("ค่าสำรวจรวมมากกว่า 30,000 บาท");
		return false;
	}
	*/
	return true;
}
</script>

<div id='msgbox' title='' style='display:none'></div>

<jsp:include page = "modalGroupInvoice.jsp" flush="false"/>