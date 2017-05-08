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
		$(".menuson li.active").removeClass("active");
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
        });  
    });  
    function closeFrameset(){
        var fs = parent.document.getElementsByTagName("frameset")[1];  
        fs.cols = "20,*";  
    }
    function openFrameset(){
        var fs = parent.document.getElementsByTagName("frameset")[1];  
        fs.cols = "180,*";
    }
	

</script>
</head> 

<body style="background:#f0f9fd;">
	<div class="lefttop" id="lefttop1"><span style="cursor:pointer;"></span>导航栏</div>
    <dl class="leftmenu">
     
    <dd>
    <div class="title">
    <span><img src="${pageContext.request.contextPath }/images/leftico01.png" /></span>办案区信息管理
    </div>
    	<ul class="menuson">
    	<!-- a标签请求action，返回的页面的结果显示在rightFrame -->
        <li><cite></cite><a id="suspect" class="suspect" href="${pageContext.request.contextPath }/suspect/load" target="rightFrame">入区人员信息登记</a><i></i></li>
         <%-- <li><cite></cite><a class="personalCheck" href="${pageContext.request.contextPath }/check/load?suspectID=LB-HB-20170328002" target="rightFrame">人身安全检查</a><i></i></li>
        <li><cite></cite><a class="inforCollect" href="${pageContext.request.contextPath }/collect/load?suspectID=LB-HB-20170328002" target="rightFrame">信息采集</a><i></i></li>
        <li><cite></cite><a class="activityRecord" href="${pageContext.request.contextPath }/activity/load?suspectID=LB-HB-20170328002" target="rightFrame">询问讯问记录</a><i></i></li>
        <li><cite></cite><a class="leaveRecord" href="${pageContext.request.contextPath }/leave/load?suspectID=LB-HB-20170328002" target="rightFrame">出区信息登记</a><i></i></li>   --%>
        <li><cite></cite><a class="leaveRecord" href="${pageContext.request.contextPath }/home/index" target="rightFrame">办理其他业务</a><i></i></li>
        </ul>    
    </dd>
        
    <dd>
    <div class="title">
    <span><img src="${pageContext.request.contextPath }/images/leftico02.png" /></span>嫌疑人信息管理</div>
    <ul class="menuson">

        <li><cite></cite><a href="${pageContext.request.contextPath }/suspectManage/load" target="rightFrame">入区人员信息汇总</a><i></i></li>
       <!-- <li><cite></cite><a href="${pageContext.request.contextPath }/report/load?susptct_id"  target="rightFrame">临时报告区</a><i></i></li> -->
        <li><cite></cite><a href="${pageContext.request.contextPath }/suspectManage/downVideoFail" target="rightFrame">录像下载失败信息</a><i></i></li>
         <li><cite></cite><a href="${pageContext.request.contextPath }/suspectManage/downVideoSucc" target="rightFrame">录像下载成功信息</a><i></i></li>

        </ul>     
    </dd> 
    
    
    <%-- <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico03.png" /></span>角色管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath }/user/load" target="rightFrame">查看所有用户</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath }/user/adduser" target="rightFrame">添加用户</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>    
    </dd>  --%>
    
    <%-- <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico03.png" /></span>房间管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath }/room/load" target="rightFrame">房间设置</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>    
    </dd>   --%>
    
    
    <%-- <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico04.png" /></span>采集设备管理</div>
    <ul class="menuson">
    	<li><cite></cite><a href="${pageContext.request.contextPath }/CardReaderManage/loadInfor" target="rightFrame">读卡器设置</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath }/band/bandFindAll" target="rightFrame">手环查看</a><i></i></li>      
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd>  --%>
    
    <%-- <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico04.png" /></span>系统日志管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="${pageContext.request.contextPath}/log/executeinfo?page=1" target="rightFrame" >工作人员日志</a><i></i></li>
        <li><cite></cite><a href="${pageContext.request.contextPath}/log/executeProcess?page=1" target="rightFrame" >嫌疑人日志</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd> 
    
    <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico04.png" /></span>数据库管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd>
    
    <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico04.png" /></span>涉案信息查询汇总</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
        <li><cite></cite><a href="#">待编辑</a><i></i></li>
    </ul>
    </dd>  
    
     <dd><div class="title"><span><img src="${pageContext.request.contextPath }/images/leftico04.png" /></span>系统初始化设置</div>
	     <ul class="menuson">
	        <li><cite></cite><a href="${pageContext.request.contextPath }/properties/load" target="rightFrame">配置文件初始化</a><i></i></li>
	        <li><cite></cite><a href="${pageContext.request.contextPath }/room/load" target="rightFrame">房间初始化</a><i></i></li>
	        <li><cite></cite><a href="${pageContext.request.contextPath }/CardReaderManage/loadInfor" target="rightFrame">读卡器初始化</a><i></i></li>
        	<li><cite></cite><a href="${pageContext.request.contextPath }/band/bandFindAll" target="rightFrame">手环初始化</a><i></i></li>    
	     </ul>
    </dd> --%>
    </dl>
    
</body>
</html>