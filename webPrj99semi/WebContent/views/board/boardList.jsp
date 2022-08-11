<%@page import="com.kh.board.vo.BoardVo"%>
<%@page import="com.kh.common.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<BoardVo> voList = (List) request.getAttribute("list");
 	PageVo pv = (PageVo) request.getAttribute("pv");
 	
 	int currentPage = pv.getCurrentPage();
 	int startPage = pv.getStartPage();
 	int endPage = pv.getEndPage();
 	int maxPage = pv.getMaxPage();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    #outer {
        width: 60%;
        height: 550px;
        margin: auto;
        padding-top: 50px;
        background-color: black;
        color: white;
    }

    #outer table {
        width: 95%;
        margin: auto;
        margin-top: 50px;
    }

    #page-area {
        width: 80%;
        text-align: center;
        margin: auto;
        padding-top: 30px;
    }

</style>
</head>
<body>

   <%@ include file="/views/common/header.jsp" %>
   
   
   <div id="outer">
   
        <h1 align="center">게시글 조회</h1>

        <table border="1">
            <!-- 글번호, 카테고리, 제목, 작성자, 조회수, 작성일시 -->
            <tr>
                <th>글번호</th>
                <th>카테고리</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일시</th>
            </tr>
            <%for(BoardVo b : voList){ %>
	            <tr>
	                <td><%=b.getNo() %></td>
	                <td><%=b.getCategory() %></td>
	                <td><%=b.getTitle() %></td>
	                <td><%=b.getWriter() %></td>
	                <td><%=b.getCnt() %></td>
	                <td><%=b.getEnrollDate() %></td>
	            </tr>
            <%} %>
        </table>

        <div id="page-area">
        	<% for(int i = startPage; i <= endPage; i++){ %>
            <a><%=i %></a>
            <%} %>
        </div>

   </div>
   
</body>
</html>