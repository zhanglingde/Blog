package com.etc.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.blog.biz.UserBiz;
import com.etc.blog.bizimpl.UserBizImpl;
import com.etc.blog.entity.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		String name=request.getParameter("username");
		String password= request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(!password.equals(confirmPassword)) {
			out.print("<script>alert('两次密码不一致！');history.go(-1);</script>");
		}else {
			UserBiz biz = new UserBizImpl();
			User user = biz.register(name, password);
			if(user==null){
				out.print("<script>alert('注册失败');location.href='login.jsp';</script>");		
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
