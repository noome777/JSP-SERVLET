<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	/* String v = (String) request.getAttribute("data"); EL 에서는 이러한 과정들이 필요 없이 바로 $ 사인 이용해서 data를 가져올 수 있다.*/
    	/* String v2 = (String) request.getAttribute("data"); */
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>view01</h1>
	
	<%-- <h3><%=v %></h3> --%>
	<h3>${data}</h3>
	<h3>${data}</h3>
	<h3>${data}</h3>
	
	<h3>${data2}</h3>
	<h3>${data3}</h3>

	<h3>${requestScope.data}</h3>
	<h3>${sessionScope.data}</h3>
	<h3>${applicationScope.data}</h3>
	
	<hr>
	
	<!-- 배열의 데이터 꺼내기 -->
	<h3>${fruits[0]}</h3>
	<h3>${fruits[1]}</h3>
	<h3>${fruits[2]}</h3>
	
	
	<hr>
	
	<!-- 맵의 데이터 꺼내기 -->
	<h3>${fruitsMap.first}</h3>
	<h3>${fruitsMap.second}</h3>
	<h3>${fruitsMap.third}</h3>
	
	
	<hr>
	
	<!-- 객체의 데이터 꺼내기 -->
<%-- 	<h3>${member.getMemberId}</h3>  ==    --%>
	<h3>${member.memberId}</h3>
	<h3>${member.memberPwd}</h3>
	<h3>${member.memberNick}</h3>
	
	<hr>
	
	<!-- 파라미터의 값 꺼내기  -->
	<h3>${param.num}</h3>
	
	<hr>
	
	<!-- 연산자 -->
	<h3>나이 : ${age < 30}</h3>
	
	<h3>${param.num eq 10 }</h3>
	<h3>${param.num ne 10 }</h3>
	
	<h3>${empty param.num}</h3>
	
	
</body>
</html>