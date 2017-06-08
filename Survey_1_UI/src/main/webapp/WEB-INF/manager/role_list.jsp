<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$("[name='roleName']").change(function(){
			alert($);

		});
	});
</script>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[权限列表]</h4>
			<form action="manager/role/batchDelete" method="post">
				<table class="table table-bordered table-hover text-center">
					<c:if test="${empty roleList }">
						<tr>
							<td>尚未创建任何权限</td>
						</tr>
					</c:if>
					<c:if test="${!empty roleList }">
						<tr>
							<td>权限ID</td>
							<td>权限名称</td>
							<td>删除权限</td>
							<td>分配权限</td>
						</tr>
						<c:forEach items="${roleList}" var="role">
							<tr>
								<td>${role.roleId }</td>
								<td>
									<input id="${role.roleId}" type="text" name="roleName" value="${role.roleName }" class="form-control"/>
								</td>
								<td>
									<input type="checkbox" name="roleIdList" value="${role.roleId }" id="checkboxId${role.roleId }"/>
									<label for="checkboxId${role.roleId }">点我更轻松</label>
								</td>
								
								<td>
									<a class="btn btn-default" href="#">权限分配资源</a>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4">
								<input type="submit" value="批量删除" class="btn btn-warning"/>
							</td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>

