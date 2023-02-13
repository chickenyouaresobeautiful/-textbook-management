<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }
    </style>
</head>
<body>
<div id="header">
    <div>
        <span>欢迎使用教材管理系统</span>
        <a href="studentServlet?action=page&classId=${sessionScope.user.classId}">领取教材</a>
        <a href="loginServlet?action=logout">注销</a>&nbsp;&nbsp;
    </div>
</div>

<div id="main">

    <h1>欢迎回来</h1>
</div>
</body>
</html>