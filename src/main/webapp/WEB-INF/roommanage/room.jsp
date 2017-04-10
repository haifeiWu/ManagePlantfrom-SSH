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
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  </head>
  
  <body>
	
	<div class="container">
		<div class="row">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<input type="submit" class="btn btn-primary" value="查询" /> 
				<a class="btn btn-default" href="toAddCourse">添加</a>
			</form>
			<table class="table table-striped">
				<tr>
					<th>房间编号</th>
					<th>房间名称</th>
					<th>设备编号</th>
					<th>流程编号</th>
					<th>录播线路编号</th>
					<th>IP地址</th>
					<!-- <th>操作</th> -->
				</tr>
				<c:forEach items="${roomCheckInfo }" var="item">
					<tr>
						<td>${item.room_ID }</td>
						<td>${item.room_Name }</td>
						<td>${item.cardReader_ID }</td>
						<td>${item.process_ID }</td>
						<td>${item.line_Number }</td>
						<td>${item.room_IPAddress }</td>
						<%-- <td><a class="text-danger glyphicon glyphicon-remove" href="dropCourse?id=${course.id }"></a>
							<a class="text-success glyphicon glyphicon-refresh" href="toUpdateCourse?id=${course.id }"></a>
						</td> --%>
					</tr>
				</c:forEach>
			</table>
		</div>


	</div>



</body>
</html>
