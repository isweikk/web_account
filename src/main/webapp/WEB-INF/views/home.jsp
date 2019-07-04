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
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
    <div class="logWindow">
        <br /><br />
        <h1>AI教育平台</h1>
        <br />
        <form action="${pageContext.request.contextPath}/user/logout" method="post">
            <input type="submit" value="退出" style="width:130px;height:30px;background-color: aliceblue"/>
        </form>
        <p style="font-size: 16px;;color:red;"> ${msg}</p>
    </div>
</body>
</html>