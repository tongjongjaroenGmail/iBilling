function getEmployeeFromBranch( branchId , elementId,hasAll){
	var datas = 'branchId='+branchId +'&seed='+ (new Date()).getTime();
	$(function(){
		$.ajax({
			url: 'combobox/getEmployeeFromBranch.jsp',
			data : datas,
			async: false,
			success: function(getData){
				var returnString = $.trim(getData);
				$("#" + elementId).text("");
				if(hasAll){
					$("#" + elementId).append("<option value=''>ทั้งหมด</option>");
				}
				$("#" + elementId).append(returnString);
			}
		}).responseText;
	});
}

