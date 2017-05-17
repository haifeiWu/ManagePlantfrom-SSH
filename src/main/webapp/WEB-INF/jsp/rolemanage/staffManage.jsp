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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script>

$(function(){
	// jQuery
	//为删除按钮绑定事件，点击删除按钮后删除当前数据并且刷新列表页面
	 $(".text-danger").off();
	$(".text-danger").on("click",function(){
		var id = $(this).attr("val");
		var url = "${pageContext.request.contextPath }/user/delete";
		//请求action删除id指定的属性
		
		$.post(url,{id:id},function(){
			alert("删除成功");
			//重新加载整个页面
			window.location.reload("${pageContext.request.contextPath }/user/load");
			//$(".container").load("${pageContext.request.contextPath }/user/load");
		});
	});
	//为搜索表单绑定事件 
	$("#searchArticleForm").off();
	$("#searchArticleForm").on("submit",function(){
		var key=$(this).find("select[name=key]").val();
		var val=$(this).find("input[name=val]").val();
		//alert(key+","+val);
		
		$(".container").load("${pageContext.request.contextPath }/user/searchStaff",{
			key:key,
			val:val
		});
		//阻止表单默认行为 （提交）
		return false;
	});
	
});
</script>
</head>

<body>

	<div class="container">
		<h4 style="margin: 13px 0 0 -20px;">
			<b style="color: #389ac7;">Staff</b> information
		</h4>
		<p id="left_title"
			style="border-bottom: 2px solid #389AC7;margin-top:0;margin-left:-20px; padding-bottom: 2px;width: 140px;">警员信息</p>
		<div class="row">

			<form class="navbar-form navbar-left" role="search" id="searchArticleForm">
				<div class="form-group">
					<select name="key" style="height: 34px;">
						<option value="Staff_Name">工号</option>
						<option value="real_Name">姓名</option>
					</select> 
					<input type="text" class="form-control" placeholder="Search" name="val"
						value="请输入工号/姓名查询">
				</div>
				<input type="submit" class="btn btn-primary" value="查询" /> <a
					class="btn btn-default"
					href="${pageContext.request.contextPath }/user/adduser">添加</a>
			</form>
			<table class="table table-striped">
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>固定电话</th>
					<th>移动电话</th>
					<th>操作</th>
					<c:forEach items="${userCheckInfo }" var="u" varStatus="s">
						<tr>
							<td>${s.index+1 }</td>
							<td>${u.staff_Name }</td>
							<td>${u.real_Name }</td>
							<td>${u.sex }</td>
							<td>${u.role_Id }</td>
							<td>${u.email }</td>
							<td>${u.phone }</td>
							<td>${u.mobile }</td>
							<td><a class="text-danger glyphicon glyphicon-remove"
								 val="${u.staff_ID }"></a> <a
								class="text-success glyphicon glyphicon-refresh"
								href="${pageContext.request.contextPath }/user/update?staff_Name=${u.staff_Name }"></a></td>
						</tr>
					</c:forEach>
			</table>
		</div>


	</div>



</body>
</html>
