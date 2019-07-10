package com.etc.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.blog.biz.TypeBiz;
import com.etc.blog.bizimpl.TypeBizImpl;
import com.etc.blog.entity.Type;

public class TypeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//设置请求编码和响应编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String flag =request.getParameter("flag");
		if(flag!=null){
			//添加文章类型
			if(flag.equals("addtype")){
				String typename = request.getParameter("type");
				
				Type type = new Type();
				type.setType_name(typename);
				
				TypeBiz biz = new TypeBizImpl();
				if(biz.addType(type)){
					out.print("<script>alert('添加文章类型成功!');location.href='TypeServlet?flag=type';</script>");
				}else{
					out.print("<script>alert('添加文章类型失败!');history.go(-1);</script>");
				}
			}
			//查询文章类型
			else if(flag.equals("querytype")){
				//查询所有新闻类型，保存到请求域。并转发到addNews.jsp
				TypeBiz biz = new TypeBizImpl();
				List<Type> list = biz.queryAllType();	
				
				//将新闻类型集合放入请求域
				request.setAttribute("list", list);
				//转发到addNews.jsp
				request.getRequestDispatcher("addarticle.jsp").forward(request,response);
				
			}
			//编辑文章类型时查询所有的文章类型
			else if(flag.equals("type")){
				//查询所有文章的类型，保存到请求域。并转发到addNews.jsp
				TypeBiz biz = new TypeBizImpl();
				List<Type> list = biz.queryAllType();	
				
				//将新闻类型集合放入请求域
				request.setAttribute("list", list);
				//转发到addNews.jsp
				request.getRequestDispatcher("articletype.jsp").forward(request,response);
			}
			//修改文章类型时查询类型
			else if(flag.equals("querybyid")){
				String id = request.getParameter("typeid");
				Integer typeid = Integer.valueOf(id);
				
				TypeBiz biz = new TypeBizImpl();
				Type type = biz.queryById(typeid);
				
				request.setAttribute("type", type);
				request.getRequestDispatcher("updatetype.jsp").forward(request, response);
			}
			//修改文章类型
			else if(flag.equals("updatetype")){
				String typename = request.getParameter("type");
				String id =request.getParameter("typeid");
				Integer typeid = Integer.valueOf(id);
				
				Type type = new Type();
				type.setType_id(typeid);
				type.setType_name(typename);
				
				//调用biz层方法修改文章类型
				TypeBiz biz = new TypeBizImpl();
				if(biz.updateType(type)){
					out.print("<script>alert('修改文章类型成功!');location.href='TypeServlet?flag=type';</script>");
				}else{
					out.print("<script>alert('修改文章类型失败!');history.go(-1);</script>");
				}
			}
			//删除文章类型
			else if(flag.equals("deletetype")){
				String id = request.getParameter("typeid");
				Integer typeid = Integer.valueOf(id); 
				
				//调用方法删除文章的类型
				TypeBiz biz = new TypeBizImpl();
				if(biz.deleteType(typeid)){
					out.print("<script>alert('删除文章类型成功!');location.href='TypeServlet?flag=type';</script>");
				}else{
					out.print("<script>alert('删除文章类型失败!');history.go(-1);</script>");
				}
			}
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
