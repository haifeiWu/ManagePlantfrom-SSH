<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>嫌疑人信息管理</title>
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/Suspect_info.css" />
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/Suspect_mes.js"></script>
<script type="text/javascript">
		
</script>
</head>

<body>
	<form class="container"
		action="${pageContext.request.contextPath }/suspectManage_searchsuspectInfor.action"
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
					class="serachImg" type="image" src="./images/search_03.png"
					border="0" />
			</div>
		</div>
	</form>
	<div class="container">
		<div class="row" style="margin-top: 3%;text-align: center;">
			<a href="#checkingPerson" style="font-size: larger;margin-right: 8%;">待查嫌疑人</a>
			<a href="#checkedPerson" style="font-size: larger;">历史嫌疑人</a>
		</div>
		<hr style="width: 100%; border: 0.5px solid #389AC7; margin-top: 5%;" />
		<div class="row" id="row2" style="height: 318px;overflow: hidden; ">
			<h4 id="checkingPerson"
				class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">待查嫌疑人</h4>
			<table class="wait_check col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<c:forEach items="${suspectCheckInfor}" var="item"
					varStatus="status">
					<c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
						<tr>
					</c:if>
					<td style="width: 160px;">
						<div style="width:150px; float:left; margin-left: -1px;">
						<a
									href="./GR_loadInfor.action?personName=${item.suspect_Name }&suspectID=${item.suspect_ID}"
									style="color:#f69d1f;font-size: large;">
							<img src="images/1-zhengmian_04.png" style="width: 150px; height: 200px;"/>
							<p style="color: #F79D1F;">
								${item.suspect_Name }<br />
								<span>2</span>小时
							</p>
							</a>
						</div>
					</td>
					<c:if test="${status.count % 5 eq 0 || status.count eq 5}">
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<p id="more">
			<input type="button" id="btn1" value="更多>>" style="background: none;border: 0px;"/>
		</p>
		<div class="row" id="row1" style="height: 313px; overflow: hidden; ">
			<h4 id="checkedPerson"
				class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">历史嫌疑人查询</h4>
			<table class="total col-lg-12 col-md-10 col-sm-12">
				<tr>
					<td>入区人员统计时间</td>
					<td><input type="date" value="" /></td>
					<td>入区人员合计</td>
					<td><input type="number" value="${8 }" readonly="readonly" />人</td>
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
				<c:forEach items="${suspectCheckedInfor}" var="item"
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
		<p id="more">
				<input type="button" id="btn" value="更多>>" style="background: none;border: 0px;"/>
		</p>
		<div class="row_2 col-lg-12" style="height: 450px;"></div>
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
		    var show_height = 318;//定义原始显示高度
			if (btn1.value=="更多>>") {
				btn1.style.display = 'block';
				obj.style.height = total_height + 'px';
				btn1.value="收起";
			} else if(btn.value="收起") {
		    	obj.style.height =show_height + 'px';
		    	btn1.value="更多>>";
			}
			});
</script
</body>
</html>