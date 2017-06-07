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
			<h4>[权限列表]</h4>
			<form action="manager/auth/batchDelete" method="post">
				<table class="table table-bordered table-hover text-center">
					<c:if test="${empty authList }">
						<tr>
							<td>尚未创建任何权限</td>
						</tr>
					</c:if>
					<c:if test="${!empty authList }">
					
						<tr>
							<td>权限ID</td>
							<td>权限名称</td>
							<td>删除权限</td>
							<td>分配权限</td>
						</tr>
						
						<c:forEach items="${authList}" var="auth">
							<tr>
								<td>${auth.authId }</td>
								<td>
									<input id="${auth.authId}" type="text" name="authName" value="${auth.authName }" class="form-control"/>
								</td>
								<td>
									<input type="checkbox" name="authIdList" value="${auth.authId }" id="checkboxId${auth.authId }"/>
									<label for="checkboxId${auth.authId }">点我更轻松</label>
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

