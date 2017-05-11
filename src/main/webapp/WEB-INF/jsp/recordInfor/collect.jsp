<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>嫌疑人信息采集</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Info_Col.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Info_collection.js"></script>

</head>

<body>
	<form class="container" id="form"
		action="${pageContext.request.contextPath }/collect/add"
		method="post">
		<div class="row">
			<!--嫌疑人入区信息-->
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Personnal</b> information Collection
			</h4>
			<p id="left_title">嫌疑人信息采集</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center">
				<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="width:200px;" type="text" id="suspectID"
				name="Suspect_ID" >${Suspect_ID }</span>
				<span name="suspect_ID" style="color: black;">${SuspectInfor.suspect_ID }</span>
				<input type="hidden" name="suspect_ID" value="${SuspectInfor.suspect_ID }"/>
			</h5>
			<!--进度条信息设置-->
			<div class="container" style="height: 180px;">
				<div class="row">
					<!--进度的数据信息-->
					<!-- <ul id="number" class="col-lg-12 col-md-10 col-sm-10" style="display: none;">
						<li>0%</li>
						<li>25%</li>
						<li>50%</li>
						<li>75%</li>
						<li>100%</li>
					</ul> -->

					<div id="state" class="col-lg-12 col-md-10 col-sm-10" style="margin-top: 30px;">

						<%-- <c:if test="${!empty suspect }">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#identityImg").attr("src","images/fgreen_03.png");
					       
					       });
					    </script>
						</c:if> --%>
						<img id="identityImg" src="${pageContext.request.contextPath }/images/fgreen_03.png" />
						<c:if test="${!empty personalCheck }">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#identityImg1").attr("src","${pageContext.request.contextPath }/images/fgreen_03.png");
					       
					       });
					    </script>
						</c:if> 
						<img id="identityImg1" src="${pageContext.request.contextPath }/images/3-inforCollection_03.png" />
						<c:if test="${!empty informatCollect }">
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
				<!--在该容器下第一个row结束-->
			</div>
			<!--进度条信息结束-->
		</div>
		<!--疑犯个人身份证信息-->
		<div class="container" style="margin-top: -2%;">
			<div class="row">
				<!--身份信息标题-->
				<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
					入区事由:<input type="text" value="${SuspectInfor.suspected_Cause }" />
				</h4>
				<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1" src="${SuspectInfor.frontal_Photo }" /> <img
							id="img_2" src="${SuspectInfor.sideWays_Photo}" />
						<p class="date_pic col-lg-6 col-md-6 col-sm-6">${nEntryTime }嫌疑人入区登记照片</p>
				</div>
				<!-- <div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
					<img
						style="width: 45%; height: 42%; -webkit-box-shadow: 0px 2px 0px rgba(0,1,1,0.7);"
						src="images/1-zhengmian_04.png" /> <img
						style="width: 53%; height: 90%; -webkit-box-shadow: 2px 4px 4px rgba(0,1,1,0.7);"
						src="images/1-cemian_06.png" />
					<p class="date_pic col-lg-6 col-md-6 col-sm-6">2016年10月20日
						&nbsp; &nbsp; &nbsp;嫌疑人入区登记照片</p>
				</div>  -->
				<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

					<hr
						style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%;margin-left: -2%;" />

					<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

						<tr style="padding: 0px;">
							<!--图片引入-->
							<td rowspan="5"><img
								style="width:95px;height:108px;"
								src="${SuspectInfor.identityCard_Photo }" />
								<p class="info_id">身份证照</p></td>
							<!--<td></td>-->
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
							<td colspan="2"><span style="color: black;">${SuspectInfor.address }</span></td>
						</tr>
						<tr >
							<td>&nbsp;身份证号码</td>
							<td colspan="2"><span style="color: black;">${SuspectInfor.identifyCard_Number }</span></td>
						</tr>
					</table>
				</div>
			</div>
			<!--身份证信息结束-->
			<input type="hidden" name="ic_StartTime" value="${start_Time }">
			<div class="container">
				<div class="row">
					<h4 id="InforCollect" style="margin-top: 0px !important;"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						信息采集<!-- <span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span> -->
					</h4>
					<table class="info_collect col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>信息采集:</td>
							<td><label for="Is_Collected_yes" style="font-weight:normal"><input type="radio" name="Is_Collected" id="Is_Collected_yes" value="是"
								 />是</label></td>
							<td><label for="Is_Collected_no" style="font-weight:normal"><input type="radio" name="Is_Collected" id="Is_Collected_no" value="否" />否</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>采集项目:</td>
							<c:forEach items="${collectionItem}" var="items"
								varStatus="status">
								<td><label for="Collected_Item${status.index }" style="font-weight:normal"><input type="checkbox" id="Collected_Item${status.index }" name="Collected_Item" value="${items.item_Name }" />${items.item_Name }</label>
								</td>
							</c:forEach>
							<td>&nbsp;&nbsp;其他<input id="otherCollected_Item" type="text" name="Collected_Item" value=""/></td>
								<script>
									var collected_Item="${informatCollect.collected_Item}";
									var result =collected_Item.split(",");
								    for (var i = 0; i < result.length; i++) {
								    	if($("input[name=Collected_Item][value="+result[i]+"]").length>0){//foreach
								    		$("input[name=Collected_Item][value="+result[i]+"]").attr("checked",true);
								    	}else{
								    		//其他
								    		document.getElementById("otherCollected_Item").value=result[i];
								    	}
								    };
								</script>
						</tr>
						<tr>
							<td>信息入库:</td>
							<td><label for="Is_Storaged_yes" style="font-weight:normal"><input type="radio" name="Is_Storaged" id="Is_Storaged_yes"value="是" />是</label>
							</td>
							<td><label for="Is_Storaged_no" style="font-weight:normal"><input type="radio" name="Is_Storaged" id="Is_Storaged_no"value="否" />否</td></label>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>检查对比:</td>
							<td><label for="Is_Checked_yes" style="font-weight:normal"><input type="radio" id="Is_Checked_yes"name="Is_Checked" value="是" />是</label>
							</td>
							<td><label for="Is_Checked_no" style="font-weight:normal"><input type="radio" id="Is_Checked_no" name="Is_Checked" value="否" />否</td></label>
							<td></td>
							<td></td>
							<td></td>
						</tr>
							<script type="text/javascript">
								//设定Is_Collected
								if("${informatCollect.is_Collected}"=="是"){
									$("input[name=Is_Collected][value=是]").attr("checked",true);
								}
								if("${informatCollect.is_Collected}"=="否"){
									$("input[name=Is_Collected][value=否]").attr("checked",true);
								}
								//设定Is_Storaged
								if("${informatCollect.is_Storaged}"=="是"){
									$("input[name=Is_Storaged][value=是]").attr("checked",true);
								}
								if("${informatCollect.is_Storaged}"=="否"){
									$("input[name=Is_Storaged][value=否]").attr("checked",true);
								}
							
								//设定Is_Checked
								if("${informatCollect.is_Checked}"=="是"){
									$("input[name=Is_Checked][value=是]").attr("checked",true);
								}
								if("${informatCollect.is_Checked}"=="否"){
									$("input[name=Is_Checked][value=否]").attr("checked",true);
								}
								
							</script>
					</table>
					<div style="float:left;width:400px;margin-left: 287px;font-size: 22px;margin-top: 16px;">
				<p id="signature">
					办案人员:<select name="staff_ID" id="staff_ID" style=" font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select>
				</p>
				</div>
					<input class="sub" id="btnAdd" type="button" onclick="check()" value="确认提交"/>
					<div class="bottom" style="height: 480px;"></div>
				</div>

			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
function check(){
			var Staff_ID=document.getElementById("staff_ID").value;
			if(Staff_ID.length==0 || Staff_ID ==0){
				alert('提交失败，请填写办案人员');
			return false;
		} else
			document.getElementById("form").submit();
			return true;
		}

</script>
</html>