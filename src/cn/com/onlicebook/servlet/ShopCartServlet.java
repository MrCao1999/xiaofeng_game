package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.ShopCart;
import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.BooksBiz;
import cn.com.onlicebook.biz.impl.BooksBizImpl;

/**
 * Servlet implementation class ShopCartServlet
 */
@WebServlet("/shopcart.do")
public class ShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BooksBiz bookBiz = new BooksBizImpl();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		//从Map中取出原有的购物车
		Map<Integer, Object> cartMap = (Map<Integer, Object>)session.getAttribute("cartMap");
		if(cartMap == null){
			//如果没有购物车，则创建一个
			cartMap = new HashMap<>();
		}
		if("add".equals(action)) {//如果是新增购物车
			//判断用户是否登录
			Userinfo user = (Userinfo) session.getAttribute("userinfo");
			String check = request.getParameter("ids");
			if(user == null){
				out.print("<script>alert('账户为登录，请先登录');location.href='login.jsp';</script>");
				
			}else if(check == null){
				out.print("<script>alert('请先选中商品');location.href='buybook.do';</script>");
				
			}
			else{
				//获取所有需要新增到购物车的书本id
				String[] idsArr = request.getParameterValues("ids");
				for(String idStr : idsArr) {//变量需要新增的书本的id
					Integer bid = Integer.valueOf(idStr);
					//直接去除购物车中的数据
					ShopCart cart = (ShopCart)cartMap.get(bid);
					if(cart == null){
						cart = new ShopCart();
					}
					//获取需要新增到购物车的数据信息
					Books book = cart.getBooks();
					if(book == null){
						book = bookBiz.showBooks(bid);
						cart.setBooks(book);
					}
					
					//数量
					Integer count = cart.getCount();
					if(count == null){
						count = 1;
					}else{
						count = count + 1;
					}
					
					cart.setCount(count);
					//小计.书本的单价*数量
					Double totalPrice = book.getB_price()*count;
					cart.setTotalPrice(totalPrice);
					//创建一条购物车实体
					
					cartMap.put(bid, cart);
				}
				//计算总价格
				double sumPrice = 0.0;
				for(Integer id: cartMap.keySet()){
					sumPrice += ((ShopCart)cartMap.get(id)).getTotalPrice();
				}
				//将购物车保存到session中
				session.setAttribute("cartMap", cartMap);
				session.setAttribute("sum", sumPrice);
				//直接重定向到购物车页码
				response.sendRedirect("shopcart.jsp");
			}
		}else if("del".equals(action)){//如果是删除
			//获取需要删除的书本id
			String idStr = request.getParameter("bid");
			Integer bid = Integer.valueOf(idStr);
			//直接移除购物车的数据
			cartMap.remove(bid);
			//计算总价格
			double sumPrice = 0.0;
			for(Integer id: cartMap.keySet()){
				sumPrice += ((ShopCart)cartMap.get(id)).getTotalPrice();
			}
			//将购物车保存到session中
			session.setAttribute("cartMap", cartMap);
			session.setAttribute("sum", sumPrice);
			//直接重定向到购物车页码
			response.sendRedirect("shopcart.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
