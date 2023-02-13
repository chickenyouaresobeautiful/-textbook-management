<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教材管理系统</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


</head>
<body>

<div id="header">
    <span class="wel_word">教材管理系统</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>


</div>

<div id="main">
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

        <c:forEach items="${requestScope.page.items}" var="teacher">
            <tr>
                <td>${teacher.username}</td>
                <td>${teacher.password}</td>
                <td>${teacher.name}</td>
                <td>${teacher.sex}</td>
                <td>${teacher.major}</td>
                <td>${teacher.grade}</td>
                <td>${teacher.classId}</td>
                <td>${teacher.phone}</td>
                <td><a href="teacherServlet?action=getTeacher&id=${teacher.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a href="teacherServlet?action=delete&id=${teacher.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>


        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/user_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加用户</a></td>
        </tr>
    </table>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>
</body>
</html>