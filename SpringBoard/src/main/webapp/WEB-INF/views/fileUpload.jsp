<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var cnt = 1;
		
		// 버튼 클릭시 파일입력 버튼 추가
		$("#addBtn").click(function(){
			$("#fileDiv").append("<input type='file' name='file"+cnt+"' accept='image/*, application/pdf'>");
			cnt++;
		});
	});
</script>


</head>
<body>
	<h1>fileUpload 호출</h1>
	<h1>fileUpload.jsp</h1>
	
	<fieldset>
		<legend> 다중파일 업로드 </legend>
		<form action="/upload" method="post" enctype="multipart/form-data">
			아이디 : <input type="text" name="userid"><br>
			이름 : <input type="text" name="username"><br>
			<input type="button" value="파일추가" id="addBtn"><br>
			
			<div id="fileDiv"></div>
			
			<hr>
			<input type="submit" value="파일 업로드">
		</form>
	
	</fieldset>
</body>
</html>