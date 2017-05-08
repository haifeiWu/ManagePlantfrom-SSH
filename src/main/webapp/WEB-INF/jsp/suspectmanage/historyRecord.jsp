<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>嫌疑人信息管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Suspect_info.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Suspect_mes.js"></script>
</head>

<body>
	<form class="container"
		action="${pageContext.request.contextPath }/suspectManage/search"
		method="post">
		<div class="row">
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Suspect</b>&nbsp;&nbsp;management
			</h4>
			<p id="left_title">嫌疑人信息管理</p>
			<!--搜索框设置-->
			<div class="st_search col-lg-12 col-md-12 col-sm-12 text-center"
				style="margin: 0px;padding: 0px;">
				<div id="txt_search" class="col-lg-12 col-md-12 col-sm-12">嫌疑人搜索</div>
				<input type="text" name="searchInfor" id="search" /> <input
					class="serachImg" type="image" src="${pageContext.request.contextPath }/images/search_03.png"
					border="0" />
			</div>
		</div>
	</form>
	<div class="container">
		<div class="row" style="margin-top: 3%;text-align: center;">
			<a href="#checkingPerson" style="font-size: larger;margin-right: 8%;">待查嫌疑人</a>
			<a href="#row1" style="font-size: larger;">历史嫌疑人</a>
		</div>
		<hr style="width: 100%; border: 0.5px solid #389AC7; margin-top: 5%;" />
		<div class="row" id="row2" style="overflow: hidden; ">
			<h4 id="checkingPerson"
				class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">待查嫌疑人
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<font >待查嫌疑人合计：${fn:length(suspectCheckInfor)}人</font>
			</h4>
				
				<table class="total col-lg-12 col-md-10 col-sm-12">
				<%-- <tr>
				    
					<td style="text-align: right; padding-right: 50px;">待查嫌疑人合计：
					${fn:length(suspectCheckInfor)}人</td>
				</tr> --%>
			</table>
			<c:if test="${fn:length(suspectCheckInfor) eq 0}">
			<div style="color:red ;text-align:center ;font-size:20px">当前没有待查嫌疑人</div>
			</c:if>
			<c:if test="${fn:length(suspectCheckInfor) >0}">
			<table class="wait_check col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<c:forEach items="${suspectCheckInfor}" var="item"
					varStatus="status">
					<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
						<tr style="margin-top:20px">
					</c:if>
					<div style="float: left;">
					<td style="width: 160px;" >
					<div style="width: 150px; height: 250px; float: left;margin-bottom:20px"class="show1">
						<div style="width:150px; float:left; margin-left: -1px;margin-top: 3%;" >
							<img src="${item.identityCard_Photo}" style="width: 150px; height: 200px;"/>
							<p >
								<a href="${pageContext.request.contextPath }/report/load?suspectID=${item.suspect_ID}"style="color:#f69d1f;font-size: large;">${item.suspect_Name }</a><br />
							</p>
						</div>
						<!--  -->
						<div class="play" style="text-align: left; width: 150px; height: 200px;float: left;/*  margin-left: 0;margin-top: -108%; */color:#FFFFFF;position: relative;left: -1px;top: -104%;">
							<span style="margin-top: 10px;">&nbsp所在房间：${roomNameList[status.count-1] }</span><br>
							<span>&nbsp</span><br>
							<span style="margin-top: 25px;">&nbsp嫌疑人编号：<br><a href="${pageContext.request.contextPath }/report/load?suspectID=${item.suspect_ID }"  target="rightFrame">&nbsp&nbsp${item.suspect_ID }</a></span><br>
							<span>&nbsp</span><br>
							<span>&nbsp身份证号：<br>${item.identifyCard_Number }</span>
						</div></div>
					</td>
					</div>
					<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
						</tr>
						
					</c:if>
				</c:forEach>
			</table>
			</c:if>
		</div>
		
		<c:if test="${fn:length(suspectCheckInfor) > 5}"> 
			<p id="more">
				<input type="button" id="btn1" value="更多>>" style="background: none;border: 0px;"/>
			</p>
		</c:if>
		<div class="row" id="row1" style=" overflow: hidden; ">
			<h4 id="checkedPerson"
				class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">历史嫌疑人查询&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<font size="4" >入区人员统计时间：</font>
				<font id="clock"></font>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<font style="text-align: center;">入区人员合计：
					${fn:length(suspectCheckedInfor)}人</font>
			</h4>
			<%-- <table>
				<tr>
					<td style="width: 150px;">入区人员统计时间：</td>
					<td id="clock"></td>
					<td style="text-align: center;">入区人员合计：
					${fn:length(suspectCheckedInfor)}人</td>
				</tr>
			</table> --%>
			<table class="All_total col-lg-12 col-md-10 col-sm-12">
				<tr style="background: #0070c0;">
					<td>序号</td>
					<td>嫌疑人姓名</td>
					<td>档案编号</td>
					<td>入区时间</td>
					<td>羁押时间</td>
					<td>入区事由</td>
					<td>出区原因</td>
					<td>随身物处理</td>
				</tr>
				<c:forEach items="${suspectCheckedInfor}" var="item"
					varStatus="status" >
					<tr>
						<td>${status.index+1 }</td>
						<td><a href="${pageContext.request.contextPath }/report/load?suspectID=${item.suspect_ID}"style="color:#f69d1f;font-size: large;">${item.suspect_Name }</a></td>
						<td><a href="${pageContext.request.contextPath }/report/load?suspectID=${item.suspect_ID }"  target="rightFrame">${item.suspect_ID }<a></td>
						<td>${item.enter_Time }</td>
						<td>${item.detain_Time }</td>
						<td>${item.suspected_Cause }</td>
						<td>教育释放</td>
						<td>全部反还</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<c:if test="${fn:length(suspectCheckedInfor) > 10}">
		<p id="more"style="margin-top:30px">
				<a href="${pageContext.request.contextPath }/suspectManage/execute?page=1" target="rightFrame">更多>></a>
		</p>
		</c:if>
		<div class="row_2 col-lg-12" style="height: 90px;"></div>
	</div>
	
	<script type="text/javascript">
		// 数据信息的显示与隐藏
		$("#btn").click(function() {
			var btn = document.getElementById('btn');
			var obj = document.getElementById('row1');
		    var total_height =  obj.scrollHeight;//文章总高度
		    var show_height = 313;//定义原始显示高度
			if (btn.value=="更多>>") {
				btn.style.display = 'block';
				obj.style.height = total_height + 'px';
				btn.value="收起";
			} else if(btn.value="收起") {
		    	obj.style.height =show_height + 'px';
		    	btn.value="更多>>";
			}
			});
			$("#btn1").click(function() {
			var btn1 = document.getElementById('btn1');
			var obj = document.getElementById('row2');
		    var total_height =  obj.scrollHeight;//文章总高度
		    var show_height = 363;//定义原始显示高度
			if (btn1.value=="更多>>") {
				btn1.style.display = 'block';
				obj.style.height = total_height + 'px';
				btn1.value="收起";
			} else if(btn1.value="收起") {
		    	obj.style.height =show_height + 'px';
		    	btn1.value="更多>>";
			}
			});
</script>
</body>
<script type="text/javascript">

	var now=new Date();
	var year=now.getFullYear();
	var month=now.getMonth();
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	document.getElementById("clock").innerHTML=""+year+"年"+month+"月"+day+"日 "+hours+":"+minutes+":"+seconds+"" ;
	//alert(document.getElementById("clock").innerHTML);

</script>
</html>