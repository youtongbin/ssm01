<%--
  Created by IntelliJ IDEA.
  User: 22321
  Date: 2019/1/7
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="insert.do"><button>添加</button></a><br>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>电话</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>${u.tele}</td>
                <td><a href="update.do?id=${u.id}"><button>修改</button></a></td>
                <td><a href="delete.do?id=${u.id}"><button>删除</button></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>
