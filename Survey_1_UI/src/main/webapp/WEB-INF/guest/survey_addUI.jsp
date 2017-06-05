<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[创建调查页面]</h4>
			
			<div  class="container">
				<form action="guest/survey/saveSurvey" method="post" class="form-horizontal" enctype="multipart/form-data">
					<c:if test="${requestScope.exception != null }">
						<div class="text-center">${requestScope.exception.message }</div>
					</c:if>
					<!--调查名称输入框  -->
					<div class="form-group">
						<label for="surveyNameInputId" class="col-lg-2 control-label">调查名称</label>
						<div class="col-lg-10">
							<input  id="surveyNameInputId" 
									class="form-control"  
									type="text" 
									name="surveyName" 
									placeholder="请输入调查名"/>
						</div>
					</div>
					<!--调查logo选择框，文件上传框  -->
					<div class="form-group">
						<label for="logoPathInputId" class="col-lg-2 control-label">请选择调查logo</label>
						<div class="col-lg-10">
							<input  id="logoPathInputId" 
									type="file"
									name="logoFile"/>
						</div>
					</div>
					<!-- 保存提交按钮  -->
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">保存调查</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>