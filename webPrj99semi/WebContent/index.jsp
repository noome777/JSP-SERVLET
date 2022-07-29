<%@page import="com.kh.common.JDBCTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	JDBCTemplate.getConnetion();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--
	회원 - 로그인, 회원가입, 정보변경, 탈퇴, 아이디 중복체크
	공지사항 - 리스트 조회, 상세조회, 작성, 수정, 삭제
	일반게시판 - 리스트 조회(+페이징), 상세조회, 작성, 수정, 삭제, 댓글 리스트 조회, 댓글 작성
	사진게시판 - 게시판 리스트 조회(썸네일), 상세조회, 작성(첨부파일 업로드)
	-->
	
	<%@ include file="/views/common/header.jsp" %>
	<h1>welcome !</h1>
	
	
	
	
	
	
	
</body>
</html>