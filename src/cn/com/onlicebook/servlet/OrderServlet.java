package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.onlicebook.bean.Items;
import cn.com.onlicebook.bean.Orders;
import cn.com.onlicebook.bean.ShopCart;
import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.OrderBiz;
import cn.com.onlicebook.biz.impl.OrderBizImpl;

@WebServlet("/order.do")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		OrderBiz orderBiz = new OrderBizImpl();
		String check = request.getParameter("ids");
		PrintWriter out = response.getWriter();
		
		//获取session，以便从中获取购物车
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		 if(check == null){
				out.print("<script>alert('请先选中商品');location.href='shopcart.jsp';</script>");
			}
		if("submit".equals(action)){
			//获取需要购买书籍的id
			String [] idArr = request.getParameterValues("ids");
			//获取购物车
			Map<Integer,Object> cartMap = (Map<Integer, Object>) session.getAttribute("cartMap");
			//获取购买的用户，也就是当前登录用户
			Userinfo user = (Userinfo) session.getAttribute("userinfo");
			//调用BIZ进行处理
			Boolean flag = orderBiz.sumbmitOrder(idArr, cartMap, user);
			if(flag){
				//还需要将购物车中已经购买的商品移除
				for(String idStr : idArr) {
					//循环遍历，需要购买的书籍id
					Integer bid = Integer.valueOf(idStr);
					//从购物车中移除
					cartMap.remove(bid);
					double sumPrice = 0.0;
					for(Integer id: cartMap.keySet()){
						sumPrice += ((ShopCart)cartMap.get(id)).getTotalPrice();
					}
					//将购物车保存到session中
					session.setAttribute("cartMap", cartMap);
					session.setAttribute("sum", sumPrice);
				}
				response.sendRedirect("order.do?action=show");
			}else{
				response.sendRedirect("buybook.do");
			}
		}else if("show".equals(action)){
			//获取购买的用户，也就是当前登录用户
			Userinfo user = (Userinfo) session.getAttribute("userinfo");
			//根据用户id查询用户所有的订单
			List<Orders>orderList = orderBiz.showOrder(user);
			//存到作用域并且转发
			request.setAttribute("orderList", orderList);
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}else if("detail".equals(action)){//查询订单详情
			String oidStr = request.getParameter("oid");
			Integer oid = Integer.valueOf(oidStr);
			List<Items> itemList = orderBiz.getOrderDetail(oid);
			//存到作用域并且转发
			request.setAttribute("oid", oid);
			request.setAttribute("itemList", itemList);
			request.getRequestDispatcher("items.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
