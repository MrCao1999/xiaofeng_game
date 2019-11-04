package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.biz.BooksBiz;
import cn.com.onlicebook.biz.impl.BooksBizImpl;

@WebServlet("/back.do")
public class BackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取流对象之前，则应该把响应的内容类型设定好
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if("add".equals(action)){
			String bookname = request.getParameter("bookname");
			String b_priceStr = request.getParameter("b_price");
			String image = request.getParameter("image");
			String stockStr = request.getParameter("stock");
			String stateStr = request.getParameter("state");
			Double b_price =  Double.valueOf(b_priceStr);
			Integer stock =  Integer.valueOf(stockStr);
			Integer state =  Integer.valueOf(stateStr);
			Books books = new Books(bookname, b_price, image, stock,state);
			
			BooksBiz  bookBiz = new BooksBizImpl();
			int result = bookBiz.addBooks(books);
			if (result > 0) {
				out.print("<script>");
				out.print("alert('新增成功！');");
				out.print("location.href = 'comm.do?action=back';");
				out.print("</script>");
			} else {
				out.print("<script>");
				out.print("alert('新增失败！');");
				out.print("location.href = 'add_book.jsp';");
				out.print("</script>");
			}
		}else if("update".equals(action)){
			String bidStr = request.getParameter("b_id");
			String bookname = request.getParameter("bookname");
			String b_priceStr = request.getParameter("b_price");
			String image = request.getParameter("image");
			String stockStr = request.getParameter("stock");
			
			Integer bid =  Integer.valueOf(bidStr);
			Double b_price =  Double.valueOf(b_priceStr);
			Integer stock =  Integer.valueOf(stockStr);
			
			Books books = new Books(bid, bookname, b_price, image, stock);
			
			BooksBiz  bookBiz = new BooksBizImpl();
			int result = bookBiz.updateBooks(books);
			if(result > 0) {
				out.print("<script>");
				out.print("alert('修改成功！');");
				out.print("location.href = 'comm.do?action=back';");
				out.print("</script>");
			}else {
				out.print("<script>");
				out.print("alert('修改失败！');");
				out.print("location.href = 'update_book.jsp';");
				out.print("</script>");
			}
		}else if("del".equals(action)){
			String bidStr = request.getParameter("bid");
			Integer bid = Integer.valueOf(bidStr);
			
			BooksBiz bookbiz = new BooksBizImpl();
			int result = bookbiz.delBooks(bid);
			if(result >0){
				out.print("<script>");
				out.print("alert('删除成功！');");
				out.print("location.href = 'comm.do?action=back';");
				out.print("</script>");
			}else{
				out.print("<script>");
				out.print("alert('删除失败！');");
				out.print("location.href = 'comm.do?action=back';");
				out.print("</script>");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
