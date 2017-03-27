$(document).ready(function(){
	//	设置state中最后一张图片的位置
	$("#state img").last().css("margin-left","-11%");
	//	设置#state中span属性
	$("#state span").css({"color":"#f69d1f","width":"120px"});
	//设置嫌疑人表格中的信息
	$(".Message td:first").css({"width":"30%","height":"7%"});
	
	$(".pic").find("img :first-child").css("-webkit-box-shadow","10px 2px 4px rgba(0,0,1,0.8)");

	//设置第二个.human_Mes 标题的样式
	$(".human_Mes:eq(0)").css("margin-top","0%");

	//设置所有input标签的属性样式
	$(".Message tr td :input").css({"color":"#080808","text-align":" center",
	"font-family":"微软雅黑","font-size":"large","border":"hidden","background":"#fff"});
	//设置最后一个input标签的属性样式
	$(".Message tr td :input").last().css({"width":"80%","text-align":"left"});
    //	设置信息表中家庭住址样式
	$(".Message textarea").css({"width":"350px","margin-top":"-5%","margin-left": "15%"});
	//设置活动记录登记表的样式
	$(".active_check tr:first").css({"background":"#0070c0","color":"#ffffff"});
	$(".active_check tr").find("td:eq(0)").css("width","5%");
//	设置input标签
//	$(".active_check .tb_activity input").css("height","30px");
//	$(".active_check .tb_activity input:lt(4)").css("width","25%");
//	设置human_mes样式
//第一个标题
	$(".human_Mes input:eq(0)").css({"border":"none","background":"none","margin-left":"1%"});

	$(".human_Mes span").css({"color":"rgb(211,26,23)","font-size":"small","margin-left":"90%","margin-top":"-26px"});
	$(".human_Mes span input").css({"color":"rgb(211,26,23)","font-size":"small","width":"38px",
});
});