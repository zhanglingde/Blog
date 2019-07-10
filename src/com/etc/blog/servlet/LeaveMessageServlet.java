package com.etc.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etc.blog.biz.ArticleBiz;
import com.etc.blog.biz.LeaveMessageBiz;
import com.etc.blog.biz.UserBiz;
import com.etc.blog.bizimpl.ArticleBizImpl;
import com.etc.blog.bizimpl.LeaveMessageBizImpl;
import com.etc.blog.bizimpl.UserBizImpl;
import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class LeaveMessageServlet
 */
@WebServlet("/LeaveMessageServlet")
public class LeaveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveMessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {  
		// TODO Auto-generated method stub
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if (flag == null) {
			out.print("<script>alert('无效操作'); location.href = 'index.jsp'</script>");
		} else if ("deleteArticle".equals(flag)) {
		} else {
			out.print("<script>alert('无效操作'); location.href = 'index.jsp'</script>");
		}
		// 关闭输出流
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		LeaveMessageBiz biz = new LeaveMessageBizImpl();
		String flag = request.getParameter("flag");
		if (flag == null) {
			out.print("<script>alert('无效操作'); location.href = 'index.jsp'</script>");
		} else if ("addLeaveMessage".equals(flag)) {
			// 获取内容
			String content = request.getParameter("content");
			// 获取被留言人
			User user = (User) request.getSession().getAttribute("user");
			// 获取留言人
			User author = (User) request.getSession().getAttribute("user");
			if (user == null) {
				out.print(2);
			} else {
				LeaveMessage leaveMessage = new LeaveMessage();
				leaveMessage.setLeaveMessage_auther(author);
				leaveMessage.setLeaveMessage_user(user);
				leaveMessage.setLeaveMessage_content(content);
				// 添加时间
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				leaveMessage.setLeaveMessage_time(time);
				if (biz.addLeaveMessage(leaveMessage)) {
					out.print(1);
				} else {
					out.print(0);
				}
			}
		} else if ("getContent".equals(flag)) {
			User user = (User) request.getSession().getAttribute("user");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			List<LeaveMessage> list = biz.getContent(user,pageIndex);
			Gson gson = new Gson();
			String glist = gson.toJson(list);
			out.print(glist);
		} else if ("getAllCount".equals(flag)) {
			User user = (User) request.getSession().getAttribute("user");
			out.print(biz.getAllCount(user));
		} else if ("deleteLeaveMessage".equals(flag)) {	
			int id = Integer.parseInt(request.getParameter("id"));
			if(biz.deleteLeaveMessage(id)) {
				out.print(1);
			}else {
				out.print(0);
			}
		} else {
			out.print("<script>alert('无效操作'); location.href = 'index.jsp'</script>");
		}
		// 关闭输出流
		out.flush();
		out.close();
	}

}
