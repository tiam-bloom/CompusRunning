<%--
  Created by IntelliJ IDEA.
  User: 30362
  Date: 2021/10/10
  Time: 1:08
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
    <c:forEach var="d" items="${DEMANDS}">
        <div class="media">
            <div class="media-left media-top">
                <img src="https://static.runoob.com/images/mix/img_avatar.png" class="media-object"
                     style="width:80px">
            </div>
            <div class="media-body">
                <h4 class="media-heading">${d.name}：</h4>
                <h4 align="center">${d.title}</h4>
                <p id="content">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${d.content}
                </p>
            </div>
            <div>
                <span id="undertake" class="btn btn-default btn-lg pull-right">已 收 入 ${d.reward} 元</span>
            </div>
        </div>
        <hr>
    </c:forEach>
</div> <!-- /container -->
</body>
</html>
