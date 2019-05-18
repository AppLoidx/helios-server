<%@ page import="com.apploidxxx.entity.Queue" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Arthur
  Date: 16.05.2019
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <jsp:useBean id="queueShowBean" class="com.apploidxxx.beans.queue.QueueShowBean"/>
    ${queueShowBean.init(param.get('queue_name'))}
    <title>Очередь ${queueShowBean.queue.name}"</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/queue/css/util.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/queue/css/main.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/menu.css'/>">
    <style>
        body{
            background: white;
        }

        .button{
            color: #6c7ae0;
        }
    </style>
</head>
<body>
<div class="header-view" style="background: #c4d3f6;">
    <a href="${pageContext.request.contextPath}/app/queue.xhtml" style="color: black">Назад</a>
</div>
<div class="queue-name">
    <h1>Имя очереди: ${queueShowBean.queue.name}</h1>
</div>

<div class="header-view">
    <h3>Администраторы очереди:</h3>
    <br>
    <ul>
        <c:forEach var="user" items="${queueShowBean.queue.superUsers}">
            <li style="list-style: none">${user.firstName} ${user.lastName}</li>
        </c:forEach>
    </ul>
    <br>

</div>
<c:if test="${queueShowBean.superUser}">

    <div class="super_user_controls">
        <a href="${pageContext.request.contextPath}/v1/queueEdit?method=SHUFFLE" class="button" style="color: #6c7ae0" >Перемешать</a>
    </div>
</c:if>
<br>
<div class="user_controls">
    <a href="${pageContext.request.contextPath}/v1/queueEdit?method=LEAVE" class="button" style="color: darkred">Покинуть очередь</a>
</div>
<br><br>


<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table">

                <div class="row header">
                    <div class="cell">
                        Имя
                    </div>
                    <div class="cell">
                        Username
                    </div>
                    <div class="cell">
                        Статус
                    </div>
                    <%--<div class="cell">--%>
                        <%--Количество участников--%>
                    <%--</div>--%>
                </div>
                <c:forEach var="user" items="${queueShowBean.queue.membersList}">
                    <div class="row">

                        <div class="cell" data-title="Name">
                                ${user.firstName} ${user.lastName}
                        </div>
                        <div class="cell" data-title="Creation Date">
                                ${user.username}
                        </div>
                        <div class="cell" data-title="Description" style="color: green">
                                В очереди
                        </div>
                        <%--<div class="cell" data-title="Members">--%>
                                <%--${queue.members.size()}--%>
                        <%--</div>--%>
                    </div>
                </c:forEach>

            </div>

        </div>
    </div>
</div>
</body>
</html>
