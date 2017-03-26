<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- 加载jstl的c标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="contain">

<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>嫌疑人入区信息录入</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/person_info.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/jqCss_pinfo.js"></script>
<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
	id="CVR_IDCard" name="CVR_IDCard" width="0" height="0"></OBJECT>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript">
/* 
	$(function(){
		   $("#addInfoForm").on("submit",function(){
		        if($("#band_ID").val().trim()=="0"){
		           alert("提交失败，请先填写手环id");
		        }
				else{
					$.post("${pageContext.request.contextPath }/suspect_addSuspectInfor.action",function(page){
						$(".contain").html(page);
					});
				}
				 return false; 
			});   
		});	 */
		
		 /* if($(".message").val()=="success"){
			alert("信息提交成功！");
			window.location="${pageContext.request.contextPath }/home_index.action";
		 }else if($(".message").val()=="error"){
		 	alert("信息提交失败");
		 } */
	
	 	/* $(".submit").off();
		$(".submit").on("click",function(){
			 if($("#band_ID").val().trim()=="0"){
			 	 	$(".submit").addClass("disaled");
			 	 	alert("提交失败，请先填写手环id");
			 	 }
		 }); */
	 
		
	function ClearForm() {
		document.all['suspect_Name'].value = "";
		document.all['sex'].value = "";
		document.all['nation'].value = "";
		document.all['birthday'].value = "";
		document.all['address'].value = "";
		document.all['identifyCard_Number'].value = "";
		document.all['pic'].src = "";
		document.all['identityCard_Photo'].value = "";
		return true;
	}
	function Button1_onclick() {
		var CVR_IDCard = document.getElementById("CVR_IDCard");
		var strReadResult = CVR_IDCard.ReadCard();
		if (strReadResult == "0") {
			//ClearForm();//清空的意思
			document.all['suspect_Name'].value = CVR_IDCard.Name;
			document.all['sex'].value = CVR_IDCard.Sex;
			document.all['nation'].value = CVR_IDCard.Nation;
			document.all['birthday'].value = CVR_IDCard.Born;
			document.all['address'].value = CVR_IDCard.Address;
			document.all['identifyCard_Number'].value = CVR_IDCard.CardNo;
			/* 之前的代码，逻辑是直接存放照片，bug
			var str = CVR_IDCard.Pic;
			var myStr = new Array();
			myStr = str.split("\\");
			var path = "/upload/" + myStr[myStr.length - 1];
			document.all['pic'].src = path;
			document.all['tdentityID_Imag'].value = str;
			 */
			/**
			 * Date:2017.02.26
			 * author:whf
			 * change:直接存放base64码
			 */
			 var str = CVR_IDCard.Picture;//读取身份证照片的base64码
			 var base64 = "data:image/jpg;base64,"+str;//更改成代码中可以直接显示的base64编码的格式
			 document.all['pic'].src = base64;//用于显示身份证照片
			 document.all['identityCard_Photo'].value = base64;//用于将base64码存放到数据库中
		} else {
			ClearForm();
			alert(strReadResult);
		}

	}



/* 	function Logger() {
		alert("信息提交成功！");
	}
 */

	$(document).ready(function() {
		$("#identityImg").attr('src', 'images/fgreen_03.png');
		$("#identityImg1").attr('src', 'images/fgreen_03.png');
	});

	window.onload = function(e) {
		//var e = window.event || e;
		// }
		// local Storage或许会有浏览器兼容的问题
		if (typeof (Storage) !== "undefined") {
			if (localStorage.clickcount) {
				localStorage.clickcount = Number(localStorage.clickcount) + 1;
				if (localStorage.clickcount > 999) {
					localStorage.clickcount = 0;
				}
			} else {
				localStorage.clickcount = 0;
			}
		} else {
			alert("抱歉。浏览器不支持");
		}
		var date = new Date();
		var Mon = date.getMonth() + 1;
		var datetime = parseInt(date.getDate());
		if (datetime < 10) {
			document.getElementById("suspectID").value = "LB-HB" + "-"
					+ date.getFullYear() + Mon + "0" + datetime
					+ localStorage.clickcount;
		} else if (Mon < 10) {
			document.getElementById("suspectID").value = "LB-HB" + "-"
					+ date.getFullYear() + "0" + Mon + datetime
					+ localStorage.clickcount;
		} else if (localStorage.clickcount < 10) {
			document.getElementById("suspectID").value = "LB-HB" + "-"
					+ date.getFullYear() + Mon + datetime + "0"
					+ localStorage.clickcount;
		}
	}
</script>
</head>
<body>

	<form class="container" id="addInfoForm"
		action="${pageContext.request.contextPath }/suspect_addSuspectInfor.action"
		enctype="multipart/form-data" method="POST">
		<c:if test="${!empty message }">
			<input class="message" type="hidden" value=${message }>
		</c:if>
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Personnal</b> information registeration
			</h4>
			<p id="left_title">嫌疑人入区信息登记</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center">
				<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;
				<input style="width:200px;" type="text" id="suspectID"
					name="Suspect_ID" value="${SuspectInfor.suspect_ID }" />
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
					<!--以上的内容都是标记进度条信息的状态，现已经完全注释，以后修改的时候再打开即可-->
					<!--引入状态截图-->
					<div id="state" class="col-lg-12 col-md-10 col-sm-10">
						<a href="#identityInfor"><img id="identityImg"
							src="images/3-inforCollection_03.png" /></a> <a
							href="#identityInfor"><img id="identityImg1"
							src="images/3-inforCollection_03.png" /></a> <a href="#personInfor"><img
							id="personInforImg" src="images/3-inforCollection_03.png" /></a> <a
							href="#enterInfor"><img id="enterInforImg"
							src="images/3-inforCollection_03.png" /></a> <a href="#confirm"><img
							id="confirmImg" src="images/3-inforCollection_07.png"
							style="margin-left: -14%;" /></a> <span>入区登记完成</span>
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
					<h4 id="identityInfor"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">身份证信息</h4>
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1" src="images/1-zhengmian_04.png" /> <img
							id="img_2" src="images/1-cemian_06.png" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">${nEntryTime }&nbsp;
							&nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						<hr
							style="width: 100%;border: 0.2px solid #389ac7;padding: 0px;margin-top: 1%;margin-left: -4%;" />

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5"><img id="pic" src="images/1-IDlogo_09.png"
									style="width: 95px;height: 108px;position: relative;top: -10px;" />
									<input type="hidden" name="identityCard_Photo" value="value">
									<p class="info_id">身份证照</p></td>
								<td colspan="2">姓名:<input type="text" 
									name="suspect_Name" value="德古拉" /></td>

							</tr>
							<!--第二行 性别 民族-->
							<tr>
								<td>性别：<input style="text-align: center;" type="text"
									name="sex" value="男"/></td>

								<td>民族：<input type="text" name="nation" value="汉" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生日期：<input type="text" style="width:70%;"
									name="birthday" value="1961" /></td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">家庭住址：</td>
							</tr>
							
							<tr>
								<td colspan="2"><textarea name="address" rows="1" cols="45">山西省离石市灵石区灵城镇三海村委会名塘村37号</textarea></td>
							</tr>
							
							<tr>
								<td><div style="margin-left: 4%;">身份证号码</div></td>
								<td colspan="2"><input type="text"
									name="identifyCard_Number" value="140104196108123556"/>
								</td>

							</tr>
						</table>
						<hr
							style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 33%; margin-left: -4%;" />
					</div>
					<div style="z-index:1;position:absolute;left:910px;top:450px;width:850px;">
						<input class="btn" type="button" value="读卡"
							onclick="return Button1_onclick()"  />
					</div>

				</div>
				<div class="row_1" style="height: 270px; ">
					<h4 id="personInfor"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">人员联系信息</h4>
					<table class="Mes_tab col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<tr>
							<td class="col-lg-2 col-md-2 col-sm-2 col-xs-2" style="text-align:center;">证件类型：</td>
							<td>
								<ol>
									<!--  -->
									<c:forEach items="${identifyCardType}" var="item"
										varStatus="status">
										<li><input type="checkbox" name="type_ID" value="${item.type_Name }" style="width:30px;"/>${item.type_Name }</li>
									</c:forEach>
								</ol>
							</td>
						</tr>
						<tr>
							<td style="text-align:center;">证件号码:</td>
							<td><input type="text" name="" /></td>
						</tr>
						<tr>
							<td style="text-align:center;">现住址：</td>
							<td>
								<input type="text" name="now_address" />
							</td>
						</tr>
						<tr>
							<td style="text-align:center;">联系方式：</td>
							<td><input type="text" name="phone" /></td>
						</tr>
					</table>
				</div>
				<div class="row_2">
					<h4 id="enterInfor"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">进入办案区信息</h4>
					<table class="Mes_case col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<tr>
							<td style="text-align:center;">入区时间:</td>
							<td style="width: 50%;">
								<div class="form-group" style="height: 25px;width: 76%;">
									<div class="input-group date form_time col-md-5"
										style="margin-left: 40%;margin-top: 2%;width: 55%;"
										data-date="" data-date-format="dd MM yyyy"
										data-link-format="yyyy-mm-dd" data-link-field="dtp_input1">
										<input class="form-control" name="enter_Time" type="text"
											style=" height: 31px;" value="" readonly> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-remove"></span></span> <span
											class="input-group-addon"><span
											class="glyphicon glyphicon-time"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" /><br />
								</div>
							</td>
							<td style="width: 22%;text-align:center;">RFID手环 :</td>
							<!--手环选择列-->
							<td colspan="2" style="text-align-last:center ;"><select
								name="band_ID" id="band_ID">
									<option value="0">-----请选择-----</option>
									<c:forEach items="${bundList}" var="item" varStatus="status">
										<option value="${item.band_ID }">${item.band_ID }</option>
									</c:forEach>
							</select>
						</tr>
						<!--  -->
						<tr>
							<td rowspan="4" style="text-align:center;">进入办案区原因：</td>
							
						</tr>
						
						<tr>
							<td colspan="3">
								<ol>
									<!-- 遍历案由数据的字段 -->
									<c:forEach items="${actionCause}" var="item" varStatus="status">
										<li>${item.cause_Name }<input type="checkbox" name="suspected_Cause"
											value="${item.cause_Name }" style="width:30px;" /></li>
									</c:forEach>
								</ol>
							</td>
						</tr>
					</table>
				</div>
				<div class="row_3">
					<h4 id="confirm"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">干警签名确认</h4>
					<table class="signatuer_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12" style="width: 450px;">
						<tr>
							<td style="width: 12%;">办案民警：</td>
							<td style="width: 24%;text-align: center;"><input type="text" name="staff_ID" style="width:250px;" /></td>
							
						</tr>
					</table>
					<input class="btn" type="submit" value="确认提交" class="submit"/>

				</div>
			</div>
			<div class="row_4" style="height: 480px;"></div>
		</div>
	</form>
</body>

</html>