<%@page import="cn.com.onlicebook.bean.Userinfo"%>
<%@page import="cn.com.onlicebook.biz.impl.UserInfoBizImpl"%>
<%@page import="cn.com.onlicebook.biz.UserInfoBiz"%>
<%@page import="cn.com.onlicebook.bean.Books"%>
<%@page import="cn.com.onlicebook.biz.impl.BooksBizImpl"%>
<%@page import="cn.com.onlicebook.biz.BooksBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   		String useridStr = request.getParameter("userid");
		Integer userid = Integer.valueOf(useridStr);
		UserInfoBiz usersBiz = new UserInfoBizImpl();
		Userinfo user = usersBiz.showUser(userid);
    	request.setAttribute("user", user);
    	request.setAttribute("userid", userid);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改图书</title>
</head>
<body>
<form action="user.do?action=update" method="post">
<input name="user_id" value="${userid}" type="hidden">
<table>
	<tr>
		<td>图书名：</td>
		<td><input type="text" name="username" value="${user.username}"/></td>
	</tr>
	<tr>
		<td>图书价格：</td>
		<td><input type="text" name="password" value="${user.password}"/></td>
	</tr>
	<tr>
		<td>路径：</td>
		<td><input type="text" name="email" value="${user.email}"/></td>
	</tr>
	<tr>
		<td>库存：</td>
		<td><input type="text" name="role" value="${user.role}"/></td>
	</tr>
</table>
<input type="submit" value="修改">
</form>
</body>
</html>