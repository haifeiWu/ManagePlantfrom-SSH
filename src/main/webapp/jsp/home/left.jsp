<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){//展开目录
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
}); 

      //隐藏/显示左边菜单栏  
$("#lefttop1").hide();//默认进入首页时隐藏显示链接  
//点击隐藏链接事件  
$("#lefttop1").click(function(){  
    //alert("hide");  
    //js改变frameset实现隐藏  
    window.parent.document.getElementById("full").cols = "0,*";  
    //jq实现隐藏后显示右frame中的显示链接  
    $("#show-leftbar",window.parent.right.document).show();  
});  
//点击显示链接事件  
$("#lefttop1").click(function(){  
    window.parent.document.getElementById("full").cols = "180,*";  
    $("#lefttop1").hide();  
});
var flag = true;
//实现菜单栏的悬挂
$(document).ready(function (){  
		closeFrameset();//默认进入首页时隐藏显示链接
        $("#lefttop1").click(function(){
        	if(flag == true){
        		closeFrameset();
        		flag = false;
        	}else{
        		openFrameset();
        		flag = true;
        	}  
        })  
    });  
    function closeFrameset(){
        var fs = parent.document.getElementsByTagName("frameset")[1];  
        fs.cols = "20,*";  
    }
    function openFrameset(){
        var fs = parent.document.getElementsByTagName("frameset")[1];  
        fs.cols = "180,*";
    }
	
//websocket 
	var websocket = null;
     //判断当前浏览器是否支持WebSocket
     if ('WebSocket' in window) {
         websocket = new WebSocket("ws://127.0.0.1:8888/ManagePlantfrom-SSH/websocket");
     }
     else {
         alert('当前浏览器 Not support websocket');
     }
 
     //连接发生错误的回调方法
     websocket.onerror = function (evt) {
         alert("WebSocket连接发生错误");
         
     };
     //连接成功建立的回调方法
     websocket.onopen = function () {
         alert("WebSocket连接成功");
         var ip=getcookie("ip");
         if(ip!=null){
         	websocket.send(ip);
         }
    };
 
     //接收到消息的回调方法
     websocket.onmessage = function (event) {
		$("suspect").trigger("click");
     };
 
     //连接关闭的回调方法
     websocket.onclose = function () {
         alert("WebSocket连接关闭");
     };
 
     //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
     window.onbeforeunload = function () {
         closeWebSocket();
     };
     //关闭WebSocket连接
     function closeWebSocket() {
         websocket.close();
     }
     //获取cookie
     function getcookie(objname){//获取指定名称的cookie的值
		var arrstr = document.cookie.split("; ");
		for(var i = 0;i < arrstr.length;i++){
			var temp = arrstr[i].split("=");
			if(temp[0] == objname) return unescape(temp[1]);
		}
	}
	
     //实现主动刷新的jsp
    
	
	function send(){
		websocket.send("1");
	}
</script>
<!-- websocket主动推送可写在这里 -->
<!-- 推送的原理是js实现a标签的点击事件-->
<!-- 如果实现不了的话，，可以使用js中Windows的location强制跳转，target为rightFrame，左侧也要实现点击该a标签的样式 -->
</head> 

<body style="background:#f0f9fd;">
	 
	<div class="lefttop" id="lefttop1"><span style="cursor:pointer;"></span>导航栏</div>
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>办案区信息管理
    </div>
    	<ul class="menuson">
    	<!-- a标签请求action，返回的页面的结果显示在rightFrame -->
        <li><cite></cite><a id="suspect" class="suspect" href="${pageContext.request.contextPath }/suspect_loadInfor.action" target="rightFrame">入区人员信息登记</a><i></i></li>
        <li><cite></cite><a class="personalCheck" href="${pageContext.request.contextPath }/personalCheck_loadInfor.action" target="rightFrame">人身安全检查</a><i></i></li>
        <li><cite></cite><a class="inforCollect" href="${pageContext.request.contextPath }/IC_loadInfor.action" target="rightFrame">信息采集</a><i></i></li>
        <li><cite></cite><a class="activityRecord" href="${pageContext.request.contextPath }/AR_loadInfor.action" target="rightFrame">询问讯问记录</a><i></i></li>
        <li><cite></cite><a class="leaveRecord" href="${pageContext.request.contextPath }/LR_loadInfor.action" target="rightFrame">出区信息登记</a><i></i></li>
        </ul>    
    </dd>
        
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>嫌疑人信息管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath }/suspectManage_loadInfor.action" target="rightFrame">入区人员信息汇总</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>角色管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>    
    </dd> 
    
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>采集设备管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>    
    </dd>  
    
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>采集设备管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd> 
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>系统日志管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath}/Log_execute.action" target="rightFrame" >查看日志</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd> 
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>数据库管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd>
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>涉案信息查询汇总</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd>   
    </dl>
    <input type="button" onclick="send()" value="diandian"/>
</body>
</html>