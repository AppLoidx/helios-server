<%--
  Created by IntelliJ IDEA.
  User: Arthur
  Date: 16.05.2019
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Просмотр очереди</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/queue/css/main.css'/>">
</head>
<body>
<%--<ul>--%>
    <%--<jsp:useBean id="queueManageBean" class="com.apploidxxx.beans.queue.QueueManageBean"/>--%>
    <%--<c:forEach var="queue" items="${queueManageBean.memberQueues}">--%>
        <%--<li>${queue.name}</li>--%>
    <%--</c:forEach>--%>
<%--</ul>--%>
<div class="header-view" style="background: #c4d3f6;">
    <a href="${pageContext.request.contextPath}/app/queue.xhtml" style="color: black">Назад</a>
</div>
<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table">

                <div class="row header">
                    <div class="cell">
                        Имя очереди
                    </div>
                    <div class="cell">
                        Дата создания
                    </div>
                    <div class="cell">
                        Описание
                    </div>
                    <div class="cell">
                        Количество участников
                    </div>
                </div>
                <jsp:useBean id="queueManageBean" class="com.apploidxxx.beans.queue.QueueManageBean"/>
                <c:forEach var="queue" items="${queueManageBean.memberQueues}">
                    <a href="${pageContext.request.contextPath}${queueManageBean.getRef(queue.name, "app/queue/manage.jsp")}" class="row">

                        <div class="cell" data-title="Name">
                            ${queue.name}
                        </div>
                        <div class="cell" data-title="Creation Date">
                            ${queue.formattedDate}
                        </div>
                        <div class="cell" data-title="Description">
                            ${queue.getFormattedDescription(100)}
                        </div>
                        <div class="cell" data-title="Members">
                            ${queue.members.size()}
                        </div>
                    </a>
                </c:forEach>

            </div>

        </div>
    </div>
</div>
</body>
</html>
