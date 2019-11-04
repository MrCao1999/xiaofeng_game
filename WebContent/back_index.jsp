<%@page import="cn.com.onlicebook.bean.Userinfo"%>
<%@page import="cn.com.onlicebook.bean.Books"%>
<%@page import="java.util.List"%>
<%@page import="cn.com.onlicebook.bean.Pagetion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Pagetion pagetion = (Pagetion) request.getAttribute("pagetion");
	String sou = (String) request.getParameter("sou");
	List<Books> booksList = pagetion.getList();
	List<Userinfo> userList = pagetion.getList();
	request.setAttribute("booksList", booksList);
	request.setAttribute("userList", userList);
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>书海后台</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
</head>

<body class="layui-layout-body" onload="pdPage()">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">书海后台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				
				<li class="layui-nav-item"><a href="comm.do?action=back">商品管理</a></li>
				<li class="layui-nav-item"><a href="comm.do?action=user">用户管理</a></li>
				<li class="layui-nav-item"><a href="comm.do?action=order">订单管理</a></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> ${sessionScope.userinfo.username}
				</a>
					</li>
				<li class="layui-nav-item"><a href="login.jsp">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">商品</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">修改商品</a>
							</dd>
							<dd>
								<a href="javascript:;">添加商品</a>
							</dd>
							<dd>
								<a href="javascript:;">删除商品</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">用户</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">新增用户</a>
							</dd>
							<dd>
								<a href="javascript:;">修改用户</a>
							</dd>

						</dl></li>

				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="layui-tab">
				<!-- <ul class="layui-tab-title">
					<li class="layui-this"><a href="#">商品管理</a></li>
					<li><a href="commuser.do">用户管理</a></li>
					<li><a href="#">订单管理</a></li>
				</ul> -->
				<div class="layui-tab-content">

					<div class="layui-tab-item layui-show">
						<div class="sou">
							<form action="#" method="post" id="form">
								<input type="text" name="sou" value="${sou == null ? '' : sou}" />
								<input type="submit" value="搜索" /> <input type="hidden"
									name="pageNo" id="pageNo">
							</form>
						</div>
						<p style="margin-bottom: 30px">
							<a href="add_book.jsp">新增图书</a>
						</p>
						<script>
							function del(bid){
								if(confirm("是否确定删除此图书?")){
									//直接调用service，删除学员
									location.href="back.do?action=del&bid="+bid;
								}
							}
						</script>
						<table border="1">
							<tr>
								<th>图书名字</th>
								<th>图书图片</th>
								<th>图书库存</th>
								<th>操作</th>
							</tr>
							<c:if test="${booksList != null && booksList.size()>0}">
								<c:forEach items="${booksList}" var="books">
									<tr>
										<td>${books.bookname}</td>
										<td><img src="img/buybook/${books.image}" /></td>
										<td>${books.stock}</td>
										<td><a href="update_book.jsp?bid=${books.bid}">修改</a>|<a href="javascript:del(${books.bid});">删除</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
						<div class="ym">
							当前是第${pagetion.pageNo}页，共${pagetion.totalPage}页&nbsp; <a
								href="javascript:goPage(1)" id="one">首页</a>&nbsp; <a
								href="javascript:goPage(${pagetion.pageNo - 1})" id="upPage">上一页</a>&nbsp;
							<a href="javascript:goPage(${pagetion.pageNo + 1})" id="downPage">下一页</a>&nbsp;
							<a href="javascript:goPage(${pagetion.totalPage})" id="end">尾页</a>&nbsp;
							<input type="text" id="toPage" size="2"
								value="${pagetion.pageNo}" size="2">页&nbsp; <input
								type="button" value="go" onclick="toPage();">
							<script type="text/javascript">
		function toPage(){
			//获取要跳转的页码，传到处理页面
			var pageNo = document.getElementById("toPage").value;
			goPage(pageNo);
		}
		function goPage(pageNo){
			//先将模糊查询表单中的隐藏域的值设置好
			document.getElementById("pageNo").value = pageNo;
			document.getElementById("form").submit();
		}
		function pdPage(){
			if( ${pagetion.pageNo == 1} ){
				var clone =  document.getElementById("one");
				var clpage = document.getElementById("upPage");
				clone.style = "display: none";
				clpage.style = "display: none";
			}
			if(${pagetion.pageNo == pagetion.totalPage} ){
				var clend =  document.getElementById("end");
				var clpage = document.getElementById("downPage");
				clend.style = "display: none";
				clpage.style = "display: none";
			}
		}
	</script>
						</div>
						<div class="layui-tab-item">
						
						</div>
						<div class="layui-tab-item">内容3</div>
						<div class="layui-tab-item">内容4</div>
						<div class="layui-tab-item">内容5</div>
					</div>
				</div>
			</div>

		</div>
		<script src="layui/layui.js"></script>
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
		</script>
</body>

</html>