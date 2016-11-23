//设置嫌疑犯第一张图片的样式
$(document).ready(function(){
	$(".pic").find("img :first-child").css("-webkit-box-shadow","10px 2px 4px rgba(0,0,1,0.8)");

	$(".Message td:first").css({"width":"30%","height":"7%"});
	$(".Message tr td").css({"color":"#389ac7","text-align":"left","margin-left":"4%"});
	//设置所有input标签的属性样式
	$(".Message tr td :input").css({"width":"18%","color":"#000","text-align":"center",
	"font-family":"微软雅黑","font-size":"large","border":"hidden","background":"#fff"});
	//设置最后一个input标签的属性样式
	$(".Message tr td :input").last().css({"width":"80%","text-align":"left"});
	
	$(".Message tr").last("text-align","center");
	
	$(".Message textarea").css({"width":"100%","margin-top":"-5%"});
	//设置人员联系信息的背景
	$(".human_Mes").css({"background":"#f1f1f1","-webkit-box-shadow":"2px 4px 4px rgba(0,0,0,0.8)"});
//	设置人员联系信息表的表格样式
	$(".Mes_tab tr td").css({"border-color":"#389ac7","height":"30px"});
	$("input[type='text']").css("height","25px");
	$("input[type='date']").css("height","25px");
	//设置进入办案区信息的td高度
	$(".Mes_case tr td").css("height","30px");
	//设置干警签名的表格td高度
	$(".signatuer_Mes tr td").css("height","30px");
});
