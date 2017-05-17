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
<script type="text/javascript">
	var check=true;
     function selectCheckBoxes(domId,value) {
   
        var inputs = document.getElementById(domId).getElementsByTagName("input");
        
        if(check){
	        for(var i = 0; i < inputs.length; i++) {
	            if (inputs[i].type == 'checkbox') {
	                inputs[i].checked=true;
	            }
	        }
	        check=false;
        }else{
        	for(var i = 0; i < inputs.length; i++) {
	            if (inputs[i].type == 'checkbox') {
	                inputs[i].checked=false;
	            }
	        }
	        check=true;
        }
    }
</script> 
<body>
	
	<div class="container theme-showcase" role="main">
		
		  <div class="page-header">
	        <h3>编辑角色信息</h3>
	      </div>
	      <form class="form-horizontal" action="${pageContext.request.contextPath }/role/updateRole" method="post">
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">角色名称</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" name="role_Name" value="${role.role_Name }" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">角色描述</label>
			    <div class="col-sm-8">
			      <textarea class="form-control" rows="4" cols="90" name="role_Description" value="${role.role_Description }"></textarea>
			    </div>
			  </div>
			  <!-- 角色为空是添加 -->
			  <c:if test="${empty role}">
				<div class="form-group">
				  <div class="col-sm-offset-2 col-sm-10">
				    <button type="submit" class="btn btn-info">下一步，为该角色添加权限</button>
				  </div>
				</div>
			  </c:if>
			  <!-- 角色不为空是修改 -->
			  <c:if test="${!empty role}">
				<input type="hidden" name="role_ID" value="${role.role_ID }">
				<div class="form-group">
				  <div class="col-sm-offset-2 col-sm-10">
				    <button type="submit" class="btn btn-info">确认修改</button>
				  </div>
				</div>
			  </c:if>
		 </form>
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</body>
</html>
