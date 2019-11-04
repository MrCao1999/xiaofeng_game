package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.UserInfoBiz;
import cn.com.onlicebook.biz.impl.UserInfoBizImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
				
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		Userinfo userinfo = new Userinfo(username, password, email);
		
		UserInfoBiz userbiz = new UserInfoBizImpl();
		int result = userbiz.register(userinfo);
		
		if(result > 0) {
			out.print("<script>alert('注册成功！'),location.href = 'login.jsp';</script>");
		}else {
			out.print("<script>");
			out.print("alert('注册失败！');");
			out.print("location.href = 'register.jsp';");
			out.print("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
