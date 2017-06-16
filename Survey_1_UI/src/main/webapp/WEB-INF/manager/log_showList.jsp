<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[日志数据]</h4>
			<table class="table table-bordered table-hover text-center">
				<c:if test="${empty page.list }">
					<tr>
						<td>暂时没有日志数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty page.list }">
					
					<tr>
						<td>ID</td>
						<td>Detail</td>
					</tr>
					
					<c:forEach items="${page.list }" var="log">
						
						<tr>
							<td>${log.logId }</td>
							<td>
								操作人：${log.operator }<br/>
								操作时间：${log.operateTime }<br/>
								方法名：${log.methodName }<br/>
								方法所在类型：${log.methodType }<br/>
								输入数据：${log.inputData }<br/>
								输出数据：${log.outputData }<br/>
								异常类型：${log.exceptionType }<br/>
								异常消息：${log.exceptionMessage }
							</td>
						</tr>
						
					</c:forEach>
					
					<tr>
						<td colspan="2">
							<c:set var="targetUrl" value="manager/log/showList" scope="page"/>
							<%@include file="/res_jsp/navigtor.jsp" %>
						</td>
					</tr>
				
				</c:if>
			</table>
		</div>
		
	</div>

</body>
</html>