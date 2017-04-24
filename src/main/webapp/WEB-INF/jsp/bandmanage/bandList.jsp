<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 加载jstl的c标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<title>手环修改页面</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<style>

.row{
	padding-left: 200px;}
</style>
<div class="row">
	<form class="navbar-form navbar-left" role="search" method="post"
		id="searchband"
		action="${pageContext.request.contextPath }/BandManage_updateBand.action">
		<table class="table table-striped  bandTable">
			<tr>
				<th >手环编号</th>
				<th>手环备注</th>
				<th>手环类型</th>
				<!-- <th>操作</th> -->
			</tr>
			<c:forEach items="${bandCheckInfo }" var="item">
				<tr >
					<td>${item.band_ID }<input type="hidden" class="form-control"
						name="band_ID" value="${item.band_ID }">
					</td>
					<td><input type="text" class="form-control" name="remark"
						value="${item.remark }"></td>
					<td><input type="text" class="form-control" name="band_Type"
						value="${item.band_Type }"></td>
				</tr>
			</c:forEach>
			<table>
				<input type="submit" class="btn btn-primary serch col-sm-offset-10 col-sm-2" value="修改" />
			</table>
		</table>
	</form>
</div>