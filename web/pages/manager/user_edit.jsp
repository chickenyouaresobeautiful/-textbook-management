<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <span class="wel_word">用户管理</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>


</div>

<div id="main">
    <form action="studentServlet" method="post">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="action" value="${empty param.id ? "add" : "update"}"/>
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <table>
            <tr>
                <td>用户名</td>
                <td>密码</td>
                <td>名字</td>
                <td>性别</td>
                <td>专业</td>
                <td>年级</td>
                <td>班级</td>
                <td>手机</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="username" value="${requestScope.user.username}" type="text"/></td>
                <td><input name="password" value="${requestScope.user.password}" type="text"/></td>
                <td><input name="name" value="${requestScope.user.name}" type="text"/></td>
                <td><input name="sex" value="${requestScope.user.sex}" type="text"/></td>
                <td><input name="major" value="${requestScope.user.major}" type="text"/></td>
                <td><input name="grade" value="${requestScope.user.grade}" type="text"/></td>
                <td><input name="classId" value="${requestScope.user.classId}" type="text"/></td>
                <td><input name="phone" value="${requestScope.user.phone}" type="text"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>