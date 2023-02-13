<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <span class="wel_word">我的订单</span>

    <div>
        <span>欢迎使用教材管理系统</span>
        <a href="loginServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="pages/manager/manager.jsp">返回</a>
    </div>


</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>

        <c:forEach items="${requestScope.page.items}" var="order">
                <tr>
                    <td>${order.create_time}</td>
                    <td>${order.price}</td>
                    <td>
                        <c:if test="${order.status == 0}">
                            已付款
                        </c:if>
                        <c:if test="${order.status == 1}">
                            未付款
                        </c:if>
                    </td>
                    <td><a href="orderServlet?action=showOrderDetails&id=${order.order_id}">查看详情</a></td>
                </tr>
        </c:forEach>


    </table>
    <%@include file="/pages/common/page_nav.jsp" %>
</div>
</body>
</html>