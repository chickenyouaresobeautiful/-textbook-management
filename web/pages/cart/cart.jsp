<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>已预定教材</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


</head>
<body>

<div id="header">
    <span class="wel_word">已预定教材</span>
    <div>
        <a href="orderServlet?action=page">我的订单</a>
        <a href="loginServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="pages/user/teacher_login_success.jsp">返回</a>
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
            <td>操作</td>
        </tr>

        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td>
                    <form action="http://localhost:8080/jcgl_Web_exploded/cartServlet?action=updateCount&id=${entry.value.id}"
                          method="post">
                        <input type="text" name="count" value="${entry.value.count}" size="10px">
                        <input type="submit" value="修改">
                    </form>
                </td>
                <td>
                    <form action="http://localhost:8080/jcgl_Web_exploded/cartServlet?action=updateClassId&id=${entry.value.id}"
                          method="post">
                        <input type="text" name="classId" value="${entry.value.classId}" size="10px">
                        <input type="submit" value="修改">
                    </form>
                </td>
                <td>${entry.value.price}</td>
                <td>${entry.value.totalPrice}</td>
                <td>
                    <a href="http://localhost:8080/jcgl_Web_exploded/cartServlet?action=deleteToCart&id=${entry.value.id}">删除</a>
                </td>
            </tr>
        </c:forEach>


    </table>

    <div class="cart_info">
        <span class="cart_span">已预定<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
        <span class="cart_span"><a
                href="http://localhost:8080/jcgl_Web_exploded/cartServlet?action=clearCart">清空</a></span>
        <span class="cart_span"><a
                href="http://localhost:8080/jcgl_Web_exploded/orderServlet?action=createOrder">去结账</a></span>
    </div>

</div>
</body>
</html>