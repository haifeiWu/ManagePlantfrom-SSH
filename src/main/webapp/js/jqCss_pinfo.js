//设置嫌疑犯第一张图片的样式
$(document).ready(function(){
//	设置state中最后一张图片的位置
	$("#state img").last().css("margin-left","-11%");
//	设置#state中span属性
	$("#state span").css({"color":"#f69d1f","width":"120px"});
	
	$(".pic").find("img :first-child").css("-webkit-box-shadow","10px 2px 4px rgba(0,0,1,0.8)");

	$(".Message td:first").css({"width":"30%","height":"7%"});
	$(".Message tr td").css({"color":"#389ac7","text-align":"left","margin-left":"4%"});
	//设置所有input标签的属性样式
	$(".Message tr td :input").css({"width":"18%","color":"#080808","text-align":" center",
	"font-family":"微软雅黑","font-size":"large","border":"hidden","background":"#fff"});
	//设置最后一个input标签的属性样式
	$(".Message tr td :input").last().css({"width":"80%","text-align":"left"});
	
	$(".Message tr").last("text-align","center");
	
	$(".Message textarea").css({"width":"100%","margin-top":"-5%","margin-left": "7%"});
	//设置人员联系信息的背景
	$(".human_Mes").css({"background":"#f1f1f1","-webkit-box-shadow":"2px 4px 4px rgba(0,0,0,0.8)"});

//设置第二个.human_Mes 标题的样式
	$(".human_Mes:eq(0)").css("margin-top","0%");

//设置人员联系信息表的表格样式
	$(".Mes_tab tr td").css({"border-color":"#389ac7","height":"30px"});
	$("input[type='text']").css("height","25px");
	$("input[type='date']").css("height","25px");
	//设置进入办案区信息的td高度
	$(".Mes_case tr td").css("height","30px");
	//设置干警签名的表格td高度
	$(".signatuer_Mes tr td").css("height","30px");
});

window.onload = function(){
	// 设置进度条变化
	if ($(".Message input[type='text']").val()!="") {
		$("#state img:lt(2)").attr("src","./images/fgreen_03.png");

		}
		else{
		$("#state img:lt(2)").attr("src","./images/3-inforCollection_03.png");

		}
		$(".Message input[type='text']").change(function(){
	// console.log($(this).val())
		if ($(this).val()!="") {
		$("#state img:lt(2)").attr("src","./images/fgreen_03.png");

		}
		else{
		$("#state img:lt(2)").attr("src","./images/3-inforCollection_03.png");

		}
			
		});
	//设置人员联系信息进度条变化
	$(document).ready(function(){

		var num=0;
		$(".Mes_tab input[type='checkbox']").change(function()
			{
			if($(this).is(":checked"))
			{
				$("#state img:eq(2)").attr("src","./images/fgreen_03.png");
				++num;
			}
			else
				--num;
			if(num==0)
				$("#state img:eq(2)").attr("src","./images/3-inforCollection_03.png");
		});

	 

	});
	// 设置进入办案区信息进度
	$(document).ready(function(){
		if ($(".Mes_case input[type='date']").val()!="") {
		$("#state img:eq(3)").attr("src","./images/fgreen_03.png");

		}
		else{
		$("#state img:eq(3)").attr("src","./images/3-inforCollection_03.png");

		}
		$(".Mes_case input[type='date']").change(function(){
	// console.log($(this).val())
		if ($(this).val()!="") {
		$("#state img:eq(3)").attr("src","./images/fgreen_03.png");

		}
		else{
		$("#state img:eq(3)").attr("src","./images/3-inforCollection_03.png");

		}
			
		});
	});
	// 干警签名确认
	$(document).ready(function(){

		if ($(".signatuer_Mes input:lt(2)").val()!="") {
		$("#state img:eq(4)").attr("src","./images/fgreen_07.png");

		}
		else{
		$("#state img:eq(4)").attr("src","./images/3-inforCollection_07.png");

		}
		$(".signatuer_Mes input:lt(2)").change(function(){
	// console.log($(this).val())
		if ($(this).val()!="") {
		$("#state img:eq(4)").attr("src","./images/fgreen_07.png");

		}
		else{
		$("#state img:eq(4)").attr("src","./images/3-inforCollection_07.png");

		}
			
		});


	});
}
