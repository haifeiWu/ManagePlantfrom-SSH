<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 加载jstl的c标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
     <c:forEach items="${groups}" var="group">
	     <dd>
		    <div class="title">
		    <span><img src="${pageContext.request.contextPath }/images/leftico01.png" /></span>${group.titleFunction.function_name }
		    </div>
	    	<ul class="menuson">
	    	<!-- a标签请求action，返回的页面的结果显示在rightFrame -->
	    	<c:forEach items="${group.childFunctions}" var="child">
	    		<li><cite></cite><a id="suspect" class="suspect" href="${pageContext.request.contextPath }${child.url}" target="rightFrame">${child.function_name}</a><i></i></li>
	    	</c:forEach>
	        </ul>    
	    </dd>
     </c:forEach>
     
    </dl>
    
</body>
</html>
