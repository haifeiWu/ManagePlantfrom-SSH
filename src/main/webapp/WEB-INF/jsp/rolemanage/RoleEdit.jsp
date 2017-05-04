<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 加载jstl的c标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/RoleEdit.css">
</head>
<body>

	<div class="container">
		<section class="main">
		<h4 style="margin: 13px 0 0 63px;">
			<b style="color: #389ac7;">Staff</b> information
		</h4>
		<p id="left_title" style="border-bottom: 2px solid #389AC7;margin-top:0;margin-left:63px; padding-bottom: 2px;width: 140px;">编辑警员信息</p>
		<div class="basic-grey">
			<form action="${pageContext.request.contextPath }/user/add" method="POST" class="STYLE-NAME">
				<h1>
					Contact Form <span>Please fill all the texts in the fields.</span>
				</h1>
				<table style="margin-left: 20%; text-align: right;">
					<tr>
						<td width="110px" text-align="right"><span>用户名 :</span></td>
						<td  style="padding-top: 12px;width: 200px;"><input id="name" type="text" name="Staff_Name"
							placeholder="Your UserName" value="${staff[0].staff_Name }" /></td>
						<td width="140px"><span>职务 :</span></td>
						<td style="padding-top: 12px;width: 200px;">
							<select name="Duties_Name" id="type_ID">
								<option value="${staff[0].duties_Name }">--------请选择职务-------</option>
								<option >是</option>
								<option>否</option>
						</select>
						</td>
					</tr>
					<tr>
						<td><span>真实姓名:</span></td>
						<td style="padding-top: 12px;width: 200px;"><input id="name" type="text" name="real_Name"
							placeholder="Your Full Name" value="${staff[0].real_Name }" /></td>
						<td><span>性别 :</span></td>
						<td style="padding-top: 12px;width: 200px;"><input id="name" type="text" name="Sex"
							placeholder="Your Sex" value="${staff[0].sex }" /></td>
					</tr>
					<tr>
						<td><span>邮箱 :</span></td>
						<td style="padding-top: 12px;width: 200px;"><input id="email" type="text" name="Email"
							placeholder="Valid Email Address" value="${staff[0].email }" /></td>
						<td><span>注册日期 :</span></td>
						<td style="padding-top: 12px;width: 200px;"><input id="name" type="text" name="RegistedDate"
							placeholder="Registration Date" value="${staff[0].registedDate }" /></td>
					</tr>
					<tr>
						<td><span>固定电话 :</span></td>
						<td style="padding-top: 12px;width: 200px;"><input id="email" type="text" name="Phone"
							placeholder="Telephone" value="${staff[0].phone }"/></td>
						<td><span>移动电话 :</span></td>
						<td style="padding-top: 12px;width: 200px;"><input id="name" type="text" name="Mobile"
							placeholder="Telephone" value="${staff[0].mobile }"/></td>
					</tr>
					<tr>
						<td><span>是否离职 :</span></td>
						<td style="padding-top: 12px;width: 200px;">
							<select name="is_leave" id="Is_Dimission">
								<option value="${staff[0].is_Dimission }" >-------------请选择-------------</option>
								<option >是</option>
								<option>否</option>
						</select>
						</td>
						<td width="140px"><span>权限 :</span></td>
						<td style="padding-top: 12px;width: 200px;">
							<select name="role_ID" id="role_Name">
								<option value="${staff[0].role_Name }" >-------------请选择------------</option>
								<option >是</option>
								<option>否</option>
						</select>
						</td>
					</tr>
					<tr>
						<td><span>&nbsp;</span></td>
						<td>&nbsp;
						</td>
						<td><span>&nbsp;</span></td>
						<td><input type="submit" value="保存"
					class="button" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	</section>
</body>
</html>
