<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[统计调查]</h4>
			
			<table class="table table-bordered table-hover text-center">
				
				<c:if test="${empty page.list }">
					
					<tr>
						<td>暂时没有可以统计的调查</td>
					</tr>
					
				</c:if>
				
				<c:if test="${!empty page.list }">
				
					<tr>
						<td>ID</td>
						<td>调查名称</td>
						<td colspan="2">操作</td>
					</tr>
					
					<c:forEach items="${page.list }" var="survey">
						
						<tr>
							<td>${survey.surveyId }</td>
							<td>${survey.surveyName }</td>
							<td>
								<a class="btn btn-default" href="manager/statistics/showSummary/${survey.surveyId }">查看调查大纲</a>
							</td>
							<td>
								<a class="btn btn-default" href="manager/statistics/exportExcel/${survey.surveyId }">导出Excel文件</a>
							</td>
						</tr>
						
					</c:forEach>
					
					<tr>
						<td colspan="4">
							<c:set var="targetUrl" value="manager/statitics/showAllAvailable"/>
							<%@include file="/res_jsp/navigtor.jsp" %>
						</td>
					</tr>
				
				</c:if>
				
			</table>
			
		</div>
		
	</div>

</body>
</html>