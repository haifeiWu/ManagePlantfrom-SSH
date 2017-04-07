$(document).ready(function(){
	//	设置state中最后一张图片的位置
	$("#state img").last().css("margin-left","-10%");
//	设置#state中span属性
	$("#state span").css({"color":"#f69d1f","width":"120px","text-align":"center"});
//	设置嫌疑人表的样式
	$(".Message tr td").css({"color":"#389ac7","font-size":"16px","height":"30px","text-align":"left","margin-left":"4%"});
//设置第一列的样式
//$(".Message td:first").css({"width":"30%","height":"7%"});
$(".Message tr td :input").css({"width":"36%","color":"#080808","text-align":"center",
	"font-family":"微软雅黑","font-size":"large","border":"hidden","background":"#fff"});
//	设置嫌疑人信息表格中身份证住址
$(".Message textarea").css({"width":"350px","margin-top":"0%","margin-left": "0%"});
//设置最后一个input标签的属性样式
$(".Message tr td :input").last().css({"width":"80%","text-align":"left"});
//设置人身检查起止时间
$(".check_time input[type=date]").css({"width":"160px","margin-left":"3%"});
//设置自述请况表格样式
$(".zishu_state textarea").css({"width":"150%","height":"120px","margin-left":"22%"});
$(".zishu_state tr td:lt(2)").css("width","25%");
$(".zishu_state tr").css({"height":"30px","font-size":"inhert"});
//检查情况样式表
$(".checked_state textarea").css({"width":"100%","margin":"0px","height":"200px"});
$(".checked_state input[type=checkbox]").css({"width":"20px","margin-top":"0%"});
//设置所有.human_mes中span标签的属性样式
//设置第二个.human_Mes 标题的样式
$(".human_Mes:eq(0)").css("margin-top","0%");
$(".human_Mes span").css({"color":"rgb(211,26,23)","font-size":"small","margin-left":"90%","margin-top":"-28px"});
$(".human_Mes input").css({"color":"#f69d1f","width":"80px","height":"30px","background":"none",
"border":"none","margin-left":"1%"});
//随身财物检查登记报表
$(".woods_check tr:first").css({"background":"#0070c0","text-align":"center","color":"#ffffff"});

$(".woods_check td:eq(0)").css("width","5%");

$(".woods_check td:eq(1)").css("width","25%");

$(".woods_check input").css({"width":"60%","height":"26px","background":"#fff","border":"none","text-align":"center"});

// 设置管理员签字的样式
$(".signature li").css({"color":"#389ac7","font-size":"large"});
$(".signature input").css({"border":"none","height":"30px","border-bottom":"1.5px solid #389ac7"});
});