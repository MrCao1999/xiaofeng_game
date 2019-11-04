<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册</title>
		<link rel="stylesheet" type="text/css" href="css/register.css"/>
		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="js/messages_zh.js"></script>
		<script type="text/javascript" src="js/yz.js"></script>
		
	</head>
	<body>
		<div class="bg">
			<div class="login">
				
				<form action="register.do" method="post" id="checkForm">
					<p><span>用户名：</span><input type="text" name="username"/><label for="username" class="error" style="display: none;"></label><br /></p>
					<p><span>密码：</span><input type="password" name="pwd" id="pwd"/><label for="pwd" class="error" style="display: none;"></label><br /></p>
					<p><span>确认密码：</span><input type="password" name="repwd" /><label for="repwd" class="error" style="display: none;"></label><br /></p>
					<p><span>邮箱：</span><input type="email" name="email" /><label for="email" class="error" style="display: none;"></label><br /></p>
					<input type="submit" value="立即注册"/>
					<p>已有账号？立即<a href="login.jsp">登录</a></p>
				</form>
			</div>
		</div>
	</body>
</html>