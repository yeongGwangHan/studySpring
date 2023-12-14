<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<button class="btn btn-block btn-success btn-lg">버튼</button>

<hr>
<a class="btn btn-block btn-social btn-github">
	<i class="fa fa-github"></i> Sign in with GitHub
</a>

<div class="box box-danger">
<div class="box-header with-border">
<h3 class="box-title">Donut Chart</h3>
<div class="box-tools pull-right">
<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
</button>
<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
</div>
</div>
<div class="box-body">
<canvas id="pieChart" style="height: 393px; width: 787px;" height="393" width="787"></canvas>
</div>

</div>

<%@ include file="include/footer.jsp" %>