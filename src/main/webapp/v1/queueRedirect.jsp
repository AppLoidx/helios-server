<%--
  Created by IntelliJ IDEA.
  User: Arthur
  Date: 18.05.2019
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redirect</title>
</head>
<body>
<%request.getRequestDispatcher("/app/queue.xhtml").forward(request, response);%>

</body>
</html>
