<%--
  Created by IntelliJ IDEA.
  User: Arthur
  Date: 13.05.2019
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WARNING</title>
    <style>
        @import url('https://fonts.googleapis.com/css?family=Just+Another+Hand|Lobster');

        body{
            /*background: #ffffff url("resources/67138867_p0.png") no-repeat fixed top;*/
            /*background-size: ;*/
        }
        .success-text{
            transform: translate(-50%, -50%);
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            width: 100%;
            padding: 40px;
            background: #000000;
        }
        .success-text > p{
            color: white;
            font-family: 'Just Another Hand', 'Lobsters', sans-serif;
            font-size: 60px;
        }
    </style>
</head>
<body>
    <div class="success-text">
        <p><%=request.getAttribute("message")%></p>
    </div>
</body>
</html>
