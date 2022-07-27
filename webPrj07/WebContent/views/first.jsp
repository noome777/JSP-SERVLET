<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		String c = request.getParameter("color");
		System.out.println(c);
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div{
		background-color: <%=c%>;
		width: 100px;
		height: 100px;
		border: 1px solid black;	
	}
</style>
</head>
<body>

	<%@ include file="/views/common/header.jsp" %>

	<h1>div 색 변경 (사용자가 고른 색)</h1>
	
	<div></div>
	
	
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>