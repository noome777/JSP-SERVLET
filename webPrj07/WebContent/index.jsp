<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/views/common/header.jsp" %>
	
	<h1>JSP</h1>
	
	<hr>
	
	<a href="/webPrj07/views/first.jsp">절대경로- div 색 바꾸기</a> 
	<br>
	<a href="views/first.jsp">상대경로- div 색 바꾸기</a>
	<hr>
	<br> <br>
	<a href="/webPrj07/views/second.jsp">절대경로- h1 글자 색 바꾸기</a>
	<br>
	<a href="views/second.jsp">상대경로- h1 글자 색 바꾸기</a>
	<hr>
	<br> <br>
	<a href="/webPrj07/views/third.jsp?num=0">절대경로- 정수판단</a>
	<br>
	<a href="views/third.jsp?num=0">상대경로- 정수판단</a>
	<hr>
	<br> <br>
	<a href="/webPrj07/views/fourth.jsp">절대경로- 반복문연습</a>
	<br>
	<a href="views/fourth.jsp">상대경로- 반복문연습</a>
	<hr>
	
	<%@ include file="/views/common/footer.jsp" %>
	
	
</body>
</html>