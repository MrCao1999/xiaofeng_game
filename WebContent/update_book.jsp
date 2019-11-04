<%@page import="cn.com.onlicebook.bean.Books"%>
<%@page import="cn.com.onlicebook.biz.impl.BooksBizImpl"%>
<%@page import="cn.com.onlicebook.biz.BooksBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   		String bidStr = request.getParameter("bid");
		Integer bid = Integer.valueOf(bidStr);
   		BooksBiz bookBiz = new BooksBizImpl();
    	Books books = bookBiz.showBooks(bid);
    	request.setAttribute("books", books);
    	request.setAttribute("bid", bid);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改图书</title>
</head>
<body>
<form action="back.do?action=update" method="post">
<input name="b_id" value="${bid}" type="hidden">
<table>
	<tr>
		<td>图书名：</td>
		<td><input type="text" name="bookname" value="${books.bookname}"/></td>
	</tr>
	<tr>
		<td>图书价格：</td>
		<td><input type="text" name="b_price" value="${books.b_price}"/></td>
	</tr>
	<tr>
		<td>路径：</td>
		<td><input type="text" name="image" value="${books.image}"/></td>
	</tr>
	<tr>
		<td>库存：</td>
		<td><input type="text" name="stock" value="${books.stock}"/></td>
	</tr>
</table>
<input type="submit" value="修改">
</form>
</body>
</html>