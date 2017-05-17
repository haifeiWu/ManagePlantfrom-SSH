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
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script> 
</head>
<script type="text/javascript">
	var check=true;
     function selectCheckBoxes(domId) {
   
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
<script type="text/javascript">
	//将之前填写的权限显示在页面上
	window.onload =$(function(){
		var array = new Array();  
		<c:forEach items="${oldFuc}" var="fuc">  
			array.push("${fuc.functionId}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
		</c:forEach>  
		for(var i=0;i<array.length;i++)  
		{  
		    document.getElementById(array[i]).checked=true;  
		} 
	});
</script>
<body>
	
	<div class="container theme-showcase" role="main">
		<form action="${pageContext.request.contextPath }/roleFunc/commitFuc" method="post">
		  <div class="page-header">
	        <h3>设置权限</h3>
	      </div>
	      <input type="hidden" name="roleId" value="${roleId }">
	      <div class="row">
	      	<c:forEach items="${groups}" var="group">
	      		<!-- 办案区业务登记 -->
	      		<div class="col-sm-4" id="${group.titleFunction.function_id }">
			          <div class="panel panel-warning">
			            <div class="panel-heading" onclick="selectCheckBoxes('${group.titleFunction.function_id }')">
			              <h4 class="panel-title">${group.titleFunction.function_name }</h3>
			            </div>
			            <div class="panel-body">
			              	<c:forEach var="func" items="${group.childFunctions }">
			              		<label><input id="${func.function_id }" name="functionId" type="checkbox" value="${func.function_id }" />${func.function_name }</label><br/> 
			              	</c:forEach>
			            </div>
			          </div>
	      		</div>
	      	</c:forEach>
	      </div>
	      <button class="pull-right btn btn-sm" type="submit">确认提交</button>
      </form>
	</div>
	
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->

</body>
</html>
