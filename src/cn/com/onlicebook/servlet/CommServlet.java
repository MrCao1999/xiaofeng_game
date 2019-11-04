package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.onlicebook.bean.Pagetion;
import cn.com.onlicebook.biz.BooksBiz;
import cn.com.onlicebook.biz.OrderBiz;
import cn.com.onlicebook.biz.UserInfoBiz;
import cn.com.onlicebook.biz.impl.BooksBizImpl;
import cn.com.onlicebook.biz.impl.OrderBizImpl;
import cn.com.onlicebook.biz.impl.UserInfoBizImpl;

@WebServlet("/comm.do")
public class CommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		if("back".equals(action)){
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
			request.getRequestDispatcher("back_index.jsp").forward(request,response);
		}else if("user".equals(action)){
			String sou = request.getParameter("sou");
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sou", sou);
			int pageNo = 1;//定义当前第几页
			int pageSize = 4;//定义每页显示多少条数据
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
			
			UserInfoBiz usersBiz = new UserInfoBizImpl();
			pagetion = usersBiz.listByPage(pagetion, param);
			request.setAttribute("pagetion", pagetion);
			request.setAttribute("sou", sou);
			request.getRequestDispatcher("back_index_user.jsp").forward(request,response);
		}else if("order".equals(action)){
			String sou = request.getParameter("sou");
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sou", sou);
			int pageNo = 1;//定义当前第几页
			int pageSize = 4;//定义每页显示多少条数据
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
			
			OrderBiz orderbiz = new OrderBizImpl();
			pagetion = orderbiz.listByPage(pagetion, param);
			request.setAttribute("pagetion", pagetion);
			request.setAttribute("sou", sou);
			request.getRequestDispatcher("back_index_order.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
