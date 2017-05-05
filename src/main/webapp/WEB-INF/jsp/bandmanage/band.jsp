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
    
    <title>手环管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="JavaScript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
  </head>
 <script type="text/javascript">
$(function(){

//查询手环信息
$(".navbar-form").off();
$(".navbar-form").on("submit",function(){
 var val = $(this).find("input[name=val]").val();
 //alert(val);
 $(".table-striped").load("${pageContext.request.contextPath }/BandManage_"+key+".action?val="+val);
 
 

});

//修改手环信息
	$(".band_edit").off();
	$(".band_edit").on("click",function(){
	var key = $(this).attr("val");
		alert(key);
	});
	
	


});


 </script>
  <body>
	
	<div class="container">
		<div class="row">
			
		<table class="table table-striped  bandTable">
			<tr>
				<th >手环编号</th>
				<th>手环备注</th>
				<th>手环类型</th>
			</tr>
			<form class="navbar-form navbar-left" role="search" method="post"
		id="searchband"
		action="${pageContext.request.contextPath }/band/updateBand">
			<c:forEach items="${bandCheckInfo }" var="item" varStatus="status" >
				<tr >
					<td>${item.band_ID }<input type="hidden" class="form-control"
						name="bandList[${status.index }].band_ID" value="${item.band_ID }">
					</td>
					<td><input type="text" class="form-control" name="bandList[${status.index }].remark"
						value="${item.remark }"></td>
					<td><input type="text" class="form-control" name="bandList[${status.index }].band_Type"
						value="${item.band_Type }"></td>
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
