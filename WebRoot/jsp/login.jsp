<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath }/js/cloud.js" ></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 
</head>
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
<div class="logintop">    
    <span>欢迎登录办案流程标准化智能管理平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
	<div><font color="red"><s:fielderror fieldName="loginError" theme="simple"/></font></div>    
    <form action="${pageContext.request.contextPath}/user_login.action" method="post">
    	<ul>
	    	<li><input name="Staff_Name" type="text" class="loginuser" onclick="JavaScript:this.value=''"/></li>
	    	<li><input name="PassWord" type="password" class="loginpwd" onclick="JavaScript:this.value=''"/></li>
	    	<li><input name="" type="submit" class="loginbtn" value="登录"/>
	    	<label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
    	</ul>
    </form>
    
    </div>
    
    </div>
    
    
    <div class="loginbm">版权所有whfstudio 2016-0801</div>
</body>
</html>