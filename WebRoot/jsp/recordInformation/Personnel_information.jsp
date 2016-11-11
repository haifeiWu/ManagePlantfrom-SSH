<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 加载jstl的c标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>嫌疑人入区信息录入</title>
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/person_info.css" />
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jqCss_pinfo.js"></script>
</head>
<body>
	<form class="container" action="${pageContext.request.contextPath }/suspect_addSuspectInfor.action" method="POST">
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;"><b style="color: #389ac7;">Personnal</b> information registeration</h4>
			<p id="left_title">嫌疑人入区信息登记</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center"><span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="Suspect_ID" value="ABS20161010-27"
			/></h5>

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
					<!--以上的内容都是标记进度条信息的状态，现已经完全注释，以后修改的时候再打开即可-->
					<!--引入状态截图-->
					<div id="state" class="col-lg-12 col-md-10 col-sm-10">
						<a href="#identityInfor"><img src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" /></a>
						<a href="#identityInfor"><img src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" /></a>
						<a href="#personInfor"><img src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" /></a>
						<a href="#enterInfor"><img src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" /></a>
						<a href="#confirm"><img src="${pageContext.request.contextPath }/images/3-inforCollection_07.png" style="margin-left: -14%;" /></a>
						<span>入区登记完成</span>
					</div>

					<!--进度的信息显示-->
					<ul id="txt" class="col-lg-12 col-md-10 col-sm-10">
						<li>身份证信息</li>
						<li>人员联系信息</li>
						<li>进入办案区信息</li>
						<li>干警签名确认</li>
					</ul>

				</div>
			</div>
			<!--疑犯个人身份证信息-->
			<div class="container">
				<div class="row">
					<!--身份信息标题-->
					<h4 id="identityInfor" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">身份证信息</h4>
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img style="width: 45%; height: 42%; -webkit-box-shadow: 0px 2px 0px rgba(0,1,1,0.7);" src="images/1-zhengmian_04.png" />
						<img style="width: 53%; height: 90%; -webkit-box-shadow: 2px 4px 4px rgba(0,1,1,0.7);" src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日 &nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5">
									<img style="width:100%;height: 75%;margin-left: -2%;" src="images/1-IDlogo_09.png" />
									<p class="info_id">身份证照</p>
								</td>
								<!--<td></td>-->
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:<input type="text" readonly="readonly" name="suspect_Name" value="德古拉" /></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>

								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性别：<input style="text-align: center;" type="text" name="sex" value="男" readonly="readonly"
									/></td>
								<td>民族：<input type="text" name="nation" value="汉" readonly="readonly" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>

								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出生日期：<input type="text" style="width:20%;" name="birthday" value="1961" readonly="readonly" />年<input
										type="text" readonly="readonly" style="width:20%;text-align: center;" name="birthday" value="8" /> 月
									<input type="text" style="width:25%;" name="birthday" value="12" readonly="readonly" />日</td>
							</tr>
							<!--第四行身份证住址-->
							<tr>

								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家庭住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><textarea readonly="readonly" name="address" rows="1" cols="30">&nbsp;&nbsp;&nbsp;山西省离石市灵石区灵城镇三海村委会名塘村37号</textarea></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证号码</td>
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="identifyCard_Number" value="140104196108123556" readonly="readonly" /></td>
							</tr>
						</table>
						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 41%; margin-left: -2%;" />
					</div>
				</div>
				<div class="row_1">
					<h4 id="personInfor" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">人员联系信息</h4>
					<table class="Mes_tab col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<tr>
							<td class="col-lg-2 col-md-2 col-sm-2 col-xs-2">证件类型：</td>
							<td>
								<ol>
									<!--  -->
									<c:forEach items="${identifyCardType}" var="item" varStatus="status">
										<li><input type="checkbox" name="type_ID" value="${item.type_Name }" />${item.type_Name }</li>
									</c:forEach>
								</ol>
							</td>
						</tr>
						<tr>
							<td>证件号码:</td>
							<td><input type="text" name="identifyCard_Number" /></td>
						</tr>
						<tr>
							<td>现住址：</td>
							<td><textarea name="now_address" >居无定所</textarea></td>
						</tr>
						<tr>
							<td>联系方式（手机或固定电话）：</td>
							<td><input type="text" name="phone" /></td>
						</tr>
					</table>
				</div>
				<div class="row_2">
					<h4 id="enterInfor" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">进入办案区信息</h4>
					<table class="Mes_case col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<tr>
							<td>入区时间</td>
							<td><input type="date" name="enter_Time" /></td>
							<td style="width: 22%;">RFID手环 :</td>
							<!--手环选择列-->
							<td colspan="2" style="text-align-last:center ;">
							
							<select name="band_ID" id="band_ID" >
								<option value="0">-----请选择-----</option>
								<c:forEach items="${bundList}" var="item" varStatus="status">
								<option value="${item.band_ID }">${item.band_ID }</option>
								</c:forEach>   
							</select>
						</tr>
						<tr>
							<td rowspan="4">进入办案区原因：</td>
							<td>案由：</td>
							<td colspan="3"><input type="text" name="suspected_Cause"/></td>
						</tr>
						<tr>
							<td colspan="3">
								<ol>
									<!-- 遍历案由数据的字段 -->
									<c:forEach items="${actionCause}" var="item" varStatus="status">
										<li><input type="checkbox" name="suspected_Cause" value="${item.cause_Name }" />${item.cause_Name }</li>
									</c:forEach>
								</ol>
							</td>
						</tr>
					</table>
				</div>
				<div class="row_3">
					<h4 id="confirm" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">干警签名确认</h4>
					<table class="signatuer_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<tr>
							<td>办案民警：</td>
							<td><input type="text" name="staff_ID" /></td>
							<td>管理员：</td>
							<td><input type="text" name="" value="${users.real_Name }" /></td>
						</tr>
					</table>
					<input class="btn" type="submit" value="确认提交" onclick="Logger()"/>
				</div>
			</div>
			<div class="row_4" style="height: 480px;"></div>
		</div>
	</form>
</body>

<script type="text/javascript">
	function Logger(){
		alert("信息提交成功！");
	}
</script>

</html>