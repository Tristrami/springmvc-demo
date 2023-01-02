<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/6/4
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<html>
<head>
  <title>注册</title>
  <base href="<%= basePath %>"/>
</head>
<body>
<form action="userVO/save3" method="post">
  <label for="username-input">用户名</label>
  <input id="username-input" name="username" type="text"/>
  <label for="password-input">密码</label>
  <input id="password-input" name="password" type="password"/>
  <input id="submit-button" type="submit" value="提交"/>
</form>
</body>
</html>
