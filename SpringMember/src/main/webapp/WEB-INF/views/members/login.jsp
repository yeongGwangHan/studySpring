<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>login.jsp</h1>
	
	<fieldset>
		<legend>로그인</legend>
		<form method="post">
			아이디 : <input type="text" name="userid"><br>
			비밀번호 : <input type="password" name="userpw"><br>
			<input type="submit" value="로그인">
			<input type="button" value="회원가입" onclick="location.href='/members/join'">
		</form>
	</fieldset>
</body>
</html>