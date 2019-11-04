<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="css/items.css"/>
</head>
<body>
	<div>
		<div class="top">
			<div class="logo">
				<img src="img/logo.jpg" />
			</div>
			<div class="dh">
				<ul>
					<li><a href="index.jsp">首页</a></li>
					<li><a href="shopcart.jsp">购物车</a></li>
					<li><a href="order.do?action=show">订单</a></li>
					<li><a href="login.jsp">${userinfo ==null ?'登录' : sessionScope.userinfo.username}</a>
						<a href="Login.do?action=sign">注销</a></li>
				</ul>

			</div>
		</div>
		<div class="item">
			<span>订单编号：${oid}</span>
			<table>
				<tr>
					<td>书籍浏览</td>
					<td>书籍名称</td>
					<td>购买数量</td>
					<td>商品单价</td>
					<td>商品小计</td>
				</tr>
				<c:forEach items="${itemList}" var="item">
					<tr>
						<td><img src="img/buybook/${item.book.image}" /></td>
						<td>${item.book.bookname}</td>
						<td>${item.count}</td>
						<td>${item.book.b_price}</td>
						<td>${item.price}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
