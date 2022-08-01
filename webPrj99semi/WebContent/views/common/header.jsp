<%@page import="com.kh.member.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");

	String alertMsg = (String)session.getAttribute("alertMsg");
	//새로고침 또 한번 했을 때 계속 로그인 성공 창이 뜨는 게 아니라 이후에는 null값이 뜨게 된다. 따라서 밑에서 null 처리 불가피함.
	session.removeAttribute("alertMsg");
	
	String contextPath = request.getContextPath();
	
%>
<!DOCTYPE html>
<html>
<head>
    <style>
        #login-wrap{
            float: right;
        }        
        nav{
        	display : flex;
        	justify-content : space-evenly;
            background-color: black;
            height: 50px;
            width: 60%;
            margin: 0 auto; /*가운데정렬- 세로0 가로auto*/
            text-align: center;
            font-size: 2rem;
        }
        nav a{
            text-decoration: none;
            color: white;
        }
        header::after{
            content: '';
            display: block;
            clear: both;
        }

        main{
            border: 1px dashed black;
            width: 60%;
            margin: 0 auto;
        }

        div{
            box-sizing: border-box;
        }

    </style>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
</head>
<body>
    
    <header>

        <h1 align="center">아름이의 홈페이지</h1>

        <div id="login-wrap">
        	<%if(loginMember == null){ %>
        	<form action="/semi/member/login" method="post">
	            <table>
	                <tr>
	                    <td>아이디</td>
	                    <td colspan="2"><input type="text" name="memberId"></td>
	                </tr>
	                <tr>
	                    <td>비밀번호</td>
	                    <td colspan="2"><input type="password" name="memberPwd"></td>
	                </tr>
	                <tr>
	                    <td></td>
	                    <td><button>로그인</button></td>
	                    <td><button type="button" onclick="location.href='/semi/member/join'">회원가입</button></td>
	                    <!--form 태그는 보통 폼태그 안에서는 기본값이 submit으로 제출되기 때문에, button으로 타입을 바꿔서 폼태그 내에서 제출되지 않도록 한다. -->
	                </tr>
	            </table>
            </form>
            <%}else{%>
            	<%=loginMember.getName() %>님 환영합니다.
            	<br>
            	<a href="<%=contextPath%>/member/myPage">마이페이지</a> 
            	<!-- 마이페이지 클릭시, 현재 로그인 유저의 정보를 담고있는 화면 보여주기 / 세션에 담긴 객체 활용해서(화면에 이미 이름, 번호, 등등이 다 채워져있는) 마이페이지 화면 만들고 링크 연결해주기-->
            	<!-- 마이페이지에서 데이터 입력 후, 회원정보변경 누르면 DB에 가서 UPDATE 진행  -->
            	<!-- 마이페이지 다시 보여주기 (세션에 담긴 객체 업데이트) -->
            	<!-- /member/myPageForm.jsp 이 경로를 보여주고 싶지 않기 떄문에 경로를 포워딩해주는 서블릿을 만들 것임 -->
            	<a href="<%=contextPath%>/member/logout">로그아웃</a>
            	<!-- 세션 만료해주기 -->
            <%}%>
        </div>

        
    </header>

    <nav>
        <a href="<%=contextPath%>">HOME</a>
        <a href="">공지사항</a>
        <a href="">일반게시판</a>
        <a href="">사진게시판</a>
    </nav>

	<script>
	/* 여기서  null처리를 해주기 때문에 이제 새로고침 해도 로그인 성공! 이나 null이 alert 되지 않는다. */
	<%if(alertMsg != null) {%>
		alert('<%=alertMsg%>');
	<%}%>
	</script>

</body>
</html>