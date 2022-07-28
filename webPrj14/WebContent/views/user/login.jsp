<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String alertMsg = (String)session.getAttribute("joinSuccess");
    //alert를 한번만 나오게, session에 남아있는 joinSuccess를 지워준다 -> alert하면 회원가입 성공메세지는 지워지고 다음에는 null값이 나온다.
    	session.removeAttribute("joinSuccess");
    
    //session에 담겨있는 회원가입 성공을 지우는 건 맞는데, 세션에 있는 값이 지워지더라도 alertMsg라는 문자열 자체는 남아 있어서 alert로 출력이 되는 거다.
    //세션에 있는 걸 지우는 것 != 그냥 변수인 alertMsg 이기 때문에. 대신 다음에 새로고침을 눌렀을 떄는 이미 remove 된 상태이기 때문에 세션에 있는 문자열이 지워져서 null값이 나오는 거임.
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>로그인</h1>
	
	<form action="/webPrj14/user/login" method="post">
		아이디 : <input type="text" name="userId">
		<br>
		비밀번호 : <input type="password" name="userPwd">
		<br>
		<input type="submit" value="로그인">
	</form>
	
	<script>
		
	//위에서 remove를 해줘서 null값이 계속 alert로 안 뜨게 하기 위해 널값이 나오는 게 아닐때(회원가입 성공일 때만) alert해줌 반복되지 않도록
		<%if(alertMsg != null){%>
				alert('<%=alertMsg%>');
		<%}%>
		
		
	</script>
	
	
</body>
</html>