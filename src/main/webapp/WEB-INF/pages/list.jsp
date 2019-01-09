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
    <style>
        ul{
            list-style: none;
        }
        li{
            float: left;
        }
        li button{
            height: 25px;
        }
    </style>
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

    <ul>
        <c:choose>
            <c:when test="${page.pages > 0}">
                <c:choose>
                    <c:when test="${page.isFirstPage}">
                        <li><button disabled="disabled">首页</button></li>
                        <li><button disabled="disabled">上一页</button></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="list.do?pageNum=1"><button>首页</button></a></li>
                        <li><a href="list.do?pageNum=${page.prePage}"><button>上一页</button></a></li>
                    </c:otherwise>
                </c:choose>

                <c:forEach items="${page.navigatepageNums}" var="i">
                    <c:choose>
                        <c:when test="${page.pageNum == i}">
                            <li><button disabled="disabled">${i}</button></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="list.do?pageNum=${i}"><button>${i}</button></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


                <c:choose>
                    <c:when test="${page.isLastPage}">
                        <li><button disabled="disabled">下一页</button></li>
                        <li><button disabled="disabled">尾页</button></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="list.do?pageNum=${page.nextPage}"><button>下一页</button></a></li>
                        <li><a href="list.do?pageNum=${page.pages}"><button>尾页</button></a></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>

    </ul>

    <br/>
    <hr/>

    <form action="doUpload.do" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <button type="submit" name="submit">上传</button>
    </form>

    <a href="exit.do"><button>退出</button></a>

</body>
</html>
