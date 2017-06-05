<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- http://localhost:8080/contextPath/guest/user/login -->
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath }/"/>

<!-- 引入bootstrap样式文件 -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!-- 引入我们自己的样式文件 -->
<link rel="stylesheet" type="text/css" href="css/my.css" />

<!-- 引入jQuery -->
<script type="text/javascript" src="js/jquery.min.js"></script>

<!-- bootstrap自身的js文件，bootstrap建议放在页面最后位置，以避免影响页面加载的速度 -->
<script src="js/bootstrap.js"></script>

<title>在线调查系统0829</title>
</head>