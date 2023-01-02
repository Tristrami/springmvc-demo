<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2022/6/8
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<html>
<head>
  <title>hello</title>
  <base href="<%= basePath %>">
</head>
<body>
  <form action="userVO/param" method="post">
    <label for="name-input">姓名</label>
    <input id="name-input" type="text" name="name"/>
    <br/>

    爱好<br/>
    <label for="hobbies-input1">爱好1</label>
    <input id="hobbies-input1" type="text" name="hobbies[0]"/>
    <br/>
    <label for="hobbies-input2">爱好2</label>
    <input id="hobbies-input2" type="text" name="hobbies[1]"/>
    <br/>

    第一个地址<br/>
    <label for="nation-input1">国家</label>
    <input id="nation-input1" type="text" name="addresses[0].nation"/>
    <label for="province-input1">省</label>
    <input id="province-input1" type="text" name="addresses[0].province"/>
    <label for="city-input1">市</label>
    <input id="city-input1" type="text" name="addresses[0].city"/>
    <br/>

    第二个地址<br/>
    <label for="nation-input2">国家</label>
    <input id="nation-input2" type="text" name="addresses[1].nation"/>
    <label for="province-input2">省</label>
    <input id="province-input2" type="text" name="addresses[1].province"/>
    <label for="city-input2">市</label>
    <input id="city-input2" type="text" name="addresses[1].city"/>
    <br/>

    第一个联系人<br/>
    <label for="contact-name-input1">姓名</label>
    <input id="contact-name-input1" type="text" name="contacts['1001'].name"/>
    <br/>
    <label for="contact-phone-input1">电话</label>
    <input id="contact-phone-input1" type="text" name="contacts['1001'].phoneNumber"/>
    <br/>

    第二个联系人<br/>
    <label for="contact-name-input2">姓名</label>
    <input id="contact-name-input2" type="text" name="contacts['1002'].name"/>
    <br/>
    <label for="contact-phone-input2">电话</label>
    <input id="contact-phone-input2" type="text" name="contacts['1002'].phoneNumber"/>
    <br/>
    <button type="submit">提交</button>
  </form>
</body>
</html>
