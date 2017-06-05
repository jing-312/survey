<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<script type="text/javascript">

	$(function(){
		
		$(".deeplyDelAnchor").click(function(){
			
			var bagName = $(this).parents("tr").children("td:eq(0)").text();
			
			var firstConfirm = confirm("您真的要删除【"+bagName+"】这个包裹吗？")
			
			if(firstConfirm){
				
				var secondConfirm = confirm("确定将删除包裹内的所有问题。");
				
				if(secondConfirm){
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
			<h4>[设计调查]</h4>
			
			<table	class="table table-bordered table-hover text-center">
				<tr><td colspan="4">调查的基本信息</td></tr>
				<tr>
					<td><img  src="${survey.logoPath}"></td>
					<td>${survey.surveyName}</td>
					<td><a class="btn btn-default" href="guest/bag/toAddUI/${survey.surveyId}">创建包裹</a></td>
					<td><a class="btn btn-default" href="guest/bag/toAdjustUI/${survey.surveyId}">调整包裹顺序</a></td>
				</tr>
			</table>
			<br/>
			<br/>
			<table class="table table-bordered table-hover text-center">
				<tr><td colspan="2">包裹信息</td></tr>
				<c:if test="${empty survey.bagSet}">
					<tr><td colspan="2">您还未创建包裹</td></tr>
				</c:if>
				<c:if test="${! empty survey.bagSet}">
					<tr>
						<td>包裹信息</td>
						<td>基本操作</td>
					</tr>
					<c:forEach items="${survey.bagSet}" var="bag">
						<tr>
							<td>${bag.bagName}</td>
							<td>
								<a class="btn btn-warning" href="guest/bag/deleteBag/${bag.bagId}/${survey.surveyId}">删除包裹</a>
								<a class="btn btn-default" href="guest/bag/deditBagUI/${bag.bagId}/${survey.surveyId}">更新包裹</a>
								<a class="btn btn-danger deeplyDelAnchor" href="guest/bag/deeplyDelete/${bag.bagId}/${survey.surveyId}">深度删除</a>
								<a class="btn btn-default" href="guest/bag/toMoveOrCopePage/${bag.bagId}/${survey.surveyId}">移动/复制包裹</a>
								<a class="btn btn-default" href="guest/question/toAddUI/${survey.surveyId}/${bag.bagId}">创建问题</a>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<table class="table table-bordered table-hover text-center">
									<c:if test="${empty bag.questionSet }">
										<tr><td>尚未创建问题</td></tr>
									</c:if>
									<c:if test="${!empty bag.questionSet }">
										<tr>
											<td>问题详情</td>
											<td>问题操作</td>
										</tr>							
										<c:forEach items="${bag.questionSet}" var="question" >
											<tr>
												<td>
													${question.questionName}
													<c:if test="${question.questionType==0}">
														<c:forEach items="${question.optionList}" var="option">
															<input type="radio"/>${option }
														</c:forEach>
													</c:if>
													<c:if test="${question.questionType==1 }">
														<c:forEach items="${question.optionList }" var="option">
															<input type="checkbox"/>${option }
														</c:forEach>
													</c:if>
													<c:if test="${question.questionType==2 }">
														<input type="text"/>
													</c:if>
												</td>
												<td>
													<a class="btn btn-warning" href="guest/question/deleteQuestion/${survey.surveyId}/${question.questionId}">删除问题</a>
													<a class="btn btn-default" href="guest/question/toEditUI/${survey.surveyId}/${question.questionId}">更新问题</a>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</table>
							</td>
						</tr>
					</c:forEach>
				</c:if>
					<tr>
						<td colspan="2">
							<a class="btn btn-default" href="guest/survey/complete/${survey.surveyId}">完成调查</a>
						</td>
					</tr>
			</table>
		</div>	
</body>
</html>