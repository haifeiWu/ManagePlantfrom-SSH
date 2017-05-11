<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<title>信息初始化界面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style_head.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Leave_depart.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery_css.js"></script>
	<style type="text/css">
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
		
	</style>
	<script>
		$(function(){				
			var pdfFile = document.getElementById("pdfFile");
	        pdfFile.onchange = function () {
	            pdfFile.select();
	            document.selection.createRange().text;
	        }
			var tomcatFile = document.getElementById("tomcatFile");
	        tomcatFile.onchange = function () {
	            tomcatFile.select();
	            document.selection.createRange().text;
	        };
		});
	</script>
</head>

<body>
	<div>
		<div class="row">
			<form action="${pageContext.request.contextPath }/properties/init" method="post">
				<div class="container ">
					<div class="row">
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
							派出所系统信息初始化
						</h4>
					</div>
					<div class="row ">
						<table class="final_Leave col-lg-12 col-md-12 col-sm-12">					
							<tr>
								<td>系统名称<br>
								(比如：交城县公安局智能化派出所系统)</td>
								<td><input type="text" name="title"></td>
							</tr>
							<tr>
								<td>派出所名称<br>
								(比如：天宁派出所)</td>
								<td><input type="text" name="name"></td>
							</tr>
						</table>
					</div>
					<div class="row">
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
							PDF信息初始化
						</h4>
					</div>
					<div class="row ">
						<table class="final_Leave col-lg-12 col-md-12 col-sm-12">					
							<tr>
								<td>本机pdf工具路径<br>
								(例如:C:\Program Files\wkhtmltopdf\bin\wkhtmltopdf.exe)</td>
								<td><div style="margin-left: 120px;"><input type="file" id="pdfFile" name="toolPath"></div></td>
							</tr>
							<tr>
								<td>Tomcat下存放PDF的文件路径<br>
								(例如:/pdf)</td>
								<td><input type="text" id="tomcatFile" name="relatePath"></td>
							</tr>
						</table>
					</div>
					<div class="row">
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
							录播系统信息初始化
						</h4>
					</div>
					<div class="row ">
						<table class="final_Leave col-lg-12 col-md-12 col-sm-12">					
							<tr>
								<td>录播系统IP<br>
								(例如:192.168.1.108)</td>
								<td><input type="text" name="recordIp"></td>
							</tr>
							<tr>
								<td>录播系统端口号<br>
								(例如:8765)</td>
								<td><input type="text" name="recordPort"></td>
							</tr>
						</table>
					</div>
					
					
					<div class="row">
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
							服务器信息初始化
						</h4>
					</div>
					<div class="row">
						<table class="final_Leave col-lg-12 col-md-12 col-sm-12">					
							<tr>
								<td>tomcat服务器IP地址<br>
								(例如:192.168.1.4)</td>
								<td><input type="text" name="serverIp"></td>
							</tr>
							<tr>
								<td>tomcat服务器端口号<br>
								(例如:8080)</td>
								<td><input type="text" name="serverPort"></td>
							</tr>
						</table>
					</div>
					<div class="row">
						<h4 class="human_Mes col-lg-12 col-md-12 col-sm-12 col-xs-12">
							FTP信息初始化(ftp的ip地址和Tomcat服务器地址一样)
						</h4>
					</div>
					<div class="row ">
						<table class="final_Leave col-lg-12 col-md-12 col-sm-12">
							<tr>
								<td>FTP服务器的用户名<br>
								(例如:dell)</td>
								<td><input type="text" name="userName"></td>
							</tr>
							<tr>
								<td>FTP服务器的密码<br>
								(例如:123)</td>
								<td><input type="text" name="passWord"></td>
							</tr>					
							<tr>
								<td>FTP服务器端口号<br>
								(例如:21)</td>
								<td><input type="text" name="ftpPort"></td>
							</tr>
							<tr>
								<td>Tomcat服务器下的存放录像文件的路径<br>
								(例如:/vedioFile)</td>
								<td><input type="text" id="ftpFile" name="uploadDir"></td>
							</tr>
						</table>
					</div>
					
					<div class="row" style="margin:50px auto;width:1000px;">
						<div style="float:left;width:400px;margin-left: 150px">
							<p id="signature">
								管理员:<input type="text" name="manager"/>
							</p>
						</div>
						<div style="float:left;width:460px;margin-left: -160px;margin-top: -10px;">
							<input type="submit" value="确认提交" class="sub" />
						</div>
					</div>
			</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>