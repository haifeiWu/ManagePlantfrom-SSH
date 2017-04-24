<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<title>登记表</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style_head.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery_css.js"></script>
</head>

<body>
	<div>
		<div class="row">
			<div style="padding: 0px;" class="head_title col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<img class="img_first" src="${pageContext.request.contextPath }/images/2-logo_03.png" />
<%-- 				<a href="${pageContext.request.contextPath }/user_logout"  target="_parent">注销</a> --%>
				<h1  class="system col-lg-12 col-md-12 col-sm-12">${title }</h1>
				<h3 class="local_police col-lg-12 col-md-12 col-sm-12">---${name }</h3>
				<img class="img_second" src="${pageContext.request.contextPath }/images/policer_02.png" />
				<img class="img_third" src="${pageContext.request.contextPath }/images/red_02.png"/>
				
				
			</div>
		</div>
	</div>
</body>
</html>