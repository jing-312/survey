<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[更新页面调查]</h4>
			
			<form:form  action="guest/survey/updateSurvey" 
					    method="post" 
						enctype="multipart/form-data" 
						cssClass="form-horizontal"
						modelAttribute="survey">
				<form:hidden path="surveyId"/>
				<form:hidden path="logoPath"/>
				<input type="hidden" name="pageNo" value="${requestScope.pageNo}"/>
				
				
				<c:if test="${requestScope.exception != null}">
					<div class="text-center">${requestScope.exception.message }</div>
				</c:if>
				
				<div class="form-group">
					<label for="surveyNameInputId" class="col-lg-2 control-label">调查名称</label>
					<div class="col-lg-10">
						<form:input 
							id="surveyNameInputId" 
							path="surveyName" 
							cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-2 control-label">当前图片</label>
					<div class="col-lg-10">
						<img src="${requestScope.survey.logoPath}"/>
					</div>
				</div>
				
				<div class="form-group">
					<!-- 巨坑：文件上传框提交的数据不要注入到String类型的logoPath属性中 -->
					<label for="logoPathInputId" class="col-lg-2 control-label">选择图片</label>
					<div class="col-lg-10">
						<input id="logoPathInputId" 
							   type="file" 
							   name="logoFile"/>
					</div>
				</div>
				
				<div style="text-align: center;">
					<button type="submit" class="btn btn-default" style="width: 30%">更新</button>
				</div>
			</form:form>
		</div>
		
	</div>

</body>
</html>