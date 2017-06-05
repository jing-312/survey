<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	$(function(){
		$("#goTo").change(function(){
			//去除前后空格
			var pageNo = $.trim(this.value);
			//检查pageNo是否是空字符串或非数字
			//NaN:not a number
			//isNaN:检查是否是非数字
			if(pageNo == "" || isNaN(pageNo)) {
				//将文本框中的数据恢复为默认值
				this.value = this.defaultValue;
				//函数停止执行
				return ;
			}
			//执行页面跳转
			window.location.href = "${pageContext.request.contextPath}/${pageScope.targetUrl }?pageNoStr="+pageNo;
		});
	});

</script>
<nav>
	<ul class="pager">
		<c:if test="${page.hasPrev }">
			<li><a href="${pageScope.targetUrl }?pageNoStr=1">首页</a></li>
			<li><a href="${pageScope.targetUrl }?pageNoStr=${page.prev }">上一页</a></li>
		</c:if>
		
		<li>★当前是第${page.pageNo }页</li>
		<li>共${page.totalPageNo }页</li>
		<li>共${page.totalRecordNo }条记录 </li>
		<li>跳转到第<input type="text" id="goTo"/>页★</li>

		<c:if test="${page.hasNext }">
			<li><a href="${pageScope.targetUrl }?pageNoStr=${page.next }">下一页</a></li>
			<li><a href="${pageScope.targetUrl }?pageNoStr=${page.totalPageNo }">末页</a></li>
		</c:if>
	</ul>
</nav>