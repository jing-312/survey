<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp"%>
<script type="text/javascript">

	$(function(){
		
		//给切换资源状态的按钮绑定单击响应函数
		$(":button").click(function(){
			
			//在单击响应函数中，把“代表当前按钮的this”缓存起来
			var $thisBtn = $(this);
			
			//准备要发送到服务器端的数据
			var resId = this.id;
			
			//准备$.post()函数需要的参数
			var url = "${pageContext.request.contextPath}/manager/res/toggleResStatus";
			var paramData = {"resId":resId,"ajaxFlag":"THIS_IS_AN_AJAX_REQUEST","time":new Date()};
			var callBack = function(respData){
				
				var ajaxInterceptorMessage = respData.ajaxInterceptorMessage;
				if(ajaxInterceptorMessage != null) {
					alert(ajaxInterceptorMessage);
					return ;
				}
				
				//{statusResult: "公共资源", invokeResult: "success"}
				var invokeResult = respData.invokeResult;
				
				if(invokeResult == "success") {
					alert("操作成功！");
					
					//JavaScript中this关键字是调用当前函数的对象的引用，此时this在回调函数中，由jQuery框架中的某个对象调用
					//所以不是当前按钮
					//将当前按钮上的文字修改为statusResult的值
					$thisBtn.val(respData.statusResult);
					
					//将样式修改为对应的值
					$thisBtn.toggleClass("btn-success btn-primary");
					
				}
				
				if(invokeResult == "failed") {
					alert("操作失败！");
				}
				
			};
			var type = "json";
			
			//调用$.post()函数发送Ajax请求
			$.post(url, paramData, callBack, type);
			
		});
		
	});

</script>
<body>

	<div class="container">
		<%@ include file="/res_jsp/manager_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[资源列表]</h4>
			<form action="manager/res/batchDelete" method="post">
				<table class="table table-bordered table-hover text-center">
					
					<c:if test="${empty resList }">
						<tr>
							<td>尚未捕获到资源</td>
						</tr>
					</c:if>
					<c:if test="${!empty resList }">
					
						<tr>
							<td>ID</td>
							<td>ServletPath</td>
							<td colspan="2">操作</td>
						</tr>
						
						<c:forEach items="${resList }" var="res">
							
							<tr>
								<td>${res.resId }</td>
								<td>${res.servletPath }</td>
								<td>
									<c:if test="${res.publicStatus }">
										<!-- button标签会提交表单，影响批量删除功能，所以不能使用，要使用input type="button" -->
										<input id="${res.resId }" type="button" value="公共资源" class="btn btn-success"/>
									</c:if>
									<c:if test="${!res.publicStatus }">
										<input id="${res.resId }" type="button" value="受保护资源" class="btn btn-primary"/>
									</c:if>
								</td>
								<td>
									<input type="checkbox" name="resIdList" value="${res.resId }" id="checkboxId${res.resId }"/>
									<label for="checkboxId${res.resId }">点我更轻松</label>
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