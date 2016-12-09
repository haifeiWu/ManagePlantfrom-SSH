<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办案流程标准化智能管理平台</title>
</head>
<frameset rows="167,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="${pageContext.request.contextPath }/home_top.action"
		name="topFrame" scrolling="No" noresize="noresize" id="topFrame"
		title="topFrame" />
	<frameset cols="*" frameborder="no" border="0" framespacing="0">
		<frame
			src="${pageContext.request.contextPath }/personalCheck_unlogin_load.action"
			name="rightFrame" id="rightFrame" title="rightFrame" />
	</frameset>
</frameset>

<noframes>
	<body>
	</body>
</noframes>
</html>