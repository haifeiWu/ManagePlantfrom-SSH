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
    
    <title>设备管理系统</title>
    
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
  <div class="row">
			<form class="navbar-form navbar-left" action="${pageContext.request.contextPath }/cardReaderManage_update.action" role="search">
				<table class="table table-striped">
				<tr>
					<th>设备编号</th>
					<th>设备名称</th>
					<th>设备类型</th>
				</tr>
					<tr>
						
						<td class="pl5"><input  type="hidden"name="cardReader_ID" value="${cardReader.cardReader_ID }">${cardReader.cardReader_ID} </td>
						<td class="pl5"><input type="text" name="cardReader_Name" value="${cardReader.cardReader_Name }"></td>
						<td class="pl5"><input type="text" name="cardReader_Type" value="${cardReader.cardReader_Type }"></td>
					</tr>
			</table>
				<input type="submit" class="btn btn-primary"  value="点击修改" /> 
			</form>
			
		</div>
	
	<%-- <div class="container">
		<div class="row">
		<div class="c_condition website_condition dot_line">
					<span class="yx_txt_center">设备信息修改</span>
				</div>
			<form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath }/cardReaderManage_update.action">
				<input type="hidden" name="cardReader_ID" value="${cardReader.cardReader_ID}">
				<div class="divtable mt5">
					<table class="tab3">
						<tbody>

							<tr>
								<td class="tr" width="80">设备名称</td>
								<td class="pl5"><input type="text" name="cardReader_Name" value="${cardReader.cardReader_Name }"></td>
							</tr>
						
							<tr>
								<td class="tr" width="80">设备类型</td>
								<td class="pl5"><input type="text" name="cardReader_Type" value="${cardReader.cardReader_Type }"></td>
							</tr>
							
						</tbody>
					</table>
				
				</div>
				<input type="submit" class="btn btn-primary" value="修改" /> 
				
			</form>
			
		</div>


	</div>
 --%>


</body>
</html>
