<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[用户登录页面]</h4>
			<c:if test="requestScope.exception != null ">
				<div class="text-center>${requestScope.exception.message}</div>			
			</c:if>
			<div  class="container">
				<form action="guest/user/login" method="post" class="form-horizontal">
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
				
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">登录</button>
					</div>
				</form>
			</div>
		</div>
		
	</div>

</body>
</html>