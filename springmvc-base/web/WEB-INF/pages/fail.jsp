<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/7/11
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<html>
<head>
  <title>操作失败</title>
  <base href="<%= basePath %>">
</head>
<body>
<h1>操作失败</h1>
</body>
</html>
