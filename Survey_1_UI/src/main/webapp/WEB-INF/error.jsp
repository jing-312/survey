<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>

<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			window.history.back();//forward()
		});
	});

</script>
<body>

	<div class="container">

		
		<!-- 巨幕 -->
		<div class="jumbotron text-center">
			<h1>[错误提示]</h1>
			<hr>
				<c:if test="${!empty exception }">${requestScope.exception.message }</c:if>
			<hr>
			<button class="btn btn-default">返回继续操作</button>
		</div>
		
	</div>

</body>
</html>