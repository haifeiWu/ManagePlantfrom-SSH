<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>嫌疑人全部信息表</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/Suspect_All.css" />
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/Suspect_All.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// 数据信息的显示与隐藏
		$(".show").click(function() {

			var Mes = $("#Person_info span").html();

			if (Mes == '隐藏信息') {

				$("#All_first").css("display", "none");

				$(this).html("显示信息");

			} else if (Mes == '显示信息') {
				$("#All_first").css("display", "block");

				$(this).html("隐藏信息");

			}

		});

		$(".show1").click(function() {
			var Mes = $("#Person_safety span").html();

			if (Mes == '隐藏信息') {

				$("#All_second").css("display", "none");

				$(this).html("显示信息");

			} else if (Mes == '显示信息') {
				$("#All_second").css("display", "block");

				$(this).html("隐藏信息");

			}
		});

		$(".show2").click(function() {
			var Mes = $("#Info_Collect span").html();

			if (Mes == '隐藏信息') {

				$("#All_third").css("display", "none");

				$(this).html("显示信息");

			} else if (Mes == '显示信息') {
				$("#All_third").css("display", "block");

				$(this).html("隐藏信息");

			}
		});

		$(".show3").click(function() {
			var Mes = $("#record_registr span").html();

			if (Mes == '隐藏信息') {

				$("#All_forth").css("display", "none");

				$(this).html("显示信息");

			} else if (Mes == '显示信息') {
				$("#All_forth").css("display", "block");

				$(this).html("隐藏信息");

			}
		});

		$(".show4").click(function() {
			var Mes = $("#Leave_depart span").html();

			if (Mes == '隐藏信息') {

				$("#fifth").css("display", "none");

				$(this).html("显示信息");

			} else if (Mes == '显示信息') {
				$("#fifth").css("display", "block");

				$(this).html("隐藏信息");

			}
		});
		
		
	});
</script>

</head>

<body>
	<div class="container">
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Suspect</b> information report
			</h4>
			<p id="left_title">嫌疑人入区报告</p>





			<form class="row">
				<h1 class="col-lg-12 col-md-10 col-sm-12">
					<img src="images/jinghui.png">&nbsp;&nbsp;&nbsp;&nbsp;离石区公安局嫌疑人入区报告
				</h1>
				<ul class="report col-lg-12 col-md-12 col-sm-10">
					<li class="l1">档案编号：<input type="text"
						value="${suspect.suspect_ID }" readonly="readonly"
						name="suspectId" /></li>
					<li class="l2">报告时间：<input type="date"
						value="${reportCreateTime }" readonly="readonly" /></li>
					<li class="l3">羁押时间：<input type="text" value="${suspect.detain_Time }"
						readonly="readonly"></li>
				</ul>
			</form>

			<!-- 文件下载链接   "-->

			<form class="row">
				<h4 id="Person_info"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					1、入区登记报告<span class="show">隐藏信息</span>
				</h4>
				<!--嫌疑人身份证信息-->
				<div id="All_first">
					<p
						style="border-bottom: 1px solid #389AC7; padding-bottom: 3px;color: #f69d1f;width: 15%;font-size: 17px;margin-top: 6%;margin-left: 23px;">1.1&nbsp;身份证信息</p>

					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1" src="images/1-zhengmian_04.png" /> <img
							id="img_2" src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日
							&nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						<!--身份证信息-->

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">
							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5"><img
									style="width:95px;height: 108px;margin-left: 14%;margin-top: 30%;"
									src="${suspect.identityCard_Photo } " />
									<p class="info_id">身份证照</p></td>
								<!--<td></td>-->
								<td colspan="2">姓名:<input type="text" readonly="readonly"
									value="${suspect.suspect_Name }" /></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>

								<td>性别：<input style="text-align: center;" type="text"
									value="${suspect.sex }" readonly="readonly" /></td>
								<td>民族：<input type="text" value="汉" readonly="readonly" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生：<input type="text" style="width:55%;"
									value="${suspect.birthday }" readonly="readonly" />
								</td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">身份证住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><textarea readonly="readonly" rows="1"
										cols="30">${suspect.address }</textarea></td>
							</tr>
							<tr>
								<td><div style="margin-left: 50px;">身份证号码</div></td>
								<td colspan="2"><input type="text"
									style="margin-left: 15px;"
									value="${suspect.identifyCard_Number }" readonly="readonly" /></td>
							</tr>
						</table>
						<hr
							style="width:152%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 34%; margin-left: -51%;margin-bottom: 10px;" />
					</div>
					<div class="row">
						<p
							style="border-bottom: 1px solid #389AC7; padding-bottom: 3px;color: #f69d1f;width: 15%;font-size: 17px;margin-left: 37px;">1.2&nbsp;人员联系信息</p>
						<table id="people_Mes" class="col-lg-12 col-md-10 col-sm-10">
							<tr>
								<td style="text-align: center;">身份证种类：</td>
								<td colspan="4">${suspect.type_ID }</td>
								<td style="text-align: center;">证件号码：</td>
								<td colspan="4">${suspect.identifyCard_Number }</td>
							</tr>

							<tr>
								<td style="text-align: center;">现住址：</td>
								<td colspan="4">${suspect.now_address }</td>
								<td style="text-align: center;">联系方式：</td>
								<td colspan="4">${suspect.phone }</td>
							</tr>

							<tr>
								<td style="text-align: center;">入区时间：</td>
								<td colspan="4">${suspect.enter_Time }</td>
								<td style="text-align: center;">RFID:</td>
								<td colspan="4">${suspect.band_ID }</td>
							</tr>
							<tr>
								<td style="text-align: center;">进入办案区原因：</td>

								<td colspan="9">${suspect.suspected_Cause }</td>

							</tr>
							<tr>
								<td style="text-align: center;">办案民警签名：</td>
								<td colspan="4">${suspect.staff_ID }</td>
								<td style="text-align: center;">管理员签名：</td>
								<td colspan="4">${suspect.staff_ID }</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
			<!-- 嫌疑人安全检查报告 -->
			<form class="row">
				<h4 id="Person_safety"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					2、人身安全检查 <span class="show1">显示信息</span>
				</h4>
				<div id="All_second">
					<div class="row_1">
						<p class="self_state col-lg-12 col-md-10 col-sm-10"
							style="font-size: 17px;">2.1&nbsp;自述情况</p>
						<table class="checked_state col-lg-12 col-md-10 col-sm-10"
							style="margin-top: 6px;margin-left: 56px;">
							<tr>
								<td style="padding-left: 40px; width: 88px;font-size: 16px;">是否饮酒：</td>
								<td style="font-size: 16px;">${personal_Check.is_Drink }</td>

							</tr>
							<tr>
								<td style="padding-left: 40px; width: 203px;font-size: 16px;">是否患有传染性疾病：
									</li>
								<td style="font-size: 16px;">${personal_Check.is_Diseases }</td>
							</tr>
						</table>
						<fieldset class="col-lg-12 col-md-12 col-sm-12"
							style="margin-left: 79px;">
							<font size="3">自述症状：${personal_Check.self_ReportS }</font>

						</fieldset>
					</div>
					<!--体检信息表-->

					<div class="row_2" style="width: 100%;">
						<p class="check col-lg-12 col-md-10 col-sm-10"
							style="font-size: 17px;">2.2&nbsp;检查情况</p>
						<!--体检信息表-->
						<table class="checked_state col-lg-12 col-md-10 col-sm-10">
							<tr>
								<c:if test="${!empty personal_Check.check_Situation }">
									<td style="padding-left: 88px; width: 120px;font-size: 16px;">人身检查状态：</td>
									<td style="font-size: 16px;">${personal_Check.check_Situation }</td>
								</c:if>

							</tr>
							<tr>
								<td
									style="padding-left: 88px;width:200px; vertical-align: top;font-size: 16px;">检查情况：</td>
								<td
									style="text-align: left; vertical-align: top; font-size: 16px;"
									rea><div style="width: 40%;height: 70px;border: 1px solid;"><p >${personal_Check.check_ReportS }</p></div></td>
							</tr>
							<tr>
								<td
									style="padding-left:88px;font-size: 16px;padding-top: 5px;width:26%;">被检查人/监护人：
								</td>
								<td style="font-size: 16px;">${suspect.suspect_Name }</td>
								<!--<td></td>-->
							</tr>
						</table>
						<img src="images/check_08.png"
							style="width:24%;position:relative;left: 551px;top:-195px;" />
					</div>
					<br>




					<!--随身财物检查登记-->

					<div class="row_1" style="margin-top: -245px;">
						<p class="check_woods col-lg-12 col-md-10 col-sm-10"
							style="font-size: 17px;">2.3&nbsp;随身财物检查登记</p>
						<div>
							<c:if test="${empty belongingS }">

								<p
									style="position: relative;margin-top:59px;left: 89px;top:-4px;width: 58%;color: #f00; text-align: left;padding-right: 367px;">该嫌疑人无随身财物检查登记记录</p>
								<!-- <div style="margin-left: 12px;margin-top: 45px;">该嫌疑人无随身财物检查登记记录</div> -->
							</c:if>
							<c:if test="${!empty belongingS }">
							<table class="woods_check col-lg-12 col-md-10 col-sm-10">
								<tr>
									<td>序号</td>
									<td>物品名称及特征</td>
									<td>编号</td>
									<td>数量</td>
									<td>单位</td>
									<td>保管措施</td>
									<td>保管柜号</td>
								</tr>

								<c:forEach items="${belongingS }" var="item">
									<tr>
										<td>${item.belongingS_ID }</td>
										<td>${item.belonging_Name }</td>
										<td>${item.belonging_Number }</td>
										<td>${item.belonging_Count }</td>
										<td>${item.belonging_Unit }</td>
										<td>${item.keeping_ID }</td>
										<td>${item.cabinet_Number }</td>

									</tr>
								</c:forEach>
							</table>

						</div>
						<div>
							<ul class="signature col-lg-12 col-md-10 col-sm-10">
								<li>办案人员： ${belongingS[0].staff_ID }</li>
								<li>随身财物管理员：${belongingS[0].staff_ID_Belonging }</li>
								<li>涉案人员: ${suspect.suspect_Name }</li>
							</ul>
						</div>
						</c:if>
					</div>

				</div>
			</form>
			<!--信息采集报告-->
			<form class="row">
				<h4 id="Info_Collect"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					3、信息采集报告<span class="show2">显示信息</span>
				</h4>
				<div id="All_third">
					<c:if test="${empty information_Collection }">
					<div
							style="position: relative;left: 89px;top:23px;width: 41%;color: #f00">该嫌疑人无信息采集记录</div>
					</c:if>
					<c:if test="${!empty information_Collection }">
					<table class="info_collect col-lg-12 col-md-10 col-sm-10"
						style="margin-left: 80px;">
						<tr>
							<td style="width: 35px;">信息采集：</td>
							<td style="width: 55px;">${information_Collection.is_Collected }</td>
							<td style="width: 35px;">采集项目：</td>
							<td style="width: 55px;">${information_Collection.collected_Item }</td>
							<td style="width: 35px;">信息入库：</td>
							<td style="width: 55px;">${information_Collection.is_Storaged }</td>
							<td style="width: 35px;">检查对比：</td>
							<td style="width: 55px;">${information_Collection.is_Checked }</td>
						</tr>

					</table>
					</c:if>
					
				</div>
			</form>
			<!--办案区活动记录-->
			<form class="row">
				<h4 id="record_registr"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					4、办案区活动记录<span class="show3">显示信息</span>
				</h4>
				<div id="All_forth">
					<c:if test="${empty activity_Record }">

						<div
							style="position: relative;left: 89px;top:23px;width: 41%;color: #f00">该嫌疑人无办案区活动记录</div>
					</c:if>
					<c:if test="${!empty activity_Record }">
					<table class="active_check col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>序号</td>
							<td>开始时间</td>
							<td>结束时间</td>
							<td>房间ID</td>
							<td>活动内容</td>
							<td>音视频编码</td>
							<td>备注</td>
						</tr>

						<c:forEach items="${activity_Record }" var="item">
							<tr>
								<td>${item.activity_Record_ID }</td>
								<td>${item.start_Time }</td>
								<td>${item.end_Time }</td>
								<td>${item.room_ID }</td>
								<td>${item.activity_Record }</td>
								<td>${item.vedio_Number }</td>
								<td>${item.remark }</td>
							</tr>
						</c:forEach>
					</table>
					</c:if>
				</div>
			</form>
			<!--离开办案区登记-->
			<form class="row">
				<h4 id="Leave_depart"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					5、离开办案区登记<span class="show4">显示信息</span>
				</h4>
				<div id="fifth">
					<div class="row_1">
						<p
							style="color: #389AC7;margin-top: 6%;margin-left:6%;font-size: 17px;">临时离开办案区</p>
						<c:if test="${empty temporaryLeaves }">

							<div
								style="position: relative;left: 90px;top:0px;width: 41%;color: #f00">该嫌疑人无临时离区记录</div>
						</c:if>
							<c:if test="${!empty temporaryLeaves }">
						<table class="transient_Leave col-lg-12 col-md-10 col-sm-10">
							<tr>
								<td>序号</td>
								<td>临时离开时间</td>
								<td>离开原因</td>
								<td>办案部门负责人签名</td>
								<td>返回时间</td>
							</tr>
							<c:forEach items="${temporaryLeaves }" var="item">
								<tr>
									<td>${item.temporary_Leave_Id }</td>
									<td>${item.tempLeave_Time }</td>
									<td>${item.tempLeave_Reason }</td>
									<td>${item.staff_ID }</td>
									<td>${item.return_Time }</td>


								</tr>
							</c:forEach>

						</table>
						</c:if>
					</div>
					<hr style="margin-top: 33px;border: 1px solid darkgray;" />
					<!--最终离开办案区的信息表-->
					<div class="row_1" style="margin-top: 20px;">
						<p
							style="color: #389AC7;margin-top: -1%;margin-left:6%;font-size: 17px;">最终离开办案区:</p>
						<c:if test="${empty leave_Record }">

							<div
								style="position: relative;left: 90px;top:0px;width: 41%;color: #f00">该嫌疑人无办案区活动记录</div>
						</c:if>
						<div>
							<table class="final_Leave col-lg-12 col-md-10 col-sm-10">
								<tr>

									<td style="text-align: center;">最终离开时间</td>
									<td style="text-align: center;">离开原因</td>
									<td style="text-align: center;">随身物品处理情况：</td>
									<td style="text-align: center;">未反还物品情况记载：</td>
									<td style="text-align: center;">领取人：</td>
									<td style="text-align: center;">身份证号码：</td>
									<td style="text-align: center;">领取时间：</td>
								</tr>
								<tr>
									<td>${leave_Record.leave_Time }</td>
									<td>${leave_Record.leave_Reason}</td>
									<td>${leave_Record.belongingS_Treatment_Method}</td>
									<td>${leave_Record.belongingS_Treatment_Record}</td>
									<td>${leave_Record.recipient_Person}"</td>
									<td>${leave_Record.recipient_Person_Number }</td>
									<td>${leave_Record.treatment_Time }</td>
								</tr>

							</table>
						</div>
					</div>
					<hr style="margin-top: 114px;border: 1px solid darkgray;" />

					<p id="signature">
						管理员签名：&nbsp;&nbsp;&nbsp;<input type="text" value="admin" />
					</p>
					
						<!-- <input  id="download" type="button" value="下载" /> -->
						<a href="${pdfFilePath }">下载入区报告</a>
						<!-- <script type="text/javascript">
						    $("#download").click(function(){
						    
						       window.location.href="${pageContext.request.contextPath }/GR_createPdf.action?suspectId=${suspect.suspect_ID }";
						       setTimeout(function(){window.location.href="tmp/${suspect.suspect_ID }.pdf";}, 500);
						    });
						   
						</script> -->
					
					<div style="height: 100px;"></div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>