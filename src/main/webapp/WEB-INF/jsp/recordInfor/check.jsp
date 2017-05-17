
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>人身安全检查登记</title>
<!--样式以及jquey库链接-->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/human_safety.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jqCSS_safety.js"></script>
<script>
function select(typeVal,numVal){
	$("select[name=Keeping_ID]   option[value='"+typeVal+"']").attr("selected",true);
	$("select[name=Cabinet_Number]   option[value='"+numVal+"']").attr("selected",true);
}

</script>

<script type="text/javascript">
	var index = 0;
	
	$(function() {
		$("#add")
				.click(
						function() {
						var typeVal=$("#selectType option:selected").val();
						var numVal=$("#selectNum option:selected").val();
						
							var num = $("#woods_check tr").length;
							index = num - 1;
							var tdnum = $("#woods_check tr:last()").find(
									"td:eq(0)");
							var lineNum=index-1;
							//添加下一行
							var addrow = "<tr>" + "<td><input name=Belonging_Number value="+index+" /></td>"
									+ "<td><input type=text name="
									
									 +"Belonging_Name style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);'/></td>"
									+ "<td><input type=text name="
									
									+ "Belonging_Character style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>"
									/* + "<td><input type=text name=belong["
									+ lineNum
									+ "].Belonging_Number style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>" */
									+ "<td><input type=number name="
									
									+ "Belonging_Count style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' value='1'/></td>"
									+ "<td><input type=text name="
								
									+ "Belonging_Unit style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' value='个' /></td>"
									+ "<td> <select id='selectType' style='width:100%;height:100%;' name="
									

									+"Keeping_ID>  <c:forEach items='${Keeping_WayType }' var='item' varStatus='status'> <option value='${item.keeping_ID}'>${item.keeping_Name }</option></c:forEach> <lect> </td>"
									+ "<td> <select style='width:100%;height:100%;' name="
									
									+ "Cabinet_Number>  <c:forEach items='${PHCSMPCabinetType }' var='item' varStatus='status'> <option value='${item.cabinet_Number}'>${item.cabinet_Number }</option> </c:forEach> <lect> </td>"
									+ "</tr>";
					
							$("#woods_check tr").eq(
									$("#woods_check tr").length - 2).after(
									addrow);
							//addrow.find("td:eq(0)").html(num - 1);

							tdnum.html(num);
							$(".woods_check input").css({"width":"60%","height":"26px","background":"#fff","border":"none","text-align":"center"});
							select(typeVal,numVal);
						});
		//				删除行
		$("#delete").click(
				function() {

					var len = $("#woods_check tr").length; //获取当前表格行数
					var td = $(this).parent().prev().html(); //获取当前行序号
					var delrow = $("#woods_check tr").get(
							$("#woods_check tr").length - 2);
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
		action="${pageContext.request.contextPath }/check/add"
		method="post">
		<%-- <c:if test="${! empty message}">
			<input id="message" type="hidden" value=${message } />
		</c:if> --%>
		<div class="row">
			<h4 style="margin-top: 13px;">
				<b style="color: #389ac7;">Personnal</b> safety check
			</h4>
			<p id="left_title">人身安全检查登记</p>
			<!--设置标题：档案编号：-->
			<%-- <h5 class="col-lg-12 col-md-10 text-center">
				<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="text" name="Suspect_ID" value="${SuspectInfor.suspect_ID }"
					readonly="readonly" />
			</h5> --%>
			<h5 class="col-lg-12 col-md-10 text-center" style="margin-top: auto;">
			<span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;
			<span style="width:200px;color:black;" type="text" id="suspectID"
				name="Suspect_ID" >${SuspectInfor.suspect_ID }</span>
				<input name="Suspect_ID" type="hidden" style="color:black;" value="${SuspectInfor.suspect_ID }" />
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

						<%-- <c:if test="${!empty SuspectInfor}">
							<script type="text/javascript">
					       $(document).ready(function(){
					            $("#identityImg").attr("src","images/fgreen_03.png");
					       
					       });
					    </script>
						</c:if> --%>
						<img id="identityImg" src="${pageContext.request.contextPath }/images/fgreen_03.png" />
						<c:if test="${!empty checkRecord }">
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
				<!--在该容器下第一个row结束-->
			</div>
			<!--进度条信息结束-->
			<!--子标题1:入区事由，治安传唤-->
			<div class="container">
				<div class="row">
					<!--身份信息标题，入区事由应填写上一房间民警填写的事由-->
					<h4 style="" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						入区事由：<input style="width:400px ! important;" type="text" value="${SuspectInfor.suspected_Cause }"
							readonly="readonly" />
					</h4>
					<div class="pic col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<img id="img_1"src="${SuspectInfor.frontal_Photo}" /> 
						<img id = "img_2"src="${SuspectInfor.sideWays_Photo}" />
						<p  class="date_pic col-lg-6 col-md-6 col-sm-6">
							&nbsp; &nbsp; 嫌疑人入区登记照片</p>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

						<hr
							style="width: 96%;border: 0.2px solid #389ac7;padding: 0px;margin-top: 1%;margin-left: -4%;" />

						<table class="Message col-lg-12 col-md-10 col-sm-8 col-xs-8">

							<tr style="padding: 0px;">
								<!--图片引入-->
								<td rowspan="5" style="width: 25%;"><img
									style="width: 95px;height: 108px;position: relative;top: -10px;"
									src="${SuspectInfor.identityCard_Photo }" />
									<p class="info_id">身份证照</p></td>
								<!--<td></td>-->
								<td colspan="2" style="color:black;">姓名:<span type="text" name="suspect_Name" style="color:black;">${SuspectInfor.suspect_Name }</span></td>
							</tr>
							<!--第二行 性别 民族-->
							<tr>
								<td style="color:black;">性别：<span style="text-align: center;color:black;" type="text"
								name="sex" >${SuspectInfor.sex }</span></td>
									
								<td style="color:black;">民族：<span type="text" name="nation" style="color:black;">${SuspectInfor.nation }</span></td>
							</tr>
							<!--第三行 出生-->
							<tr>
								<td colspan="2" style="color:black;">出生日期：<span type="text" style="width:70%;color:black;"
								name="birthday;" >${SuspectInfor.birthday }</span></td>
							</tr>
							<!--第四行身份证住址-->
							<%-- <tr>
								<td colspan="2">家庭住址：</td>
							</tr>
							<tr>
								<td colspan="2"><textarea readonly="readonly" rows="1" style="width: 350px;margin-left: 10%;" 
										cols="30">${SuspectInfor.address }</textarea></td>
							</tr>
							<tr>
								<td><div style="margin-left: 4%;">身份证号码</div></td>
								<td colspan="2"><input type="text"
									value="${SuspectInfor.identifyCard_Number }"
									readonly="readonly" /></td>
							</tr> --%>
							<tr>
								<td colspan="2">家庭住址：</td>
							</tr>
							<tr>
								<!--<td></td>-->
								<td colspan="2"><span  style="color:black;">${SuspectInfor.address }</span></td>
							</tr>
							<tr>
								<td><div style="margin-left: 38px;">身份证号码</div></td>
								<td colspan="2" ><span  style="color:black;">${SuspectInfor.identifyCard_Number  }</span></td>
							</tr>
						</table>
						<hr
							style="width: 96%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 33%; margin-left: -4%;" />
					</div>
				</div>
				<div class="row">
					<h4 id="readMe" style="margin-top: -25px;"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						自述情况<span class="col-lg-12 col-md-12 col-sm-12"></span>
					</h4>
					<input type="hidden"  name="Check_StartTime" value="${start_time_time}">
					<table style="margin-left: 25px !important;" class="zishu_state col-lg-12 col-md-10 col-sm-10">
						<tr style="border-bottom: 1px solid #389AC7; padding-bottom: 4px;">
							<td>是否饮酒:</td>
							<td><label for="is_Drink_Yes" style="font-weight:normal">是<input id="is_Drink_Yes" type="radio" name="is_Drink" value="是" /></label></td>
							<td><label for="is_Drink_No" style="font-weight:normal">否<input id="is_Drink_No" type="radio" name="is_Drink" value="否" /></label></td>
						</tr>
							
						<tr style="border-bottom: 1px solid #389AC7; padding-bottom: 4px;">
							<td>是否患有传染性疾病:</td>
							<td><label for="is_Diseases_Yes" style="font-weight:normal">是<input id="is_Diseases_Yes"  type="radio" name="is_Diseases" value="是" /></label></td>
							<td><label for="is_Diseases_No" style="font-weight:normal">否<input id="is_Diseases_No"  type="radio" name="is_Diseases" value="否"  /></label></td>
						</tr>
							<script type="text/javascript">
								//设定is_Drink
								if("${checkRecord.is_Drink}"=="是"){
									$("input[name=is_Drink][value=是]").attr("checked",true);
								}
								if("${checkRecord.is_Drink}"=="否"){
									$("input[name=is_Drink][value=否]").attr("checked",true);
								}
								//设定is_Diseases
								if("${checkRecord.is_Diseases}"=="是"){
									$("input[name=is_Diseases][value=是]").attr("checked",true);
								}
								if("${checkRecord.is_Diseases}"=="否"){
									$("input[name=is_Diseases][value=否]").attr("checked",true);
								}
							</script>
						<tr rowspan="3">
							<td>自述症状:</td>
							<td colspan="2"><textarea id="Self_ReportS" name="Self_ReportS" rows="5" cols="80" style="width:450px !important;height:90px !important;margin-left: 0px !important;" value="${checkRecord.self_ReportS }" ></textarea> </td>
								<script>
											//设定Check_ReportS
											document.getElementById("Self_ReportS").value="${checkRecord.self_ReportS }";
								</script> 
						</tr>
						<tr>
							<td></td>
							<td style="color: red;font-size: 13px;">可输入255个字</td>
							<td></td>
						</tr>
							
					</table>
					<h4 id="inspect"
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						检查情况
					</h4>
						<table style="width:600px !important; margin:0px 25px !important; margin-top:15px !important;font-size: 15px !important;"  class="checked_state col-lg-12 col-md-10 col-sm-10">
							<tr style="margin:5px 5px !important;">
								 <c:forEach items="${InspectionSituationType }" var="item" varStatus="status">
									
									<td style="width: 10%;"><label style="font-weight:normal" for="Check_Situation${status.index}">${item.situation_Name } &nbsp;&nbsp;<input
										id="Check_Situation${status.index}" type="checkbox" name="Check_Situation" value="${item.situation_Name }"/></label></td>
										
								</c:forEach> 
								
									<script>
										var check_Situation="${checkRecord.check_Situation}";
										var result =check_Situation.split(",");
									    for (var i = 0; i < result.length; i++) { // 改为result.length
									    	$("input[name=Check_Situation][value="+result[i]+"]").attr("checked",true);
									    };
									</script>
							</tr>
							
							<tr style="height: 5px !important"></tr>
							<tr   style="margin:5px 5px !important;">
										<td rowspan="4" colspan="1">检查情况：</td>
										<td rowspan="4" colspan="2"><textarea style="width:350px !important; height:120px !important;" cols="1" rows="1"  name="Check_ReportS" id="Check_ReportS" value="${checkRecord.check_ReportS }"/></textarea></td>
									<script>
										//设定Check_ReportS
										document.getElementById("Check_ReportS").value="${checkRecord.check_ReportS }";
									</script> 
							</tr>
							<tr style="margin:5px 5px !important;"></tr>
							<tr style="margin:5px 5px !important;"></tr>
							<tr style="margin:5px 5px !important;"></tr>
							<tr style="margin:5px 5px !important;">
								<td></td>
								<td style="color: red;font-size: 13px;">可输入255个字</td>
							</tr>
							<tr style="height: 5px !important"></tr>
							<tr  style="margin:5px 5px !important;margin-top:25px !important;">
								<td style="">检查民警：</td>
								<td style=""  colspan="2">
								<select name="staff_ID" id="staff_ID1" style=" font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select></td>
							</tr>
						</table>
						
						<img src="${pageContext.request.contextPath }/images/check_08.png"
									style="width:230px !important; margin-top: 0%;"/>
					<h4 id="belongInspect" 
						class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
						随身财物检查登记<!-- <span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span> -->
					</h4>
					<table style="margin-left:75px !important;" id="woods_check"
						class="woods_check col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>序号</td>
							<td>物品名称</td>
							<td>物品特征</td>
							<td>物品数量</td>
							<td>物品单位</td>
							<td>保管措施</td>
							<td>保管柜号</td>
						</tr>
						<tr>
							<td><input name="Belonging_Number" value="1" readonly="readonly" style="text-align: center;"/></td>

							<td><input type="text" name="Belonging_Name"
								value="" /></td>
							<td><input type="text" name="Belonging_Character"
								value="" /></td>
							<td><input type="number" value="1"
								name="Belonging_Count" /></td>
							<td><input type="text" value="个"
								name="Belonging_Unit" /></td>
							<td><select id="selectType" name="Keeping_ID" style="width: 100%;height: 100%;">
									<option value="">---请选择---</option>
									<c:forEach items="${Keeping_WayType }" var="item" varStatus="status">
										<option value="${item.keeping_ID}">${item.keeping_Name }</option>
									</c:forEach>
									
									<!-- <option value="2">暂存</option>
									<option value="3">代保管</option> -->
							</select></td>
							<td>
								<select id="selectNum" name="Cabinet_Number">
										<option value="">---请选择---</option>
										<c:forEach items="${PHCSMPCabinetType }" var="item" varStatus="status">
											<option value="${item.cabinet_Number}">${item.cabinet_Number }</option>
										</c:forEach>
								</select>
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
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<ul class="signature col-lg-12 col-md-10 col-sm-10">
						<li style="margin-left: 65px;">办案人员：
						  <select name="staff_ID" id="staff_ID2" style=" font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;随身财物管理员：
							<select name="staff_ID" id="staff_ID3" style=" font-color: black;">
									<option value="0">--------请选择--------</option>
									<c:forEach items="${staff }" var="item"
										varStatus="status">
										<option value="${item.staff_ID }">${item.staff_ID } &nbsp---------&nbsp ${item.staff_Name }</option>
									</c:forEach>
							</select>
						</li>
					</ul>
				</div>
				<input style="margin-top: 10px;" class="sub" id="btnAdd" type="button" onclick="check()" value="确认提交" />
				<div class="row_2 col-lg-12" style="height: 180px;"></div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function check(){
		
			var Staff_ID=document.getElementById("staff_ID1").value;
			var belongstaff = document.getElementById("staff_ID2").value;
			var staff = document.getElementById("staff_ID3").value;
			
			if((Staff_ID.length==0 || Staff_ID ==0)||(belongstaff.length==0 ||belongstaff==0)||(staff.length==0 ||staff==0)){
				alert('提交失败，请填写办案人员或检查民警');
			return false;
		} else{
			document.getElementById("form").submit();
			return true;
			}
		}
</script>

</html>