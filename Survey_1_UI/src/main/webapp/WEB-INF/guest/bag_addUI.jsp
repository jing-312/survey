<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[创建包裹页面]</h4>
			<div class="container">
				<form action="guest/bag/saveBag" method="post" class="form-horizontal">
					<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
					<div class="form-group">
						<label for="bagNameInputId" class="col-lg-2 control-label">包裹名称</label>
						<div class="col-lg-10">
							<input id="bagNameInputId" 
								   class="form-control" 
								   type="text" 
								   name="bagName"
								   placeholder="请输入包裹名称"/>
						</div>
					</div>
					
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">保存</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>