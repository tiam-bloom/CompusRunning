<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/10
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="section/import.jsp" %>
</head>
<body>
<%@include file="section/head.jsp" %>
<div class="container" id="container">
    <font color="red">${tips}</font>
    <c:forEach var="d" items="${DEMANDS}">
        <div class="media">
            <div class="media-left media-top">
                <img src="https://static.runoob.com/images/mix/img_avatar.png" class="media-object"
                     style="width:80px">
            </div>
            <div class="media-body">
                <h4 class="media-heading"><a onclick="DisplayUser('${d.id}')" data-toggle="modal" data-target="#Modal_userinfo">${d.name}:</a></h4>
                <h4 align="center">${d.title}</h4>
                <p id="content">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.content}
                </p>
            </div>
            <div>
                <button id="undertake" onclick="undertake(${d.did})" type="button"
                        class="btn btn-primary btn-lg pull-right">接 单 立 赚 ${d.reward} 元!
                </button>
            </div>
        </div>
        <hr>
    </c:forEach>
</div> <!-- /container -->
<!-- 模态框——用户信息查看（Modal） -->
<div class="modal fade" id="Modal_userinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModal_add">
                    用户信息
                </h4>
            </div>
            <div class="modal-body">
                <ul class="list-group">
                    <li id="id" class="list-group-item active"></li>
                    <li id="name" class="list-group-item"></li>
                    <li id="email" class="list-group-item"></li>
                    <li id="tel" class="list-group-item"></li>
                    <li id="address" class="list-group-item"></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    function undertake(did) {
        location.href = "${ctx}/undertakeDemand.action?did=" + did;
    }
    //显示用户信息
    function DisplayUser(id) {
        $.ajax({
            url:"${ctx}/lookUser.action?id="+id,
            success:function (data) {
                $("#id").text("用户唯一ID："+data.id);
                $("#name").text("昵称："+data.name);
                $("#email").text("邮箱："+data.email);
                $("#tel").text("电话："+data.tel);
                $("#address").text("地址："+data.address);
            }
        })
    }
</script>
</body>
</html>
