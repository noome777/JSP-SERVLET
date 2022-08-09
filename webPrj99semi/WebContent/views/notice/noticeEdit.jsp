<%@page import="com.kh.notice.vo.NoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	NoticeVo vo = (NoticeVo) request.getAttribute("vo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    #outer{
        background-color: black;
        color: white;
        width: 60%;
        height: 70vh;
        margin: auto;

    }

    #outer h1{
        padding-top: 50px;
    }
    #section{
        border: 1px solid wheat;
        width: 90%;
        height: 60%;
        margin: auto;
    }

    #section *{
        width: 100%;
    }
    #div-btn-area{
        width: 30%;
        margin: auto;
        margin-top: 30px;
    }
</style>
</head>
<body>

<%@ include file="/views/common/header.jsp" %>

<div id="outer">
    <h1 align="center">공지사항 수정하기</h1>

	<!--(작성자번호, 제목, 내용)  -->
    <form action="<%=contextPath%>/notice/edit" method="post"> <!--엄청 긴 데이터가 들어가므로 post방식이 적합  -->
    	<input type="hidden" name="num" value="<%=vo.getNo()%>">
       <input type="hidden" name="writerNo" value="<%= loginMember.getNo()%>">
       <div id="section">
        제목 <input type="text" size="120" name="title" required value="<%=vo.getTitle()%>">
        <br>
        내용
        <br>
        <textarea rows="10" cols="130" style="resize: none;" name="content" required><%=vo.getContent()%></textarea>
       </div>

       <div id="div-btn-area">
            <input type="submit" value="수정하기">
            <input type="button" value="뒤로가기" onclick="history.go(-1)">
            <input type="reset" value="초기화">
            <!-- <input type="button" value="뒤로가기" onclick="history.back"> -->
       </div>
       </div>

    </form>


</div>

</body>
</html>