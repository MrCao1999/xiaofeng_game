package cn.com.onlicebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.onlicebook.bean.Books;
import cn.com.onlicebook.bean.Userinfo;
import cn.com.onlicebook.biz.BooksBiz;
import cn.com.onlicebook.biz.UserInfoBiz;
import cn.com.onlicebook.biz.impl.BooksBizImpl;
import cn.com.onlicebook.biz.impl.UserInfoBizImpl;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取流对象之前，则应该把响应的内容类型设定好
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if("add".equals(action)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String roleStr = request.getParameter("role");
			String stateStr = request.getParameter("state");
			
			Integer state =  Integer.valueOf(stateStr);
			Integer role =  Integer.valueOf(roleStr);
			Userinfo userinfo = new Userinfo(username, password, email,role,state);
			
			UserInfoBiz  userBiz = new UserInfoBizImpl();
			int result = userBiz.addUser(userinfo);
			if (result > 0) {
				out.print("<script>");
				out.print("alert('新增成功！');");
				out.print("location.href = 'comm.do?action=user';");
				out.print("</script>");
			} else {
				out.print("<script>");
				out.print("alert('新增失败！');");
				out.print("location.href = 'add_user.jsp';");
				out.print("</script>");
			}
		}else if("update".equals(action)){
			String bidStr = request.getParameter("user_id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String roleStr = request.getParameter("role");
			
			Integer userid =  Integer.valueOf(bidStr);
			Integer role =  Integer.valueOf(roleStr);
			Userinfo userinfo = new Userinfo(userid, username, password, email, role); 
			
			UserInfoBiz  userBiz = new UserInfoBizImpl();
			int result = userBiz.updateUser(userinfo);
			if(result > 0) {
				out.print("<script>");
				out.print("alert('修改成功！');");
				out.print("location.href = 'comm.do?action=user';");
				out.print("</script>");
			}else {
				out.print("<script>");
				out.print("alert('修改失败！');");
				out.print("location.href = 'update_book.jsp';");
				out.print("</script>");
			}
		}else if("del".equals(action)){
			String Usertr = request.getParameter("userid");
			Integer userid = Integer.valueOf(Usertr);
			
			UserInfoBiz usersBiz = new UserInfoBizImpl();
			
			int result = usersBiz.deluserinfo(userid);
			if(result >0){
				out.print("<script>");
				out.print("alert('删除成功！');");
				out.print("location.href = 'comm.do?action=user';");
				out.print("</script>");
			}else{
				out.print("<script>");
				out.print("alert('删除失败！');");
				out.print("location.href = 'comm.do?action=user';");
				out.print("</script>");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
