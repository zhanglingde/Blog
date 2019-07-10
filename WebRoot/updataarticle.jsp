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
.layui-form-select .layui-input {
	padding-right: 30px;
	cursor: pointer
}
</style>

<script src="ueditor/ueditor.config.js" charset="utf-8"></script>
<script src="ueditor/ueditor.all.js" charset="utf-8"></script>
<script src="ueditor/third-party/jquery-1.10.2.js"></script>
<script type="text/javascript" charset="gbk" src="ueditor/lang/zhcn/zhcn.js"></script>

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
           		<form action="ArticleServlet" method="post" >
	           
	           		
	           		
	           		<label class="layui-form-label">文章类型：</label>
		           		<select name="type" >
						  	<c:forEach items="${requestScope.list }" var="type">
						  		<!-- <option value="${pageScope.type.type_id }">${pageScope.type.type_name }</option> -->
						  		<c:if test="${pageScope.type.type_id == requestScope.article.article_type.getType_id() }">
						  			<option value="${pageScope.type.type_id }" selected>${pageScope.type.type_name }</option>
						  		</c:if>
						  		<c:if test="${pageScope.type.type_id != requestScope.article.article_type.getType_id() }">
						  			<option value="${pageScope.type.type_id }">${pageScope.type.type_name }</option>
						  		</c:if>
						  		<!--
						  		
						  		-->
						  		<%--
						  				<c:when test="${pageScope.type.type_id == requestScope.article.article_type.getType_id() }">
						  			<option value="${pageScope.type.type_id }" selected>${pageScope.type.type_name }</option>
						  		</c:when>
						  		<c:otherwise>
						  			<option value="${pageScope.type.type_id }">${pageScope.type.type_name }</option>
						  		</c:otherwise>
						  		 --%>
						  		
						  	
						  	</c:forEach>
						</select>
						<br><br><br>
					
					<label class="layui-form-label" >标题：</label>
					<input class="layui-input" type="text" name="title" value="${requestScope.article.article_title }"><br>
					
					<label class="layui-form-label" >内容：</label>
					<textarea id="mytext" name="mytext" >${requestScope.article.article_content }</textarea><br><br>
					<script >
						var editor = new baidu.editor.ui.Editor();
						editor.render("mytext");
					</script>
					
					
					<input type="hidden" name="flag" value="updatearticle">
					<input type="hidden" name="articleid" value="${requestScope.article.article_id }">
						
	           		<input class="layui-btn" type="submit" value="完成"><br>
	           </form> 
          
           
     
           </div>
         </div>
         <!--end left -->
         <!--right-->
          <div class="right" id="c_right">
          <div class="s_about">
          		<jsp:include page="amanagerbar.jsp"></jsp:include>
          </div>
          <!--栏目分类-->
           <div class="lanmubox">
              <div class="hd">
               <ul><li>精心推荐</li><li>最新文章</li><li class="hd_3">随机文章</li></ul>
              </div>
              <div class="bd">
                <ul>
					<li><a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a></li>
					<li><a href="#" title="关于响应式布局">关于响应式布局</a></li>
					<li><a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
					<li><a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a></li>
					<li><a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a></li>
				</ul>
                 <ul>
					<li><a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a></li>
					<li><a href="#" title="关于响应式布局">关于响应式布局</a></li>
					<li><a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
					<li><a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a></li>
					<li><a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a></li>
				</ul>
                 <ul>
					<li><a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a></li>
					<li><a href="#" title="关于响应式布局">关于响应式布局</a></li>
					<li><a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
					<li><a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a></li>
					<li><a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a></li>
				</ul>
                 
                
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
