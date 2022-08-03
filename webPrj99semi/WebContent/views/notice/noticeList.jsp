<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	#outer {
		background-color: black;
		color: white;
		width: 60%;
		margin: 0 auto; /*가운데정렬*/
		padding-top: 50px;
		padding-bottom: 50px;
		display: flex;
		justify-content: center;
		align-items: flex-start;
		flex-wrap: wrap;
		text-align: center;
		
	}

	#outer h1 {
		display: inline-block;
		width: 80%;
	}

	#write {
		align-self: flex-end;
	}

	#table-main {
		border: 1px solid white;
		width: 95%;
		margin-top: 50px;

	}

</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<div id="outer">

		<h1>공지사항</h1>
		
		<%if(loginMember != null && "ADMIN".equals(loginMember.getId())){ %>
			<button id="write">공지사항 작성</button>
		<%}%>

		<table id="table-main">
			
	
			<tr>
				<td>글번호</td>
				<td>글제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일</td>
	
	
			</tr>
			<tr>
				<td>100</td>
				<td>제목01</td>
				<td>관리자01</td>
				<td>30</td>
				<td>2022-08-03</td>
	
	
			</tr>
			<tr>
				<td>200</td>
				<td>제목02</td>
				<td>관리자02</td>
				<td>222</td>
				<td>2121-21-21</td>
	
	
			</tr>
		</table>
	</div>
</body>
</html>