<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>系统日志</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Suspect_info.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Suspect_mes.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	function validate() {
		var page = document.getElementsByName("page")[0].value;

		if ( page>${pageBean.totalPage}|| (page == undefined&& page == "")) {
			alert("你输入的页数错误，页面将跳转到首页！");

			$("body").load("${pageContext.request.contextPath}/log/executeinfo?page=1");

			return false;
		}
		return true;
		
	};
	$(function(){
		$(".datepicker").datepicker();
	});
	
</script>

</head>

<body>
	
	<div style="width: 800px;height: 33px;position: relative;left: 124px;top: 0px;margin-top: 27px">
	<form class="navbar-form navbar-left" role="search" style="width: 400px;float: left;" action="${pageContext.request.contextPath}/log/loginfosearchs?page=1">
					<input type="hidden" name="page" value="1">
					<input type="text" class="form-control" placeholder="输入操作人查询" name="staffName">
				
				<input type="submit" class="btn btn-primary" value="查询" /> 
			</form>
			<form class="navbar-form navbar-left" role="search" style="width: 400px;float: left;" action="${pageContext.request.contextPath}/log/loginfosearchd?page=1">
					<input type="hidden" name="page" value="1">
					<select class="form-control" name="year">
					  <c:forEach begin="0" end="30" var="year">
					  <option>${2017+year }</option>
					  </c:forEach>
					  
					</select>
					<select class="form-control" name="month">
					  <c:forEach begin="1" end="9" var="month">
					  <option>0${month }</option>
					  </c:forEach>
					  <c:forEach begin="10" end="12" var="month">
					  <option>${month }</option>
					  </c:forEach>
					</select>
					<select class="form-control" name="day">
					  <c:forEach begin="1" end="9" var="day">
					  <option>0${day }</option>
					  </c:forEach>
					  <c:forEach begin="10" end="31" var="day">
					  <option>${day }</option>
					  </c:forEach>
					</select>
				
				<input type="submit" class="btn btn-primary" value="查询" /> 
			</form>
			</div>
	
	<div> 
	<table class="All_total col-lg-12  col-sm-12 table table-bordered" style="width: 1168px">
			<tr style="background: #3c96c8;height: 55px;text-align: center;">
			<th>序号</th>
			<th>操作时间</th>
			<th>操作者</th>
			<th>操作内容</th>
			<th>操作模块</th>
			</tr>
			<c:forEach items="${pageBean.loglist}" var="item"
				varStatus="status">
				<tr>
					<td style="width: 150px">${item.log_ID }</td> 
					<td style="width: 238px">${item.operation_Time }</td>
					<td>${item.staff_Name }</td>
					<td>${item.operation_Info }</td>
					<td>${item.operation_Model }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		
		
	<!-- ===========分頁============ -->
	<br>
	<div>
		<center>
			<font size="5">共<font color="red">${ pageBean.totalPage}</font>页
			</font> <font size="5">共<font color="red">${ pageBean.allRows}</font>条记录
			</font> <font size="5">当前页<font color="red">${ pageBean.currentPage}</font></font><br> <br>
			<c:choose>
			<c:when test="${ pageBean.currentPage eq 1}">  
            首页   上一页  
        </c:when>
			<c:otherwise>
				<a
					href="${pageContext.request.contextPath }/log/executeinfo?page=1">首页</a>
				<a
					href="${pageContext.request.contextPath }/log/executeinfo?page=${ pageBean.currentPage - 1}">上一页</a>
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when
				test="${ pageBean.currentPage ne pageBean.totalPage}">
				<a
					href="${pageContext.request.contextPath}/log/executeinfo?page=${ pageBean.currentPage + 1}">下一页</a>
				<a
					href="${pageContext.request.contextPath}/log/executeinfo?page=${ pageBean.totalPage}">尾页</a>
			</c:when>

			<c:otherwise>  
            下一页   尾页  
        </c:otherwise>
        </c:choose>
        
		</center>
		<br>
		<center>

			<form action="${pageContext.request.contextPath}/log/executeinfo" onsubmit="return validate();">
				<font size="4">跳转至</font> <input type="text" size="2" name="page" value="">页
				<input type="submit" value="跳转">
			</form>

		</center>
		<%-- <p id="more">
				<a href="${pageContext.request.contextPath }/suspectManage/load" target="rightFrame">返回>></a>
		</p> --%>
	</div>
	
	<div class="row_2 col-lg-12" style="height: 100px;"></div>
	</div>
	
</body>
<script type="text/javascript" src="js/dcalendar.picker.js"></script>
<script type="text/javascript">
	$("#mydatepicker").dcalendarpicker(); 
	$('#mydatepicker2').dcalendarpicker({
		format:'yyyy-mm-dd'
	}); 
	$('#mycalendar').dcalendar();
</script>

</html>
