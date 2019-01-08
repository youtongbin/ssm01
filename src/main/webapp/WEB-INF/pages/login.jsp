<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="doLogin.do" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username" value=""></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password" value=""></td>
            </tr>
        </table>
        <label><input type="radio" name="save" value="Save" checked="checked">保存密码</label>
        <label><input type="radio" name="save" value="noSave">取消保存</label><br>
        <button type="submit" name="submit">登录</button>
    </form>

</body>
</html>