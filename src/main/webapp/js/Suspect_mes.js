
$(document).ready(function(){
	//设置btn_search搜索框样式
	$("#btn_search img").css("margin-left","16%");
	//设置待检查嫌疑人表格样式
	$(".wait_check input[type='text']").css({"width":"70%","border":"none","background":"none",
	"font-size":"large","margin-top":"1%"});
	
	$(".wait_check input[type='time']").css({"width":"30%","border":"none","background":"none",});
	
	//	设置历史嫌疑人查询表格样式
	$(".total li:first").css("width","16.5%");
	$(".total li").next().css("width","28%");
	$(".total li").eq(2).css("width","14%");
	$(".total li").last().css("width","41.5%");
	$(".All_total tr:first").css("color","#FFFFFF");
	$(".All_total input").css({"border":"none","text-align":"center"});
	//设置。human_Mes距离搜索框的距离
	$(".human_Mes:eq(0)").css("margin-top","0%");
	
});
