<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教材总览</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("button.addToCart").click(function () {
                var bookId = $(this).attr("bookId");
                $.getJSON("http://localhost:8080/jcgl_Web_exploded/cartServlet", "action=ajaxAddToCart&id=" + bookId, function (data) {
                    $("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品");
                })
            })
        })
    </script>

</head>
<body>

<div id="header">
    <span class="wel_word">教材管理系统</span>
    <div>
        <a href="orderServlet?action=page">我的订单</a>
        <a href="loginServlet?action=logout">注销</a>&nbsp;
        <a href="pages/cart/cart.jsp">已预定教材</a>
        <a href="pages/user/teacher_login_success.jsp">返回</a>
    </div>
</div>

<div id="main">
    <div id="book">

        <div style="text-align: center" id="cartTotalCount">
            <span>您已预定${sessionScope.cart.totalCount}件商品</span>
        </div>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">类别:</span>
                        <span class="sp2">${book.category}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">预定</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

    <%@include file="/pages/common/page_nav.jsp" %>

</div>
</body>
</html>