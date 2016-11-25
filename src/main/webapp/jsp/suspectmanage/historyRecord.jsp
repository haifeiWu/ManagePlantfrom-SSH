<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form class="container">
		<div class="row">
			<h4 style="margin-top: 13px;"><b style="color: #389ac7;">Suspect</b>&nbsp;&nbsp;management</h4>
			<p id="left_title">嫌疑人信息管理</p>
			<!--搜索框设置-->
			<p class="st_search col-lg-12 col-md-12 col-sm-12 text-center" style="margin: 0px;padding: 0px;">
				<div id="txt_search" class="col-lg-12 col-md-12 col-sm-12">嫌疑人搜索</div>
				<input type="text" name="searchInfor" id="search" />
				<a href="#"><!-- 响应搜索的action -->
					<div id="btn_search"><img src="./images/search_03.png" /></div>
				</a>
			</p>
		</div>
	</form>
	<div class="container">
		<div class="row" style="margin-top: 3%;text-align: center;">
			<a href="#checkingPerson" style="font-size: larger;margin-right: 8%;">待查嫌疑人</a>    
			<a href="#checkedPerson" style="font-size: larger;">历史嫌疑人</a>
		</div>
		<hr style="width: 100%; border: 0.5px solid #389AC7; margin-top: 5%;" />
		<div class="row">
			<h4 id="checkingPerson" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">待查嫌疑人</h4>
			<table class="wait_check col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<tr>
					<c:forEach items="${suspectInfor}" var="item" varStatus="status">
						<td>
							<img src="images/1-zhengmian_04.png" />
							<p><a href="${pageContext.request.contextPath }/GR_loadInfor.action?personName=${item.suspect_Name }&suspectID=${item.suspect_ID}" style="color:#f69d1f;font-size: large;">${item.suspect_Name }</a><br/><span>2</span>小时</p>
						</td>
					</c:forEach>
					<%-- <td>
						<img src="images/1-zhengmian_04.png" />
						<p><a href="${pageContext.request.contextPath }/GR_loadInfor.action?personName=haifeisi&suspectID=haifei125345" style="color:#f69d1f;font-size: large;">德古拉</a><br/><span>2</span>小时</p>
					</td>
					<td>
						<img src="images/1-zhengmian_04.png" />
						<p><a href="#" style="color:#f69d1f;font-size: large;">德古拉</a><br/><span>2</span>小时</p>
					</td>
					<td>
						<img src="images/1-zhengmian_04.png" />
						<p><input type="text" value="雅美佳.买买提" /><br>已入区<input type="time" />小时</p>
					</td>
					<td>
						<img src="images/1-zhengmian_04.png" />
						<p><input type="text" value="张德古拉.买买提" /><br>已入区<input type="time" />小时</p>
					</td> --%>
				</tr>
				<tr>
					
				</tr>
			</table>
		</div>
		<p id="more" onclick="addTab()"><a href="#">更多</a></p>
		<div class="row">
			<h4 id="checkedPerson" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">历史嫌疑人查询</h4>
			<table class="total col-lg-12 col-md-10 col-sm-12">
				<tr>
					<td>入区人员统计时间</td>
					<td><input type="date" />自定义时间</td>
					<td>入区人员合计</td>
					<td><input type="number" value="329" />人</td>
				</tr>
			</table>
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
				<tr>
					<td>1</td>
					<td><input type="text" value="张三" /></td>
					<td><input type="text" value="SN78791010" /></td>
					<td><input type="datetime" /></td>
					<td><input type="time" /></td>
					<td><input type="text" value="治安传唤" /></td>
					<td><input type="text" value="教育释放" /></td>
					<td><input type="text" value="全部反还" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td><input type="text" value="张三" /></td>
					<td><input type="text" value="SN78791010" /></td>
					<td><input type="datetime" /></td>
					<td><input type="time" /></td>
					<td><input type="text" value="据传" /></td>
					<td><input type="text" value="教育释放" /></td>
					<td><input type="text" value="全部反还" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td><input type="text" value="张三" /></td>
					<td><input type="text" value="SN78791010" /></td>
					<td><input type="datetime" /></td>
					<td><input type="time" /></td>
					<td><input type="text" value="投案自首" /></td>
					<td><input type="text" value="行政拘留" /></td>
					<td><input type="text" value="全部反还" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<p id="smore" onclick="addTable()"><a href="#">更多</a></p>
		<div class="row_2 col-lg-12" style="height: 450px;"></div>
	</div>
</body>
</html>