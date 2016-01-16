function getEmployeeFromBranch( branchId , elementId){
	var datas = 'branchId='+branchId +'&seed='+ (new Date()).getTime();
	$(function(){
		$.ajax({
			url: 'combobox/getEmployeeFromBranch.jsp',
			data : datas,
			async: false,
			success: function(getData){
				var returnString = $.trim(getData);
				$("#" + elementId).text("");
				$("#" + elementId).append(returnString);
			}
		}).responseText;
	});
}

