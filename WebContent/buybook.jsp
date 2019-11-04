
<%@page import="cn.com.onlicebook.bean.Pagetion"%>
<%@page import="java.util.List"%>
<%@page import="cn.com.onlicebook.bean.Books"%>
<%@page import="cn.com.onlicebook.biz.impl.BooksBizImpl"%>
<%@page import="cn.com.onlicebook.biz.BooksBiz"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>图书购买</title>
		<link rel="stylesheet" type="text/css" href="css/sy.css"/>
		<link rel="stylesheet" type="text/css" href="css/buy.css"/>
		<link rel="stylesheet" type="text/css" href="css/jquery_ys.css"/>
		<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
		 <script type="text/javascript">
			$(function(){
				$("#buy a").click(function(){
					var i = parseInt($(".shopping_car1 span").text());
					$(".shopping_car1 span").text(i+1);
				});
				$(function(){
					$("#allbox").on('click',function(){
						$(".checkbox").prop("checked",this.checked);
					});
					$(".checkbox").on('click',function(){
						var $subs = $(".checkbox");
						$("#allbox").prop("checked",$subs.length == $subs.filter(":checked").leng ? true : false);
					});
				});
			});
		</script>
	</head>
	<body onload="pdPage()">
		<div class="bg">
			<jsp:include page="header.jsp"/>
			
			<div class="shopping_car">
				<div class="shopping_car1"><input type="checkbox" id="allbox" >全选</div>
			</div>
			<div class="sou">
				<form action="#" method="post" id="form">
					<input type="text" name="sou" value="${sou == null ? '' : sou}"/>
					<input type="submit" value="搜索"/>
					<input type="hidden" name="pageNo" id="pageNo">
				</form>
			</div>
			<div class="buybook">
				<div class="buybook-1">
				<form action="shopcart.do?action=add" method="post">
					<c:if test="${booksList != null && booksList.size()>0}">
						<c:forEach items="${booksList}" var="books">
							<div class="buybook-1-1">
						<div>
							<img src="img/buybook/${books.image}" />
						</div>
						<div>
							<p>图书名：${books.bookname}</p>
							<p>xxxxxxx</p>
							<p id="buy"><span>${books.b_price}</span><span><a href="#?bid=${books.bid}">立即购买</a></span></p>
							<p><a href="details.jsp?bid=${books.bid}">查看详情</a><input type="checkbox" name="ids" value="${books.bid}" class="checkbox" id="checkbox"></p>
						</div>
					</div>
						</c:forEach>
					</c:if>
					<input type="submit" value="加入购物车" id="add"/>
					</form>
		</div>
	
	
		</div>
		
		<div class="ym">
		当前是第${pagetion.pageNo}页，共${pagetion.totalPage}页&nbsp;
		<a href="javascript:goPage(1)" id="one" >首页</a>&nbsp;
		<a href="javascript:goPage(${pagetion.pageNo - 1})" id="upPage">上一页</a>&nbsp;
		<a href="javascript:goPage(${pagetion.pageNo + 1})" id="downPage">下一页</a>&nbsp;
		<a href="javascript:goPage(${pagetion.totalPage})" id="end">尾页</a>&nbsp;
		<input type="text" id="toPage" size="2" value="${pagetion.pageNo}" size="2">页&nbsp;
		<input type="button" value="go" onclick="toPage();" id="tz">
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
	</div>
		
	</body>
</html>
