<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[分配角色]</h4>
			<form action="manager/admin/doDispatcher" method="post">
				<input type="hidden" name="adminId" value="${requestScope.adminId }"/>
				<table class="table table-bordered table-hover text-center">
					
					<c:if test="${empty roleList }">
						<tr>
							<td>暂时没有可供分配的角色</td>
						</tr>
					</c:if>
					<c:if test="${!empty roleList }">
					
						<c:forEach items="${roleList }" var="role">
							<tr>
								<td>
									<input type="checkbox" 
											name="roleIdList" 
											value="${role.roleId }" 
											id="checkbox${role.roleId }"
											<c:forEach items="${currentRoleIdList }" var="currentRoleId">
												<c:if test="${currentRoleId == role.roleId }">checked="checked"</c:if>
											</c:forEach>
											/>
									<label for="checkbox${role.roleId }">${role.roleName }</label>
								</td>
							</tr>
						</c:forEach>
						
						<tr>
							<td>
								<input type="submit" value="OK" class="btn btn-default"/>
							</td>
						</tr>
					
					</c:if>
					
				</table>
			</form>
		</div>
		
	</div>

</body>
</html>