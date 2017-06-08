<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/res_jsp/base.jsp" %>
<body>

	<div class="container">
		<%@include file="/res_jsp/manager_top.jsp" %>
		
		<div class="jumbotron">
			<h4>[创建角色]</h4>
			<div class="container">
				<form action="manager/role/saveRole" method="post" role="form" class="form-horizontal">
					<div class="form-group">
						<label for="roleNameInput" class="col-lg-2 control-label">角色名称</label>
						<div class="col-lg-10">
							<input 	type="text" 
									name="roleName" 
									class="form-control" 
									id="roleNameInput" 
									placeholder="填写角色名称">
						</div>
					</div>
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">创建</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>