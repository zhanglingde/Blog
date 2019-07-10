<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--header start-->
	<div id="header">
		<h1>个人博客</h1>
		<p>${sessionScope.user.user_motto}</p>
	</div>
<!--nav-->
<div id="nav">
	<ul>
		 <li><a href="ArticleServlet?flag=queryallarticle">首页 </a></li>
         <li><a href="AboutServlet?flag=about">关于我</a></li>
         <li><a href="riji.jsp">个人日记</a></li>
        
         <li><a href="guestbook.jsp">留言板</a></li>
         <div class="clear"></div>
    </ul>
</div>
<!--nav end-->