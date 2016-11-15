<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			 $(document).ready(function(){
			 	// 数据信息的显示与隐藏
			 	$(".show").click(function(){

			 		var Mes = $("#Person_info span").html();

			 		if (Mes=='隐藏信息') {

					$("#All_first").css("display","none");

					$(this).html("显示信息");


			 		} 
			 		else if (Mes=='显示信息') {
			 			$("#All_first").css("display","block");

			 			$(this).html("隐藏信息");	

			 		}

			 	});
			 	
			 	$(".show1").click(function(){
			 		var Mes = $("#Person_safety span").html();

			 		if (Mes=='隐藏信息') {

					$("#All_second").css("display","none");

					$(this).html("显示信息");


			 		}else if (Mes=='显示信息') {
			 			$("#All_second").css("display","block");

			 			$(this).html("隐藏信息");	

			 		}
			 	});
			 	
			 	$(".show2").click(function(){
			 		var Mes = $("#Info_Collect span").html();

			 		if (Mes=='隐藏信息') {

					$("#All_third").css("display","none");

					$(this).html("显示信息");


			 		}else if (Mes=='显示信息') {
			 			$("#All_third").css("display","block");

			 			$(this).html("隐藏信息");	

			 		}
			 	});
			 	
			 	 $(".show3").click(function(){
			 		var Mes = $("#record_registr span").html();

			 		if (Mes=='隐藏信息') {

					$("#All_forth").css("display","none");

					$(this).html("显示信息");


			 		}else if (Mes=='显示信息') {
			 			$("#All_forth").css("display","block");

			 			$(this).html("隐藏信息");	

			 		}
			 	});
			 	
			 	$(".show4").click(function(){
			 		var Mes = $("#Leave_depart span").html();

			 		if (Mes=='隐藏信息') {

					$("#fifth").css("display","none");

					$(this).html("显示信息");


			 		}else if (Mes=='显示信息') {
			 			$("#fifth").css("display","block");

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
			<h4 style="margin-top: 13px;"><b style="color: #389ac7;">Suspect</b> information report</h4>
			<p id="left_title">嫌疑人入区报告</p>

			<!--进度条信息设置-->
			<div class="container" style="height: 180px;">
				<div class="row">
					<!--进度的数据信息-->
					<ul id="number" class="col-lg-12 col-md-10 col-sm-10">
						<li>1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
					</ul>
					<!--进度的状态-->
					<!--引入状态截图-->
					<div id="state" class="col-lg-12 col-md-10 col-sm-10">
						<a href="#Person_info"><img src="images/fgreen_03.png" /></a>
						<a href="#Person_safety"><img src="images/fgreen_03.png" /></a>
						<a href="#Info_Collect"><img src="images/fgreen_03.png" /></a>
						<a href="#record_registr"><img src="images/fgreen_03.png" /></a>
						<a href="#Leave_depart"><img src="images/fgreen_07.png" style="margin-left: -9%;" /></a>
					</div>
				</div>
				<!--进度的信息显示-->
				<ul id="txt" class="col-lg-12 col-md-10 col-sm-10">
					<li>入区登记报告</li>
					<li>人身检查报告</li>
					<li>信息采集报告</li>
					<li>办案区活动记录</li>
					<li>出区登记报告</li>
				</ul>
				<hr style="width: 100%;border: 0.5px solid #389AC7;margin-top: 3%;" />
			</div>
			<!--进度条信息结束-->
			<form class="row">
				<h1 class="col-lg-12 col-md-10 col-sm-12"><img src="images/jinghui.png">&nbsp;&nbsp;&nbsp;&nbsp;离石区公安局嫌疑人入区报告</h1>
				<ul class="report col-lg-12 col-md-12 col-sm-10">
					<li>档案编号:<input type="text" /></li>
					<li>报告时间:<input type="date" /></li>
				</ul>
			</form>
			<hr style="width: 100%;border: 0.5px solid #389AC7;" />
			<a class="col-lg-12 col-md-12 col-sm-12" id="download_All" href="${pageContext.request.contextPath }/GR_downFile.action?date=2016-11-10&fileName=123_425184199203091528_2016111021_0100.MP4">嫌疑人入所视频文件下载</a>
			<!-- 嫌疑人入区登记 -->
			<form class="row">
				<h4 id="Person_info" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">1、入区登记报告<span class="show">隐藏信息</span></h4>
				<!--嫌疑人身份证信息-->
				<div id="All_first">
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1" src="images/1-zhengmian_04.png" />
						<img id="img_2" src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日 &nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						<!--身份证信息-->
						<p id="ID_Mes">1.1&nbsp;身份证信息</p>
						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 0%;margin-left: -2%;" />
						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">
							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5">

									<img style="width:100%;height: 75%;margin-left: -2%;" src="images/1-IDlogo_09.png" />
									<p class="info_id">身份证照</p>
								</td>
								<!--<td></td>-->
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:<input type="text" readonly="readonly" value="德古拉" /></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>

								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：<input style="text-align: center;" type="text" value="男" readonly="readonly"
									/></td>
								<td>民族：<input type="text" value="汉" readonly="readonly" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>

								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出生：<input type="text" style="width:20%;" value="1961" readonly="readonly" />年<input
										type="text" readonly="readonly" style="width:20%;text-align: center;" value="8" /> 月
									<input type="text" style="width:25%;" value="12" readonly="readonly" />日
								</td>
							</tr>
							<!--第四行身份证住址-->
							<tr>

								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><textarea readonly="readonly" rows="1" cols="30">&nbsp;&nbsp;&nbsp;山西省离石市灵石区灵城镇三海村委会名塘村37号</textarea></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证号码</td>
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="140104196108123556" readonly="readonly" /></td>
							</tr>
						</table>
						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 41%; margin-left: -2%;" />

					</div>
					<div class="row">
						<p style="border-bottom: 1px solid #389AC7; padding-bottom: 3px;color: #f69d1f;width: 10%;">1.2&nbsp;人员联系信息</p>
						<table id="people_Mes" class="col-lg-12 col-md-10 col-sm-10">
							<tr>
								<td>身份证种类:</td>
								<td colspan="4"><input type="checkbox" value="护照" />护照</td>
							</tr>
							<tr>
								<td>证件号码:</td>
								<td colspan="4"><input type="text" value="12319283279" /></td>
							</tr>
							<tr>
								<td>现住址:</td>
								<td colspan="4"><input type="text" value="六道河三里沟光荣村7组" /></td>
							</tr>
							<tr>
								<td>联系方式:</td>
								<td colspan="4"><input type="text" value="15337898699" /></td>
							</tr>
							<tr>
								<td>入区时间:</td>
								<td><input type="date" /><input type="time" /></td>
								<td>RFID:</td>
								<td colspan="4"><input type="text" value="RD312" /></td>
							</tr>
							<tr>
								<td rowspan="2">进入办案区原因:</td>
								<td>案由:</td>
								<td colspan="4"><input type="text" value="迫于我公安强大通缉压力自首" /></td>
							</tr>
							<tr>
								<td>办案民警签名:</td>
								<td><input type="text" readonly="readonly"/></td>
								<td>管理员签名:</td>
								<td><input type="text" readonly="readonly"/></td>
							</tr>
						</table>
					</div>
				</div>
			</form>
			<!-- 嫌疑人安全检查报告 -->
			<form class="row">
				<h4 id="Person_safety" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">2、人身安全检查 <span class="show1">显示信息</span></h4>
				<div id="All_second">
					<div class="row_1">
						<p class="self_state col-lg-12 col-md-10 col-sm-10">2.1&nbsp;自述情况</p>
						<ul class="checkbox col-lg-12 col-md-10 col-sm-10">
							<li>是否饮酒:</li>
							<li><input type="checkbox" value="是" />是</li>
							<li>是否患有传染性疾病:</li>
							<li><input type="checkbox" value="否" />否</li>
						</ul>
						<fieldset class="col-lg-12 col-md-12 col-sm-12">
							<legend>自述症状:</legend>
							<textarea style="height: 120px;"></textarea>
						</fieldset>
					</div>
					<!--体检信息表-->
					<div class="row_2">
						<p class="check col-lg-12 col-md-10 col-sm-10">2.2&nbsp;检查情况</p>
						<!--体检信息表-->
						<table class="checked_state col-lg-12 col-md-10 col-sm-10">
							<tr>
								<td style="width: 20%;">体表有伤 &nbsp;&nbsp;<input type="checkbox" /></td>
								<td style="width: 20%;">有饮酒 &nbsp;&nbsp;<input type="checkbox" /></td>
								<td style="width: 20%;">有拍照 &nbsp;&nbsp;<input type="checkbox" /></td>
								<td rowspan="2"><img src="images/check_08.png" style="width: 70%; margin-left: 1%; margin-top: 3%;" /></td>
								<!--引入体表检查图-->
							</tr>
							<tr>
								<td style="text-align: left; vertical-align: top;">检查情况:</td>
								<td colspan="2" style="text-align: left; vertical-align: top;"><textarea></textarea></td>
								<!--<td></td>-->
							</tr>
							<tr>
								<td>检查民警签名确认<br><input type="text" /></td>
								<td colspan="3" style="text-align: center;">被检查人/监护人签字捺印 <br><input type="text" /></td>
								<!--<td></td>-->
							</tr>
						</table>
					</div>
					<!--随身财物检查登记-->
					<div class="row_1">
						<p class="check_woods col-lg-12 col-md-10 col-sm-10">2.3&nbsp;随身财物检查登记</p>
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
							<tr>
								<td>1</td>
								<td><input type="text" value="房间钥匙" /></td>
								<td><input type="text" value="房间钥匙" /></td>
								<td><input type="number" value="2" /></td>
								<td><input type="text" value="把" /></td>
								<td><input type="text" value="房间钥匙" /></td>
								<td><input type="number" value="5" /></td>
							</tr>
						</table>
						<ul class="signature col-lg-12 col-md-10 col-sm-10">
							<li>办案人员签字:<br /><input type="text" /></li>
							<li>随身财物管理员签字:<br /><input type="text" /></li>
							<li>涉案人员签字:<br /><input type="text" /></li>
						</ul>
					</div>
				</div>
			</form>
			<!--信息采集报告-->
			<form class="row">
				<h4 id="Info_Collect" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">3、信息采集报告<span class="show2">显示信息</span></h4>
				<div id="All_third">
					<table class="info_collect col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>信息采集:</td>
							<td>是 &nbsp;&nbsp;<input type="checkbox" /></td>
							<td>否 &nbsp;&nbsp;<input type="checkbox" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>采集项目:</td>
							<td> &nbsp;&nbsp;<input type="checkbox" />身份信息</td>
							<td> &nbsp;&nbsp;<input type="checkbox" />指纹</td>
							<td> &nbsp;&nbsp;<input type="checkbox" />血液</td>
							<td> &nbsp;&nbsp;<input type="checkbox" />尿液</td>
							<td> &nbsp;&nbsp;<input type="checkbox" />其他<input type="text" /></td>
						</tr>
						<tr>
							<td>信息入库:</td>
							<td>是 &nbsp;&nbsp;<input type="checkbox" /></td>
							<td>否 &nbsp;&nbsp;<input type="checkbox" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>检查对比:</td>
							<td>是 &nbsp;&nbsp;<input type="checkbox" /></td>
							<td>否 &nbsp;&nbsp;<input type="checkbox" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</form>
			<!--办案区活动记录-->
			<form class="row">
				<h4 id="record_registr" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">4、人身安全检查<span class="show3">显示信息</span></h4>
				<div id="All_forth">
					<table class="active_check col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>序号</td>
							<td colspan="3">时间</td>
							<td>房间名称</td>
							<td>活动内容</td>
							<td>音视频编码</td>
							<td>备注</td>
						</tr>
						<tr>
							<td>1</td>
							<td>——</td>
							<td><input type="time" />时间</td>
							<td id="select">
								<select>
										<option>101</option>
										<option>102</option>
										<option>103</option>
										<option>104</option>
									</select>
							</td>
							<td id="select">
								<select>
										<option>询问</option>
										<option>讯问</option>
										<option>审讯</option>
										<option>传唤</option>
									</select>
							</td>
							<td><input type="text" value="音视频文件" /></td>
							<td><input type="text" /></td>
							<td></td>
						</tr>
					</table>
				</div>
			</form>
			<!--离开办案区登记-->
			<form class="row">
				<h4 id="Leave_depart" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">5、离开办案区登记<span class="show4">显示信息</span></h4>
				<div id="fifth">
					<div class="row_1">
						<p style="color: #389AC7;margin-top: 1%;margin-left:4%;font-size: inherit;">临时离开办案区</p>
						<table class="transient_Leave col-lg-12 col-md-10 col-sm-10">
							<tr>
								<td>序号</td>
								<td>临时离开时间</td>
								<td>离开原因</td>
								<td>办案部门负责人签名</td>
								<td>返回时间</td>
							</tr>
							<tr>
								<td>1</td>
								<td><input type="time" />时间</td>
								<td id="select">
									<select>
										<option>101</option>
										<option>102</option>
										<option>103</option>
										<option>104</option>
									</select>
								</td>
								<td><input type="text" /></td>
								<td><input type="time" />时间</td>

							</tr>
						</table>
					</div>
					<hr style="margin-top: 180px;border: 1px solid darkgray;" />
					<!--最终离开办案区的信息表-->
					<div class="row_1">
						<p style="color: #389AC7;margin-top: 2%;margin-left:4%;font-size: inherit;">最终离开办案区:</p>
						<table class="final_Leave col-lg-12 col-md-10 col-sm-10">
							<tr>
								<td>最终离开时间</td>
								<td>离开原因</td>
							</tr>
							<tr>
								<td><input type="date" /><input type="time" /></td>
								<td>
									<select>
										<option>查证结束</option>
										<option>刑拘</option>
										<option>行政拘留</option>
										<option>警告</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>随身物品处理情况:</td>
								<td>
									<input type="checkbox" />全部反还
									<input type="checkbox" />部分反还
									<input type="checkbox" />未反还
								</td>
							</tr>
							<tr>
								<td>未反还物品情况记载:</td>
								<td><textarea></textarea></td>
							</tr>
							<tr>
								<td>领取人签名:</td>
								<td><input type="text" /></td>
							</tr>
							<tr>
								<td>身份证号码:</td>
								<td><input type="text" /></td>
							</tr>
							<tr>
								<td>领取时间:</td>
								<td><input type="date" /></td>
							</tr>
						</table>

					</div>
					<hr style="margin-top: 380px;border: 1px solid darkgray;" />
					<!--</div>-->
					<p id="detain_time">该嫌疑人羁押时间:&nbsp;&nbsp;<input type="time" />小时</p>
					<p id="signature">管理员签名:&nbsp;&nbsp;&nbsp;<input type="text" /></p>
					<input id="download" type="button" value="下载" />
					<input id="print" type="button" value="打印" />
					<div style="height: 100px;"></div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>