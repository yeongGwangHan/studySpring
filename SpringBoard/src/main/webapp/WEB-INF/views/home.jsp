<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

작성자 : <input type="text" name="writer" id="writer"> <br>
제목 : <input type="text" name="title" id="title"><br>
내용 : <input type="text" name="content" id="content"><br>

<input type="button" value="글쓰기" id="btnCreate">
<hr>

<!-- 특정 글번호를 조회 -->

<input type="button" value="1번글 조회" id="btnRead">

<div id="divRead"></div>


<script>
	$(document).ready(function(){
		//alert("A");
		
		// 1번글 조회
		$("#btnRead").click(function(){
			
			var num = 100;
			
			$.ajax({
// 				url : "/boards/"+num,
				url : "/boards/1",
				type :"GET",
				success : function(data){
					alert("글 조회 페이지 다녀옴");
					console.log(data);
					$('#divRead').append(data);
					
					// List $.each(); jquery 반복문 출력
				}
			});
		});
		
		
		// 글쓰기 /주소 + 데이터(JSON)
		$("#btnCreate").click(function(){
			var boardVO = {
					"writer" : $("#writer").val(),
					"title" : $("#title").val(),
					"content" : $("#content").val()
				};
			$.ajax({
				url : "/boards",
				type: "POST",
				data: JSON.stringify(boardVO),
				contentType : "application/json",
				success : function(data){
					alert("페이지 다녀옴 - 글쓰기");
					alert(data);
					if(data == "createOK"){
						$("#writer").val("");
						$("#title").val("");
						$("#content").val("");
					}
				}
			});
		});
		
		
		
		
		
		
	});
</script>

<%@ include file="include/footer.jsp" %>