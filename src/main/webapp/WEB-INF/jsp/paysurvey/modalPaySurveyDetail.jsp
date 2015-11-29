<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="modalPaySurveyDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="overflow-x: auto; overflow-y: auto;">
	<div class="modal-dialog" style="width: 1100px">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h5 class="modal-title" id="modalSaveHeaderLabel">รายละเอียดใบจ่ายค่าสำรวจ</h5>
			</div>
			
			<div class="modal-body" style="padding: 5px;">	
				<input type="hidden" id="hiddenPaySurveyId">
				
				<div class="row">
					<div class="col-sm-12">
						<div class="table-responsive">
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
									<b>เลขที่ใบจ่าย : </b> 
								</div>
							</div>
							
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right">
									<input class="form-control require" id="txtPaySurveyNo" type="text" maxlength="20" readonly="readonly"/> 
								</div>
							</div>
							
							<div class="col-sm-3 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: left;">
								</div>
							</div>
							
							<div class="col-sm-2 no-padding-left">		
								<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
									<b>วันที่สร้างใบจ่าย : </b>
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
					<table id="tblClaim" class="table table-striped table-bordered table-hover" style="width: 100%;">
						<thead>
							<tr>
								<th></th>
								<th>เลขเคลม</th>
								<th>วันที่จ่ายงาน</th>
								<th>ค่าบริการ</th>
								<th>ค่าพาหนะ</th>
								<th>ค่าประจำวัน</th>
								<th>ค่ารูป</th>					
								<th>ค่าเรียกร้อง</th>
								<th>ค่าโทรศัพท์</th>
								<th>ค่าเงื่อนไขฝ่ายถูก</th>
								<th>ค่าใช้จ่ายอื่นๆ</th>
								<th>ค่าปรับ</th>
								<th>ยอดรวม</th>
							</tr>
						</thead>
			
						<tbody>
			
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="modal-footer">
	
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">    
var tblGroupPaySurveyDt;

function setPageForDetailPaySurvey(paySurveyId){
	$("#modalPaySurveyDetail").find('input,textarea,select').each(function() {
        $(this).val("");
    }); 
	
	var table = $('#tblClaim').DataTable();	 
	table.clear().draw();
	
	$.ajax({
		url : '${pageContext.request.contextPath}/paysurvey/find?id=' + paySurveyId,
		contentType : 'application/json',
		mimeType : 'application/json',
		success : function(result) {
			$("#hiddenPaySurveyId").val(result.id);
			$("#txtPaySurveyNo").val(result.code);
			$("#txtCreateDate").val(result.createDate);
			
			for ( var i = 0; i < result.claims.length; i++) {
				var item = result.claims[i];
				table.row.add( {
					 "rowNumber" : (i + 1)  ,
					 "claimNo" : item.claimNo ,
					 "dispatchDate"  : item.dispatchDate ,
					 "surveyInvest" : item.surveyInvest,
					 "surveyTrans" : item.surveyTrans,
					 "surveyDaily" : item.surveyDaily,
					 "surveyPhoto" : item.surveyPhoto,
					 "surveyClaim" : item.surveyClaim,
					 "surveyTel" : item.surveyTel,
					 "surveyConditionRight" : item.surveyConditionRight,
					 "surveyOther" : item.surveyOther,
					 "surveyFine" : item.surveyFine,
					 "surveyTotal" : item.surveyTotal
				    } ).draw();
			}
		}
	});
}

$(document).ready(function() {
	tblGroupPaySurveyDt = $("#tblClaim").dataTable({
		"info": false,
		"paging": false,
		"ordering": false,
		"searching": false,
		"aoColumns" : [ 
			{ "mData" : "rowNumber"},
			{ "mData" : "claimNo"},
			{ "mData" : "dispatchDate"},
			{ "mData" : "surveyInvest"},
			{ "mData" : "surveyTrans"},
			{ "mData" : "surveyDaily"},
			{ "mData" : "surveyPhoto"},
			{ "mData" : "surveyClaim"},
			{ "mData" : "surveyTel"},
			{ "mData" : "surveyConditionRight"},
			{ "mData" : "surveyOther"},
			{ "mData" : "surveyFine"},
			{ "mData" : "surveyTotal"}
		   ],
	});
});

</script>