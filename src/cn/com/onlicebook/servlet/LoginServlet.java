package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.UserInfoBiz;
import cn.com.onlicebook.biz.impl.UserInfoBizImpl;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取流对象之前，则应该把响应的内容类型设定好
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		
		Userinfo userinfo = new Userinfo();
		userinfo.setUsername(username);
		userinfo.setPassword(password);
		//调用业务逻辑层
		UserInfoBiz userinfoBiz = new UserInfoBizImpl();
		userinfo = userinfoBiz.login(userinfo);
		if("login".equals(action)){
			
			if(userinfo != null) {
				//将登录信息存在session中
				session.setAttribute("userinfo", userinfo);
				
				if(userinfo.getRole() == 1){
					response.sendRedirect("index.jsp");
					
				}else if(userinfo.getRole() == 2){
					response.sendRedirect("comm.do?action=back");
				}
				
			}else {
				out.print("<script>alert('账号或密码错误，请重新登录');location.href='login.jsp';</script>");
			}
		}else if("sign".equals(action)){
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
