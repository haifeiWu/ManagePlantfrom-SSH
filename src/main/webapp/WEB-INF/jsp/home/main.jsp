<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>

</head>

<frameset rows="167,*" cols="*" frameborder="no" border="0" framespacing="0">
  <!-- ${pageContext.request.contextPath } 是jsp取得绝对路径的方式，取得的是项目名-->
  <frame src="${pageContext.request.contextPath }/home/top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="120,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath }/home/left" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="${pageContext.request.contextPath }/home/index" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
</frameset>

<noframes><body>
</body></noframes>
</html>