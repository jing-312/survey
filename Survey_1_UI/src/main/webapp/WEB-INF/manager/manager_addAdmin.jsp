<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/manager_top.jsp" %>
		
		<div class="jumbotron">
			<h4>[创建账号]</h4>
			<div class="container">
				<form action="manager/admin/saveAdmin" method="post" role="form" class="form-horizontal">
					<div class="form-group">
						<label for="adminNameInput" class="col-lg-2 control-label">账号名称</label>
						<div class="col-lg-10">
							<input 	type="text" 
									name="adminName" 
									class="form-control" 
									id="adminNameInput" 
									placeholder="填写账号名称">
						</div>
					</div>
					<div class="form-group">
						<label for="adminPwdInput" class="col-lg-2 control-label">密码</label>
						<div class="col-lg-10">
							<input  type="password" 
									name="adminPwd" 
									class="form-control" 
									id="adminPwdInput" 
									placeholder="输入密码">
						</div>
					</div>
			<!-- 		<div class="form-group">
						<label for="adminPwdConfirmInput" class="col-lg-2 control-label">确认</label>
						<div class="col-lg-10">
							<input  type="password" 
									name="pwdConfirm" 
									class="form-control" 
									id="adminPwdConfirmInput"
									placeholder="确认密码">
						</div>
					</div> -->
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">创建</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>