
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>活动记录登记</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/record_regist.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/record_regist.js"></script>
<script type="text/javascript">
	var index = 0;
	  
	$(function() {
		 setTimeout(is_Checked_Complete,3000);
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
									+ "<td style='width: 38%;'>"
							+"<div class='form-group' style='height: 30px;width: 190%;'>"
								+"<div class='input-group date form_time col-md-5'"
									+"style='margin-left: 8%;margin-top: 2%;'"
									+"data-date-format='yyyy-mm-dd hh:ii' data-link-field='dtp_input1'>"
									+"<input class='form-control' name='activity["+index+"].start_Time' type='text'>  "
									+	"<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span> "
									+   "<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
								+"</div>"
								+"<input type='hidden' id='dtp_input1' /><br />"
							+"</div>"
						+"</td>"
									+"<td style='width: 38%;'>"
							+"<div class='form-group' style='height: 30px;width: 190%;'>"
								+"<div class='input-group date form_time col-md-5'"
									+"style='margin-left: 8%;margin-top: 2%;'"
									+"data-date-format='yyyy-mm-dd hh:ii' data-link-field='dtp_input1'>"
									+"<input class='form-control' name='activity["+index+"].end_Time' type='text'> "
									+	"<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span> "
									+   "<span class='input-group-addon'><span class='glyphicon glyphicon-time'></span></span>"
								+"</div>"
								+"<input type='hidden' id='dtp_input1' /><br />"
							+"</div>"
						+"</td>"
									+ "<td> <select name=activity["+index+"].activity_Record> <option value=>---请选择---</option> <option value=询问>询问</option> <option value=讯问>讯问</option> <option value=审讯>审讯</option><option value=传唤>传唤</option> </select> </td>"
									+"<td><input type='text' name='activity["+index+"].remark' style='width:250px; height: 30px;text-align: center;' /></td>"
									+ "</tr>";
							$(".active_check tr").eq(
									$(".active_check tr").length - 2).after(
									addrow);
							//addrow.find("td:eq(0)").html(num - 1);
							tdnum.html(num-1);
							
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
	<form class="container" id="form"
		action="${pageContext.request.contextPath }/activity/add"
		method="post">
			<div class="row">
				<!--嫌疑人入区信息-->
				<h4 style="margin-top: 13px;">
					<b style="color: #389ac7;">Activity</b> record registration
				</h4>
				<p id="left_title">活动记录登记</p>
				<!--设置标题：档案编号：-->
				<h5 class="col-lg-12 col-md-10 text-center" >
					<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="suspect_ID" style="color: black;" value="${SuspectInfor.suspect_ID }" />
				</h5>
				<!--进度条信息设置-->
				<div class="container" style="height: 180px;">
					<div class="row">
						<!--进度的数据信息-->
	
						<div id="state" class="col-lg-12 col-md-10 col-sm-10" style="margin-top: 30px;">
	
							<img id="identityImg" src="${pageContext.request.contextPath }/images/fgreen_03.png" />
							<c:if test="${!empty personal_Check }">
								<script type="text/javascript">
						       $(document).ready(function(){
						            $("#identityImg1").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
						       
						       });
						    </script>
							</c:if> 
							<img id="identityImg1" src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" />
							<c:if test="${!empty information_Collection }">
								<script type="text/javascript">
						       $(document).ready(function(){
						            $("#personInforImg").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
						       
						       });
						    </script>
							</c:if>
							<img id="personInforImg" src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" />
							<c:if test="${!empty activity_record_infor}">
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
					<!--在该容器下第一个row结束-->
			</div>
			<!--进度条信息结束-->
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
							style="margin-left:17%; width: 120px; height: 156px; -webkit-box-shadow: 0px 2px 0px rgba(0,1,1,0.7);"
							src="${SuspectInfor.frontal_Photo }" /> <img
							style="width: 120px; height: 156px; -webkit-box-shadow: 2px 4px 4px rgba(0,1,1,0.7);"
							src="${SuspectInfor.sideWays_Photo }" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日
							&nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

						<hr
							style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -3%;" />

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5"><img
									style="width:95px;height: 108px;"
									src="${SuspectInfor.identityCard_Photo }" />
									<p class="info_id">身份证照</p></td>
								<td colspan="2">姓名:<span style="color: black;">${SuspectInfor.suspect_Name } </span></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>
								<td>性别：<span style="color: black;">${SuspectInfor.sex } </span></td>
								<td>民族：<span style="color: black;">${SuspectInfor.nation }</span></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2">出生日期：<span style="color: black;">${SuspectInfor.birthday } </span></td>
							</tr>
							<!--第四行身份证住址-->
							<tr>
								<td colspan="2">住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><span style="color: black;">${SuspectInfor.address }</span></td>
							</tr>
							<tr>
								<td><div style="margin-left: 38px;">身份证号码</div></td>
								<td colspan="2"><span style="color: black;">${SuspectInfor.identifyCard_Number  } </span></tr>
						</table>
						<hr
							style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 30%; margin-left: -3%;" />
					</div>
				</div>
			</div>
			
			<!-- 信息采集 --> 
			<c:if test="${!empty information_Collection}">
				<div class="container" style="margin-top: 0%;display: none;" >
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
									<td ><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">是否入库</p></td>
									<td><input type="text" style="width: 280px !important;" readonly="readonly" value="${information_Collection.is_Storaged}" /></td>
									<td><p style="margin-left: 10px; margin-top:10px !important;  width: 100px !important;">信息登记房间</p></td>
									<td><input type="text" style="width: 160px !important;" readonly="readonly" value="${information_Collection.room_ID}" /></td>
								</tr>
								<tr>
									<td><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">是否进行采集</p></td>
									<td><input type="text"  style="width: 280px !important;" readonly="readonly" value="${information_Collection.is_Collected}" /></td>
									
									<td ><p style="margin-left: 10px; margin-top:10px; width: 100px !important;">办案民警</p></td>
									<td ><input type="text" style="width: 280px !important;" readonly="readonly" value="${information_Collection.staff_ID}" /></td>
								</tr>
								<tr>
								<td><p style="margin-left: 10px; margin-top:10px;  width: 100px !important;">采集项目</p></td>
									<td><input type="text" style="width: 280px !important;"  readonly="readonly" value="${information_Collection.collected_Item}" /></td>
									
								</tr>
							</table>
							<hr
								style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 10%; margin-left: -2%;" />
						</div>
					</div>
				</div>
			</c:if>
			
			
			
			<!-- 完整性信息显示 -->
			<div>
				<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="margin-top: 0%;">

							活动记录
				</h4>
				<table class="active_check col-lg-12 col-md-10 col-sm-10" style="margin-left: 30px !important;width:960px !important; ">
					<tr>
						<td>序号</td>				
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp活&nbsp&nbsp&nbsp&nbsp&nbsp动&nbsp&nbsp&nbsp&nbsp&nbsp记&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp录&nbsp&nbsp&nbsp&nbsp&nbsp</td>
						<td>功能房间</td>

						<td>开始时间</td>
						<td>结束时间</td>
						<td>完整性</td>					
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
					</tr>
				<tr>

				   <td>1</td>
					<td style="text-align: left;">&nbsp&nbsp入区人员登记信息</td>
					<td>
					  值班室
					</td>
						<td >
							 <c:if test="${!empty SuspectInfor}">${SuspectInfor.enter_Time}</c:if>
							   <c:if test="${empty SuspectInfor}">&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
						</td>
						<td >
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

						</td>
						<td >
							 <c:if test="${!empty SuspectInfor}">${complete_degree}%</c:if>
							 <c:if test="${empty SuspectInfor}">${complete_degree}</c:if>
						</td>
						<td style="text-align: left;">
							  <c:if test="${!empty SuspectInfor}">
							 	&nbsp&nbsp进入办案区原因:&nbsp&nbsp&nbsp&nbsp&nbsp${SuspectInfor.suspected_Cause}</c:if>
							 <c:if test="${empty SuspectInfor}">
							  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

							  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp
							  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
						</td>
				</tr>
				<tr>

				    <td>2</td>
					<td style="text-align: left;">&nbsp&nbsp人身安全检查</td>
					
					<td >
							<c:if test="${!empty personal_Check}">${checkRoomName}</c:if>
							  <c:if test="${empty personal_Check}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
					</td>
					</td>
						<td >
							<c:if test="${!empty personal_Check}">${personal_Check.check_StartTime}</c:if>
							  <c:if test="${empty personal_Check}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
						</td>
						<td >
							<c:if test="${!empty personal_Check}">${personal_Check.check_EndTime}</c:if>
							  <c:if test="${empty personal_Check}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
						</td>
						<td >
							 <c:if test="${!empty personal_Check}">${complete_degree1 }%</c:if>
							 <c:if test="${empty personal_Check}">${complete_degree1 }%</c:if>
						</td>
						<td style="text-align: left;">
							<c:if test="${!empty personal_Check}">
							 	&nbsp&nbsp人身检查状态:&nbsp&nbsp&nbsp&nbsp&nbsp${personal_Check.check_Situation}</c:if>
						  <c:if test="${empty personal_Check}">&nbsp&nbsp未填写人身安全检查</c:if>
						</td>
				</tr>
				<tr>

				    <td>3</td>
					<td style="text-align: left;">&nbsp&nbsp信息采集</td>
					<td>
					  <c:if test="${!empty information_Collection}">${ICRoomName}</c:if>
							<c:if test="${empty information_Collection}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
				    </td>
						<td >
							<c:if test="${!empty information_Collection}">${information_Collection.ic_StartTime}</c:if>
							<c:if test="${empty information_Collection}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>
						</td>
						<td >
							<c:if test="${!empty information_Collection}">${information_Collection.ic_EndTime}</c:if>
							<c:if test="${empty information_Collection}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</c:if>

						</td>
						<td >
							 <c:if test="${!empty information_Collection}">${complete_degree2}%</c:if>
							 <c:if test="${empty information_Collection}">${complete_degree2}%</c:if>
						</td>
						<td style="text-align: left;">
							  <c:if test="${!empty information_Collection}">
							 	  &nbsp&nbsp采集项目:&nbsp&nbsp&nbsp&nbsp&nbsp${information_Collection.collected_Item}</c:if>
							  <c:if test="${empty information_Collection}">&nbsp&nbsp未填写信息采集</c:if>
						</td>
				</tr>

				<c:if test="${!empty activity_record}">
				
				 <c:forEach items="${activity_record }" var="ari" varStatus="s" >
					   <tr style="height: 70px;" >
					   <td>${s.index+4 }</td>
					   <td style="text-align: left;">&nbsp&nbsp${ari.activity_Record }</td>
					        <td>${ari.room_Name }</td>
					   		<td>${ari.start_Time }</td>
					   		<td>${ari.end_Time }</td>
					   		<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
					   		<td rows="2">${ari.remark }</textarea></td>
					   		
					   	</tr>
					   </c:forEach>
				</c:if>

				</table>
			</div>
			
			
			<!--活动记录登记表-->

			<%-- <div class="row">
				<h4 id="activityReco"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					历史活动记录登记<!-- <span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span> -->
				</h4>
				
				
				<c:if test="${!empty activity_record_infor}">
					<table class="active_check col-lg-12 col-md-10 col-sm-10" style="margin-left: 45px !important;width:960px !important; ">
					<tr style="background-color: rgb(0,112,192);color:white;">
						<!-- 						<td>音视频编码</td> -->
						<td>&nbsp房&nbsp间&nbsp号&nbsp</td>
						<td style="width: 100px">&nbsp活&nbsp动&nbsp内&nbsp容&nbsp</td>
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp开&nbsp&nbsp始&nbsp&nbsp时&nbsp&nbsp间&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp结&nbsp&nbsp束&nbsp&nbsp时&nbsp&nbsp间&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
					</tr>
					   <c:forEach items="${activity_record_infor }" var="ari">
					   <tr style="height: 70px;" >
					        <td>${ari.room_ID }</td>
					   		<td>${ari.activity_Record }</td>
					   		
					   		<td>${ari.start_Time }</td>
					   		<td>${ari.end_Time }</td>
					   		<td rows="2"><textarea rows="2" warp="virtual" style="width:100%;height:100" >${ari.remark }</textarea></td>
					   		
					   	</tr>
					   </c:forEach>
						
					</table>
				
			    </c:if>

				 --%>
				<h4 id="activityReco"
					class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					询问讯问记录<!-- <span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span> -->
				</h4>

				<p class="row_1">
				注：1、请办案民警注意对嫌疑人在办案区的活动做详细记录，确保嫌疑人在办案区内无时间盲区的登记遗漏.
			</p> 
				<table class="active_check col-lg-12 col-md-10 col-sm-10" style="margin-left: 45px !important;width:960px !important; ">
					<tr style="background-color: rgb(0,112,192);color:white;">
						<!-- <td style="display: none;">序号</td>
						<td style="display: none;">开始时间</td>
						<td style="display: none;">结束时间</td> -->
						<td style="width: 100px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp活&nbsp&nbsp&nbsp动&nbsp&nbsp&nbsp内&nbsp&nbsp&nbsp容&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td><!-- 						<td>音视频编码</td> -->
						<td>备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注&nbsp&nbsp&nbsp&nbsp&nbsp( 可输入255个字 )</td>
					</tr>
					<tr>
						<td style="display: none;">0</td>
						<td style="width: 38%;display: none;">
							<div class="form-group" style="height: 30px;width: 190%;">
								<div class="input-group date form_time col-md-5"
									style="margin-left: 8%;margin-top: 2%;" 
									data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
									
									<input class="form-control" name="start_Time"
										type="text" value="${start_Time }" readonly> 
										
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</td>
						<%-- <td style="width: 38%;display: none;">
							<div class="form-group" style="height: 30px;width: 190%;">
								<div class="input-group date form_time col-md-5"
									style="margin-left: 8%;margin-top: 2%;" data-date=""
									data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
									<input class="form-control" name="activity[0].end_Time"
										type="text" value="" readonly> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
										<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
								</div>
								<input type="hidden" id="dtp_input1" value="" /><br />
							</div>
						</td> --%>
						 <td id="select"><select name="activity_Record" >
						<!--<td id="select"><select name="select_record"> -->
						<!-- c:foreach    -->
						 <c:if test="${!empty activity_Record}">
								<option value=>${activity_Record }</option>
							 </c:if>	
							  <c:if test="${empty activity_Record}">
								<option value=>---请选择---</option>
							 </c:if>
								<%-- 
								<c:forEach items="${ }" var="v"></c:forEach> --%>
								<option value="询问">询问</option>
								<option value="讯问">讯问</option>
								<option value="审讯">审讯</option>
								<option value="传唤">传唤</option>
						</select></td>
						<td><!-- <input type="text" name="remark"
							style="width:790px; height: 80px;text-align: center;" value="${activity_remark }"/> -->
							<textarea class="textarea" name="remark" style="width: 780px;" clos="300" rows="3" warp="virtual">${activity_remark }</textarea>
							</td>
					</tr>
					<tr>
						<td style="display: none;">1</td>
						<td style="display: none;">
							<div class="btn" id="add">+添加</div>
							<div class="btn" id="delete">-删除</div>
						</td>
					</tr>
				</table>
			</div>
			<div style="float:left;width:400px;margin-left: 287px;font-size: 22px;margin-top: 16px;">
				<p id="signature">
					办案人员:<select name="staff_ID" id="staff_ID" style=" font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select>
					<input type="button" onclick="check()" value="确认提交" class="sub" id="btnAdd" />
				</p>
				</div>
				<br />
				<div style="padding-top: 20px;text-align: center;">

			<!-- <p class="row_1">
				注：1、请办案民警注意对嫌疑人在办案区的活动做详细记录，确保嫌疑人在办案区内无时间盲区的登记遗漏.
			</p> -->
			<!-- <div style="width: 614px;">
				<input type="button" onclick="check()" value="确认提交" class="sub" id="btnAdd" />
				</div>

		</div> -->
	</form>
	<div style="height: 400px;"></div>


</body>
<script type="text/javascript">
function check(){
			var Staff_ID=document.getElementById("staff1").value;
			if(Staff_ID.length==0 || Staff_ID ==""){
				alert('提交失败，请填写办案人员');
			return false;
		} else
			document.getElementById("form").submit();
			return true;
		}
</script>

</html>