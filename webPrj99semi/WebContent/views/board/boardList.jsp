<%@page import="com.kh.board.vo.BoardVo"%>
<%@page import="com.kh.common.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
 	List<BoardVo> voList = (List<BoardVo>) request.getAttribute("list");
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
        
        <!-- 로그인 한 사람만 글쓰기 버튼이 보이게 해주기 -->
        <div style="text-align: right; width: 95%">
       <%-- 
       <%if(loginMember != null){%>
	   <a class="btn btn-primary" href="<%=contextPath%>/board/insert">글쓰기</a>
	   <%} %>  
	   --%>
	    
	    
	    <!-- jstl 사용 -->
		    <c:if test="${not empty loginMember}">
		        <a class="btn btn-primary" href="${cp}/board/insert">글쓰기</a>
		    </c:if>
        </div>

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
            <%-- 
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
            --%>
            
            
           <%--
            	<c:forEach items="<%=voList%>" var="b">
	            <tr>
	                <td>${b.no}</td>
	                <td>${b.category}</td>
	                <td>${b.title}</td>
	                <td>${b.writer}</td>
	                <td>${b.cnt}</td>
	                <td>${b.enrollDate}</td>
	            </tr>
           	</c:forEach> 
           	--%>
           	
           	<c:forEach items="${list}" var="b">
	            <tr>
	                <td>${b.no}</td>
	                <td>${b.category}</td>
	                <td>${b.title}</td>
	                <td>${b.writer}</td>
	                <td>${b.cnt}</td>
	                <td>${b.enrollDate}</td>
	            </tr>
           	</c:forEach>
        </table>

        <div id="page-area">
        
        	<!--현재 페이지가 1이 아닐때만 이전 버튼이 만들어지게  -->
        	<%if(currentPage != 1){ %>
	        	<a class="btn btn-sm btn-primary" href="<%=contextPath%>/board/list?p=<%=currentPage-1%>"> &lt; </a>
        	<%} %>
        	
        	
        	<% for(int i = startPage; i <= endPage; i++){ %>
        	<!-- 현재페이지에서 새로고침시에도 활성화 되지 않게 -->
        		<%if(i == currentPage) {%>
        			<a class="btn btn-sm btn-primary"><%=i %></a>
        			<%} else {%>
            		<a class="btn btn-sm btn-primary" href="<%=contextPath %>/board/list?p=<%=i%>"><%=i %></a>
        			<%} %>
            <%} %>
            
            <!--현재 페이지가 maxPage가 아닐때만 다음 버튼이 만들어지게  -->
            <%if(currentPage != maxPage){ %>
	        	<a class="btn btn-sm btn-primary" href="<%=contextPath%>/board/list?p=<%=currentPage+1%>"> &gt; </a>
        	<%} %>
        	
            
        </div>

   </div>
   
</body>
</html>