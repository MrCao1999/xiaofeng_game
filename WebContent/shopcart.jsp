<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/shopcart.css" />
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/shopcart.js"></script>
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
		<div class="bt">
			<h3>购物车</h3>
		</div>
		<div class="scart">
			<form action="order.do?action=submit" method="post">
				<table>
					<tr>
						<td><input type="checkbox" id="allbox" />全选</td>
						<td>书籍</td>
						<td>图书名</td>
						<td>单价（元）</td>
						<td>数量</td>
						<td>小计</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${cartMap}" var="cart">
						<tr>
							<td><input type="checkbox" name="ids" value="${cart.key}"
								class="checkbox" /></td>
							<td><img src="img/buybook/${cart.value.books.image}" /></td>
							<td>${cart.value.books.bookname}</td>
							<td>￥<span>${cart.value.books.b_price}</span></td>
							<td><a id="reduce">-</a><input type="text"
								value="${cart.value.count}" id="sum"><a id="plus">+</a></td>
							<td>￥<span>${cart.value.totalPrice}</span></td>
							<td><a href="shopcart.do?action=del&bid=${cart.key}"
								id="del">删除</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td><input type="checkbox" id="all" />全选</td>
						<td><a href="#">批量删除</a></td>
						<td colspan="3">已选择1件商品</td>
						<td>总计：${sum}</td>
						<td></td>
					</tr>
				</table>
				<input type="submit" value="购买" id="gm" />
			</form>
		</div>
	</div>
</body>
</html>

