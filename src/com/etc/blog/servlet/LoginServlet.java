package com.etc.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.blog.biz.UserBiz;
import com.etc.blog.bizimpl.UserBizImpl;
import com.etc.blog.entity.User;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		
		String name=request.getParameter("username");
		String password= request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		UserBiz biz = new UserBizImpl();
		User user = biz.checkLogin(name, password);
		
		if(user==null){
			
			out.print("<script>alert('用户名或者密码错误！');location.href='login.jsp';</script>");		
		}else{
			//将登录对象user存入请求域中
			request.setAttribute("user",user);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			//转发到index.jsp
			//request.getRequestDispatcher("index.jsp").forward(request, response);

			response.sendRedirect("ArticleServlet?flag=queryallarticle");
		}
	}

}
