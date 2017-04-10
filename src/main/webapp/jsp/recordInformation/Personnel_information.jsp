<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
$(function() {
	$("#btnSave").click(function() {
		var x = document.getElementById("band_ID").value;
		//var y =	document.getElementById("failSubmit").value;
		alert('11111111');
		/* alert(x); */
		alert('22222222');
		if (x == 0) {
			alert('提交失败，请填写手环');
			return false;
		} else
			return true;
		
	});
});


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
		document.all['now_address'].value = CVR_IDCard.Address;
		document.all['identifyCard_Number1'].value = CVR_IDCard.CardNo;

		/**
		 * Date:2017.02.26
		 * author:whf
		 * change:直接存放base64码
		 */
		var str = CVR_IDCard.Picture;//读取身份证照片的base64码
		var base64 = "data:image/jpg;base64," + str;//更改成代码中可以直接显示的base64编码的格式
		document.all['pic'].src = base64;//用于显示身份证照片
		document.all['identityCard_Photo'].value = base64;//用于将base64码存放到数据库中
	} else {
		//ClearForm();
		alert(strReadResult);
	}

}

$(document).ready(function() {
	$("#identityImg").attr('src', 'images/fgreen_03.png');
	$("#identityImg1").attr('src', 'images/fgreen_03.png');
});

function fileshow1()
{
    document.getElementById("img_1").src=document.getElementById("file_1").value;
} 
function fileshow2()
{
    document.getElementById("img_2").src=document.getElementById("file_2").value;
} 

</script>
<style type="text/css">
	.colorRed{
		color:red !important;
	}
	.file {
		margin-left:20px;
		margin-top:10px;
	    position: relative;
	    display: inline-block;
	    background: #D0EEFF;
	    border: 1px solid #99D3F5;
	    border-radius: 4px;
	    padding: 4px 12px;
	    overflow: hidden;
	    color: #1E88C7;
	    text-decoration: none;
	    text-indent: 0;
	    line-height: 20px;
	    left:45px;
	}
	.file input {
	    position: absolute;
	    font-size: 100px;
	    right: 0px;
	    top: 0;
	    opacity: 0;
	    margin-right:-10px;
	}
	.file:hover {
	    background: #AADFFD;
	    border-color: #78C3F3;
	    color: #004974;
	    text-decoration: none;
	}
</style>
</head>
<body>

	<form class="container" id="addInfoForm"
		action="${pageContext.request.contextPath }/suspect_addSuspectInfor.action"

		enctype="multipart/form-data" method="POST">
		<c:if test="${!empty message }">
			<input class="message" type="hidden" value=${message }>
		</c:if>

		<c:if test="${!empty msg }">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span>
				${msg }
			</div>
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
					name="Suspect_ID" value="${Suspect_ID }" />
			</h5>

		

			<!--疑犯个人身份证信息-->
			<div class="container">
				<div class="row">
					<!--身份信息标题-->
					<h4 id="identityInfor" style="margin-top: -25px;"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">身份证信息</h4>
					<!-- 正面照、侧面照 -->
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4" style="margin-top:20px;">					
						<img id="img_1" src="" style="border:1px solid #ccc"/>						
						<img id="img_2" src="" style="border:1px solid #ccc"/>
		
							<a href="javascript:;" class="file">选择正面照
							    <input id="file_1" type="file" name="file" onchange="return fileshow1()">
							</a>
							<a href="javascript:;" class="file">选择侧面照
							    <input id="file_2" type="file" name="sfile" onchange="return fileshow2()">
							</a>	
						
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
						<!-- <hr
							style="width: 100%;border: 0.2px solid #389ac7;padding: 0px;margin-top: 1%;margin-left: -4%;" /> -->
						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8" style="margin-top: 20px !important;">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5"><img id="pic" src=""
									style="width: 95px;height: 108px;position: relative;top: -10px;" />
									<input type="hidden" name="identityCard_Photo" value="value">
									<p class="info_id">身份证照</p></td>
								<td colspan="2">姓名:<input type="text" name="suspect_Name"
									value="" /></td>

							</tr>
							<!--第二行 性别 民族-->
							<tr>
								<td>性别：<input style="text-align: center;" type="text"
									name="sex" value="" /></td>

								<td>民族：<input type="text" name="nation" value="" /></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生日期：<input type="text" style="width:70%;"
									name="birthday" value="" /></td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">家庭住址：</td>
							</tr>

							<tr>
								<td colspan="2"><textarea name="address" rows="1" cols="45"></textarea></td>
							</tr>

							<tr>
								<td><div style="margin-left: 4%;">身份证号码</div></td>
								<td colspan="2"><input type="text"
									name="identifyCard_Number1" value="" /></td>

							</tr>
						</table>
						<hr
							style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 33%; margin-left: -4%;" />
					</div>
					<div
						style="z-index:1;position:absolute;left:890px;top:330px;width:850px;">
						<input class="btn" type="button" value="读卡"
							onclick="return Button1_onclick()" />
					</div>

			</div>
			<div class="row_1" style="margin-top: -60px !important;">
				<h4 id="personInfor" style="margin-top: -5px;"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">人员联系信息</h4>
				<table class="Mes_tab col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<tr>
						<td class="col-lg-2 col-md-2 col-sm-2 col-xs-2"
							style="text-align:center;color: #389ac7;">证件类型：</td>
						<td style="text-align:center; width: 168px">
							
							<select name="type_ID" id="type_ID">
								<option value="0">-------------请选择-------------</option>
								<c:forEach items="${identifyCardType}" var="item" varStatus="status">
									<option value="${item.type_Name }">${item.type_Name }</option>
								</c:forEach>
						</select>
						</td>
					<td style="text-align:center;color: #389ac7;">联系方式：</td>
						<td><input type="text" name="phone" /></td>
						
						
						 </tr>
					<tr> 
						<td style="text-align:center;color: #389ac7;">证件号码：</td>
						<td  colspan="3" style="text-align:center;"><input type="text" name="identifyCard_Number" />
					 <!--</tr>
					<tr>-->
						
					 </tr>
					 <tr>
					 <td style="text-align:center;color: #389ac7;" >现住址：</td>
						<td colspan="3" style="width:300px!important" ><input  type="text" name="now_address" /></td>
					 </tr>
				</table>
			</div>
			<input type="hidden" name="enter_Time" value="${entry_Time}">
			<div class="row_2">
				<h4 id="enterInfor" 
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">进入办案区信息</h4>
				<table class="Mes_case col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<tr>
						<!--  
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
					-->
						<td style="width: 22%;text-align:center;color: #389ac7;">RFID手环 :</td>
						<!--手环选择列-->
						<td colspan="2" style="text-align-last:center ;text-align:center;"><select
							name="band_ID" id="band_ID" style="margin-left: -87%;">
								<option value="0">-----请选择-----</option>
								<c:forEach items="${bundList}" var="item" varStatus="status">
									<option value="${item.band_ID }">${item.band_ID }</option>
								</c:forEach>
						</select>
					<!-- </tr>
					
					<tr> -->
						<td rowspan="4" style="text-align:center;color: #389ac7;">进入办案区原因：</td>

					<!-- </tr>

					<tr> -->
						<td colspan="3" style="text-align:center;">
							
							<select name="suspected_Cause" id="suspected_Cause" style="margin-left: -87%;">
								<option value="0">-----请选择-----</option>
								<c:forEach items="${actionCause}" var="item" varStatus="status">
									<option value="${item.cause_Name }">${item.cause_Name }</option>
								</c:forEach>
						   </select>
						</td>
					</tr>
				</table>
			</div>
			<div class="row_3">
				<h4 id="confirm"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">干警签名确认</h4>
				<table
					class="signatuer_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="width: 450px;">
					<tr>
						<td style="width: 22%;color: #389ac7;text-align: center;">办案民警：</td>
						<td style="width: 24%;text-align: center;">
						<input
							type="text" name="staff_ID" style="width:233px;margin-left:-44%; border:0;border-bottom:1px solid #389ac7;background:#fff;" /></td>
					</tr>
				</table>
				<input id="btnSave" class="btn" type="submit" value="确认提交"
					class="submit" />
			</div>
		</div>
		<div class="row_4" style="height: 480px;"></div>
		</div>
	</form>
</body>

</html>