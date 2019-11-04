<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>订单</title>
		<link rel="stylesheet" type="text/css" href="css/order.css"/>
	</head>
	<body>
	<div class="bg">
		<div class="top">
			<div class="logo">
				<img src="img/logo.jpg" />
			</div>
			<div class="dh">
				<ul>
					<li><a href="buybook.do">图书购买</a></li>
					<li><a href="shopcart.jsp">购物车</a></li>
					<li><a href="order.do?action=show">订单</a></li>
					<li><a href="login.jsp">${userinfo ==null ?'登录' : sessionScope.userinfo.username}</a>
						<a href="Login.do?action=sign">注销</a></li>

				</ul>

			</div>
		</div>
		<div class="bg_table">
		<table>
			<tr>
				<td>订单编号</td>
				<td>下单时间</td>
				<td>支付金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${orderList}" var="order">
			<tr>
				<td>${order.oid}</td>
				<td>${order.createtime}</td>
				<td>${order.total_price}</td>
				<td><a href="order.do?action=detail&oid=${order.oid}">订单详情</a></td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	
	</body>
</html>
