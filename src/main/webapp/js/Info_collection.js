$(document).ready(function(){
	//设置标题身份证信息到状态进度条的top
//	$("h4:first").css("margin-top","-100px");
//	设置state中最后一张图片的位置
	$("#state img").last().css("margin-left","-11%");
//	设置#state中span属性
	$("#state span").css({"color":"#f69d1f","width":"120px","text-align":"center"});
	$(".Message tr td").css({"color":"#389ac7","font-size":"16px","height":"30px","text-align":"left","margin-left":"4%"});
	//设置第一列的样式
	$(".Message td:first").css({"width":"30%","height":"7%"});
	$(".Message tr td :input").css({"width":"37%","color":"#080808","text-align":"center",
		"font-family":"微软雅黑","font-size":"large","border":"hidden","background":"#fff"});
	//	设置嫌疑人信息表格中身份证住址
	$(".Message textarea").css({"width":"70%","margin-top":"0%","margin-left": "0%"});
	//设置最后一个input标签的属性样式
	$(".Message tr td :input").last().css({"width":"80%","text-align":"left"});
	
	//设置第二个.human_Mes 标题的样式
	$(".human_Mes:eq(0)").css("margin-top","0%");
	
	//设置信息采集起止时间
	$(".check_time input[type=date]").css({"width":"160px","margin-left":"3%"});
	//设置所有.human_mes中span标签的属性样式
	$(".human_Mes span").css({"color":"rgb(211,26,23)","font-size":"small","margin-left":"90%","margin-top":"-28px"});
	$(".human_Mes input").css({"color":"#f69d1f","width":"300px","height":"30px","background":"none",
	"border":"none","margin-left":"1%"});
//	设置采集信息第二行第二列以后的字体颜色
	$(".info_collect tr:eq(1)").find("td:gt(0)").css("color","#101010");
	


});


