<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>嫌疑人信息查看</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Suspect_info.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Suspect_mes.js"></script>
</head>

<body>

	<div class="container">
		<div class="row"">
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Suspect</b>&nbsp;&nbsp;management
			</h4>
			<p id="left_title">嫌疑人信息查看</p>
		</div>
		<c:if test="${!empty suspectNow || !empty suspect}">
			<div class="row" style="margin-top: 50px;">
				<h4 id="checkingPerson"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">待查嫌疑人</h4>
				<table class="wait_check col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<c:forEach items="${suspectNow }" var="item" varStatus="status">
						<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
							<tr>
						</c:if>
						<div>
							<td style="width: 160px;" class="show1">
								<div style="width: 150px; height: 250px; float: left;">
									<div style="width:150px; float:left; margin-left: -1px;">
										<img src="${item.identityCard_Photo }"
											style="width: 150px; height: 200px;" />
										<p>
											${item.suspect_Name }<br />
										</p>
									</div>
									<!--  -->
									<div class="play"
										style="text-align: left; width: 150px; height: 200px;float: left;color:#FFFFFF; position: relative;left: -1px; top: -104%;">
										<span style="margin-top: 10px;">&nbsp所在房间：${item.room_Now
											}</span><br> <span>&nbsp</span><br> <span
											style="margin-top: 25px;">&nbsp嫌疑人编号：<br>&nbsp&nbsp${item.suspect_ID
											}
										</span><br> <span>&nbsp</span><br> <span>&nbsp身份证号：<br>${item.identifyCard_Number }</span>
									</div>
								</div>
							</td>
						</div>
						<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<c:if test="${empty suspectNow}">
				<div style="color:red ;text-align:center ;font-size:20px">当前没有待查嫌疑人</div>
				</c:if>
			</div>
			<div class="row">
				<h4 id="checkedPerson"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">历史嫌疑人查询</h4>
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
					<c:forEach items="${suspect }" var="item" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td><a
								href="./GR_loadInfor.action?personName=${item.suspect_Name }&suspectID=${item.suspect_ID}"
								style="color:#f69d1f;font-size: large;">${item.suspect_Name }</a></td>
							<td>${item.suspect_ID }</td>
							<td>${item.enter_Time }</td>
							<td><input type="time" /></td>
							<td>${item.suspected_Cause }</td>
							<td>教育释放</td>
							<td>全部反还</td>
						</tr>
					</c:forEach>
				</table>
				<c:if test="${empty suspect}">
				<div style="color:red ;text-align:center ;font-size:20px">此记录无历史嫌疑</div>
				</c:if>
			</div>
			<div class="row_2 col-lg-12" style="height: 100px;"></div>
		</c:if>
		<c:if test="${empty suspect && empty suspectNow}">
			<table border=0 cellpadding=2 cellspacing=0 width=100%>
				<tr>
					<td bgcolor=#3366cc><font face=arial,sans-serif color=#ffffff><b>Message</b></td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
			<blockquote>
				<H1>对不起，未查询到结果！</H1>
				<p>
			</blockquote>
			<table width=100% cellpadding=0 cellspacing=0>
				<tr>
					<td bgcolor=#3366cc><img alt="" width=1 height=4></td>
				</tr>
			</table>
			
		</c:if>
		<p id="more">
				<a
					href="${pageContext.request.contextPath}/log/executeinfo?page=1"
					target="rightFrame">返回>></a>
			</p>
			<div class="row_2 col-lg-12" style="height: 50px;"></div>
	</div>
</body>
<style>
<!--
body {
	font-family: arial, sans-serif
}

div.nav {
	margin-top: 1ex
}

div.nav A {
	font-size: 10pt;
	font-family: arial, sans-serif
}

span.nav {
	font-size: 10pt;
	font-family: arial, sans-serif;
	font-weight: bold
}

div.nav A,span.big {
	font-size: 12pt;
	color: #0000cc
}

div.nav A {
	font-size: 10pt;
	color: black
}

A.l:link {
	color: #6f6f6f
}

A.u:link {
	color: green
}
//
-->
</style>
</html>