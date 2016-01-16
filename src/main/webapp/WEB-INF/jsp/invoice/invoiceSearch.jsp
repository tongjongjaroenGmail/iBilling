<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>ค้นหาข้อมูลวางบิล</h1>
	</div>
	<!-- /.page-header -->

<div id="divParamSearch">
	<!-- PAGE CONTENT BEGINS -->
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>วันที่สร้างเลขวางบิล : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtCreateDateStart" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
						<input class="form-control date-picker" id="txtCreateDateEnd" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
						<b>เลขที่วางบิล : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input type="text" id="txtInvoiceCode">
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
					<button class="btn btn-info" type="button" id="btnSearch" onclick="searchInvoice();">
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
		<table id="tblInvoice" class="table table-striped table-bordered table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th>เลขที่วางบิล</th>
					<th>วันที่สร้างเลขวางบิล</th>
					<th>ยอดใบวางบิล</th>
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

var tblInvoiceDt;
var firstTime = true;
var isSearch = false;

$(document).ready(function() {
	tblInvoiceDt = $("#tblInvoice").dataTable({
		"lengthMenu": [[10,15,20, 25, 50, 100], [10,15,20, 25, 50, 100]],
		"aoColumns" : [ 
			{ "mData" : "invoiceCode"  },
			{ "mData" : "createDate" },
			{ "mData" : "total" ,
			  "mRender" : function (data, type, full) {
				return addCommas(data.toFixed(2));					
			  }
			},
			{ "mData" : "invoiceId",
				"bSortable": false,
				"mRender" : function (data, type, full) {
					return '<button id="btnInvoiceDetail" class="btn-info" type="button" onclick="openInvoiceDetailModal(' + data + ');">รายละเอียด</button>';
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
                "url": '${pageContext.request.contextPath}/invoice/search',
                "type": "POST",
                "data": function ( d ) {
                     d.txtCreateDateStart =  $("#divParamSearch").find("#txtCreateDateStart").val(),  
                     d.txtCreateDateEnd   =  $("#divParamSearch").find("#txtCreateDateEnd").val(),  
                     d.txtInvoiceCode  		=  $("#divParamSearch").find("#txtInvoiceCode").val(),  
                     d.firstTime            =  firstTime
                }
            },
            "fnDrawCallback" : function() {
            	firstTime = false;
            }
	});
});

function searchInvoice(){
	delay(function(){
		tblInvoiceDt.fnDraw();
	}, 500 );
	
	isSearch = true;
}

function openInvoiceDetailModal(invoiceId){
	setPageForDetailInvoice(invoiceId);
	$('#modalPrintInvoice').modal(
		{
			backdrop:'static'
		}
	);
}
</script>

<div id='msgbox' title='' style='display:none'></div>

<jsp:include page = "modalPrintInvoice.jsp" flush="false"/>