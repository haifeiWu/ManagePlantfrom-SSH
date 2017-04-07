<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>嫌疑人信息查看</title>
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/Suspect_info.css" />
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/Suspect_mes.js"></script>
</head>

<body>
		
	<div class="container">
		<div class="row"">
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Suspect</b>&nbsp;&nbsp;management
			</h4>
			<p id="left_title">嫌疑人信息查看</p>
		</div>
		<div class="row"  style="margin-top: 50px;">
			<h4 id="checkingPerson"
				class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">待查嫌疑人</h4>
			<table class="wait_check col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<c:forEach items="${suspectNow }" var="item"
					varStatus="status">
					<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
						<tr>
					</c:if>
					<div>
					<td style="width: 160px;" class="show1">
						<div style="width:150px; float:left; margin-left: -1px;">
							<img src="${item.identityCard_Photo }" style="width: 150px; height: 200px;"/>
							<p>
								<a
									href="./GR_loadInfor.action?personName=${item.suspect_Name }&suspectID=${item.suspect_ID}"
									style="color:#f69d1f;font-size: large;">${item.suspect_Name }</a><br />
							</p>
						</div>
						<!--  -->
						<div class="play" style="text-align: left; width: 150px; height: 200px;float: left; margin-left: -15.4%;margin-top: 0%;color:#FFFFFF;">
							<span style="margin-top: 10px;">&nbsp所在房间：${item.room_Now }</span><br>
							<span>&nbsp</span><br>
							<span style="margin-top: 25px;">&nbsp嫌疑人编号：<br>&nbsp&nbsp${item.suspect_ID }</span><br>
							<span>&nbsp</span><br>
							<span>&nbsp身份证号：<br>${item.identifyCard_Number }</span>
						</div>
					</td>
					</div>
					<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
						</tr>
					</c:if>
				</c:forEach>
			</table>
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
				<c:forEach items="${suspect }" var="item"
					varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td><a href="./GR_loadInfor.action?personName=${item.suspect_Name }&suspectID=${item.suspect_ID}"
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
		</div>
		<div class="row_2 col-lg-12" style="height: 450px;"></div>
	</div>
</body>
</html>