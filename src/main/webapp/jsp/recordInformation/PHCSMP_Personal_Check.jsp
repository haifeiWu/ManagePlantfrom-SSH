<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>人身安全检查登记</title>
	<!--样式以及jquey库链接-->
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/human_safety.css" />
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jqCSS_safety.js"></script>
	
	<script type="text/javascript">
	var index = 0;
	$(function(){
			 $("#add").click(function()
			 {
				 var num = $("#woods_check tr").length;
				 	index = num-2;
				 var tdnum = $("#woods_check tr:last()").find("td:eq(0)");
				//添加下一行
			 	 var addrow = "<tr>"
				 			+"<td>"+index+"</td>"
							+"<td><input type=text name=belong["+index+"].Belonging_Name style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);'/></td>"
							+"<td><input type=text name=belong["+index+"].Belonging_Character style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>"
							+"<td><input type=text name=belong["+index+"].Belonging_Number style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>"
							+"<td><input type=number name=belong["+index+"].Belonging_Count style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>"
							+"<td><input type=text name=belong["+index+"].Belonging_Unit style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>"
							+"<td> <select name=belong["+index+"].Keeping_ID> <option value=>---请选择---</option> <option value=1>扣押</option> <option value=2>暂存</option> <option value=3>代保管</option> </select> </td>"
							+"<td><input type=text name=belong["+index+"].Cabinet_Number style='width: 60%; height: 26px; border: none; background: rgb(241, 241, 241);' /></td>"
							+"</tr>";
				 	$("#woods_check tr").eq($("#woods_check tr").length-2).after(addrow);
				  	addrow.find("td:eq(0)").html(num-1);
					
					tdnum.html(num);		 	
			
			 });
//				删除行
			$("#delete").click(function()
			{

				var len = $("#woods_check tr").length; //获取当前表格行数
				var td=$(this).parent().prev().html();  //获取当前行序号
				var delrow = $("#woods_check tr").get($("#woods_check tr").length - 2);
				if (len>2) 
				{
					$(delrow).remove();
					 
					$(this).parent().prev().html($(this).parent().prev().html()-1);
					
				} 	
			});
		 
		});
	</script>
</head>

<body>
	<form class="container" action="${pageContext.request.contextPath }/personalCheck_addCheckPersonInfor.action" method="post">
		<div class="row">
			<h4 style="margin-top: 13px;"><b style="color: #389ac7;">Personnal</b> safety check</h4>
			<p id="left_title">人身安全检查登记</p>
			<!--设置标题：档案编号：-->
			<h5 class="col-lg-12 col-md-10 text-center"><span style="color: #389AC7;font-size: large;">档案编号</span>：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="Suspect_ID" value="${SuspectInfor.Suspect_ID }"
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
					<div id="state" class="col-lg-12 col-md-10 col-sm-10">
						<a href="suspect_updateInfor.action?Suspect_ID=haifieisi"><img src="images/3-inforCollection_03.png" /></a>
						<a href="#readMe"><img src="images/3-inforCollection_03.png" /></a>
						<a href="#inspect"><img src="images/3-inforCollection_03.png" /></a>
						<a href="#belongInspect"><img src="images/3-inforCollection_03.png" /></a>
						<a href="#belongInspect"><img src="images/3-inforCollection_07.png" style="margin-left: -14%;" /></a>
						<span>信息采集登记表</span>
					</div>
					<!--进度的信息显示-->
					<ul id="txt" class="col-lg-12 col-md-10 col-sm-10">
						<li>入区登记</li>
						<li>自述情况</li>
						<li>检查情况</li>
						<li>随身财物检查</li>
						<li>签字确认</li>
					</ul>
				</div>
				<!--在该容器下第一个row结束-->
			</div>
			<!--进度条信息结束-->
			<!--子标题1:入区事由，治安传唤-->
			<div class="container">
				<div class="row">
					<!--身份信息标题，入区事由应填写上一房间民警填写的事由-->
					<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">入区事由：<input type="text" value="治安传唤" readonly="readonly" /></h4>
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
								<td rowspan="5" style="width: 25%;">
									<img style="width:80%;height: 75%;margin-left: 0%;" src="images/1-IDlogo_09.png" />
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

								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家庭住址：</td>
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
						<hr style="width: 75%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 38%; margin-left: -2%;" />
					</div>
				</div>
				<!--第一个数据项结束-->
				<div class="check_time row_1">人身检查起止时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="date" name="Check_Date" />&nbsp;<input type="time" name="start_time" />至&nbsp;<input type="time" name="end_time"/></div>
				
				<hr style="width: 100%; border: 0.2px solid #389ac7; padding: 0px;margin-top: 2%; margin-left: 0%;" />
				<div class="row">
					<h4 id="readMe" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">自述情况<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span></h4>
					<table class="zishu_state col-lg-12 col-md-10 col-sm-10">
						<tr style="border-bottom: 1px solid #389AC7; padding-bottom: 4px;">
							<td>是否饮酒:</td>
							<td>是<input type="radio" name="isDrink" /></td>
							<td>否<input type="radio" name="isDrink" /></td>
						</tr>
						<tr style="border-bottom: 1px solid #389AC7; padding-bottom: 4px;">
							<td>是否患有传染性疾病:</td>
							<td>是<input type="radio" name="isDiseases" /></td>
							<td>否<input type="radio" name="isDiseases" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<fieldset>
									<legend>自述症状:</legend>
									<textarea name="Self_ReportS">健康状况良好</textarea>
								</fieldset>
							</td>
							<td></td>
						</tr>
					</table>
					<h4 id="inspect" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">检查情况<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span></h4>
					<table class="checked_state col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td style="width: 20%;">体表有伤 &nbsp;&nbsp;<input type="checkbox" name="Check_Situation" /></td>
							<td style="width: 20%;">有饮酒 &nbsp;&nbsp;<input type="checkbox" name="Check_Situation" /></td>
							<td style="width: 20%;">有拍照 &nbsp;&nbsp;<input type="checkbox" name="Check_Situation" /></td>
							<td rowspan="2"><img src="images/check_08.png" style="width: 70%; margin-left: 1%; margin-top: 3%;" /> </td>
							<!--引入体表检查图-->
						</tr>
						<tr>
							<td style="text-align: left; vertical-align: top;">检查情况:</td>
							<td colspan="2" style="text-align: left; vertical-align: top;"><textarea name="Check_ReportS"></textarea></td>
						</tr>
						<tr>
							<td>检查民警：<br><input type="text" name="Staff_ID" value="" /></td>
							<td colspan="3" style="text-align: center;">被检查人/监护人：<br><input type="text" value="" /></td>
						</tr>
					</table>
					<h4 id="belongInspect" class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">随身财物检查登记<span class="col-lg-12 col-md-12 col-sm-12">填写完整度0%</span></h4>
					<table id="woods_check" class="woods_check col-lg-12 col-md-10 col-sm-10">
						<tr>
							<td>序号</td>
							<td>物品名称</td>
							<td>物品特征</td>
							<td>物品编号</td>
							<td>物品数量</td>
							<td>物品单位</td>
							<td>保管措施</td>
							<td>保管柜号</td>
						</tr>
						<tr>
							<td>1</td>
							<td><input type="text" name="belong[0].Belonging_Name" value="房间钥匙" /></td>
							<td><input type="text" name="belong[0].Belonging_Character" value="房间钥匙" /></td>
							<td><input type="text" name="belong[0].Belonging_Number" /></td>
							<td><input type="number" value="1" name="belong[0].Belonging_Count" /></td>
							<td><input type="text" value="把" name="belong[0].Belonging_Unit" /></td>
							<td>
								<select name="belong[0].Keeping_ID">
									<option value="">---请选择---</option>
									<option value="1">扣押</option>
									<option value="2">暂存</option>
									<option value="3">代保管</option>
								</select>
							</td>
							<td><input type="text" value="5" name="belong[0].Cabinet_Number" /></td>
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
						<li>办案人员：<br /><input type="text" name="Staff_ID" /></li>
						<li>随身财物管理员：<br /><input type="text" value="" /></li>
						<li>涉案人员：<br /><input type="text" value="" /></li>
					</ul>
				</div>
				<input class="sub" type="submit" value="确认提交" onclick="Logger()"/>
				<div class="row_2 col-lg-12" style="height: 480px;"></div>
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">
	function Logger(){
		alert("信息提交成功！");
	}
</script>
</html>