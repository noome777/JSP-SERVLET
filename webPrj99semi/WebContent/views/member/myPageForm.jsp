<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
	main table{
		margin: 0 auto;
	}

	main{
		background-color: black;
		color: white;
	}


</style>
</head>
<body>

<!-- 
	view에서 controller로 데이터 넘기기 
	화면(브라우저) -> 서버(톰캣) 데이터 넘기기
	
	1. form 태그 ->method를 post 방식으로도 가능함
	2. url 입력-> 메소드 get 방식으로만 가능함 , query string (?id=abc%pwd=1234)
-->

	<%@include file="/views/common/header.jsp" %>
	
	<main>
		<h1 align="center">마이페이지</h1>
		<form action="/semi/member/myPage" method="post">
			<input type="hidden" value="<%=loginMember.getNo()%>" name="memberNo">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="memberId" maxlength="10"  value="<%=loginMember.getId() %>" required readonly></td>
				
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="memberName" maxlength="3" value="<%=loginMember.getName() %>" required></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="memberPhone" value="<%=loginMember.getPhone() %>" placeholder="- 없이 입력"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="memberEmail" value="<%=loginMember.getEmail() %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="memberAddr" value="<%=loginMember.getAddr() %>"></td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td>
						<input type="checkbox" name="interest" value="game" id="game">
						<label for="game">게임</label>
						<input type="checkbox" name="interest" value="workout" id="workout">
						<label for="game">운동</label>
						<input type="checkbox" name="interest" value="cook" id="cook">
						<label for="game">요리</label>	
						<br>
						<input type="checkbox" name="interest" value="music" id="music">
						<label for="game">음악</label>
						<input type="checkbox" name="interest" value="book" id="book">
						<label for="game">독서</label>
						<input type="checkbox" name="interest" value="darw" id="darw">
						<label for="game">그림</label>
						
					</td>
				</tr>

				<tr>
					<td><input type="submit" id="" value="정보변경"></td>
					<td>
						<input type="button" id=""  value="비밀번호변경">
						<input type="button" id=""  value="회원탈퇴">
					</td>
					
				</tr>
			</table>


		</form>
	</main>

	<script>
	
		$(function(){
			
			//로그인 유저의 취미 가져오기
			const interest = '<%=loginMember.getInterest()%>';
			console.log(interest);
			
			//체크박스 가져오기
			$('input[name=interest]').each(function(){
				/*console.log(this.value);*/
				/*this.checked = true;*/
				
				var result = interest.includes(this.value);
				console.log(result);
				
				if(result == true) this.checked = true;
				
			});
			
		});
	
	</script>
	
	
</body>
</html>