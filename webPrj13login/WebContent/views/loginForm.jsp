<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>로그인</h1>

<form action="/loginPrj/login" method="post">
	id : <input type="text" name="userId"> <br>
	pwd : <input type="text" name="userPwd"> <br>
	<input type="submit">
</form>
</body>
</html>