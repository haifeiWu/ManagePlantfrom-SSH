$(function(){
	//	设置state中最后一张图片的位置
	$("#state img").last().css("margin-left","-11%");
//	设置#state中span属性
	$("#state span").css({"color":"#f69d1f","width":"120px"});
//	设置身份照片
	$(".Message td:first").css({"width":"30%","height":"7%"});
//	设置身份证信息表的input标签
	$(".Message tr td :input").css({"width":"36%","color":"#080808","text-align":" center",
	"font-family":"微软雅黑","font-size":"large","border":"hidden","background":"#fff"});
	//设置最后一个input标签的属性样式
	$(".Message tr td :input").last().css({"width":"80%","text-align":"left"});
	$(".Message tr").last("text-align","center");
	
	
	//设置第二个.human_Mes 标题的样式
	$(".human_Mes:eq(0)").css("margin-top","0%")
	
	$(".Message textarea").css({"width":"100%","margin-top":"-0%","margin-left": "7%","text-align":"left"});
	//设置离开办案区登记表的样式
	$(".transient_Leave tr:first").css({"background":"#0070c0","color":"#ffffff"})

	$(".final_Leave tr").find("td:eq(0)").css({"width":"40%","text-align":"center"});
	
	$(".final_Leave input[type='checkbox']").css({"width":"40px","height":"20px"});
		//	设置date,time 的样式
	//设置所有.human_mes中span标签的属性样式
	$(".human_Mes span").css({"color":"rgb(211,26,23)","font-size":"small","margin-left":"90%","margin-top":"-28px"});
	$(".human_Mes input").css({"color":"#f69d1f","width":"80px","height":"30px","background":"none",
	"border":"none","margin-left":"1%"});
//	设置最终离开办案区第二行第一列字的样式	
	$(".final_Leave tr:eq(2)").find("td:first").css("color","red");


});

window.onload = function(){
// 设置进度条变化
//if ($(".Message input[type='text']").val()!="") {
//	$("#state img:eq(0)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//	else{
//	$("#state img:eq(0)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//	$(".Message input[type='text']").change(function(){
//// console.log($(this).val())
//	if ($(this).val()!="") {
//	$("#state img:eq(0)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//	else{
//	$("#state img:eq(0)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
		
	});

// 出区登记

$(document).ready(function(){

//	if ($(".transient_Leave input:lt(2)").val()!="") {
//	$("#state img:eq(5)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//	else{
//	$("#state img:eq(5)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//	$(".transient_Leave input:lt(2)").change(function(){
//// console.log($(this).val())
//	if ($(this).val()!="") {
//	$("#state img:eq(5)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//	else{
//	$("#state img:eq(5)").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
//
//	}
//		
//	});


});



}