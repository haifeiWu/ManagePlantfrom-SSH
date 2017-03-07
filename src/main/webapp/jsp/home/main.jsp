<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>离石区公安局智能化派出所系统</title>

</head>

<frameset rows="167,*" cols="*" frameborder="no" border="0" framespacing="0">
  <!-- ${pageContext.request.contextPath } 是jsp取得绝对路径的方式，取得的是项目名-->
  <frame src="${pageContext.request.contextPath }/home_top.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="120,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath }/home_left.action" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="${pageContext.request.contextPath }/home_index.action" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
</frameset>

<noframes><body>
</body></noframes>
</html>