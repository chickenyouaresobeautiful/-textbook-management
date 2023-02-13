<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教材出入库状态</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


</head>
<body>

<div id="header">
    <span class="wel_word">教材出入库状态</span>
    <div>
        <a href="loginServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="pages/manager/manager.jsp">返回</a>
    </div>


</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>班级</td>
            <td>单价</td>
            <td>金额</td>
            <td>领取状态</td>
        </tr>

        <c:forEach items="${requestScope.page.items}" var="cartItem">
            <tr>
                <td>${cartItem.name}</td>
                <td>${cartItem.count}</td>
                <td>${cartItem.classId}</td>
                <td>${cartItem.price}</td>
                <td>${cartItem.totalPrice}</td>
                <td>未领取</td>
            </tr>

        </c:forEach>


    </table>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>
</body>
</html>