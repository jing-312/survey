<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[以列表形式查看简答题统计结果]</h4>
			<table class="table table-bordered table-hover text-center">
				
				<c:if test="${empty listResult }">
					<tr>
						<td>没有获取到有效的数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty listResult }">
				
					<c:forEach items="${listResult }" var="result">
						<tr>
							<td>${result }</td>
						</tr>
					</c:forEach>
				
				</c:if>
				
			</table>
		</div>
		
	</div>

</body>
</html>