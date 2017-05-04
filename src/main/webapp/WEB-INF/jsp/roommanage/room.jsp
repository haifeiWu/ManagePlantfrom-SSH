<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 加载jstl的c标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>房间管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
  </head>
  
  <body>
	
	<div class="container">
		<div class="row">
			
		<table class="table table-striped  bandTable">
			<tr>
				<tr>
					<th>房间编号</th>
					<th>房间名称</th>
					<th>设备编号</th>
					<th>IP地址</th>
				</tr>
			</tr>
			<form class="navbar-form navbar-left" role="search" method="post"
		id="searchroom"
		action="${pageContext.request.contextPath }/room/updateRoom">
			<c:forEach items="${roomCheckInfo }" var="item" varStatus="status" >
				<tr >
					<td>${item.room_ID }<input type="hidden" class="form-control"
						name="roomList[${status.index }].room_ID" value="${item.room_ID }">
					</td>
					<td>${item.room_Name }<input type="hidden" class="form-control" name="roomList[${status.index }].room_Name"
						value="${item.room_Name }"></td>
					<td><input type="text" class="form-control" name="roomList[${status.index }].cardReader_ID"
						value="${item.cardReader_ID }"></td>
					<td><input type="text" class="form-control" name="roomList[${status.index }].room_IPAddress"
						value="${item.room_IPAddress }"></td>
				</tr>
			</c:forEach>
			<table>
				<input type="submit" class="btn btn-primary serch col-sm-offset-10 col-sm-2" value="修改" />
			</table>
		</table>
	</form>
		</div>


	</div>



</body>
</html>
