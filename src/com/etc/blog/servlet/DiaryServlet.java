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

import com.etc.blog.biz.DiaryBiz;
import com.etc.blog.biz.LeaveMessageBiz;
import com.etc.blog.bizimpl.DiaryBizImpl;
import com.etc.blog.bizimpl.LeaveMessageBizImpl;
import com.etc.blog.entity.Diary;
import com.etc.blog.entity.LeaveMessage;
import com.etc.blog.entity.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class DiaryServlet
 */
@WebServlet("/DiaryServlet")
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiaryServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		DiaryBiz biz = new DiaryBizImpl();
		if (flag == null) {
			out.print("<script>alert('无效操作'); location.href = 'index.jsp'</script>");
		} else if ("addDiary".equals(flag)) {
			// 获取内容
			String content = request.getParameter("content");
			// 获取留言人
			User author = (User) request.getSession().getAttribute("user");
			if (author == null) {
				out.print(2);
			} else {
				Diary diary = new Diary();
				diary.setDiary_auther(author);
				diary.setDiary_content(content);
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				diary.setDiary_time(time);
				if (biz.addDiary(diary)) {
					out.print(1);
				} else {
					out.print(0);
				}
			}
		} else if ("getContent".equals(flag)) {
			User user = (User) request.getSession().getAttribute("user");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			List<Diary> list = biz.getContent(user,pageIndex);
			Gson gson = new Gson();
			String glist = gson.toJson(list);
			System.out.println(glist);
			out.print(glist);
		} else if ("getAllCount".equals(flag)) {
			User user = (User) request.getSession().getAttribute("user");
			out.print(biz.getAllCount(user));
		} else if ("delete".equals(flag)) {	
			int id = Integer.parseInt(request.getParameter("id"));
			if(biz.delete(id)) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
