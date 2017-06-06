<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="manager/admin/toMainUI">在线调查系统后台</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				
				<%-- 判断用户是否登录 --%>
				<%-- session.setAttribute("loginUser",user); --%>
				<c:if test="${sessionScope.loginAdmin==null }">
					<li><a href="manager/admin/toLoginUI">登录</a></li>
				</c:if>
				
				<c:if test="${sessionScope.loginAdmin!=null }">
					<li><a>欢迎您：${sessionScope.loginAdmin.adminName }</a></li>
					<li><a href="manager/admin/logout">退出登录</a></li>
					<li><a href="manager/statitics/showAllAvailable">统计调查</a></li>
					<li><a href="manager/res/showList">资源列表</a></li>
					<li><a href="manager/auth/addAuth">创建权限</a></li>
					<li><a href="manager/auth/showList">权限列表</a></li>
					<li><a href="manager/role/addRole">创建角色</a></li>
					<li><a href="manager/role/showList">角色列表</a></li>
					<li><a href="manager/role/showList">创建账号</a></li>
					<li><a href="manager/role/showList">账号列表</a></li>
					<li><a href="manager/role/showList">日志列表</a></li>
				</c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="manager/admin/toMainUI">首页</a></li>
				<li><a href="index.jsp">前台用户首页</a></li>
            </ul>
          </div>
	</div>
</nav>