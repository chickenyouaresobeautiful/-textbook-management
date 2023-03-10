<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>

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
            <td>书名</td>
            <td>数量</td>
            <td>单价</td>
            <td>总金额</td>
            <td>订单号</td>
        </tr>

        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.total_price}</td>
                <td>${orderItem.order_id}</td>
            </tr>
        </c:forEach>


    </table>
</div>
</body>
</html>