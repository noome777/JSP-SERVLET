<%@page import="com.kh.notice.vo.NoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<NoticeVo> voList = (ArrayList<NoticeVo>) request.getAttribute("voList");
    
    //for문에서 이제 list에 있는 거 빼준다. -> 테이블로
    
   		
    
    %>
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
	#table-main>tbody tr:hover{
		background-color: darkgray;
		cursor: pointer;
	}

</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	<div id="outer">

		<h1>공지사항</h1>
		
		<%if(loginMember != null && "admin".equals(loginMember.getId())){ %>
			<button id="write" onclick="location.href='/semi/notice/insert'">공지사항 작성</button>
		<%}%>

		<table id="table-main">
			
			<thead>
				<tr>
					<td>글번호</td>
					<td>글제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			
			<tbody>
				<%for(int i = 0; i < voList.size(); i++){ %>
					<tr>
						<td><%= voList.get(i).getNo() %></td>
						<td><%= voList.get(i).getTitle() %></td>
						<td><%= voList.get(i).getWriter() %></td>
						<td><%= voList.get(i).getCnt() %></td>
						<td><%= voList.get(i).getEnrollDate() %></td>
					</tr>
				<%} %>
			</tbody>
			
		</table>
	</div>
	
	<script>
		$(function(){
			$('#table-main>tbody>tr').click(function(){
				//행 클릭되었을 때, 동작할 내용
				
				//글 번호 가져오기 (this -> tr태그)
				const num = $(this).children().eq(0).text();
				console.log(num);
				//해당 번호로 요청 보내기
				location.href='/semi/notice/detail?num=' + num;
				
			});
		})
	</script>
	
	
</body>
</html>