<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/10
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="section/import.jsp" %>
    <style type="text/css">
        /* 模态框高度 */
        .upBalance {
            top: 200px;
        }

        .upPassword {
            top: 150px;
        }

        .upUserInfo {
            top: 100px;
        }
    </style>
</head>
<body>
<%@include file="section/head.jsp" %>
<div class="container" id="container">
    <font color="red">${err_msg}</font>
    <font color="green">${success_msg}</font>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">个人信息</div>
        <!-- List group -->
        <ul class="list-group">
            <li class="list-group-item">个人唯一ID：${UserInfo.id}</li>
            <li class="list-group-item">个人昵称：${UserInfo.name}</li>
            <li class="list-group-item">邮箱：${UserInfo.email}</li>
            <li class="list-group-item">联系电话：${UserInfo.tel}</li>
            <li class="list-group-item">地址：${UserInfo.address}</li>
            <li class="list-group-item">账户余额：${UserInfo.balance} 元</li>
            <li class="list-group-item">
                <div class="btn-group" role="group" aria-label="...">
                    <button id="upUserInfo" type="button" class="btn btn-default" data-toggle="modal"
                            data-target=".upUserInfo">编辑资料
                    </button>
                    <button id="upPassword" type="button" class="btn btn-default" data-toggle="modal"
                            data-target=".upPassword">修改密码
                    </button>
                    <button id="upBalance" type="button" class="btn btn-default" data-toggle="modal"
                            data-target=".upBalance">账户充值
                    </button>
                </div>
            </li>
        </ul>
    </div>
</div>
<!-- 修改资料 -->
<div class="modal fade upUserInfo" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑资料</h4>
            </div>
            <form action="${ctx}/upUserInfo.action" method="post" class="messages">
                <div class="modal-body">
                    <div class="messlist">
                        <label>昵称：</label>
                        <input type="text" name="name" value="${UserInfo.name}"/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>邮箱：</label>
                        <input type="text" name="email" value="${UserInfo.email}"/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>电话：</label>
                        <input type="text" name="tel" value="${UserInfo.tel}"/>
                        <div class="clears"></div>
                    </div>

                    <div class="messlist">
                        <label>地址：</label>
                        <input type="text" name="address" value="${UserInfo.address}"/>
                        <div class="clears"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">确认修改</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改密码 -->
<div class="modal fade upPassword" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <form action="${ctx}/upPassword.action" method="post" class="messages">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" id="psw" placeholder="请输入原密码">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password1" class="form-control" id="new_psw" placeholder="请输入新密码">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="new_psw1" placeholder="确认新密码">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">确认修改</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    // 验证密码相同
    $("#new_psw1").blur(function () {
        var p1 = $("#new_psw").val();
        var p2 = $("#new_psw1").val();
        if (p1 != p2){
            alert("请保持两次密码一致!");
            setTimeout("$('#new_psw1').focus()",100);
            return false;
        }else{
            return true;
        }
    })
</script>
<!-- 充值表单 -->
<div class="modal fade upBalance" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">充钱使人强大!</h4>
            </div>
            <form action="${ctx}/charge.action" method="post" class="messages">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="1">1元
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="6">6元
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="18">18元
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="30">30元
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="68">68元
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="118">118元
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="198">198元
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="348">348元
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="balance" value="648" checked>648元
                        </label>
                    </div>
<%--                    <div class="form-group">--%>
<%--                        <input type="text" name="balance" class="form-control" placeholder="自定义金额" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">--%>
<%--                    </div>--%>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">充值</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
