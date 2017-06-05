<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[用户注册页面]</h4>
			<c:if test="${requestScope.exception != null }">
				<div class="text-center">${requestScope.exception.message}</div>
			</c:if>
			<div  class="container">
				<form action="guest/user/regist" method="post" class="form-horizontal">
					<div class="form-group">
						<label for="userNameInputId" class="col-lg-2 control-label">账号</label>
						<div class="col-lg-10">
							<input id="userNameInputId" class="form-control"  type="text" name="userName" placeholder="请输入用户名"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userPwdInputId" class="col-lg-2 control-label">密码</label>
							<div class="col-lg-10">
								<input id="userPwdInputId" class="form-control"  type="password" name="userPwd" placeholder="请输入密码"/>
							</div>
					</div>
					
					<div class="form-group">
						<label for="pwdConfirmInputId" class="col-lg-2 control-label">确认</label>
						<div class="col-lg-10">
							<input id="pwdConfirmInputId" 
								   class="form-control" 
								   type="password" 
								   name="pwdConfrim"
								   placeholder="请确认输入密码"/>
						</div>
					</div>
					
					<div class="form-group" style="text-align: center;">
					
						<input type="radio" name="company" value="true" checked="checked" id="companyTrue"/>
						<label for="companyTrue">企业用户</label>
						<input type="radio" name="company" value="false" id="companyFalse"/>
						<label for="companyFalse">个人用户</label>
					
					</div>
					
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">注册</button>
					</div>
				</form>
			</div>
		</div>
		
	</div>

</body>
</html>