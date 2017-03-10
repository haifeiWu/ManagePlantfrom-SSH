<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>离开办案区登记</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/Leave_depart.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/Leave_depart.js"></script>

<script type="text/javascript">
	var index = 0;
	$(function() {

		$(".form_time").datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd hh:ii',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0
		});

		$(".form_datetime").datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd hh:ii',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
		$("#add")
				.click(
						function() {
							var num = $(".transient_Leave tr").length;
							index = num - 2;
							var tdnum = $(".transient_Leave tr:last()").find(
									"td:eq(0)");
							//添加下一行
							var addrow = "<tr>" + "<td>"
									+ index
									+ "</td>"
									+ "<td style=width:35%;>"
									+ "<div class='form-group' style='height: 30px;' >"
									+ "<div class='input-group date form_time col-md-5' style='margin-left: 30%;margin-top: 2%;' data-date='' data-date-format='hh:ii' data-link-field='dtp_input1'>"
									+ "<input class=form-control name=temporaryLeave["+index+"].TempLeave_Time type=text readonly>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
									+ "</div>"
									+ "<input type='hidden' id='dtp_input1'/><br/>"
									+ "</div> </td>"
									+ "<td> <select name=temporaryLeave["+index+"].TempLeave_Reason> <option value=>---请选择---</option> <option value=1>扣押</option> <option value=2>暂存</option> <option value=3>代保管</option> </select> </td>"
									+ "<td><input name=temporaryLeave["+index+"].Staff_ID /></td>"
									+ "<td style=width:35%;>"
									+ "<div class='form-group' style='height: 30px;' >"
									+ "<div class='input-group date form_time col-md-5' style='margin-left: 30%;margin-top: 2%;' data-date='' data-date-format='hh:ii' data-link-field='dtp_input1'>"
									+ "<input class=form-control name=temporaryLeave["+index+"].Return_Time type=text readonly>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>"
									+ "<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
									+ "</div>"
									+ "<input type='hidden' id='dtp_input1'/><br/>"
									+ "</div> </td>" + "</tr>";
							$(".transient_Leave tr").eq(
									$(".transient_Leave tr").length - 2).after(
									addrow);
							/* 	addrow.find("td:eq(0)").html(num-1); */
							tdnum.html(num);
						});
		//删除行
		$("#delete").click(
				function() {
					var len = $(".transient_Leave tr").length; //获取当前表格行数
					var td = $(this).parent().prev().html(); //获取当前行序号
					var delrow = $(".transient_Leave tr").get(
							$(".transient_Leave tr").length - 2);
					if (len > 2) {
						$(delrow).remove();
						$(this).parent().prev().html(
								$(this).parent().prev().html() - 1);
					}
				});

	});
</script>

<script type="text/javascript">
	$(function() {
		$(".transient").hide();
		$(".final").show();
		$(".two").addClass("bg");
		$(".two").css({
			"color" : "#fff"
		});
		$(".two").click(function() {
			$(".transient").hide();
			$(".final").show();
			$(".two").removeClass("originbg");
			$(".two").addClass("bg");
			$(".on").addClass("originbg");
			$(".two").css({
				"color" : "#fff"
			});
			$(".on").css({
				"color" : "#000"
			});
		});
		$(".on").click(function() {
		//如果表中有一条属于当前嫌疑人的出区返回时间为空则进行提示
			
			 if($(".tempLeave_Time").attr("value")!=null){
				alert("经系统查询 该嫌疑人为出区返回！请填写返回时间！");
			} 
			
			$(".transient").show();
			$(".final").hide();
			$(".on").removeClass("originbg");
			$(".on").addClass("bg");
			$(".two").addClass("originbg");
			$(".on").css({
				"color" : "#fff"
			});
			$(".two").css({
				"color" : "#000"
			});
			
		});

	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<!--嫌疑人离开办案区-->
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Registration</b> of departure
			</h4>
			<p id="left_title">离开办案区登记</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center">
				<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="text" name="suspect_ID" value="${suspectInfor.suspect_ID }"
					readonly="readonly" />
			</h5>
			<!--进度条信息设置-->
			<div class="container" style="height: 180px;">
				<div class="row">
					<!--进度的数据信息-->
					<ul id="number" class="col-lg-12 col-md-10 col-sm-10">
						<li>0%</li>
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
							src="images/3-inforCollection_03.png" /></a> <a
							href="AR_updateInfor.action?Suspect_ID=haifieisi"><img
							src="images/3-inforCollection_03.png" /></a> <a href="#leaveReco"><img
							src="images/3-inforCollection_07.png" /></a>
					</div>
					<!--进度的信息显示-->
					<ul id="txt" class="col-lg-12 col-md-10 col-sm-10">
						<li>入区登记</li>
						<li>人身检查</li>
						<li>信息采集</li>
						<li>活动登记</li>
						<li>出区登记</li>
					</ul>
				</div>
			</div>
			<!--疑犯个人身份证信息-->
			<div class="container" style="margin-top: 0%;">
				<div class="row">
					<!--身份信息标题-->
					<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						入区事由:<input type="text" value="治安传唤" />
					</h4>
					<!--疑犯个人身份证信息-->
			<div class="container">
				<div class="row">
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1" src="images/1-zhengmian_04.png" /> <img
							id="img_2" src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">${nEntryTime }&nbsp;
							&nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						<hr
							style="width: 96%;border: 0.2px solid #389ac7;padding: 0px;margin-top: 1%;margin-left: -4%;" />

							<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">
							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5"><img id="pic" src="${suspectInfor.identityCard_Photo }"
									style="width: 95px;height: 108px;position: relative;top: 0px;" />
									<input type="hidden" name="identityCard_Photo" value="value">
									<p class="info_id">身份证照</p></td>
								<td colspan="2">姓名:<input type="text" 
									name="suspect_Name" value="${suspectInfor.suspect_Name }" /></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>
								<td>性别：<input style="text-align: center;" type="text"
									name="sex" value="${suspectInfor.sex }"/></td>
								<td>民族：<input type="text" name="nation" value="汉" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生日期：<input type="text" style="width:60%;"
									name="birthday" value="${suspectInfor.birthday }" /></td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">家庭住址：</td>
							</tr>
							<tr>
								<td colspan="2"><textarea
										name="address" rows="1" cols="45">${suspectInfor.address }</textarea></td>
							</tr>
							
							<tr>
								<td><div style="margin-left: 4%;">身份证号码</div></td>
								<td colspan="2"><input type="text"
									name="identifyCard_Number" value="${suspectInfor.identifyCard_Number }"/>
								</td>

							</tr>
						</table>
						<hr
							style="width: 96%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 33%; margin-left: -4%;" />
					</div>
				</div>
			</div>
		</div>
		<!--离开办案区登记表-->
		<div class="container">
			<div class="row">
				<h4 id="leaveReco"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					离开办案区登记<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span>
				</h4>
				<div id="tab">
					<ul>
						<li class="on">临时离开办案区</li>
						<li class="two">最终离开办案区</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<form class="transient"
		action="${pageContext.request.contextPath }/LR_addTemporaryLeaveInfor.action"
		method="post">
		<div class="container">
			<div class="row">
				<table class="transient_Leave col-lg-12 col-md-10 col-sm-10">
					<tr class="bg1">
						<td>序号</td>
						<td>临时离开时间</td>
						<td>离开原因</td>
						<td>办案部门负责人签名</td>
						<td>返回时间</td>
					</tr>
					<c:if test="${!empty temporaryLeave }">
						<tr>
							<td>0</td>
							<td style="width: 35%;">
								<div class="form-group" style="height: 30px; margin-left: 80px;">
									<div style="width:100px!important;" class="input-group date form_time"
										style="margin-left: 19%;margin-top: 8%;width:60px;" data-date=""
										data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
										<input class="form-control tempLeave_Time" name="tempLeave_Time" type="text"
											value="${temporaryLeave.tempLeave_Time }"
											style="width:150px!important;margin-left:-25px;"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" /><br />
								</div>
							</td>
							<td id="select"><select name="tempLeave_Reason">
									<option value="${temporaryLeave.tempLeave_Reason }">${temporaryLeave.tempLeave_Reason }</option>
									<option value="扣押">扣押</option>
									<option value="暂存">暂存</option>
									<option value="代保管">代保管</option>
							</select></td>
							<td style="padding:6px 0 6px 0"><input name="staff_ID"
								type="text" style="border-radius:6px;border:1px solid #ccc;"
								value="${temporaryLeave.staff_ID }" /></td>
							<td style="width: 35%;">
								<div class="form-group" style="height: 30px;">
									<div class="input-group date form_time col-md-5"
										style="margin-left: 25%;margin-top: 2%;" data-date=""
										data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
										<input class="form-control" name="return_Time" type="text"
											value="${temporaryLeave.return_Time }"
											style="width:150px!important;margin-left:-25px;"> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" /><br />
								</div>
							</td>
						</tr>
					</c:if>

					<c:if test="${empty temporaryLeave }">
						<tr>
							<td>0</td>
							<td style="width: 35%;">
								<div class="form-group" style="height: 30px;">
									<div class="input-group date form_time col-md-7"
										style="margin-left: 19%;margin-top: 2%;" data-date=""
										data-date-format="hh:ii" data-link-field="dtp_input1">
										<input class="form-control" name="tempLeave_Time" type="text"
											value="" readonly> <span class="input-group-addon"><span
											class="glyphicon glyphicon-remove"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" /><br />
								</div>
							</td>
							<td id="select"><select name="tempLeave_Reason">
									<option value="">---请选择---</option>
									<option value="扣押">扣押</option>
									<option value="暂存">暂存</option>
									<option value="代保管">代保管</option>
							</select></td>
							<td style="padding:6px 0 6px 0"><input type="text"
								name="staff_ID" value=""
								style="border-radius:6px;border:1px solid #ccc;" /></td>
							<td style="width: 35%;">
								<div class="form-group" style="height: 30px;">
									<div class="input-group date form_time col-md-7"
										style="margin-left: 19%;margin-top: 2%;" data-date=""
										data-date-format="hh:ii" data-link-field="dtp_input1">
										<input class="form-control" name="return_Time" type="text"
											value="" readonly> <span class="input-group-addon"><span
											class="glyphicon glyphicon-remove"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" /><br />
								</div>
							</td>
						</tr>
					</c:if>
					<!-- <tr>
						<td>2</td>
						<td>
							<div class="btn" id="add">+添加</div>
							<div class="btn" id="delete">-删除</div>
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr> -->
				</table>
			</div>
			<div class="row" style="margin-top:30px">
				<p id="signature">
					管理员:<input type="text" name="" />
					<input type="submit" value="确认提交" class="sub" />
				</p>
				<
			</div>
		</div>

	</form>
	<!--最终离开办案区的信息表-->
	<form class="final"
		action="${pageContext.request.contextPath }/LR_addLeaveRecordInfor.action"
		method="post">
		<div class="container ">
			<div class="row ">
				<table class="final_Leave col-lg-12 col-md-12 col-sm-12">
					<tr>
						<td>最终离开时间</td>
						<td>
							<div class="form-group" style="height: 30px;">
								<div class="input-group date form_datetime col-md-5"
									style="margin-left: 20%;margin-top: 2%;width: 55%;"
									data-date="" data-date-format="dd MM yyyy - HH:ii"
									data-link-field="dtp_input1">
									<input class="form-control" name="leave_Time" size="8"
										type="text" value="" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-time"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</td>

					</tr>
					<tr>
						<td>离开原因</td>
						<td><select name="leave_Reason">
								<option value="">---请选择---</option>
								<option value="查证结束">查证结束</option>
								<option value="刑拘">刑拘</option>
								<option value="行政拘留">行政拘留</option>
								<option value="警告">警告</option>
						</select></td>
					</tr>
					<tr>
						<td class="td">随身物品处理情况:</td>
						<td><input type="radio" name="belongingS_Treatment_Method"
							value="全部反还">全部反还 <input type="radio"
							name="belongingS_Treatment_Method" value="部分反还" />部分反还 <input
							type="radio" name="belongingS_Treatment_Method" value="未反还" />未反还
						</td>
					</tr>
					<tr>
						<td>未反还物品情况记载:</td>
						<td style="padding: 8px 0;"><input name="belongingS_Treatment_Record"
								style="border-radius:6px;border:1px solid #ccc;padding:8px 0 8px 0;"/></td>
					</tr>
					<tr>
						<td>领取人签名:</td>
						<td style="padding:8px 0 8px 0"><input
							style="border-radius:6px;border:1px solid #ccc;padding:8px 0 8px 0;" type="text"
							name="" value="" /></td>
					</tr>
					<tr>
						<td>身份证号码:</td>
						<td style="padding:8px 0 8px 0"><input
							style="border-radius:6px;border:1px solid #ccc;padding:8px 0 8px 0;" type="text"
							name="" value="" /></td>
					</tr>
					<tr>
						<td>领取时间:</td>
						<td>
							<div class="form-group" style="height: 30px;">
								<div class="input-group date form_time col-md-5"
									style="margin-left: 30%;margin-top: 2%;" data-date=""
									data-date-format="hh:ii" data-link-field="dtp_input1">
									<input class="form-control" name="treatment_Time" size="8"
										type="text" value="" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-time"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="row" style="margin-top:30px">
				<p id="signature">
					管理员:<input type="text" name="" />
				</p>
				<input type="submit" value="确认提交" class="sub" />
			</div>
		</div>
	</form>
	<div style="height: 100px;"></div>
</body>
</html>