<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/members/list.jsp</h1>

<%-- 		${requestScope } --%>
	<hr>
	${memberList}
	<hr>

	<table border="1">
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>회원가입일</td>
			<td>최종수정일</td>
		</tr>
		
	<c:forEach var="vo" items="${memberList }">
		<tr>
			<td>${vo.userid}</td>
			<td>${vo.userpw}</td>
			<td>${vo.username}</td>
			<td>${vo.useremail}</td>
			<td>${vo.regdate}</td>
			<td>${vo.updatedate }</td>
		</tr>
	</c:forEach>
		
	</table>

</body>
</html>