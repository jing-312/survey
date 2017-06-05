<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<script type="text/javascript">
	
	$(function(){
		
		$(":text").change(function(){
			
			var bagOrder = $.trim(this.value);
			
			if(bagOrder == "" || isNaN(bagOrder)) {
				
				this.value = this.defaultValue;
				
				return ;
			}
			
			this.value = bagOrder;
			
		});
		
	});
	
</script>
<body>
	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[调整包裹的顺序]</h4>
			
			<form action="guest/bag/doAdjust" method="post">
			
				<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
				
				<table class="table table-bordered table-hover text-center">
					
					<c:if test="${empty bagList }">
						<tr>
							<td>没有包裹可供显示</td>
						</tr>
					</c:if>
					<c:if test="${!empty bagList }">
					
						<c:if test="${exception != null }">
							<tr>
								<td colspan="3">${requestScope.exception.message }</td>
							</tr>
						</c:if>
						
						<tr>
							<td>ID</td>
							<td>包裹名称</td>
							<td>序号</td>
						</tr>
						
						<c:forEach items="${bagList }" var="bag">
							
							<tr>
								<td>${bag.bagId }</td>
								<td>${bag.bagName }</td>
								<td>
									<input type="hidden" name="bagIdList" value="${bag.bagId }"/>
									<input type="text" name="bagOrderList" value="${bag.bagOrder }" class="form-control"/>
								</td>
							</tr>
							
						</c:forEach>
						
						<tr>
							<td colspan="4">
								<input class="btn btn-default" type="submit" value="OK"/>
							</td>
						</tr>
					
					</c:if>
					
				</table>
				
			</form>
			
		</div>
		
	</div>

</body>
</html>