<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/11
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="section/import.jsp" %>
</head>
<body>
    <%@include file="section/ad_head.jsp" %>
    <!-- 主体部分表单 -->
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>
                        用户ID
                    </th>
                    <th>
                        用户名
                    </th>
                    <th>
                        密码
                    </th>
                    <th>
                        用户昵称
                    </th>
                    <th>
                        邮箱
                    </th>
                    <th>
                        电话
                    </th>
                    <th>
                        地址
                    </th>
                    <th>
                        账户余额
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="u" items="${USERS}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.username}</td>
                        <td>${u.password}</td>
                        <td>${u.userInfo.name}</td>
                        <td>${u.userInfo.email}</td>
                        <td>${u.userInfo.tel}</td>
                        <td>${u.userInfo.address}</td>
                        <td>${u.userInfo.balance}</td>
                        <td>
                            <a onclick="Pdemand('${u.id}')">发布的</a>|
                            <a onclick="Udemand('${u.id}')">承担的</a>|
                            <a onclick="delUser('${u.id}')">删除</a>|
                            <a href="#Model_update" data-toggle="modal"
                               onclick="upUser('${u.id}','${u.username}','${u.password}','${u.userInfo.name}','${u.userInfo.email}','${u.userInfo.tel}','${u.userInfo.address}','${u.userInfo.balance}')">修改</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

<!-- 修改——模态框 -->
<div class="modal fade" id="Model_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModal_update">
                    修改表单
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="${ctx}/upUser.action" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户ID</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="id_up" name="id" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username_up" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username_up" name="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password_up" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="password_up" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username_up" class="col-sm-2 control-label">用户昵称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name_up" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username_up" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="email_up" name="email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username_up" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="tel_up" name="tel">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username_up" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="address_up" name="address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username_up" class="col-sm-2 control-label">账户余额</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="balance_up" name="balance">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                        </button>
                        <button type="submit" class="btn btn-primary">
                            确定修改
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<script>
    function upUser(id, username, password,name,email,tel,address,balance) {
        id_up.value = id;
        username_up.value = username;
        password_up.value = password;
        name_up.value = name;
        email_up.value = email;
        tel_up.value = tel;
        address_up.value = address;
        balance_up.value = balance;
    }
    function delUser(id) {
        location.href="/delUser.action?id="+id;
    }
    function Pdemand(id) {
        location.href="/Pdemand.action?id="+id;
    }
    function Udemand(id) {
        location.href="/Udemand.action?id="+id;
    }
</script>
</body>
</html>
