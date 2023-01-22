<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/9
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <!--图标库 可无视-->
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
    <!--核心样式-->
    <link rel="stylesheet" href="${ctx}/resources/css/style.css">
    <script src="${ctx}/resources/js/jquery-3.6.0.js"></script>
    <script src="${ctx}/resources/js/script.js" defer></script>
</head>
<body>
<div class="container">
    <div class="card"></div>
    <!-- 注册表单 -->
    <div class="card">
        <h1 class="title">用户登录</h1>
        <form action="${ctx}/login.action" method="post">
            <div class="input-container">
                <font color="red" id="login_msg">${login_msg}</font>
                <font color="green" id="register_msg">${register_msg}</font>
                <input type="text" id="username" name="username" required="required"/>
                <label for="username">账号</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="password" id="password" name="password" required="required"/>
                <label for="password">密码</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" value="" id="CheckCode" class="input-val" style="width:228px" required="required"/>
                <label for="CheckCode">验证码</label>
                <canvas id="canvas" width="100" height="50"></canvas>
                <div class="bar"></div>
                <font color="red" id="msg" style="display: none;">验证码错误！请重新输入！</font>
            </div>
            <div class="button-container">
                <button type="submit" class="btn"><span>登录</span></button>
            </div>
        </form>
    </div>
    <!-- 注册表单 -->
    <div class="card alt">
        <div class="toggle"></div>
        <h1 class="title">会员注册
            <div class="close"></div>
        </h1>
        <form action="${ctx}/register.action" method="post">
            <div class="input-container">
                <input type="text" id="name" name="name" required="required" maxlength="8"/>
                <label for="name">会员昵称</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="username1" name="username" required="required" maxlength="15"/>
                <label for="username1">账号</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="password1" name="password" required="required" maxlength="15"/>
                <label for="password1">密码</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="password2" required="required" maxlength="15"/>
                <label for="password2">确认密码</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="email" name="email" required="required" maxlength="15"/>
                <label for="email">邮箱</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="tel" name="tel" required="required" maxlength="11"/>
                <label for="tel">电话</label>
                <div class="bar"></div>
            </div>
            <div class="input-container">
                <input type="text" id="address" name="address" required="required" maxlength="15"/>
                <label for="address">地址</label>
                <div class="bar"></div>
            </div>
            <div class="button-container">
                <button type="submit"><span>提交注册</span></button>
            </div>
        </form>
    </div>
</div>

<script>
    // 账号长度大于5
    $("#username1").blur(function () {
        var u1 = $("#username1").val();
        if (u1.length < 5){
            alert("请输入账号长度大于5");
            setTimeout("$('#username1').focus()",100);
            return false;
        }else {
            return true;
        }
    })
    // 密码长度大于6位
    $("#password1").blur(function () {
        var p1 = $("#password1").val();
        if (p1.length < 6){
            alert("密码长度应大于6位");
            setTimeout("$('#password1').focus()",100);
            return false;
        }else {
            return true;
        }
    })
    // 验证密码相同
    $("#password2").blur(function () {
        var p1 = $("#password1").val();
        var p2 = $("#password2").val();
        if (p1 != p2){
            alert("请保持两次密码一致!");
            setTimeout("$('#password2').focus()",100);
            return false;
        }else{
            return true;
        }
    })
    // 邮箱效验
    $("#email").blur(function () {
        var email = $("#email").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if(!myreg.test(email))
        {
            alert('提示\n\n请输入有效的邮箱!');
            setTimeout("$('#email').focus()",100);
            return false;
        }

    })
    // 手机号效验
    $("#tel").blur(function(){
        var mobile=$("#tel").val();
        var phone = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
        if(mobile.length == 11){//手机号码
            if(phone.test(mobile)) {
                return true;
            }
        }else {
            alert('请输入11位有效的号码！');
            setTimeout("$('#tel').focus()",100);
            return false;
        }
    })
</script>
</body>
</html>
