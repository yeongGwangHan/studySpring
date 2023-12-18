<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/modify.jsp</h1>

<!-- 글수정 폼태그 시작 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 글수정</h3>
	</div>


	<form role="form" method="post">
		<input type="hidden" name="bno" value="${resultVO.bno }">
		<div class="box-body">
			<div class="form-group">
				<label for="writerInput">작성자</label> 
				<input type="text" class="form-control" id="writerInput" name = "writer" value="${resultVO.writer}" readonly>
			</div>
			<div class="form-group">
				<label for="titleInput">제 목</label> 
				<input type="text" class="form-control" id="titleInput" name = "title" value="${resultVO.title}">
			</div>
			<div class="form-group">
				<label for="contentInput">내 용</label> 
				<textarea rows="10" cols="100" class="form-control" id="contentInput" name ="content" >${resultVO.content}</textarea>
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-success" >글수정</button>
		</div>
	</form>
</div>
<!-- 글쓰기 폼태그 끝 -->

<%@ include file="../include/footer.jsp"%>
