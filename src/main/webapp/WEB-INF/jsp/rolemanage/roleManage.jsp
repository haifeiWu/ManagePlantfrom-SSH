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
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css"> -->
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script> --%>
</head>

<body>
<div class="container theme-showcase" role="main">
	<div class="page-header">
      <h3>角色管理</h3>
    </div>
    <c:if test="${empty role}">没有角色信息，请创建<a class="btn btn-info btn-sm" style="width:110px;margin-left: 60px;" href="${pageContext.request.contextPath }/role/toEdit" role="button">创建新角色</a></c:if>
    <c:if test="${!empty role}">
	    <form class="form-inline"  action="${pageContext.request.contextPath }/role/operator">
		  <div class="form-group col-sm-4" style="margin-top: 3px;">
		  	<label for="exampleInputName2" style="margin-left: 20px;" >选择要操作的角色&nbsp;&nbsp;&nbsp;&nbsp;</label>
			  <select name="roleId" style="width:100px;">
			  	<c:forEach items="${role}" var="r">
					<option value="${r.role_ID }">${r.role_Name }</option>
				</c:forEach>
			  </select>
		  </div>
		  <div class="form-group col-sm-3" style="margin-top: 3px;">
		    <label for="exampleInputEmail2">选择操作&nbsp;&nbsp;&nbsp;&nbsp;</label>
		    <select name="operator" style="width:100px;">
		    	<option value="1">修改</option>
		    	<option value="2">删除</option>
		    	<option value="3">查看权限</option>
		    </select>
		  </div>
		  <button type="submit col-sm-2" class="btn btn-info btn-sm" style="width:110px;font-size: 12px;margin-left: 25px;">提交</button>
		  <a class="btn btn-info btn-sm" style="width:110px;margin-left: 60px;" href="${pageContext.request.contextPath }/role/toEdit" role="button">创建新角色</a>
		</form>
		
		<!-- 显示所有的角色信息 -->
		<table class="table table-striped" style="margin-top: 20px;">
			<tr>
				  <td style="width: 250px;text-align: center;">角色</td>
				  <td style="text-align: center;">角色描述</td>
			</tr>
			<c:forEach items="${role}" var="r">
				<tr>
					  <td style="text-align: center;">${r.role_Name }</td>
					  <td style="text-align: center;">${r.role_Description }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
</div>	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</body>
</html>
