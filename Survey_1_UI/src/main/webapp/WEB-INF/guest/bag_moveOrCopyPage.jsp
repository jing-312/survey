<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[移动和复制包裹页面]</h4>
			<table class="table table-bordered table-hover text-center">
				<tr>
					<td> ID </td>
					<td> 调查名称  </td>
					<td> 操作  </td>
				</tr>
				
				<c:forEach items="${page.list}" var="survey">
					<tr>
						<td>${survey.surveyId}</td>
						<td>${survey.surveyName}</td>
						<td>
							<c:if test="${survey.surveyId == requestScope.surveyId}">
								当前调查
							</c:if>
							<c:if test="${survey.surveyId != requestScope.surveyId}">
								<a class="btn btn-default" href="guest/bag/moveHere/${requestScope.bagId }/${survey.surveyId }">移动到这个调查</a>|
								<a class="btn btn-default" href="guest/bag/copyHere/${requestScope.bagId }/${survey.surveyId }">复制到这个调查</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">
						<c:set var="targetUrl" value="guest/bag/toMoveOrCopyPage/${requestScope.bagId}/${requestScope.surveyId}"/>
						<%@include file="/res_jsp/navigtor.jsp"  %>					
					</td>
				</tr>
			</table>
		</div>
		
	</div>

</body>
</html>