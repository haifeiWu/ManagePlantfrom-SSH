<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/Leave_depart.js"></script>
	
	<script type="text/javascript">
	var index = 0;
	$(function(){
	 $("#add").click(function()
		{
		 	var num = $(".transient_Leave tr").length;
		 		index = num-2;
		 	var tdnum = $(".transient_Leave tr:last()").find("td:eq(0)");
		 	//添加下一行
		 	var addrow = "<tr>"
			 			+"<td>"+index+"</td>"
						+"<td style=width:35%;>"
						+"<div class='form-group' style='height: 30px;' >"
						+"<div class='input-group date form_time col-md-5' style='margin-left: 30%;margin-top: 2%;' data-date='' data-date-format='hh:ii' data-link-field='dtp_input1'>"
               			+"<input class=form-control name=temporaryLeave["+index+"].TempLeave_Time type=text readonly>"
                    	+"<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>"
						+"<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
                		+"</div>"
						+"<input type='hidden' id='dtp_input1'/><br/>"
            			+"</td>" 
						+"<td> <select name=temporaryLeave["+index+"].TempLeave_Reason> <option value=>---请选择---</option> <option value=1>扣押</option> <option value=2>暂存</option> <option value=3>代保管</option> </select> </td>"
						+"<td><input name=temporaryLeave["+index+"].Staff_ID /></td>"
						+"<td style=width:35%;>"
						+"<div class='form-group' style='height: 30px;' >"
						+"<div class='input-group date form_time col-md-5' style='margin-left: 30%;margin-top: 2%;' data-date='' data-date-format='hh:ii' data-link-field='dtp_input1'>"
               			+"<input class=form-control name=temporaryLeave["+index+"].Return_Time type=text readonly>"
                    	+"<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>"
						+"<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
                		+"</div>"
						+"<input type='hidden' id='dtp_input1'/><br/>"
            			+"</div> </td>"
						+"</tr>";
		 	$(".transient_Leave tr").eq($(".transient_Leave tr").length-2).after(addrow);
		  /* 	addrow.find("td:eq(0)").html(num-1); */
			tdnum.html(num);
		 });
			//删除行
		$("#delete").click(function()
		{
			var len = $(".transient_Leave tr").length; //获取当前表格行数
			var td=$(this).parent().prev().html();  //获取当前行序号
			var delrow = $(".transient_Leave tr").get($(".transient_Leave tr").length - 2);
			if (len>2) 
			{
				$(delrow).remove();
				$(this).parent().prev().html($(this).parent().prev().html()-1);
			} 	
		});
		
		 $(".form_time").datetimepicker({
	      	language:  'zh-CN',
	        format: 'hh:ii',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 1,
			minView: 0,
			maxView: 1,
			forceParse: 0
	    });
	    
		});
		
		
	</script>
</head>
<body>
	<form class="container" action="${pageContext.request.contextPath }/LR_addLeaveRecordInfor.action" method="post">
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;"><b style="color: #389ac7;">Registration</b> of departure</h4>
			<p id="left_title">离开办案区登记</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center"><span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="Suspect_ID" value="${SuspectInfor.Suspect_ID }" readonly="readonly" /></h5>
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
						<a href="suspect_updateInfor.action?Suspect_ID=haifieisi"><img src="images/fgreen_03.png" /></a>
						<a href="personalCheck_updateInfor.action?Suspect_ID=haifieisi"><img src="images/fgreen_03.png" /></a>
						<a href="IC_updateInfor.action?Suspect_ID=haifieisi"><img src="images/3-inforCollection_03.png" /></a>
						<a href="AR_updateInfor.action?Suspect_ID=haifieisi"><img src="images/3-inforCollection_03.png" /></a>
						<a href="#leaveReco"><img src="images/3-inforCollection_07.png" /></a>
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
					<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">入区事由:<input type="text" value="治安传唤" /></h4>
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1" src="images/1-zhengmian_04.png" />
						<img id="img_2" src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日 &nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5">
									<img style="width:89%;height: 75%;margin-left: -2%;" src="images/1-IDlogo_09.png" />
									<p class="info_id">身份证照</p>
								</td>
								<!--<td></td>-->
								<td colspan="2">姓名:<input type="text" readonly="readonly" value="德古拉" /></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>

								<td>性别：<input style="text-align: center;" type="text" value="男" readonly="readonly"
									/></td>
								<td>民族：<input type="text" value="汉" readonly="readonly" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生：<input type="text" style="width:20%;" value="1961" readonly="readonly" />年<input
										type="text" readonly="readonly" style="width:20%;text-align: center;" value="8" /> 月
									<input type="text" style="width:25%;" value="12" readonly="readonly" />日</td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">身份证住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><textarea readonly="readonly" rows="1" cols="30">山西省离石市灵石区灵城镇三海村委会名塘村37号</textarea></td>
							</tr>
							<tr>
								<td><div style="margin-left: 38px;">身份证号码</div></td>
								<td colspan="2"><input type="text" value="140104196108123556" readonly="readonly" /></td>
							</tr>
						</table>
						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 40%; margin-left: -2%;" />
					</div>
				</div>
			</div>
			<!--离开办案区登记表-->
			<div class="row">
				<h4 id="leaveReco" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">离开办案区登记<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span></h4>
				<p style="color: #389AC7;margin-top: 10%;margin-left:4%;font-size: large;">临时离开办案区</p>
				<table class="transient_Leave col-lg-12 col-md-10 col-sm-10">
					<tr>
						<td>序号</td>
						<td>临时离开时间</td>
						<td>离开原因</td>
						<td>办案部门负责人签名</td>
						<td>返回时间</td>
					</tr>
					<tr>
						<td>0</td>
						<td style="width: 35%;">
							<div class="form-group" style="height: 30px;" >
				                <div class="input-group date form_time col-md-5" style="margin-left: 30%;margin-top: 2%;" data-date="" data-date-format="hh:ii" data-link-field="dtp_input1">
				                    <input class="form-control" name="temporaryLeave[0].TempLeave_Time" type="text" value="" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
				                </div>
								<input type="hidden" id="dtp_input1" value="" /><br/>
				            </div>
						</td>
						<td id="select">
							<select name="temporaryLeave[0].TempLeave_Reason">
								<option value="">---请选择---</option>
								<option value="扣押">扣押</option>
								<option value="暂存">暂存</option>
								<option value="代保管">代保管</option>
							</select>
						</td>
						<td><input name="temporaryLeave[0].Staff_ID" /></td>
						<td style="width: 35%;">
							<div class="form-group" style="height: 30px;" >
				                <div class="input-group date form_time col-md-5" style="margin-left: 30%;margin-top: 2%;" data-date="" data-date-format="hh:ii" data-link-field="dtp_input1">
				                    <input class="form-control" name="temporaryLeave[0].Return_Time" type="text" value="" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
				                </div>
								<input type="hidden" id="dtp_input1" value="" /><br/>
				            </div>
						</td>
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
				<!-- <script type="text/javascript">
						  $('.form_datetime').datetimepicker({
						      	language:  'zh-CN',
						        format: 'hh:ii',
						        weekStart: 1,
						        todayBtn:  1,
								autoclose: 1,
								todayHighlight: 1,
								startView: 1,
								minView: 0,
								maxView: 1,
								forceParse: 0
						    });
				 </script> -->
			</div>
			<hr style="margin-top: 3%;border: 1px solid darkgray;" />
			<!--最终离开办案区的信息表-->
			<div class="row">
				<p style="color: #389AC7;margin-top: 0%;margin-left:4%;font-size: large;">最终离开办案区:</p>
				
				<table class="final_Leave col-lg-12 col-md-12 col-sm-12">
					<tr>
						<td>最终离开时间</td>
						<td>离开原因</td>
					</tr>
					<tr>
						<td>
							<div class="form-group" style="height: 30px;" >
				                <div class="input-group date form_time col-md-5" style="margin-left: 30%;margin-top: 2%;" data-date="" data-date-format="hh:ii" data-link-field="dtp_input1">
				                    <input class="form-control" name="" size="8" type="text" value="" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
				                </div>
								<input type="hidden" id="dtp_input1" value="" /><br/>
				            </div>
						</td>
						<td>
							<select name="Leave_Reason">
								<option value="">---请选择---</option>
								<option value="查证结束">查证结束</option>
								<option value="刑拘">刑拘</option>
								<option value="行政拘留">行政拘留</option>
								<option value="警告">警告</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>随身物品处理情况:</td>
						<td>
							<input type="radio" name="BelongingS_Treatment_Method" value="全部反还">全部反还
							<input type="radio" name="BelongingS_Treatment_Method" value="部分反还"/>部分反还
							<input type="radio" name="BelongingS_Treatment_Method" value="未反还"/>未反还
						</td>
					</tr>
					<tr>
						<td>未反还物品情况记载:</td>
						<td><textarea name="BelongingS_Treatment_Record"></textarea></td>
					</tr>
					<tr>
						<td>领取人签名:</td>
						<td><input type="text" name="Recipient_Person" /></td>
					</tr>
					<tr>
						<td>身份证号码:</td>
						<td><input type="text" name="Recipient_Person_Number" /></td>
					</tr>
					<tr>
						<td>领取时间:</td>
						<td><input type="time" name="" /></td>
					</tr>
				</table>
				
			</div>
			<hr style="margin-top: 3%;border: 1px solid darkgray;" />
			<p id="signature">管理员:<input type="text" name="" /></p>
		</div>
		<input type="submit" value="确认提交" class="sub" />
	</form>
			<div style="height: 100px;"></div>
</body>

</html>