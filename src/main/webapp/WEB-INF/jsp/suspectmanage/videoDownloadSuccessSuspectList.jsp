<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>录像下载失败的嫌疑人信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Suspect_info.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Suspect_mes.js"></script>



</head>

<body>
	<div class="container">
		<div class="row">
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Suspect</b>&nbsp;&nbsp;management
			</h4>
			<p id="left_title">嫌疑人信息查看</p>
		</div>
		<div class="row">
			<h4 style="text-align: center;">录像下载成功的嫌疑人信息</h4>
			<table class="All_total col-lg-12 col-md-10 col-sm-12">
				<tr style="background: #0070c0;">
					<td>序号</td>
					<td>嫌疑人姓名</td>
					<td>档案编号</td>
					<td>身份证号码</td>
					<td>入区时间</td>
					
					<td>离区时间</td>
					<td>操作</td>
					
				</tr>
				<c:forEach items="${suspect }" var="item" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td>${item.suspect_Name }</td>
						<td>${item.suspect_ID}</td>
						<td>${item.identifyCard_Number}</td>
						<td>${item.enter_Time}</td>
						
						<td>${item.leave_Time}</td>
						<td><a class="button" href="${pageContext.request.contextPath }/suspectManage/downloadVeio?vedioName=${item.vedio_number }">下载</a></td>
						<td><a class="button" href="${vedioPath }\\${item.vedio_number }">下载</a></td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
