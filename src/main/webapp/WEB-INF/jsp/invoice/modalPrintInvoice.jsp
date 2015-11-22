<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="modalPrintInvoice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="overflow-x: auto; overflow-y: auto;">
	<div class="modal-dialog" style="width: 1100px">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h5 class="modal-title" id="modalSaveHeaderLabel">รายละเอียดวางบิล</h5>
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
									<input class="form-control require" id="txtInvoiceNo" type="text" maxlength="20" title="เลขที่วางบิล" readonly="readonly"/> 
								</div>
							</div>
							
							<div class="col-sm-3 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
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
				<button type="button" class="btn btn-success" id="btnPrintInvoice" onclick="printInvoice();">พิมพ์ใบวางบิล</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">    
var tblGroupInvoiceDt;

function setPageForDetailInvoice(invoiceId){
	$("#modalPrintInvoice").find('input,textarea,select').each(function() {
        $(this).val("");
    }); 
	
	var table = $('#tblGroupInvoice').DataTable();	 
	table.clear().draw();
	
	$.ajax({
		url : '${pageContext.request.contextPath}/invoice/find?id=' + invoiceId,
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(result) {
			$("#hiddenInvoiceId").val(result.invoiceId);
			$("#txtInvoiceNo").val(result.invoiceCode);
			$("#txtCreateDate").val(result.invoiceCreateDate);

			for ( var i = 0; i < result.claims.length; i++) {
				var item = result.claims[i];
				table.row.add( {
					 "rowNumber" : (i + 1)  ,
					 "claimNo" : item.claimNo ,
					 "surInvest" : item.surInvest,
					 "surTrans" : item.surTrans,
					 "surDaily" : item.surDaily,
					 "surPhoto" : item.surPhoto,
					 "surClaim" : item.surClaim,
					 "surTel" : item.surTel,
					 "surInsure" : item.surInsure,
					 "surTowcar" : item.surTowcar,
					 "surOther" : item.surOther,
					 "surTotal" : item.surTotal
				    } ).draw();
			}
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
			{ "mData" : "rowNumber"},
			{ "mData" : "claimNo"},
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
});
</script>