<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title></title>
	<style type="text/css">
		*{margin:0;padding:0;}
		form{margin: 2% 15%;}
		table{width: 100%; margin-bottom: 15px;}
		table td{font-size: 12x;line-height:50px; height:50px; text-align: center;}
		.title{margin-top: 2%;text-align: center;font-weight: bold;font-size: 24px;}
		.bigtouxiang{width: 140px; height: 211px;border: 1px solid red; margin: 2% auto;}
		.touxiang{width: 70px; height: 106px;border: 1px solid red; margin: 0 auto;}
		.t input{border:0;background:seashell;outline: medium;width: 90%;text-indent: 5px;height: 70%;}
		.checkbox{padding: 5px;}
		.checkbox input{display: inline-block; margin-left: 2%;}
		.btn{background-color: #1fbba6; width:122px;height: 37px;float: right;text-align: center;font-size: 16px;margin:2%;line-height: 37px;border-radius: 5px;color: #FFF;}
		.add,.remove{float: left;width: 112px;height: 27px;font-size: 16px;line-height: 27px;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/progress-bar.css" type="text/css">
<script type="text/javascript">
var count = 0;
		var myArr = new Array();
		//初始化数组myArr
		for(var i=0;i<10;i++){
			myArr[i] = 0;
		}
		
        function onMouseOut() {
        	var isChange = false;
            var belongingName = document.getElementById("Belonging_Name").value;
            var Belonging_Number = document.getElementById("Belonging_Number").value;
            var belongingCharacter = document.getElementById("Belonging_Character").value;
			var belongingCount = document.getElementById("Belonging_Count").value;
			var belongingUnit = document.getElementById("Belonging_Unit").value;
			var keepingID = document.getElementById("Keeping_ID").value;
			var cabinetNumber = document.getElementById("Cabinet_Number").value;
			
            if(belongingName != ""&&myArr[1] == 0){
            	console.log(belongingName);
                count = count+1;
                myArr[1]=1;
                isChange = true;
                
            }
            
             if(belongingCharacter != ""&&myArr[2] == 0){
             console.log(belongingCharacter);
                count = count+1;
                myArr[2]=1;
                isChange = true;
            } 
            
             if(Belonging_Number != ""&&myArr[3] == 0){
             console.log(Belonging_Number);
                count = count+1;
                myArr[3]=1;
                isChange = true;
            } 
            
            if(belongingCount != ""&&myArr[4] == 0){
            console.log(belongingCount);
                count = count+1;
                myArr[4]=1;
                isChange = true;
            }
            
            if(belongingUnit != ""&&myArr[5] == 0){
            console.log(belongingUnit);
                count = count+1;
                myArr[5]=1;
                isChange = true;
            } 
             if(keepingID != "-----请选择-----"&&myArr[6] == 0){
             console.log(keepingID);
                count = count+1;
                myArr[6]=1;
                isChange = true;
            } 
            //保险柜编号
             if(cabinetNumber != ""&&myArr[7] == 0){
             console.log(cabinetNumber);
                count = count+1;
                myArr[7]=1;
                isChange = true;
            }
            
            
            if(isChange){
            	jindutiao(count*8);
            }
        }
	
		function jindutiao(coun) {
            //var oDiv = document.getElementById("big");
            var oSpan = document.getElementsByClassName("spanText")[0];
            var oP = document.getElementById("numPoint");
            var num=0;
            var time = 0;
            var timer = null;
            timer = setInterval(function(){
                if(num < coun){
                    oSpan.style.width = oSpan.offsetWidth + 2 + 'px';
                    num = num + 1;
                    oP.innerHTML = num + '%';
                }else{
                    clearInterval(timer);
                }
            },time);
        }
</script>
	
</head>
<body>
    <!-- 包含页面 -->
	<c:if test="${users.real_Name==null}">
		<c:import url="../home/top.jsp"></c:import> 	
		<br><br>
		<br><br>
	</c:if>
	<div class="title">
		   	随身物品检查记录
	</div>
	<form action="belonging_addBelongingInfor.action" method="post" id="Form" class="Form">
	<table  class="t" cellspacing="0" cellpadding="0" bordercolor="#cbcbcb" border="1px">
		  <tr>
		    <td width="15%" rowspan="4"><div class="bigtouxiang"><img src="${pageContext.request.contextPath }/images/ercun.png"></div></td>
		    <td width="12%" rowspan="4"><div class="touxiang"><img src="${pageContext.request.contextPath }/images/yicun.png"></div></td>
		    <td width="10%" height="10">姓名</td>
		    <td width="20%"><input type="text" value="${SuspectInfor.suspect_Name }" ></input></td>    
		  </tr>
		  <tr>
		  	<td width="10%" >性别</td>
		    <td width="1%"><input type="text" name="" value="${SuspectInfor.sex}" ></input></td>
		  </tr>
		  <tr>
		  	<td width="10%" >档案编号</td>
		    <td width="1%"><input type="text" name="" value="${SuspectInfor.suspect_ID }" ></input></td>
		  </tr>
		  <tr>
		  	<td width="10%" >身份证号</td>
		    <td width="1%"><input type="text" name="" value="${SuspectInfor.identifyCard_Number }" ></input></td>
		  </tr>
		  <tr>
		    <td>身份类型</td>
		    <td><input type="text" name="${SuspectInfor.type_ID }" /></td>
		    <td>房间号</td>
		    <td><input type="number" name="Room_ID" /></td>
		  </tr>
		  <tr>
		    <td>物品名称</td>
		    <td><input type="text" name="Belonging_Name" id="Belonging_Name" onblur="onMouseOut()" /></td>
		    <td>物品特征</td>
		    <td><input type="text" name="Belonging_Character" id="Belonging_Character" onblur="onMouseOut()" /></td>
		  </tr>
		  <tr>
		    <td>物品编号</td>
		    <td><input type="text" name="Belonging_Number" id="Belonging_Number" onblur="onMouseOut()" /></td>
		    <td>物品数量</td>
		    <td><input type="number" name="Belonging_Count" id="Belonging_Count" onblur="onMouseOut()"/></td>
		   </tr>
		   <tr>
		    <td>物品单位</td>
		    <td><input type="text" name="Belonging_Unit" id="Belonging_Unit" onblur="onMouseOut()" /></td>
		    <td>保管措施</td>
		    <td>
		    	<select name="Keeping_ID" id="Keeping_ID" onblur="onMouseOut()">
					<option value="0">-----请选择-----</option>
					<option value="1">扣押</option>
					<option value="2">暂存</option>
					<option value="3">代保管</option>
				</select>	
		  	</td>
		   </tr>
		   <tr>
		    <td>进入办案区原由</td>
		    <td><input type="text" name="" value="${SuspectInfor.suspected_Cause }" placeholder="投案自首   治安传唤" readonly="true" /></td>
		    <td>保险柜编号</td>
		    <td><input type="text" name="Cabinet_Number" id="Cabinet_Number" onblur="onMouseOut()" /></td>
		  </tr>
		  <tr>
		    <td>办案人员</td>
		    <td ><input type="text" name=""  value="${users.real_Name}"/></td>
		    <td >物品保管员</td>
		    <td><input type="text" name="" value="${users.real_Name}"></td>
		  </tr>
		</table>
		<div id='big' >
		    <div id='div1'><span class="spanText" id="span-bar"></span></div>
		    <p id='text'>已完成</p>
		    <p id='numPoint'>0%</p>
		</div>
		<input type="submit" class="btn" value="提交"/>
	</form>
</body>
</html>