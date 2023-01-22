<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/10
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 发布Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">发布表单</h4>
            </div>
            <form action="${ctx}/publishDemand" method="get" class="messages">
                <div class="modal-body">
                    <div class="messlist">
                        <label>标题</label>
                        <input type="text" name="title" placeholder="请输入标题" maxlength="15"/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist textareas">
                        <label>内容</label>
                        <textarea placeholder="请输入内容(上限800字)" name="content" maxlength="800"></textarea>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>金额</label>
                        <input type="text" name="reward" placeholder="请输入金额" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"/>
                        <div class="clears"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">发布</button>
                    <button type="reset" class="btn btn-danger">重置</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">CampusRunning</a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${ctx}/community" title="点击刷新">Community</a></li>
                <li><a href="#">About</a></li>
            </ul>
            <form class="navbar-form navbar-left" action="${ctx}/findDemandByTitle">
                <div class="form-group">
                    <input type="text" name="title" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎您:</a></li>
                <li><a href="#">${USER_SESSION.username}</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">个人中心<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/publish.action">我发布的</a></li>
                        <li><a href="${ctx}/undertake.action">我承担的</a></li>
                        <li><a href="${ctx}/home.action">个人信息</a></li>
                        <li><a href="${ctx}/logout.action">退出登录</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">new</li>
                        <li><a href="#" data-toggle="modal" data-target="#myModal">我要发布</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>