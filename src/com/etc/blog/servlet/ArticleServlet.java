package com.etc.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.blog.biz.ArticleBiz;
import com.etc.blog.biz.TypeBiz;
import com.etc.blog.biz.UserBiz;
import com.etc.blog.bizimpl.ArticleBizImpl;
import com.etc.blog.bizimpl.TypeBizImpl;
import com.etc.blog.bizimpl.UserBizImpl;
import com.etc.blog.dao.TypeDao;
import com.etc.blog.daoimpl.TypeDaoImpl;
import com.etc.blog.entity.Article;
import com.etc.blog.entity.Type;
import com.etc.blog.entity.User;

public class ArticleServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求编码和响应编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String flag =request.getParameter("flag");
		
		if(flag!=null){
			if(flag.equals("queryallarticle")){
				//分页查询文章并进入首页
				int page = 0;	//当前页数
				int size = 5;	//每页显示文章数量
				int pagenum = 0;//总页数
				long count = 0;	//文章总条数
				int lastpage = 0;	//上一页
				int nextpage = 0;	//下一页
				
				//获取从地址栏传过来的当前页数
				if(request.getParameter("page")==null){
					//如果地址栏没有默认首页
					page = 1;
				}else{
					try {
						page =Integer.valueOf(request.getParameter("page"));
					} catch (NumberFormatException e) {
						//如果页面上传过来的是字符，那么page默认是首页
						page = 1;
					}
				}
				
				//获取某个作者的文章  总条数
				User user2 = (User) request.getSession().getAttribute("user");
				ArticleBiz biz = new ArticleBizImpl();
				count = biz.getArticleCount(user2.getUser_id());
				//根据session域中保存的登录用户的id查询登录的用户
				UserBiz biz2 = new UserBizImpl();
				User user = biz2.queryById(user2.getUser_id());
				//总页数
				pagenum=(int) ((count%size==0)?(count/size):(count/size)+1);
				
				//对当前页数page进行容错
				page=page>pagenum?pagenum:page;	//上限容错
				page=page<=0?1:page;//下限容错
				
				
				//上一页
				if(page-1<=0){
					lastpage = 1;
				}else{
					lastpage = page-1;
				}
				
				//下一页
				nextpage = (page+1>pagenum)?pagenum:page+1;
				
				//根据容错后的page和size查询每页对应的数据				
				List<Article> list = null;
				if(page <= pagenum) 
					list = biz.queryArticleForPage(page, size, user.getUser_id());
				
				//将list，page，pagenum放入请求域中
				request.setAttribute("list", list);
				request.setAttribute("page", page);
				request.setAttribute("pagenum", pagenum);
				request.setAttribute("lastpage", lastpage);
				request.setAttribute("nextpage", nextpage);
				request.setAttribute("user", user);
				
				
				//转发到对应页数的index.jsp
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			//进入文章列表
			else if(flag.equals("articlelist")){	
				int page = 0;	//当前页数
				int size = 10;	//每页显示文章数量
				int pagenum = 0;//总页数
				long count = 0;	//文章总条数
				int lastpage = 0;	//上一页
				int nextpage = 0;	//下一页
				
				//获取从地址栏传过来的当前页数
				if(request.getParameter("page")==null){
					//如果地址栏没有默认首页
					page = 1;
				}else{
					try {
						page =Integer.valueOf(request.getParameter("page"));
					} catch (NumberFormatException e) {
						//如果页面上传过来的是字符，那么page默认是首页
						page = 1;
					}
				}
				
				//获取某个作者的文章  总条数
				User user = (User) request.getSession().getAttribute("user");
				ArticleBiz biz = new ArticleBizImpl();
				count = biz.getArticleCount(user.getUser_id());
				//总页数
				pagenum=(int) ((count%size==0)?(count/size):(count/size)+1);
				
				//对当前页数page进行容错
				page=page<=0?1:page;//下限容错
				page=page>pagenum?pagenum:page;	//上限容错
				
				//上一页
				if(page-1<=0){
					lastpage = 1;
				}else{
					lastpage = page-1;
				}
				
				//下一页
				nextpage = (page+1>pagenum)?pagenum:page+1;
				//根据容错后的page和size查询每页对应的数据
				
				List<Article> list = biz.queryArticleForPage(page, size, user.getUser_id());
				
				//将list，page，pagenum放入请求域中
				request.setAttribute("list", list);
				request.setAttribute("page", page);
				request.setAttribute("pagenum", pagenum);
				request.setAttribute("lastpage", lastpage);
				request.setAttribute("nextpage", nextpage);
				
				
				//转发到对应页数的index.jsp
				request.getRequestDispatcher("articlelist.jsp").forward(request, response);
			}
			//删除文章
			else if(flag.equals("deletearticle")){
				String id = request.getParameter("articleid");
				Integer articleid = Integer.valueOf(id);
				
				//调用biz层的方法进行数据库的操作-删除文章
				ArticleBiz biz = new ArticleBizImpl();
				if(biz.delete(articleid)){
					out.print("<script>alert('删除文章成功!');location.href='ArticleServlet?flag=queryallarticle';</script>");
				}else{
					out.print("<script>alert('删除文章失败!');history.go(-1);</script>");
				}
				out.flush();
				out.close();
			}
			//编辑文章
			else if(flag.equals("queryarticlebyid")){
				String id = request.getParameter("articleid");
				Integer articleid = Integer.valueOf(id);
				
				//调用方法根据对应的id查询要编辑的文章
				ArticleBiz biz = new ArticleBizImpl();
				Article article = biz.queryArticleById(articleid);
				
				//查询文章的类型，并转发到编辑文章界面，以便文章类型下拉框遍历
				TypeBiz biz2 = new TypeBizImpl();
				List<Type> list = biz2.queryAllType();
				
				//将文章放入请求域，并转发到编辑页面
				request.setAttribute("article", article);
				request.setAttribute("list", list);
				
				request.getRequestDispatcher("updataarticle.jsp").forward(request, response);
			}
			//阅读全文
			else if(flag.equals("readarticle")){
				String id = request.getParameter("articleid");
				int articleid = Integer.valueOf(id);
				
				//调用方法根据对应的id查询要编辑的文章
				ArticleBiz biz = new ArticleBizImpl();
				Article article = biz.queryArticleById(articleid);
				
				User user = (User) request.getSession().getAttribute("user");
				
			
				//将文章放入请求域，并转发到编辑页面
				request.setAttribute("article", article);
				request.setAttribute("user", user);
				
				request.getRequestDispatcher("news.jsp").forward(request, response);
			}
			//添加文章
			else if(flag.equals("addarticle")){
				String title = request.getParameter("title");
				String content = request.getParameter("mytext");
				String id = request.getParameter("type");
				
				int typeid = Integer.valueOf(id);
				TypeDao tdao = new TypeDaoImpl();
				Type type = tdao.queryById(typeid);
				
				User user = new User();
				user.setUser_id(1);
				
				//将值传入文章对象
				Article article = new Article();
				article.setArticle_content(content);
				article.setArticle_image(null);				
				article.setArticle_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ).format(new Date()));
				article.setArticle_title(title);
				article.setArticle_type(type);
				article.setArticle_user(user);
				
				//调用biz层方法添加文章到数据库
				ArticleBiz biz = new ArticleBizImpl();
				if(biz.addArticle(article)){
					out.print("<script>alert('添加文章成功!');location.href='ArticleServlet?flag=queryallarticle';</script>");
				}else{
					out.print("<script>alert('添加文章失败!');history.go(-1);</script>");
				}
			}
			//修改文章
			else if(flag.equals("updatearticle")){
				String title = request.getParameter("title");
				String content = request.getParameter("mytext");//内容
				String id2 = request.getParameter("articleid");
				Integer articleid = Integer.valueOf(id2);
				String id = request.getParameter("type");
				
				int typeid = Integer.valueOf(id);
				TypeDao tdao = new TypeDaoImpl();
				Type type = tdao.queryById(typeid);
				
				//将值传入文章对象
				Article article = new Article();
				article.setArticle_content(content);
				article.setArticle_image(null);				
				article.setArticle_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				article.setArticle_title(title);
				article.setArticle_type(type);
				article.setArticle_id(articleid);
				
				//调用biz层方法添加文章到数据库
				ArticleBiz biz = new ArticleBizImpl();
				if(biz.updateArticle(article)){
					out.print("<script>alert('修改文章成功!');location.href='ArticleServlet?flag=queryallarticle';</script>");
				}else{
					out.print("<script>alert('修改文章失败!');history.go(-1);</script>");
				}
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
