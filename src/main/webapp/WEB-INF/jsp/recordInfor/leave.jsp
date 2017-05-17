<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>离开办案区登记</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Leave_depart.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
	
<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
	id="CVR_IDCard" name="CVR_IDCard" width="0" height="0"></OBJECT>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Leave_depart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
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
			 if($(".staff_ID").attr("value")!=null){
				alert("经系统查询 该嫌疑人为出区返回!");
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
		
		$(".subs").on("click",function(){
			var CVR_IDCard = document.getElementById("CVR_IDCard");
			var strReadResult = CVR_IDCard.ReadCard();
			if (strReadResult == "0") {
				if($(".identity").val()==""){
					$(".identity").attr("value",CVR_IDCard.CardNo);
				}
			}
		});
		
		$(".identity").on("click",function(){
			$(".identity").attr("value","");
		});
		
		if($(".msg").text()!=""){//说明不完整
			setTimeout(alertInfo,5000);
		}
		function alertInfo(){
			/* if(!confirm("信息填写不完整！")){
				//window.location.href="${pageContext.request.contextPath }/WEB-INF/jsp/home/index.jsp";
				window.location.href="${pageContext.request.contextPath }/home/index"; 
			} */
			alert("信息填写不完整！");
		}
		
		
		$(".complete").each(function(){
			if($(this).text().trim()!=100+"%"){
				$(this).addClass("colorRed");
			}
		});
		
		
		$(".checkRadio").each(function(){
			if($(this).val()=="${PHCSMP_Leave_Record.belongingS_Treatment_Method}"){
				$(this).prop("checked",true);
			}
		});
		
		$(".checkRadio1").each(function(){
			if($(this).val()=="${PHCSMP_Leave_Record.belongingS_Treatment_Record}"){
				$(this).prop("checked",true);
			}
		});
		
});
</script>
<style type="text/css">
	.colorRed{
		color:red !important;
	}	
</style>
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
					type="text" name=suspect_ID value="${suspectInfor.suspect_ID }"
					readonly="readonly" style="background:#FFF"/>
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
						<img  src="${pageContext.request.contextPath }/images/fgreen_03.png" />
						
						<c:if test="${!empty personalCheck }">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#identityImg1").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
					       
					       });
					    </script>
						</c:if> 
						<img id="identityImg1" src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" />
						
						<c:if test="${!empty informationCollection }">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#personInforImg").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
					       
					       });
					    </script>
						</c:if>
						<img id="personInforImg" src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" />
						<c:if test="${!empty activityRecord }">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#enterInforImg").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
					       
					       });
					    </script>
						</c:if>
						<img id="enterInforImg" src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" />
						<c:if test="${!empty leaveRecord }">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#confirmImg").attr("src","${pageContext.request.contextPath }/images/fgreen_07.png");
					       
					       });
					    </script>
						</c:if>
						<img id="confirmImg" src="${pageContext.request.contextPath }/images/3-inforCollection_07.png"
							style="margin-left: -10%;" /> 
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
			<div class="container">
				<div class="row" style="margin-bottom:-20px !important;">
					<!--身份信息标题-->
					<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						入区事由:<input type="text" value="治安传唤" />
					</h4>
					<!--疑犯个人身份证信息-->
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4" style="margin-top:40px;">					
						<img id="img_1" src="${suspectInfor.frontal_Photo}" style="border:1px solid #ccc"/>						
						<img id="img_2" src="${suspectInfor.sideWays_Photo}" style="border:1px solid #ccc"/>
						<p class="date_pic col-lg-6 col-md-6 col-sm-6" style="margin-left:0px;">${nEntryTime }嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8" style="margin-top:30px;">
						<hr style="width: 96%;border: 0.2px solid #389ac7;padding: 0px;margin-top: 1%;margin-left: -10%;" />
							<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

						<tr style="padding: 0px;">
							<!--图片引入-->
							<td rowspan="5"><img
								style="width:95px;height:108px;"
								src="${suspectInfor.identityCard_Photo }" />
								<p class="info_id">身份证照</p></td>
							<!--<td></td>-->
							<td colspan="2">姓名:<span style="color: black;">${suspectInfor.suspect_Name } </span></td>
						</tr>
						<!--第二行 性别 民族-->
						<tr>
							<td>性别：<span style="color: black;">${suspectInfor.sex } </span></td>
							<td>民族：<span style="color: black;">${suspectInfor.nation }</span></td>
						</tr>
						<!--第三行 出生-->
						<tr>
							<td colspan="2">出生日期：<span style="color: black;">${suspectInfor.birthday } </span></td>
						</tr>
						<!--第四行身份证住址-->
						<tr>
							<td colspan="2">住址：</td>
						</tr>
						<tr>
							<td colspan="2"><span style="color: black;">${suspectInfor.address }</span></td>
						</tr>
						<tr >
							<td>&nbsp;身份证号码</td>
							<td colspan="2"><span style="color: black;">${suspectInfor.identifyCard_Number }</span></td>
						</tr>
					</table>
						<hr style="width: 96%; border: 0.2px solid #389ac7; padding: 0px;margin-top:33%; margin-left:-10%;" />
					</div>
				</div>
			</div>
		<!-- 完整性检查表 -->
		<div class="container">
			<div class="row">
				<h4 id="leaveReco"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					完整性检查<!-- <span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span> -->
				</h4>
			</div>
		</div>
		<div class="msg" style="color:red;margin-top:20px;margin-left:25px;">${sb }</div>
		<div class="container" style="margin-top:20px;">
			<div class="row">
				<table class="transient_Leave col-lg-12 col-md-10 col-sm-10">
					<tr class="bg1">
						<td style="width:20%">业务</td>
						<td style="width:20%">开始时间</td>
						<td style="width:20%">结束时间</td>
						<td style="width:10%">完整度</td>
						<td style="width:30%">备注</td>
					</tr>

					<tr>
						<td>入区登记</td>
						<c:if test="${!empty suspectInfor }">
							<td>${suspectInfor.enter_Time }</td>
							<td>----</td>
							<td class="complete">${suspectComplete}%</td>
							<c:if test="${empty suspectInfor.suspected_Cause }">
								<td style="text-align:left;padding-left:30px;">进入办案区原因：空</td>
							</c:if>
							<c:if test="${!empty suspectInfor.suspected_Cause }">
								<td style="text-align:left;padding-left:30px;">进入办案区原因：${suspectInfor.suspected_Cause }</td>
							</c:if>
						</c:if>
						<c:if test="${empty suspectInfor }">
							<td>空</td>
							<td>----</td>
							<td class="complete">${suspectComplete}%</td>
							<td style="text-align:left;padding-left:30px;">进入办案区原因：空</td>
						</c:if>				
					</tr>
					<tr>
						<td>人身检查</td>
						<c:if test="${!empty personalCheck }">
							<td>${personalCheck.check_StartTime }</td>
							<td>${personalCheck.check_EndTime }</td>
							<td class="complete">${personalCheckComplete }%</td>
							<c:if  test="${empty personalCheck.check_Situation }">
								<td style="text-align:left;padding-left:30px;">人身检查状态:空</td>
							</c:if>
							<c:if  test="${! empty personalCheck.check_Situation }">
								<td style="text-align:left;padding-left:30px;">人身检查状态:${personalCheck.check_Situation }</td>
							</c:if>
						</c:if>
						<c:if test="${empty personalCheck }">
							<td>空</td>
							<td>空</td>
							<td class="complete">${personalCheckComplete }%</td>
							<td style="text-align:left;padding-left:30px;">人身检查状态:空</td>
						</c:if>
					</tr>
					<tr>
						<td>信息采集</td>
						<c:if test="${!empty informationCollection }">
							<td>${informationCollection.ic_StartTime }</td>
							<td>${informationCollection.ic_EndTime }</td>
							<td class="complete">${informationCollectionComplete }%</td>
							<c:if test="${!empty informationCollection.collected_Item }">
								<td style="text-align:left;padding-left:30px;">采集项目:${informationCollection.collected_Item }</td>
							</c:if>
							<c:if test="${empty informationCollection.collected_Item }">
								<td style="text-align:left;padding-left:30px;">采集项目:空</td>
							</c:if>
						</c:if>
						<c:if test="${empty informationCollection }">
							<td>空</td>
							<td>空</td>
							<td class="complete">${informationCollectionComplete }%</td>
							<td style="text-align:left;padding-left:30px;">采集项目:空</td>
						</c:if>					
					</tr>
					<c:if test="${! empty activityRecordList }">
						<c:forEach items="${activityRecordList }" var="a" varStatus="s">
							<tr>
								<c:if test="${fn:length(activityRecordList) gt 1}">
									<td>询问讯问${s.index+1}</td>
								</c:if>
								<c:if test="${fn:length(activityRecordList) eq 1}">
									<td>询问讯问</td>
								</c:if>
								<td>${a.start_Time }</td>
								<td>${a.end_Time }</td>
								
								
								<%-- <td class="complete activityComplete">${activityRecordCompleteList[s.index]}%</td> --%>
								<td class="complete activityComplete">---</td>
								<c:if test="${! empty a.activity_Record }">	
									<td style="text-align:left;padding-left:30px;" class="activity">活动内容：${a.activity_Record }</td>						
								</c:if>
								<c:if test="${empty a.activity_Record }">	
									<td style="text-align:left;padding-left:30px;" class="activity">活动内容：空</td>						
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty activityRecordList }">
					<tr>
						<td>询问讯问</td>
						<td>空</td>
						<td>空</td>
						<td class="complete">---</td>
						<td style="text-align:left;padding-left:30px;">活动内容：空</td>
						</tr>
					</c:if>
					<c:if test="${!empty pastList }">
						<c:forEach items="${pastList }" var="p" varStatus="status">
						    <tr>
						    <td>临时离区${status.index+1 }</td> 
						    <td>${p.tempLeave_Time }</td>
						    <td>${p.return_Time }</td>
						    <td class="complete">---</td>
							<td style="text-align:left;padding-left:30px;">离区原因：${p.tempLeave_Reason }</td>
							</tr>	
						</c:forEach>
					</c:if>
					
				</table>
			</div>
		</div>
	<!--离开办案区登记表-->
		<div class="container">
			<div class="row">
				<h4 id="leaveReco"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					离开办案区登记<!-- <span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span> -->
				</h4>
				<div id="tab" style="margin-left:16px;">
					<ul>
						<li class="on">临时离开办案区</li>
						<li class="two">最终离开办案区</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<form class="transient" id="form1"
		action="${pageContext.request.contextPath }/leave/addtemp"
		method="post">
		<div class="container">
			<div class="row">
				<table class="transient_Leave col-lg-12 col-md-10 col-sm-10">
					
					<c:if test="${!empty temporaryLeave }">
						<tr class="bg1">						
						<td style="width:30%">临时离区原因</td>
						<td style="width:40%">办案部门负责人签名</td>
						</tr>
						<tr>							
							<td id="select">
								<input 
								type="text" style="border-radius:6px;border:1px solid #ccc;padding:8px 0 8px 0;text-align: center;"
								value="${temporaryLeave.tempLeave_Reason }" readonly="readonly" />
							</td>
							<td style="padding:6px 0 6px 0">
							<select name="return_staff_ID" value="${temporaryLeave.tempLeave_staff_ID }" id="return_staff_ID">
									<!-- <option value="">--------请选择--------</option> -->
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select>
							</td>		
						</tr>
					</c:if>

					<c:if test="${empty temporaryLeave }">
					<tr class="bg1">						
						<td style="width:30%">临时离开原因</td>
						<td style="width:40%">办案部门负责人签名</td>
					</tr>
						<tr>													
							<td id="select"><select name="tempLeave_Reason">
								<c:if test="${!empty tempLeave_Reason }">
									<option>${tempLeave_Reason }</option>
								</c:if>
								<option value="">---请选择---</option>
								<c:forEach items="${leaveReason }" var="v" varStatus="status">
									<option value="${v.leaving_Name }">${v.leaving_Name }</option>
								</c:forEach>
							</select></td>
							<td style="padding:6px 0 6px 0">
							<select name="tempLeave_staff_ID" id="tempLeave_staff_ID">
									<option value="">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select></td>							
						</tr>
					</c:if>					
				</table>
			</div>
			<div class="row" style="margin-top:30px;width:1000px;">
				<div style="width:400px;margin-left: 150px;">
				<p id="signature">
					<font color="#389AC7">管理员:</font>
							<select name="tempLeave_manager" id="tempLeavemanager" style=" font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select>		
				</p>
				</div>
				<div style="width:460px;margin-left: -160px;margin-top: -10px;position: relative;left: 357px;top: 28px;">
					<input type="submit" value="确认提交" class="sub" onclick="check1()" />
				</div>			
			</div>
		</div>
		<input type="hidden" name="suspectID" value="${suspectInfor.suspect_ID }"/>
	</form>
	<!--最终离开办案区的信息表-->
	<form class="final" id="form"
		action="${pageContext.request.contextPath }/leave/add"
		method="post">
		<div class="container ">
			<div class="row ">
				<table class="final_Leave col-lg-12 col-md-12 col-sm-12">					
					<tr>
						<td>离开原因</td>
						<td><select name="leave_Reason">
							<c:if test="${! empty PHCSMP_Leave_Record.leave_Reason}">
								<option value="">${PHCSMP_Leave_Record.leave_Reason }</option>
							</c:if>
							<option value="">---请选择---</option>
							<c:forEach items="${leaveReason }" var="v" varStatus="status">
								<option value="${v.leaving_Name }">${v.leaving_Name }</option>
							</c:forEach>							
						</select></td>
					</tr>
					<tr>
						<td class="td">随身物品处理情况:</td>
						<td>	
							<c:forEach items="${keepingWay }" var="v" varStatus="status">
								<label for="belongingS_Treatment_Method${status.index }" style="font-weight:normal"><input type="radio" id="belongingS_Treatment_Method${status.index }"name="belongingS_Treatment_Method" value="${v.keeping_Name }" class="checkRadio">${v.keeping_Name }</label>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td style="color:#000 !important;">未反还物品情况记载:</td>						
						<td style="padding: 8px 0;">
							<c:forEach items="${treatmentMethod }" var="t" varStatus="status">
								<label for="belongingS_Treatment_Record${status.index }" style="font-weight:normal"><input id="belongingS_Treatment_Record${status.index }"type="radio" name="belongingS_Treatment_Record" value="${t.treatment_Name }" class="checkRadio1">${t.treatment_Name }</label>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>领取人签名:</td>
						<td style="padding:8px 0 8px 0"><input
							style="border-radius:6px;border:1px solid #ccc;padding:8px 0 8px 0;" type="text"
							name="recipient_Person" value="${PHCSMP_Leave_Record.recipient_Person }" /></td>
					</tr>
					<tr>
						<td>领取人身份证号码:</td>
						<td style="padding:8px 0 8px 0"><input
							style="margin-left:60px;border-radius:6px;border:1px solid #ccc;padding:8px 0 8px 0;" type="text"
							name="recipient_Person_Number" value="${recipient_Person_Number }" class="identity"/>
							<input type="button" value="读卡" class="subs" />
						</td>
					</tr>					
				</table>
			</div>
			<div class="row" style="margin-top:30px;width:1000px;">
				<div style="float:left;width:300px;margin-left: 220px;position: relative;left: 107px;">
				
					管理员:
							<select name="tempLeave_manager" id="tempLeave_manager1" style="font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							<lect>		
				
				</div>
				<div style="float:left;width:460px;margin-left: -80px;margin-top: -10px;">
					<input style="height: 30px;text-align:center;line-height: 30px;width: 84px;position: relative;left: -61px;top: 21px; " type="button" onclick="check()" value="确认提交" class="sub" id="btnAdd" />
				</div>
				
			</div>

		</div>
		<input type="hidden" name="suspectID" value="${suspectInfor.suspect_ID }"/>
	</form>
	<div style="height: 100px;"></div>
</body>
<script type="text/javascript">
function check(){
			
			var tempLeave_manager1 = document.getElementById("tempLeave_manager1").value;
			
		 if( tempLeave_manager1==0){
			alert('提交失败，请填写管理人员');
			return false;
		}
		else{
			document.getElementById("form").submit();
			return true;
			}
		}
		
		function check1(){
		
			//var return_staff_ID=document.getElementById("return_staff_ID").value;
			//var tempLeave_staff_ID = document.getElementById("tempLeave_staff_ID").value;
			var tempLeave_manager = document.getElementById("tempLeavemanager").value;
			alert(return_staff_ID+""+tempLeave_staff_ID+""+tempLeave_manager);
			
			if(return_staff_ID==0 || tempLeave_staff_ID ==0){
				alert('提交失败，请填写办案人员');
			return false;
		} 
		else if(tempLeave_manager==0){
			alert('提交失败，请填写管理人员');
			return false;
		}
		else{
			document.getElementById("form1").submit();
			return true;
			}
		}

		


</script>
<script type="text/javascript">
	document.getElementById("return_staff_ID").value = "${temporaryLeave.tempLeave_staff_ID }";
	document.getElementById("return_staff_ID")[${temporaryLeave.tempLeave_staff_ID }].selected = true;
</script>
</html>