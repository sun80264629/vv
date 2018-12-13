<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 配置页面根路径 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>" />

<!-- 引入jqury -->
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript">
function test(){
	var param = {"user.name": $('#name').val(),
		 "user.password": $('#password').val()};
	$.ajax({ 										 	// 调用jquery的ajax方法
		type : 'POST', 								    // 设置ajax方法提交数据的形式
		url :'example/HelloWorld!test.action', 
		data : param,
		dataType: "json",
		async: false,
		success : function(data) { 						// 提交成功后的回调，
			alert(data);
		},
		error: function(){
			result = false;
		}
	});
}
</script>
</head>

<body>
用户信息<br>
姓名：  <input id="name" value="${user.name}"> <br>
密码：   <input id="password" value="${user.password}"> <br>
<input type="button" value="提交" onclick="test();">
</body>
</html>