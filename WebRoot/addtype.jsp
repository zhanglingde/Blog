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
<style type="text/css">
.layui-btn {
	display: inline-block;
	height: 38px;
	line-height: 38px;
	padding: 0 33px;
	background-color: #009688;
	color: #fff;
	white-space: nowrap;
	text-align: center;
	font-size: 14px;
	border: none;
	border-radius: 2px;
	cursor: pointer;
	margin-left: 110px;
	
}
.layui-input{
	display: block;
	width: 200px;
	height:30px;
	padding-left: 10px
	
}
.layui-form-label {
	float: left;
	display: block;
	padding: 9px 15px;
	width: 80px;
	font-weight: 400;
	line-height: 20px;
	text-align: right
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
	           <form action="TypeServlet" method="post" style="height:270px">
	           		<input type="hidden" name="flag" value="addtype">
	           		<label class="layui-form-label">文章类型：</label><input class="layui-input" type="text" name="type" ><br>
	           		<input class="layui-btn" type="submit" value="添加"><br>
	           </form>        
           		
           		
     
           </div>
         </div>
         <!--end left -->
         <!--right-->
          <div class="right" id="c_right">
         		<div class="s_about">
		          <h2>关于博主</h2>
		           <img src="upload/${sessionScope.user.user_image }" width="230" height="230" alt="博主"/>
		           <p>博主：${sessionScope.user.user_rname }</p>
		           <p>职业：${sessionScope.user.user_job }</p>
		           <p>简介：${sessionScope.user.user_info }</p>
		           <p>
		           <div class="clear"></div>
		           </p>
          		</div>
          <!--栏目分类-->
           <div class="lanmubox">
              <div class="hd">
               <ul><li>文章管理</li></ul>
              </div>
              <div class="bd">
               	<jsp:include page="amanagerbar.jsp"></jsp:include>
                 
                
                 
                
              </div>
           </div>
           <!--end-->
         </div>
         <!--end  right-->
         <div class="clear"></div>
         
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
