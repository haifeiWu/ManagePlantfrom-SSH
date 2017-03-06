<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>活动记录登记</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/record_regist.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/record_regist.js"></script>
<script type="text/javascript">
	var index = 0;
	$(function() {
		$(".form_time").datetimepicker({
			language : 'zh-CN',
			format : 'hh:ii',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0
		});

		$("#add")
				.click(
						function() {
							var num = $(".active_check tr").length;
							index = num - 2;
							var tdnum = $(".active_check tr:last()").find(
									"td:eq(0)");

							var addrow = "<tr>" + "<td>"
									+ index
									+ "</td>"
									+ "<td style='width: 35%;'>"
									+ "<div class='form-group' style='height: 30px;width: 170%;'>"
									+ "<div class='input-group date form_time col-md-5' style='margin-left: 8%;margin-top: 2%;' data-date='' data-date-format='hh:ii' data-link-field='dtp_input1'>"
									+ "<input class='form-control' name=activity["+index+"].start_Time type='text' readonly>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
									+ "</div>"
									+ "<input type='hidden' id='dtp_input1' value='' /><br/>"
									+ "</div></td>"
									+ "<td style='width: 35%;'>"
									+ "<div class='form-group' style='height: 30px;width: 170%;'>"
									+ "<div class='input-group date form_time col-md-5' style='margin-left: 8%;margin-top: 2%;' data-date='' data-date-format='hh:ii' data-link-field='dtp_input1'>"
									+ "<input class='form-control' name=activity["+index+"].end_Time type='text' readonly>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
									+ "</div>"
									+ "<input type='hidden' id='dtp_input1' value='' /><br/>"
									+ "</div></td>"
									+ "<td> <select name=activity["+index+"].activity_Record> <option value=>---请选择---</option> <option value=询问>询问</option> <option value=讯问>讯问</option> <option value=审讯>审讯</option><option value=传唤>传唤</option> </select> </td>"
									+ "<td><input type=text name=activity["
									+ index
									+ "].remark style='height:30px;width: 65%;text-align: center;' /></td>"
									+ "</tr>";
							$(".active_check tr").eq(
									$(".active_check tr").length - 2).after(
									addrow);
							addrow.find("td:eq(0)").html(num + 1);

							tdnum.html(num);
						});
		//删除行
		$("#delete").click(
				function() {
					var len = $(".active_check tr").length; //获取当前表格行数
					var delrow = $(".active_check tr").get(
							$(".active_check tr").length - 2);
					if (len > 2) {
						$(delrow).remove();

						$(this).parent().prev().html(
								$(this).parent().prev().html() - 1);

					}
				});

	});
</script>
</head>

<body>
	<form class="container"
		action="${pageContext.request.contextPath }/AR_addActivityRecordInfor.action"
		method="post">
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Activity</b> record registration
			</h4>
			<p id="left_title">活动记录登记</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center">
				<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="text" name="suspect_ID" value="${SuspectInfor.suspect_ID }"
					readonly="readonly" />
			</h5>
			<!--进度条信息设置-->
			<div class="container" style="height: 180px;">
				<div class="row">
					<!--进度的数据信息-->
					<ul id="number" class="col-lg-12 col-md-10 col-sm-10">
						<li>25%</li>
						<li>50%</li>
						<li>75%</li>
						<li>100%</li>
					</ul>
					<!--进度的状态-->

					<!--以上的内容都是标记进度条信息的状态，现已经完全注释，以后修改的时候再打开即可-->
					<!--引入状态截图-->
					<div id="state" class="col-lg-12 col-md-10 col-sm-10">
						<a href="suspect_updateInfor.action?Suspect_ID=haifieisi"><img
							src="images/fgreen_03.png" /></a> <a
							href="personalCheck_updateInfor.action?Suspect_ID=haifieisi"><img
							src="images/fgreen_03.png" /></a> <a
							href="IC_updateInfor.action?Suspect_ID=haifieisi"><img
							src="images/fgreen_03.png" /></a> <a href="#activityReco"><img
							src="images/3-inforCollection_07.png" style="margin-left: -14%;" /></a>
						<span>出区登记</span>
					</div>

					<!--进度的信息显示-->
					<ul id="txt" class="col-lg-12 col-md-10 col-sm-10">
						<li>入区登记</li>
						<li>人身检查</li>
						<li>信息采集</li>
						<li>活动登记确认提交</li>
					</ul>
				</div>
			</div>
			<!--疑犯个人身份证信息-->
			<div class="container" style="margin-top: 0%;">
				<div class="row">
					<!--身份信息标题-->
					<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12"
						style="margin-top: 0%;">
						入区事由:<input type="text" value="治安传唤" />
					</h4>
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img
							style="width: 45%; height: 42%; -webkit-box-shadow: 0px 2px 0px rgba(0,1,1,0.7);"
							src="images/1-zhengmian_04.png" /> <img
							style="width: 53%; height: 90%; -webkit-box-shadow: 2px 4px 4px rgba(0,1,1,0.7);"
							src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日
							&nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

						<hr
							style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5"><img
									style="width:89%;height: 75%;margin-left: -2%;"
									src="${SuspectInfor.identityCard_Photo }" />
									<p class="info_id">身份证照</p></td>
								<td colspan="2">姓名:<input type="text" readonly="readonly"
									value="${SuspectInfor.suspect_Name }" /></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>
								<td>性别：<input style="text-align: center;" type="text"
									value="${SuspectInfor.sex }" readonly="readonly" /></td>
								<td>民族：<input type="text" value="${SuspectInfor.nation }" readonly="readonly" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生日期：<input type="text" style="width:36%;"
									value="${SuspectInfor.birthday }" readonly="readonly" />
								</td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><textarea readonly="readonly" rows="1"
										cols="30">${SuspectInfor.address }</textarea></td>
							</tr>
							<tr>
								<td><div style="margin-left: 38px;">身份证号码</div></td>
								<td colspan="2"><input type="text"
									value="${SuspectInfor.identifyCard_Number  }" readonly="readonly" /></td>
							</tr>
						</table>
						<hr
							style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 40%; margin-left: -2%;" />
					</div>
				</div>
			</div>
			<!--人身检查-->
			<c:if test="${!empty personal_Check }"> 
				<div class="container" style="margin-top: 0%;">
					<div class="row">
						<!--身份信息标题-->
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="margin-top: 0%;">
							人身检查信息:
						</h4>
						
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
	
							<hr
								style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />
	
							<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8" style="height:150px;">
								<tr style="padding: 0px;">
									<td ><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">自述症状</p></td>
									<td ><input type="text" style="width: 280px !important;" readonly="readonly" value="${personal_Check.self_ReportS}" /></td>
									<td><p style="margin-left: 10px; margin-top:10px !important;  width: 100px !important;">信息登记房间</p></td>
									<td><input type="text" style="width: 160px !important;" readonly="readonly" value="${personal_Check.room_ID}" /></td>
								</tr>
								<tr>
									<td><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">人身检查状态</p></td>
									<td><input type="text"  style="width: 280px !important;" readonly="readonly" value="${personal_Check.check_Situation}" /></td>
									<td><p style="margin-left: 10px; margin-top:10px;  width: 100px !important;">办案民警</p></td>
									<td><input type="text" style="width: 160px !important;"  readonly="readonly" value="${personal_Check.staff_ID}" /></td>
								</tr>
								<tr>
									<td ><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">检查情况</p></td>
									<td><input type="text" style="width: 280px !important;" readonly="readonly" value="${personal_Check.check_ReportS}" /></td>
								</tr>
							</table>
							<hr
								style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 10%; margin-left: -2%;" />
						</div>
					</div>
				</div>
			</c:if>
			<!-- 信息采集 --> 
			<c:if test="${!empty information_Collection}">
				<div class="container" style="margin-top: 0%;">
					<div class="row">
						<!--身份信息标题-->
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="margin-top: 0%;">
							信息采集信息:
						</h4>
						
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
	
							<hr
								style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />
	
							<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8" style="height:150px;">
								<tr style="padding: 0px;">
									<td ><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">办案民警</p></td>
									<td ><input type="text" style="width: 280px !important;" readonly="readonly" value="${information_Collection.staff_ID}" /></td>
									<td><p style="margin-left: 10px; margin-top:10px !important;  width: 100px !important;">信息登记房间</p></td>
									<td><input type="text" style="width: 160px !important;" readonly="readonly" value="${information_Collection.room_ID}" /></td>
								</tr>
								<tr>
									<td><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">是否进行采集</p></td>
									<td><input type="text"  style="width: 280px !important;" readonly="readonly" value="${information_Collection.is_Collected}" /></td>
									<td><p style="margin-left: 10px; margin-top:10px;  width: 100px !important;">采集项目</p></td>
									<td><input type="text" style="width: 160px !important;"  readonly="readonly" value="${information_Collection.collected_Item}" /></td>
								</tr>
								<tr>
									<td ><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">是否入库</p></td>
									<td><input type="text" style="width: 280px !important;" readonly="readonly" value="${information_Collection.is_Storaged}" /></td>
								</tr>
							</table>
							<hr
								style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 10%; margin-left: -2%;" />
						</div>
					</div>
				</div>
			</c:if>
			<!--活动记录登记表-->
			<div class="row">
				<h4 id="activityReco"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					活动记录登记<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span>
				</h4>
				<table class="active_check col-lg-12 col-md-10 col-sm-10">
					<tr>
						<td>序号</td>
						<td>开始时间</td>
						<td>结束时间</td>
						<td>活动内容</td>
<!-- 						<td>音视频编码</td> -->
						<td>备注</td>
					</tr>
					<tr>
						<td>0</td>
						<td style="width: 23%;">
							<div class="form-group" style="height: 30px;width: 130%;">
								<div class="input-group date form_time col-md-5"
									style="margin-left: 3%;margin-top: 2%;" data-date=""
									data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
									<input style="width: 130px" class="form-control" name="activity[0].start_Time"
										type="text" value="" readonly><!--  <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"> --></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-time"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</td>
						<td style="width: 23%;">
							<div class="form-group" style="height: 30px;width: 130%;">
								<div class="input-group date form_time col-md-5"
									style="margin-left: 3%;margin-top: 2%;" data-date=""
									data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
									<input style="width: 130px" class="form-control" name="activity[0].end_Time"
										type="text" value="" readonly> <!-- <span
										class="input-group-addon"> --><!-- <span
										class="glyphicon glyphicon-remove"> --></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-time"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</td>
						<td id="select"><select name="activity[0].activity_Record">
								<option value=>---请选择---</option>
								<option value="询问">询问</option>
								<option value="讯问">讯问</option>
								<option value="审讯">审讯</option>
								<option value="传唤">传唤</option>
						</select></td>
<!-- 						<td><input type="text" name="activity[0].vedio_Number"
							value="音视频文件" style="height: 30px;text-align: center;" /></td> -->
						<td><input type="text" name="activity[0].remark"
							style="width:250px; height: 30px;text-align: center;" /></td>
					</tr>
					<tr>
						<td>2</td>
						<td>
							<div class="btn" id="add">+添加</div>
							<div class="btn" id="delete">-删除</div>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
			<p class="row_1">
				注：1、请办案民警注意对嫌疑人在办案区的活动做详细记录，确保嫌疑人在办案区内无时间盲区的登记<br />遗漏.
			</p>
			<input type="submit" value="确认提交" class="sub" />
		</div>
	</form>
	<div style="height: 400px;"></div>


</body>

</html>