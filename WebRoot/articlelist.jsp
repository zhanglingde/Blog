<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta charset="UTF-8">
<title>学无止境-个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css"/>
<link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->

<style>
	.edit{
		float:left;
		width:110px;
	}
	.title{
		width:560px;
		float:left;
		font-size:17px;
	}
	.layui-btn-sm {
		height: 30px;
		line-height: 30px;
		padding: 0 10px;
		font-size: 12px;
		background-color: #009688;
		color: #fff;
		display: inline-block;
		border: none;
	}
	.amanagerbar{
		font-size: 15px;
		
	}
	
</style>

<script>
function mydelete(articleid){
	if(confirm("确定删除？")){
		location.href="ArticleServlet?flag=deletearticle&articleid="+articleid;
	}
}

</script>
</head>

<body>
      <!--header start-->
		<jsp:include page="navigation.jsp"></jsp:include>
		<!--header end-->
    <!--content start-->
    <div id="content">
       <!--left-->
         <div class="left" id="learn">
           <div class="weizi">
           <div class="wz_text">当前位置：<a href="#">首页</a>><h1>学无止境</h1></div>
           </div>
           <div class="l_content">
           
           <!--wz-->
           <c:forEach items="${requestScope.list }" var="article" varStatus="status">
   			<div class="wz">
   					<div class="title">
   						
	            		<h3><a href="#" >${status.count}.${pageScope.article.article_title }</a></h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   					</div>
	            	<div class="edit ">
	    		         <span><a href="javascript:mydelete(${pageScope.article.article_id })"><input type="button" class="layui-btn-sm" value="删除"></a>&nbsp;&nbsp;&nbsp;
	    		         <a href="ArticleServlet?flag=queryarticlebyid&articleid=${pageScope.article.article_id }"><input type="button" class="layui-btn-sm" value="编辑"></a> </span>
	            	</div>
	             <dl>	               
	               <dd>	                
		               <p class="dd_text_2">
		               <span class="left author">${pageScope.article.article_user.getUser_rname() }</span><span class="left sj">时间:${pageScope.article.article_time }</span>
		               <span class="left fl">分类:<a href="#" title="学无止境">${pageScope.article.article_type.getType_name() }</a></span><span class="left yd"><a href="ArticleServlet?flag=readarticle&articleid=${pageScope.article.article_id }" title="阅读全文">阅读全文</a>
		               </span>
		                <div class="clear"></div>
		               </p>
	               </dd>
	               <div class="clear"></div>
	             </dl>
           	</div>
           </c:forEach>
           
           <!--wz end-->
           
     
           </div>
         </div>
         <!--end left -->
         <!--right-->
          <div class="right" id="c_right">
         	<div class="s_about">
		          <h2>关于博主</h2>
		           <img src="upload/${requestScope.user.user_image }" width="230" height="230" alt="博主"/>
		           <p>博主：${requestScope.user.user_rname }</p>
		           <p>职业：${requestScope.user.user_job }</p>
		           <p>简介：${requestScope.user.user_info }</p>
		           <p>
		           <div class="clear"></div>
		           </p>
          	</div>
          
          <!--栏目分类-->
           <div class="lanmubox">
              <div class="hd">
               <ul><li>精心推荐</li></ul>
              </div>
              <div class="bd">
               	<jsp:include page="amanagerbar.jsp"></jsp:include>
                 
                
                 
                
              </div>
           </div>
           <!--end-->
         </div>
         <!--end  right-->
         <div class="clear"></div>
          <!-- 分页 -->
    <div>
    	<p align="center">
						<a href="ArticleServlet?flag=articlelist&page=1">首页</a> 
						<a	href="ArticleServlet?flag=articlelist&page=${requestScope.lastpage} ">上一页</a>
						<script>
							//显示具体的页数的列表
							var num = "${requestScope.pagenum}"; //总页数
							var page = "${requestScope.page}";//当前页数
							if (num != "" && page != "") {
								num = parseInt(num);
								page = parseInt(page);
								for (var i = 1; i <= num; i++) {
									if (i == page) {
										//显示具体的页数，且当前的页数为红色
										document.write("<a style='color:red' href='ArticleServlet?flag=articlelist&page="+ i+ "'>"+ i+ "</a>&nbsp;&nbsp;");
									} else {
										document.write("<a href='ArticleServlet?flag=articlelist&page="+ i+ "'>"+ i+ "</a>&nbsp;&nbsp;");
									}
								}
							}
						</script>
						<a href="ArticleServlet?flag=articlelist&page=${requestScope.nextpage} ">下一页</a>
						<a href="ArticleServlet?flag=articlelist&page=${requestScope.pagenum }">末页</a>


					</p>
    </div>
    <!-- 分页结束 -->
    </div>
    <!--content end-->
    <!--footer-->
    <div id="footer">
     
    </div>
    <!--footer end-->
    <script type="text/javascript">jQuery(".lanmubox").slide({easing:"easeOutBounce",delayTime:400});</script>
    <script  type="text/javascript" src="js/nav.js"></script>
</body>
</html>
