<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<h1>/board/listAll.jsp</h1>

<%-- ${boardList } --%>
${result}

<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판</h3>
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 10px">BNO</th>
					<th>TITLE</th>
					<th>WRITER</th>
					<th>REGDATE</th>
					<th style="width: 40px">VIEWCNT</th>
				</tr>
				<c:forEach var="bvo" items="${boardList }">
					<tr>
						<td>${bvo.bno }</td>
						<td>
							<a href="/board/read?bno=${bvo.bno}">${bvo.title }</a> 
						</td>
						<td>${bvo.writer }</td>
						<td>
							<fmt:formatDate value="${bvo.regdate }" pattern="yyyy-MM-dd (a)hh:mm:ss"/>
						</td>
						<td><span class="badge bg-red">${bvo.viewcnt }</span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			<li><a href="#">«</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">»</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	
	// JSP(java) / JSTL,EL / HTML / JavaScript

 	//alert("${result}");/* 자바 스크립트로 정보전달 가능 */
	var result = "${result}";
	
	if(result == "CREATEOK"){
		alert("글 쓰기 완료!");
	}
	
</script>

<%@ include file="../include/footer.jsp"%>