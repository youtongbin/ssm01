<%--
  Created by IntelliJ IDEA.
  User: 22321
  Date: 2019/1/8
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <form method="post" action="doUpdate.do">
        <input type="hidden" name="id" value="${user.id}">
        姓名：<input type="text" name="username" value="${user.username}" placeholder="输入姓名"><br>
        密码：<input type="password" name="password" value="${user.password}" placeholder="输入密码">
        验证密码：<input type="password" name="passwords" value="${user.password}" placeholder="输再次入密码"><br>
        电话：<input type="text" name="tele" value="${user.tele}" placeholder="输入电话号码"><br>
        <button type="submit">提交</button>

    </form>

</body>
</html>
