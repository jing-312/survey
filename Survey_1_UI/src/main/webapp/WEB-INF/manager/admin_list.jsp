<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$("[name=authName]").change(function(){
			var authId = this.id;
			var authName = this.value;
			
			var url="${pageContext.request.contextPath}/manager/auth/updateAuth";
			var paramData={"authId":authId,"authName":authName,"time":new Date()};
			var callBack=function(respData){
				var message = respData.message;
				if(message == "success"){
					alert("更新权限名称成功！");
				}
				if(message == "failed"){
					alert("更新权限名称失败！");
				}
			}
			var type="json";
			
			$.post(url,paramData,callBack,type);
		});		
	});
</script>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[账号列表]</h4>
			<form action="manager/admin/batchDelete" method="post">
				<table class="table table-bordered table-hover text-center">
					<c:if test="${empty adminList }">
						<tr>
							<td>尚未创建任何权限</td>
						</tr>
					</c:if>
					<c:if test="${!empty adminList }">
					
						<tr>
							<td>账号ID</td>
							<td>账号名称</td>
							<td>删除账号</td>
							<td>分配账号</td>
						</tr>
						
						<c:forEach items="${adminList}" var="admin">
							<tr>
								<td>${admin.adminId }</td>
								<td>
									<input id="${admin.adminId}" type="text" name="adminName" value="${admin.adminName }" class="form-control"/>
								</td>
								<td>
									<input type="checkbox" name="adminIdList" value="${admin.adminId }" id="checkboxId${admin.adminId }"/>
									<label for="checkboxId${admin.adminId }">点我更轻松</label>
								</td>
								
								<td>
									<a class="btn btn-default" href="manager/admin/toDispatcherUI/${admin.adminId}">权限分配资源</a>
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

