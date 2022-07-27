<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% /* 
	화면(view)에서 서블릿쪽으로 데이터를 가져올 때는 파라미터를 통해서 가져오고,
	서블릿에서 jsp로 데이터를 넘겨줄 때는 attribute로 가져간다.
	
	setAttrubute로 데이터 담았던 것을 가져오기
	이때, getAttribute는 object 객체를 반환해주기 때문에 String으로 형변환을 해줘야한다.
	*/
	String name = (String)request.getAttribute("name");
	String phone = (String)request.getAttribute("phone");
	String addr = (String)request.getAttribute("addr");
	String memo = (String)request.getAttribute("memo");
	String base = (String)request.getAttribute("base");
	String topping = (String)request.getAttribute("topping");
	String[] sideArr = (String[])request.getAttribute("sideArr");
	String pay = (String)request.getAttribute("pay");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>주문 내역</h1>
	
	<table border="1">
		<tr>
			<td>name</td>
			<td><%=name%></td>
		</tr>
		<tr>
			<td>phone</td>
			<td><%=phone%></td>
		</tr>
		<tr>
			<td>addr</td>
			<td><%=addr%></td>
		</tr>
		<tr>
			<td>memo</td>
			<td><%=memo%></td>
		</tr>
		<tr>
			<td>base</td>
			<td><%=base%></td>
		</tr>
		<tr>
			<td>topping</td>
			<td><%=topping%></td>
		</tr>
		<tr>
			<td>side</td>
			<td><%=String.join("," , sideArr)%></td>
		</tr>
		<tr>
			<td>pay</td>
			<td><%=pay%></td>
		</tr>
	</table>
	
</body>
</html>








