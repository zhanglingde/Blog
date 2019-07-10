package com.etc.blog.servlet;

import java.io.File;
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
import com.etc.blog.util.FileUp;
import com.etc.blog.util.RenamePolicyCos;
import com.oreilly.servlet.MultipartRequest;

public class UserServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.根据相对路径获取服务器的绝对路径
		String filepath = request.getRealPath("/upload");
		//判断该文件夹是否存在
		File file = new File(filepath);
		if(!file.exists()){
			//如果不存在就创建用来保存上传文件的文件夹
			if(file.mkdirs()){
				System.out.println("创建文件夹成功！");
			}
		}
		
		//2.设置参数
		String saveDirectory = filepath;  //设置上传路径
		int maxPostSize = 1024*1024*100; //最大上传100M的文件
		
		//3.设置文件重命名策略,在util下写两个类
		
		//4.设置表单上传过来的数据的请求对象
		MultipartRequest mulrerequest = new MultipartRequest(request, 
				saveDirectory, maxPostSize, "utf-8", new RenamePolicyCos());
		
		//5.通过二进制请求对象获取表单的内容 
		String username = mulrerequest.getParameter("name");
		String sex = mulrerequest.getParameter("sex");
		String job = mulrerequest.getParameter("job");
		String id = mulrerequest.getParameter("userid");
		String motto = mulrerequest.getParameter("motto");
		Integer userid = Integer.valueOf(id);
		//获取上传的文件重命名后的文件对象
		String files = "";
		try {
			files = FileUp.uploadfiles(mulrerequest);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		//将获取到的值封装进user对象中，以便进行数据库的操作
		User user = new User();
		user.setUser_rname(username);
		user.setUser_sex(sex);
		user.setUser_job(job);
		user.setUser_id(userid);
		user.setUser_image(files);
		user.setUser_motto(motto);
		user.setUser_info("大家好，我是渣渣华");
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//6.调用biz层方法进行数据库值的修改
		UserBiz biz = new UserBizImpl();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(biz.updataUser(user)){
			out.print("<script>alert('修改用户信息成功！');location.href='ArticleServlet?flag=queryallarticle';</script>");
		}else{
			out.print("<script>alert('修改用户信息失败！');history.go(-1);</script>");
		}
		
				
	}

}
