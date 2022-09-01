<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<body>

	<h1>인덱스 페이지 (ajax 테스트)</h1>
	
	<input type="text" id="t">
	
	<button onclick="test();">클릭ㅋㅋ</button>
	
	<div id="result">
		결과영역
	</div>
	
	<script>
		function test(){
			//$('#result').load("/web16/kh");
			
			const temp = $('#t').val();
			
			//ajax
			$.ajax({
				url : "/web16/kh", 
				method : "GET",
				data : {
							userId : temp 
						},
				success : function(x){
					console.log("통신성공ㅋㅋ");
					console.log(x);
					
					//json 형태로 전달받은 문자열을 객체로 반환하기
					const result = JSON.parse(x);
					console.log(result);
				},
				fail : function(e){
					console.log("통신실패ㅠㅠ");
					console.log(e);
				}
			});
			
		}
	</script>
	
</body>
</html>