package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.biz.BooksBiz;
import cn.com.onlicebook.biz.impl.BooksBizImpl;


/**
 * Servlet implementation class BuybookServlet
 */
@WebServlet("/buybook.do")
public class BuybookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String sou = request.getParameter("sou");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("sou", sou);
		int pageNo = 1;//定义当前第几页
		int pageSize = 6;//定义每页显示多少条数据
		//接收要翻到的页码
		String pageNoStr = request.getParameter("pageNo");
		//为了避免用于输入非数据的异常，所以加上try-catch进行异常捕获
		try{
			if(pageNoStr != null && !"".equals(pageNoStr)){
				pageNo = Integer.valueOf(pageNoStr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Pagetion pagetion = new Pagetion();
		pagetion.setPageNo(pageNo);
		pagetion.setPageSize(pageSize);
		
		BooksBiz bookBiz = new BooksBizImpl();
		pagetion = bookBiz.listByPage(pagetion, param);
		request.setAttribute("pagetion", pagetion);
		request.setAttribute("sou", sou);
		List<Books> booksList = pagetion.getList();
   		request.setAttribute("booksList", booksList);
		request.getRequestDispatcher("buybook.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
