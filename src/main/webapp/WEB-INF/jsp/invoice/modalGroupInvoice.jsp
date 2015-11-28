<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="modalGroupInvoice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="overflow-x: auto; overflow-y: auto;">
	<div class="modal-dialog" style="width: 1100px">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h5 class="modal-title" id="modalSaveHeaderLabel">จัดชุดวางบิล</h5>
			</div>
			
			<div class="modal-body" style="padding: 5px;">	
				<input type="hidden" id="hiddenInvoiceId">
				
				<div class="row">
					<div class="col-sm-12">
						<div class="table-responsive">
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
									<b>เลขที่วางบิล : </b> 
								</div>
							</div>
							
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right">
									<input class="form-control require" id="txtInvoiceNo" type="text" maxlength="20" title="เลขที่วางบิล"/> 
								</div>
							</div>
							
							<div class="col-sm-3 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
									<button type="button" class="btn btn-success" id="btnSaveInvoice">สร้างเลขวางบิล</button>
								</div>
							</div>
							
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
									<b>วันที่สร้างเลขวางบิล : </b>
								</div>
							</div>
							
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right">
									<input class="form-control" id="txtCreateDate" type="text" readonly="readonly"/> 
								</div>
							</div>
							
						</div>
					</div>		
				</div>
				
				<div class="table-responsive">
					<br> <br>
					<table id="tblGroupInvoice" class="table table-striped table-bordered table-hover" style="width: 100%;">
						<thead>
							<tr>
								<th></th>
								<th>เลขเคลม</th>
								<th>ค่าบริการ</th>
								<th>ค่าพาหนะ</th>
								<th>ค่าประจำวัน</th>
								<th>ค่ารูป</th>
								<th>ค่าเรียกร้อง</th>
								<th>ค่าโทรศัพท์</th>
								<th>ค่าประกันตัว</th>
								<th>ค่ารถยก</th>
								<th>ค่าใช้จ่ายอื่นๆ</th>
								<th>ยอดรวมภาษี</th>
							</tr>
						</thead>
			
						<tbody>
			
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-success" id="btnPrintInvoice" onclick="download();" disabled="disabled">พิมพ์ใบวางบิล</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">    
var tblGroupInvoiceDt;

function setPageForGroupInvoice(){
	$("#modalGroupInvoice").find('input,textarea,select').each(function() {
        $(this).val("");
    }); 
	
	var table = $('#tblGroupInvoice').DataTable();
	 
	table.clear().draw();
	
	$('#btnSaveInvoice').prop('disabled', false);
	$('#btnPrintInvoice').prop('disabled', true);
	$('#hiddenInvoiceId').val("");
		
	var i = 1;
	
	$("#tblClaim tbody").find('tr').each(function() {
		 if($(this).find("[name='chk']").is( ":checked" )){			 
			 // get the position of the current data from the node
		     var aPos = tblClaimDt.fnGetPosition( this );
	         // get the data array
	         var aData = tblClaimDt.fnGetData( aPos[0] );
		        
			 table.row.add( {
				 "rowNumber" : i++  ,
				 "claimNo" : aData[aPos].claimNo ,
				 "surInvest" : aData[aPos].surInvest,
				 "surTrans" : aData[aPos].surTrans,
				 "surDaily" : aData[aPos].surDaily,
				 "surPhoto" : aData[aPos].surPhoto,
				 "surClaim" : aData[aPos].surClaim,
				 "surTel" : aData[aPos].surTel,
				 "surInsure" : aData[aPos].surInsure,
				 "surTowcar" : aData[aPos].surTowcar,
				 "surOther" : aData[aPos].surOther,
				 "surTotal" : aData[aPos].surTotal
			    } ).draw();
		 }
	});          
}

$(document).ready(function() {
	tblGroupInvoiceDt = $("#tblGroupInvoice").dataTable({
		"info": false,
		"paging": false,
		"ordering": false,
		"searching": false,
		"aoColumns" : [ 
			{ "mData" : "rowNumber"  },
			{ "mData" : "claimNo"  },
			{ "mData" : "surInvest"},
			{ "mData" : "surTrans"},
			{ "mData" : "surDaily"},
			{ "mData" : "surPhoto"},
			{ "mData" : "surClaim"},
			{ "mData" : "surTel"},
			{ "mData" : "surInsure"},
			{ "mData" : "surTowcar"},
			{ "mData" : "surOther"},
			{ "mData" : "surTotal"}
		   ],
	});
	
	$( "#btnSaveInvoice" ).click(function() {
		if(!validate("modalGroupInvoice")){
			return;
		}
		
		var claimIds = "";
		$("#tblClaim tbody").find('tr').each(function() {
			 if($(this).find("[name='chk']").is( ":checked" )){			 
				 // get the position of the current data from the node
			     var aPos = tblClaimDt.fnGetPosition( this );
		         // get the data array
		         var aData = tblClaimDt.fnGetData( aPos[0] );
			        
		         claimIds += "," + aData[aPos].claimId;
			 }
		});     
		
		var requestData = new Object();
		requestData.claimIds = claimIds.substring(1);
		requestData.invoiceNo = $("#txtInvoiceNo").val();

		$.ajax({
			url : '${pageContext.request.contextPath}/invoice/save',
			dataType: 'json',
			data : requestData,
			contentType: 'application/json',
		    mimeType: 'application/json',
			success : function(data) {
				if(data.message != ""){			
					alert(data.message);
				}
				
				if(data.error == false && data.data != 0){
					$('#btnPrintInvoice').prop('disabled',false);
					$('#btnSaveInvoice').prop('disabled', true);
					$('#hiddenInvoiceId').val(data.data);
					
					searchClaim();
				}
			}
		});
	});
});

function exportFile(token){
	var param = "token=" + token;
	$("#divParamSearch").find('input,textarea,select').each(function() {
		param += "&invoiceId=" + $("#hiddenInvoiceId").val();
    });  
     
	window.location = '${pageContext.request.contextPath}/report/invoice?' + param;
}
</script>