<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>嫌疑人信息采集</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/Info_Col.css" />
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/Info_collection.js"></script>
</head>

<body>
	<form class="container" action="${pageContext.request.contextPath }/IC_addInformationCollection.action" method="post">
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;"><b style="color: #389ac7;">Personnal</b> information Collection</h4>
			<p id="left_title">嫌疑人信息采集</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center"><span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="ABS20161010-27"
					readonly="readonly" /></h5>

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

				</div>
				<!--以上的内容都是标记进度条信息的状态，现已经完全注释，以后修改的时候再打开即可-->
				<!--引入状态截图-->
				<div id="state" class="col-lg-12 col-md-10 col-sm-10">
					<img src="images/3-inforCollection_03.png" />
					<img src="images/3-inforCollection_03.png" />
					<img src="images/3-inforCollection_03.png" />
					<img src="images/3-inforCollection_03.png" />
					<img src="images/3-inforCollection_07.png" style="margin-left: -14%;" />
					<span>活动记录登记表</span>
				</div>

				<!--进度的信息显示-->
				<ul id="txt" class="col-lg-12 col-md-10 col-sm-10">
					<li>入区登记</li>
					<li>人身安全检查</li>
					<li>信息采集及项目登记</li>
					<li>信息入库</li>
					<li>检查对比</li>
				</ul>
			</div>
		</div>
		<!--疑犯个人身份证信息-->
		<div class="container" style="margin-top: -2%;">
			<div class="row">
				<!--身份信息标题-->
				<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">入区事由:<input type="text" value="治安传唤" /></h4>
				<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<img style="width: 45%; height: 42%; -webkit-box-shadow: 0px 2px 0px rgba(0,1,1,0.7);" src="images/1-zhengmian_04.png" />
					<img style="width: 53%; height: 90%; -webkit-box-shadow: 2px 4px 4px rgba(0,1,1,0.7);" src="images/1-cemian_06.png" />
					<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日 &nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

					<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />
					<!--<hr style="width: 100%; border: 1px solid red; padding: 0px;margin-top: 100%;margin-left: -5%; "/>-->

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

							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出生日期：<input type="text" style="width:20%;" value="1961" readonly="readonly" />年<input
									type="text" readonly="readonly" style="width:20%;text-align: center;" value="8" /> 月
								<input type="text" style="width:25%;" value="12" readonly="readonly" />日</td>
						</tr>
						<!--第四行身份证住址-->
						<tr>

							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;住址：</td>
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
					<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 40%; margin-left: -2%;" />

				</div>
			</div>
			<!--身份证信息结束-->
			<div class="check_time row_1">信息采集起止时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" name="IC_Date" />&nbsp;<input type="time" name="start_time" />至&nbsp;<input type="time" name="end_time"
				/></div>
			<hr style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%; margin-left: 0%;" />
			<div class="container">
				<div class="row">
					<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">信息采集<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span></h4>
					<table class="info_collect col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>信息采集:</td>
							<td>是 &nbsp;&nbsp;<input type="radio" name="Is_Collected" /></td>
							<td>否 &nbsp;&nbsp;<input type="radio" name="Is_Collected" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>采集项目:</td>
							<td> &nbsp;&nbsp;<input type="checkbox" name="Collected_Item" />身份信息</td>
							<td> &nbsp;&nbsp;<input type="checkbox" name="Collected_Item" />指纹</td>
							<td> &nbsp;&nbsp;<input type="checkbox" name="Collected_Item" />血液</td>
							<td> &nbsp;&nbsp;<input type="checkbox" name="Collected_Item" />尿液</td>
							<td> &nbsp;&nbsp;其他<input type="text" name="Collected_Item" /></td>
						</tr>
						<tr>
							<td>信息入库:</td>
							<td>是 &nbsp;&nbsp;<input type="radio" name="Is_Storaged" /></td>
							<td>否 &nbsp;&nbsp;<input type="radio" name="Is_Storaged" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>检查对比:</td>
							<td>是 &nbsp;&nbsp;<input type="radio" name="Is_Checked" /></td>
							<td>否 &nbsp;&nbsp;<input type="radio" name="Is_Checked" /></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<input class="sub" type="submit" value="确认提交" onclick="Logger()" />
					<div class="bottom" style="height: 100px;"></div>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function Logger(){
		alert("信息提交成功");
	}
</script>
</html>