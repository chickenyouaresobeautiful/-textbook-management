<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">结算</span>
		<div>
			<a href="orderServlet?action=page">我的订单</a>
			<a href="loginServlet?action=logout">注销</a>&nbsp;&nbsp;
			<a href="pages/user/teacher_login_success.jsp">返回</a>
		</div>




	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		
	
	</div>
</body>
</html>