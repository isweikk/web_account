<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>AI教育平台</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style>
        .logWindow {
            position: absolute;
            margin:auto;
            top: 0; left: 0; bottom: 0; right: 0;
            width: 40%;
            height: 50%;
            background-color:#eeeeee;
            text-align: center;
        }
    </style>
<%--    <meta http-equiv="refresh" content="0;url=${pageContext.request.contextPath}/toLogin">--%>
</head>

<body>
    <div class="logWindow">
        <br /><br />
        <h1>欢迎来到我的博客</h1>
        <a href="./toLogin"><p style="font-size: 20px;font-weight:normal;">点击登录</p></a>
    </div>
</body>
</html>


