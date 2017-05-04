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
			
			<table class="table table-striped">
				<tr>
					<th>设备编号</th>
					<th>设备名称</th>
					<th>设备类型</th>
					
					
				</tr>
				<form class="navbar-form navbar-left" method="post"action="${pageContext.request.contextPath }/CardReaderManage/updateCardReader" >
				<c:forEach items="${cardReaderlist }" var="item"  varStatus="status">
				
					<tr>
						<td>
							${item.cardReader_ID }<input type="hidden" class="form-control" readonly="readonly" value="${item.cardReader_ID }" name="cardReader_ID"/>
						</td>
						<td>
							<input type="text" class="form-control" value="${item.cardReader_Name}" name="cardReader_Name"/>
						</td>
						<td>
							<input type="text" class="form-control" value="${item.cardReader_Type}" name="cardReader_Type"/>
						</td>
						
						
					</tr>
				
				</c:forEach>
				<input type="submit"  class="btn btn-primary"  onclick="submit()" value="保存"/>
			</form>
			
			
			</table>
		</div>


	</div>



</body>
</html>
