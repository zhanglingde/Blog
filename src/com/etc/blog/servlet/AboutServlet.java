package com.etc.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.blog.biz.ArticleBiz;
import com.etc.blog.biz.UserBiz;
import com.etc.blog.bizimpl.ArticleBizImpl;
import com.etc.blog.bizimpl.UserBizImpl;
import com.etc.blog.entity.Article;
import com.etc.blog.entity.User;

public class AboutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置请求编码和响应编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String flag = request.getParameter("flag");

		if (flag != null) {
			if (flag.equals("about")) {
				// 获取某个作者的文章 总条数
				User user2 = (User) request.getSession().getAttribute("user");
				ArticleBiz biz = new ArticleBizImpl();
				// 根据session域中保存的登录用户的id查询登录的用户
				UserBiz biz2 = new UserBizImpl();
				User user = biz2.queryById(user2.getUser_id());
				request.setAttribute("user", user);
				// 转发到对应页数的index.jsp
				request.getRequestDispatcher("about.jsp").forward(request,response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
