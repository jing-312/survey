<%@page import="com.atguigu.survey.utils.DataprocessUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp"%>

		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[参与调查]</h4>
			
			<table class="table table-bordered table-hover text-center">
				
				<tr>
					<td colspan="2">调查信息</td>
				</tr>
				<tr>
					<td><img src="${sessionScope.CURRENT_SURVEY.logoPath }"/></td>
					<td>${sessionScope.CURRENT_SURVEY.surveyName }</td>
				</tr>
				
			</table>
			
			<br/>
			
			<div class="container">
				<form action="guest/engage/engage" method="post" class="form-horizontal">
				
					<input type="hidden" name="currentIndex" value="${requestScope.CURRENT_INDEX }"/>
				
					<table class="table table-bordered table-hover text-center">
						
						<tr>
							<td colspan="2">当前包裹：${requestScope.CURRENT_BAG.bagName }</td>
						</tr>
						
						<c:forEach items="${requestScope.CURRENT_BAG.questionSet }" var="question">
							<tr>
								<td>${question.questionName }</td>
								<td>
									<c:if test="${question.questionType==0 }">
										<c:forEach items="${question.optionList }" var="option" varStatus="optionStatus">
											
											<input type="radio" 
													name="q${question.questionId }" 
													value="${optionStatus.index }" 
													id="${question.questionId }_${optionStatus.index }"
													<%=DataprocessUtils.determineRedisplay(pageContext) %>
													/>
											<label for="${question.questionId }_${optionStatus.index }">${option }</label>
											
										</c:forEach>
									</c:if>
									
									<c:if test="${question.questionType==1 }">
										<c:forEach items="${question.optionList }" var="option" varStatus="optionStatus">
											
											<input type="checkbox" 
													name="q${question.questionId }" 
													value="${optionStatus.index }" 
													id="${question.questionId }_${optionStatus.index }"
													<%=DataprocessUtils.determineRedisplay(pageContext) %>
													/>
											<label for="${question.questionId }_${optionStatus.index }">${option }</label>
											
										</c:forEach>
									</c:if>
									
									<c:if test="${question.questionType==2 }">
									
										<input type="text" 
												class="form-control" 
												name="q${question.questionId }"
												<%=DataprocessUtils.determineRedisplay(pageContext) %>
												/>
									
									</c:if>
								</td>
							</tr>
						</c:forEach>
						
						<tr>
							<td colspan="2">
								
								<c:if test="${requestScope.CURRENT_INDEX > 0 }">
									<input type="submit" name="submitPrev" value="返回上一个" class="btn btn-primary"/>
								</c:if>
								
								<c:if test="${requestScope.CURRENT_INDEX < sessionScope.LAST_INDEX }">
									<input type="submit" name="submitNext" value="进入下一个" class="btn btn-info"/>
								</c:if>
								
								<input type="submit" name="submitQuit" value="放弃" class="btn btn-danger"/>
								
								<c:if test="${requestScope.CURRENT_INDEX == sessionScope.LAST_INDEX }">
									<input type="submit" name="submitDone" value="完成" class="btn btn-success"/>
								</c:if>
								
							</td>
						</tr>
						
					</table>
				</form>
			</div>
			
		</div>

	</div>

</body>
</html>