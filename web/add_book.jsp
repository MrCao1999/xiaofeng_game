<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="back.do?action=add" method="post">
<table>
	<tr>
		<td>图书名：</td>
		<td><input type="text" name="bookname"/></td>
	</tr>
	<tr>
		<td>图书价格：</td>
		<td><input type="text" name="b_price"/></td>
	</tr>
	<tr>
		<td>路径：</td>
		<td><input type="text" name="image" /></td>
	</tr>
	<tr>
		<td>库存：</td>
		<td><input type="text" name="stock" /></td>
	</tr>
	<tr>
		<td>图书状态：</td>
		<td><input type="text" name="state" /></td>
	</tr>
</table>
<input type="submit" value="新增">
</form>
</body>
</html>