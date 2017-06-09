<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/res_jsp/base.jsp" %>
<body>
	<div class="container">
		<%@include file="/res_jsp/manager_top.jsp" %>
		
		<div class="jumbotron">
			<h4>分配资源</h4>
			<form action="manager/auth/dispatcher" method="post">
				<input type="hidden" name="authId" value="${authId }"/>
				<table class="table table-bordered table-hover text-center">
					<c:if test="${empty resList }">
						<tr>
							<td>暂时没有资源</td>
						</tr>
					</c:if>
					<c:if test="${!empty resList }">
						<c:forEach items="${resList }" var="res">
							<tr>
								<td>
									<input id="checkbox${res.resId }" type="checkbox" name="resIdList" value="${res.resId }"
										<c:forEach items="${currentIdList }" var="currentId">
											<c:if test="${currentId==res.resId }">checked="checked"</c:if>
										</c:forEach>
									/>
									<label for="checkbox${res.resId }">${res.servletPath }</label>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td><button type="submit" class="btn btn-default" style="width: 30%">OK</button></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>