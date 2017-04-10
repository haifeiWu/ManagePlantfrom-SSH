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
			<form class="navbar-form navbar-left" action="${pageContext.request.contextPath }/cardReaderManage_findById.action" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search" name="cardReader_ID">
				</div>
				<input type="submit" class="btn btn-primary"  value="查询" /> 
				
			</form>
			<table class="table table-striped">
				<tr>
					<th>设备编号</th>
					<th>设备名称</th>
					<th>设备类型</th>
					
					<th>操作</th>
				</tr>
				<c:forEach items="${cardReaderCheckInfo }" var="item">
					<tr>
						<td>${item.cardReader_ID }</td>
						<td>${item.cardReader_Name}</td>
						<td>${item.cardReader_Type}</td>
						
						<td>
							<a class="text-success glyphicon glyphicon-refresh" href="${pageContext.request.contextPath }/cardReaderManage_toUpdate.action?cardReader_ID=${item.cardReader_ID }"></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>


	</div>



</body>
</html>
