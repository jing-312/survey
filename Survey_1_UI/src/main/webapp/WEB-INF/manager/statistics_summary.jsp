<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[调查大纲：${survey.surveyName }]</h4>
			
			<table class="table table-bordered table-hover text-center">
				
				<tr>
					<td colspan="2">包裹信息</td>
				</tr>
				<c:forEach items="${survey.bagSet }" var="bag">
					<tr>
						<td>${bag.bagName }</td>
						<td>查看问题统计结果</td>
					</tr>
					<tr>
						<td><!-- 保留空单元格，向内缩进 --></td>
						<td>
							<table class="table table-bordered table-hover text-center">
								<c:forEach items="${bag.questionSet }" var="question">
									
									<tr>
										<td>${question.questionName }</td>
										<td>
											<c:if test="${question.questionType==0 || question.questionType==1 }">
												<a class="btn btn-default" href="manager/statistics/generateChartResult/${question.questionId }">以图表形式查看统计结果</a>
											</c:if>
											<c:if test="${question.questionType==2 }">
												<a class="btn btn-default" href="manager/statistics/showList/${question.questionId }">以列表形式查看统计结果</a>
											</c:if>
										</td>
									</tr>
									
								</c:forEach>
							</table>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			
		</div>
		
	</div>

</body>
</html>