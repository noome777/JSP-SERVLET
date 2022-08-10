<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <!-- 글번호, 제목, 작성자, 조회수, 작성일시 -->
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>작성일시</th>
            </tr>
            <tr>
                <td>0</td>
                <td>임시제목</td>
                <td>하드코딩</td>
                <td>777</td>
                <td>2022-08-10</td>
            </tr>
        </table>

        <div id="page-area">
            <a>1</a>
            <a>2</a>
            <a>3</a>
            <a>4</a>
            <a>5</a>
            <a>6</a>
            <a>7</a>
            <a>8</a>
            <a>9</a>
            <a>10</a>
        </div>

   </div>
   
</body>
</html>