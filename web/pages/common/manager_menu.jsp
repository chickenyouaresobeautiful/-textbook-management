<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <a href="manager/bookServlet?action=page">教材管理</a>
    <a href="studentServlet?action=studentInfo">学生管理</a>
    <a href="teacherServlet?action=teacherInfo">老师管理</a>
    <a href="orderServlet?action=orderInfo">所有订单</a>
    <a href="cartServlet?action=cartInfo">教材出入库管理</a>
    <a href="loginServlet?action=logout">注销</a>
    <a href="pages/manager/manager.jsp">返回</a>
</div>