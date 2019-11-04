<%@page import="cn.com.onlicebook.bean.Books"%>
<%@page import="cn.com.onlicebook.biz.impl.BooksBizImpl"%>
<%@page import="cn.com.onlicebook.biz.BooksBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String bidStr = request.getParameter("bid");
		Integer bid = Integer.valueOf(bidStr);
		BooksBiz bookbiz = new BooksBizImpl();
		Books books = bookbiz.showBooks(bid);
		request.setAttribute("books", books);
    %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>图书详情</title>
		<style type="text/css">
			.book_order {
				width: 400px;
				height: 300px;
				margin-top: 20px;
				border: 1px solid red;
			}
			
			.book_order div:first-child {
				width: 200px;
				height: 300px;
				float: left;
			}
			
			.book_order div:nth-child(2) {
				width: 200px;
				height: 300px;
				float: left;
			}
			
			.book_order div:nth-child(2) p {
				line-height: 50px;
			}
			.book_buy{
				width: 150px;
				height: 20px;
				background-color: #66CCFF;
				text-align: center;
			}
			.book_buy a{
				text-decoration: none;
				color: white;
			}
		</style>
	</head>

	<body>
		<div class="bg">
			<div class="book_buy">
				<a href="buybook.do">返回继续购买</a>
			</div>
			<div class="book_order">
				<div>
					<img src="img/buybook/${books.image}" />
				</div>
				<div>
					<p>图书名：${books.bookname}</p>
					<p>图书价格：${books.b_price}</p>
					<p>库存:${books.stock}</p>
				</div>
			
			</div>
			
		</div>
	</body>

</html>