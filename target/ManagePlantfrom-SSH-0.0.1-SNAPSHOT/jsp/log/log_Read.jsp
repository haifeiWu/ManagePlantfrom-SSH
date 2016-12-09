<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
	function validate() {
		var page = document.getElementsByName("page")[0].value;

		if (page > <s:property value="#request.pageBean.totalPage"/>) {
			alert("你输入的页数大于最大页数，页面将跳转到首页！");

			window.document.location.href = "${pageContext.request.contextPath}/Log_execute.action";

			return false;
		}

		return true;
	}
</script>

</head>

<body>

	<h1>
		<font color="blue">日志查信息</font>
	</h1>
	<hr>
<body>
	<h3 align="center">日志列表</h3>
	<table border="1" width="70%" align="center">
		<tr>
			<th>序号</th>
			<th>操作时间</th>
			<th>操作者</th>
			<th>操作内容</th>
			<th>操作模块</th>
		</tr>
		<s:iterator value="#request.pageBean.list" id="pb">
			<tr>
				<th><s:property value="#pb.Log_ID" /></th>
				<th><s:property value="#pb.Operation_Time" /></th>
				<th><s:property value="#pb.Staff_Name" /></th>
				<th><s:property value="#pb.Operation_Info" /></th>
				<th><s:property value="#pb.Operation_Model" /></th>
			</tr>
		</s:iterator>
	</table>
	<center>
		<font size="5">共<font color="red"><s:property
					value="#request.pageBean.totalPage" /></font>页
		</font> <font size="5">共<font color="red"><s:property
					value="#request.pageBean.allRows" /></font>条记录
		</font> <font size="5">当前页<font color="red"><s:property
					value="#request.pageBean.currentPage" /></font></font><br> <br>
		<s:if test="#request.pageBean.currentPage == 1">  
            首页   上一页  
        </s:if>
		<s:else>
			<a href="${pageContext.request.contextPath }/Log_execute.action">首页</a>
			<a
				href="${pageContext.request.contextPath }/Log_execute.action?page=<s:property value="#request.pageBean.currentPage - 1"/>">上一页</a>
		</s:else>
		<s:if
			test="#request.pageBean.currentPage != #request.pageBean.totalPage">
			<a
				href="${pageContext.request.contextPath}/Log_execute.action?page=<s:property value="#request.pageBean.currentPage + 1"/>">下一页</a>
			<a
				href="${pageContext.request.contextPath}/Log_execute.action?page=<s:property value="#request.pageBean.totalPage"/>">尾页</a>
		</s:if>

		<s:else>  
            下一页   尾页  
        </s:else>

	</center>
	<br>
	<center>

		<form action="Log_execute" onsubmit="return validate();">
			<font size="4">跳转至</font> <input type="text" size="2" name="page">页
			<input type="submit" value="跳转">
		</form>

	</center>

</body>
</html>
