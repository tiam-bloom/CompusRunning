<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/11
  Time: 16:48
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
<!-- 编辑Modal -->
<div class="modal fade" id="upDemand1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">编辑内容</h4>
            </div>
            <form action="${ctx}/upDemand1" method="post" class="messages">
                <div class="modal-body">
                    <div class="messlist">
                        <label>订单编号</label>
                        <input type="text" id="did1" name="did" readonly/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>标题</label>
                        <input type="text" id="title1" name="title" placeholder="请输入标题" maxlength="15"/>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist textareas">
                        <label>内容</label>
                        <textarea placeholder="请输入内容(上限800字)" id="content1" name="content" maxlength="800"></textarea>
                        <div class="clears"></div>
                    </div>
                    <div class="messlist">
                        <label>金额</label>
                        <input type="text" name="reward" id="reward1" placeholder="请输入金额" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"/>
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
<!-- 主体部分表单 -->
<div class="row clearfix">
    <div class="col-md-12 column">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>
                    订单编号
                </th>
                <th>
                    发布人ID
                </th>
                <th>
                    发布人昵称
                </th>
                <th>
                    标题
                </th>
                <th width="550px">
                    内容
                </th>
                <th>
                    赏金
                </th>
                <th>
                    订单状态
                </th>
                <th>
                    承接人ID
                </th>
                <th>
                    操作
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="d" items="${Demands}">
                <tr>
                    <td>${d.did}</td>
                    <td>${d.id}</td>
                    <td>${d.name}</td>
                    <td>${d.title}</td>
                    <td>${d.content}</td>
                    <td>${d.reward}</td>
                    <td>${d.state}</td>
                    <td>${d.ltd}</td>
                    <td>
<%--                        ,'${d.title}','${d.content}','${d.reward}'--%>
                        <a onclick="Deldemand('${d.did}')">删除</a>|
                        <a data-target="#upDemand1" data-toggle="modal"
                           onclick="upDemand2('${d.did}')">修改</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
<script type="text/javascript">
    function Deldemand(did) {
        location.href="/delDemand1.action?did="+did;
    }

    // function upDemand2(did, title, content,reward) {
    //     did1.value = did;
    //     title1.value = title;
    //     content1.value = content;
    //     reward1.value = reward;
    // }

    //显示用户信息
    function upDemand2(did) {
        $.ajax({
            url:"${ctx}/findDemandByDid?did="+did,
            success:function (data) {
                $("#did1").val(data.did);
                $("#title1").val(data.title);
                $("#content1").val(data.content);
                $("#reward1").val(data.reward);
            }
        })
    }
</script>
</body>
</html>
