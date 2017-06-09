<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[分配权限]</h4>
			<form action="manager/role/dispatcher" method="post">
				<input type="hidden" name="roleId" value="${requestScope.roleId }"/>
				<table class="table table-bordered table-hover text-center">
					
					<c:if test="${empty authList }">
						<tr>
							<td>暂时没有可供分配的权限</td>
						</tr>
					</c:if>
					<c:if test="${!empty authList }">
						<c:forEach items="${authList }" var="auth">
							<tr>
								<td>
									<input type="checkbox" 
											name="authIdList" 
											value="${auth.authId }" 
											id="checkbox${auth.authId }"
											<c:forEach items="${currentAuthIdList }" var="currentRoleId">
												<c:if test="${currentAuthId == auth.authId }">checked="checked"</c:if>
											</c:forEach>
											/>
									<label for="checkbox${auth.authId }">${auth.authName }</label>
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