<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>

<script type="text/javascript">

	$(function(){
		
		$(".deeplyDelAnchor").click(function(){
			
			var surveyName = $(this).parents("tr").children("td:eq(2)").text();
			
			if(confirm("您真的要删除【"+surveyName+"】这个调查吗？")){
				
				if(confirm("您真的要删除【"+surveyName+"】这个调查吗？")){
					
					return true;
					
				}
				
			}
			
			return false;
			
		});
		
	});
</script>
<body>
	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[显示我未完成的调查]</h4>
			<table class="table table-bordered table-hover text-center">
				<c:if test="${empty page.list }">
					<tr>
						<td>您尚未创建调查</td>
					</tr>
				</c:if>
				<c:if test="${!empty page.list }">
					<tr>
						<td>ID</td>
						<td>Logo</td>
						<td>调查名称</td>
						<td colspan="4">操作</td>
					</tr>
					<c:forEach items="${page.list }" var="survey">
						<tr>
							<td>${survey.surveyId }</td>
							<td><img src="${survey.logoPath }"/></td>
							<td>${survey.surveyName }</td>
							<td>
								<a class="btn btn-default" href="guest/survey/toDesignUI/${survey.surveyId}">设计</a>
							</td>
							<td>
								<a class="btn btn-warning" href="guest/survey/deleteSurvey/${survey.surveyId}/${page.pageNo}">删除</a>
							</td>
							<td>
								<a class="btn btn-default" href="guest/survey/toEditUI/${survey.surveyId }/${page.pageNo}">更新</a>
							</td>
							<td>
								<a class="btn btn-danger deeplyDelAnchor" href="guset/survey/deleteDpeelySurvey/${survey.surveyId}">深度删除</a>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7">
							<c:set var="targetUrl" scope="page" value="guest/survey/showMyUncompleted"/>
							<%@include file="/res_jsp/navigtor.jsp" %>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
</body>
</html>