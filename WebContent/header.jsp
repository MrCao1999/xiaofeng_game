<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="top">
				<div class="logo">
					<img src="img/logo.jpg" />
				</div>
				<div class="dh">
					<ul>
						<li>
							<a href="index.jsp">首页</a>
						</li>
						<li>
							<a href="buybook.do">图书购买</a>
						</li>
						<li>
							<a href="ebook.jsp">电子图书</a>
						</li>
						<li>
							<a href="network.jsp">网络文学</a>
						</li>
						<li>
							<a href="shopcart.jsp">购物车</a>
						</li>
						<li>
							<a href="order.do?action=show">订单</a>
						</li>
						<li>
							<a href="login.jsp">${userinfo ==null ?'登录' : sessionScope.userinfo.username}</a>
							<a href="Login.do?action=sign">注销</a>
						</li>
						
					</ul>

				</div>
			</div>
</body>
</html>