<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>view02</h1>
	
	
<%-- <%if (request.getAttribute("userId") == null || request.getAttribute("userId").equals("")){%>
		<h3>로그인 해주세요</h3>
	<%} else {%> 
		<h3> ${userId} 님 환영합니다.</h3>
	<%} %> --%>
	
	
	<c:if test="${ empty userId }">
		<h3>로그인 해주세요</h3>
	</c:if>
	
	<c:if test="${ not empty userId }">
		<h3> ${userId} 님 환영합니다.</h3>
	</c:if>
	
	
	
	
	<hr><hr><hr>




<%--<% String[] arr = (String[])request.getAttribute("foods"); %>
	
	<%for(int i = 0; i < arr.length; i++) { %>
		<h3><%=arr[i] %></h3>
	<%} %> --%>
	
	<c:forEach items="${foods}" var="food">
		<h3>${food}</h3>
	</c:forEach>
	
	<c:forEach begin="0" end="2" step="1" varStatus="st">
		<h3>${st.current}</h3>		<!-- 현재 객체 -->
		<h3>${st.index}</h3>		<!-- 인덱스 -->
		<h3>${st.count}</h3>		<!-- 반복 횟수 -->
		<h3>${st.first}</h3>		<!-- 현재 객체가 첫번쨰인지 (true/false) -->
		<h3>${st.last}</h3>			<!-- 현재 객체가 마지막인지 (true/false)-->
		<h3>${st.begin}</h3>		<!-- 시작값 -->
		<h3>${st.end}</h3>			<!-- 종료값 -->
		<h3>${st.step}</h3>			<!-- 증감값 -->
		<hr>
	</c:forEach>
	
	<!-- 마지막 객체는 출력되지 않도록 -->
	<c:forEach items="${foods}" var="f" varStatus="vs">
		 <c:if test="${not vs.last}">
			 <h5>${f}</h5>
		 </c:if>
	</c:forEach>

	<hr>
	
	<c:out value="${param.data}"></c:out>








</body>
</html>