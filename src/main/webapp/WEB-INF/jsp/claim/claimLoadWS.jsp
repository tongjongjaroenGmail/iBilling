<%@page import="com.metasoft.ibilling.model.ClaimStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>โหลดเคลม</h1>
	</div>
	<!-- /.page-header -->

<div id="divParamSearch">
	<!-- PAGE CONTENT BEGINS -->
	<div class="row">
		<div class="col-sm-12">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>วันที่โหลด : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtLoadDate" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
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
					<button class="btn btn-info" type="button" id="btnSearch" onclick="claimLoadWS();">
						<i class="icon-download"></i> โหลด
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

function claimLoadWS(){
	if($("#txtLoadDate").val() == ""){
		alert("กรุณาเลือก วันที่โหลด");
		return;
	}
	
	$.ajax({
		url : '${pageContext.request.contextPath}/claim/claimLoadWS?txtLoadDate=' + $("#txtLoadDate").val() ,
		dataType: 'json',
		data : "",
		contentType: 'application/json',
	    mimeType: 'application/json',
		success : function(data) {
			alert(data.message);
		}
	});
}
</script>

<div id='msgbox' title='' style='display:none'></div>

<jsp:include page = "modalClaimDetail.jsp" flush="false"/>