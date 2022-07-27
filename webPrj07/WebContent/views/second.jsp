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
	h1 {
		color: <%=c%>
	}
</style>
</head>
<body>


	<h1>h1의 글자 색 변경 (사용자로부터 입력 받은 값)</h1>
	
</body>
</html>