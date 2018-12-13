<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 使用Spring Security提供的taglib -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "： //" + request.getServerName() + "： " + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http： //www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>认证成功</title>
</head>
<body>
	<h1>认证成功
	<%
		out.println("This is success.jsp");
	%>
	</h1>
	<BR> 用户名：  <sec:authentication property="name"/>  <%-- | 密码：  <sec:authentication property="credentials"/> --%>
	<BR> SessionID： <%=session.getId()%>
	<BR> SessionIP： <%=request.getServerName()%>
	<BR> SessionPort： <%=request.getServerPort()%>
	<BR> 
</body>
</html>