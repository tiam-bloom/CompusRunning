<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/10
  Time: 1:03
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
<!-- 编辑Modal -->
<div class="modal fade" id="upDemand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑内容</h4>
            </div>
            <form action="${ctx}/upDemand" method="post" class="messages">
                <div class="modal-body">
                    <div class="messlist" style="display: none">
                        <label>订单编号</label>
                        <input type="text" id="did" name="did" readonly/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>标题</label>
                        <input type="text" id="title" name="title" placeholder="请输入标题" maxlength="15"/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist textareas">
                        <label>内容</label>
                        <textarea placeholder="请输入内容(上限800字)" id="content1" name="content" maxlength="800"></textarea>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>金额</label>
                        <input type="text" name="reward" id="reward" placeholder="请输入金额" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"/>
                        <div class="clears"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">确定修改</button>
                    <button type="reset" class="btn btn-danger">重置</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container" id="container">
    <font color="red">${err_msg}</font>
    <font color="green">${success_msg}</font>
    <c:forEach var="d" items="${DEMANDS}">
        <div class="media">
            <div class="media-left media-top">
                <img src="https://static.runoob.com/images/mix/img_avatar.png" class="media-object"
                     style="width:80px">
            </div>
            <div class="media-body">
                <h5 class="media-heading">${d.name}：</h5>
                <h4 align="center">${d.title}</h4>
                <p id="content">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.content}
                </p>
            </div>
            <div>
                <div class="btn-group btn-group-lg pull-right" role="group" aria-label="...">
                    <span class="btn btn-default">金额:${d.reward}元</span>
                    <button type="button" onclick="DisplayDemand('${d.did}')" class="btn btn-info" data-toggle="modal" data-target="#upDemand">编辑</button>
                    <button type="button" onclick="delDemand('${d.did}')" class="btn btn-danger">删除</button>
                </div>
            </div>
        </div>
        <hr>
    </c:forEach>
</div> <!-- /container -->
<script>
    function delDemand(did) {
        location.href="${ctx}/delDemand.action?did="+did
    }
    function DisplayDemand(did) {
        $.ajax({
            url:"${ctx}/findDemandByDid?did="+did,
            success:function (data) {
                $("#did").val(data.did);
                $("#title").val(data.title);
                $("#content1").val(data.content);
                $("#reward").val(data.reward);
            }
        })
    }
</script>
</body>
</html>
