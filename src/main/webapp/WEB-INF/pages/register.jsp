<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form id="register_fm">
    <table>
        <caption>注册</caption>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" id="username" class="check" placeholder="请输入用户名"></td>
            <td id="uResult"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" id="password" class="check" placeholder="请输入密码"></td>
            <td id="pResult"></td>
        </tr>
        <tr>
            <td>验证密码：</td>
            <td><input type="password" name="passwords" id="passwords" class="check" placeholder="请确认输入"></td>
            <td id="psResult"></td>
        </tr>
        <tr>
            <td>电话：</td>
            <td><input type="text" name="tele" id="tele" class="check"></td>
        </tr>
    </table>
    <button type="button" id="register_btn" disabled="disabled">注册</button>

</form>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.serializejson.min.js"></script>
<script>
    $(function () {

        $(".check").blur(function () {
            var jsonObj = $("#register_fm").serializeJSON();
            var jsonStr = JSON.stringify(jsonObj)
            $.ajax({
                type:"post",
                url:"doRegisterCheck.do",
                contentType:"application/json",
                data:jsonStr,

                success:function (result) {
                    if (result == "usernameIsNull"){
                        // alert("用户名为空")
                        $("#uResult").text("用户名不能为空")
                        $("#pResult").text("")
                        $("#psResult").text("")
                        $("#register_btn").attr("disabled",true)
                    }
                    if (result == "userExists") {
                        // alert("用户名已存在")
                        $("#uResult").text("用户名已存在")
                        $("#pResult").text("")
                        $("#psResult").text("")
                        $("#register_btn").attr("disabled",true)
                    }
                    if (result == "passwordIsNull"){
                        // alert("密码输入为空")
                        $("#uResult").text("√")
                        $("#pResult").text("请输入密码")
                        $("#psResult").text("")
                        $("#register_btn").attr("disabled",true)
                    }
                    if (result == "passwordFail"){
                        // alert("密码验证失败")
                        $("#uResult").text("√")
                        $("#pResult").text("√")
                        $("#psResult").text("请验证密码")
                        $("#register_btn").attr("disabled",true)
                    }
                    if (result == "success") {
                        // alert("验证成功")
                        $("#uResult").text("√")
                        $("#pResult").text("√")
                        $("#psResult").text("√")
                        $("#register_btn").attr("disabled",false)
                    }
                }
            })
        });

        $("#register_btn").click(function () {
            var jsonObj = $("#register_fm").serializeJSON();
            var jsonStr = JSON.stringify(jsonObj)
            $.ajax({
                type:"post",
                url:"doRegister.do",
                contentType:"application/json",
                data:jsonStr,

                success:function (result) {
                    if (result == "success"){
                        alert("注册成功")
                        window.location.href = "login.do"
                    }
                }
            })
        });
    })
</script>

</body>
</html>