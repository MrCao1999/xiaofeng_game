<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="user.do?action=add" method="post">
<table>
	<tr>
		<td>用户名：</td>
		<td><input type="text" name="username"/></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input type="text" name="password"/></td>
	</tr>
	<tr>
		<td>邮箱：</td>
		<td><input type="text" name="email" /></td>
	</tr>
	<tr>
		<td>角色：</td>
		<td><input type="text" name="role" /></td>
	</tr>
	<tr>
		<td>状态：</td>
		<td><input type="text" name="state" /></td>
	</tr>
</table>
<input type="submit" value="新增">
</form>
</body>
</html>