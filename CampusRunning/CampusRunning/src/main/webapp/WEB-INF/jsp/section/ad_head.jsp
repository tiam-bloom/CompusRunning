<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/11
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 导航栏 -->
<div class="row clearfix">
    <div class="col-md-12 column">
        <nav class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand">校园跑后台管理系统</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${ctx}/admin">User</a>
                    </li>
                    <li>
                        <a href="${ctx}/admin_demand">Demand</a>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search" action="${ctx}/findUser" method="get">
                    <div class="form-group">
                        <input name="name" type="text" class="form-control" placeholder="请输入账号或用户昵称"/>
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#">欢迎你:${USER_SESSION.username}</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心<strong
                                class="caret"></strong></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${ctx}/logout.action">注销</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>