<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>果力智能 - 注册</title>
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
    <script type="text/javascript">
        function change(){
            var img =document.getElementById("verify");
            img.src="user/getVerifyCode?a="+new Date().getTime();
        }
        $(document).ready(function () { //刷新自动更新图片验证码
            change();
        });
    </script>
</head>

<body>
<div class="logWindow">
    <br /><br />
    <h1>注册</h1>
    <br />
    <form action="${pageContext.request.contextPath}/user/regist" method="post">
        <table align = "center">
            <tr>
                <td align="right"><p style="font-size: 16px;">帐号类型：</p></td>
                <td colspan="2"><input type="text" name="userType" value="${userType}" style="width:200px;height:25px;"/></td>
            </tr>
            <tr>
                <td align="right"><p style="font-size: 16px;">输入帐号：</p></td>
                <td colspan="2"><input type="text" name="username" value="${username}" style="width:200px;height:25px;"/></td>
            </tr>
            <tr>
                <td align="right"><p style="font-size: 16px;">输入密码：</p></td>
                <td colspan="2"><input type="password" name="password" value="${password}" style="width:200px;height:25px;"/></td>
            </tr>
            <tr>
                <td align="right"><p style="font-size: 16px;">确认密码：</p></td>
                <td colspan="2"><input type="password" name="rePassword" value="${rePassword}" style="width:200px;height:25px;"/></td>
            </tr>
            <tr>
                <td align="right"><p style="font-size: 16px;">验证码：</p></td>
                <td valign="middle"><input type="text" name="verifycode" style="width:100px;height:25px;"/></td>
                <td width="90" valign="middle"><a href="javascript:change()"><img src="user/getVerifyCode" id="verify" border="0"></a></td>
            </tr>
            <tr><td colspan="3" align="center">
                <input type="submit" value="注册" style="width:130px;height:30px;background-color: aliceblue"/>
            </td></tr>
        </table>
    </form>
    <a href="${pageContext.request.contextPath}/toLogin"><p style="font-size: 16px;font-weight:normal;">已有帐号，点击登录</p></a>
    <p style="font-size: 16px;;color:red;"> ${msg}</p>
</div>
</body>
</html>
