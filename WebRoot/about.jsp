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
		<title>关于我-个人博客</title>
		<meta name="keywords" content="个人博客" />
		<meta name="description" content="" />
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/mycss.css" />
		<script type="text/javascript" src="js/jquery1.42.min.js"></script>
		<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
		<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->

		

		<style>
			.layui-input {
				display: block;
				width: 200px;
				height: 30px;
				padding-left: 10px
			}
			.layui-form-radio {
				line-height: 28px;
				margin: 6px 10px 0 0;
				padding-right: 10px;
				cursor: pointer;
				font-size: 0
			}
			.layui-anim.layui-icon {
				display: inline-block
			}
			.layui-unselect {
				-webkit-user-select: none;
				-ms-user-select: none;
				-moz-user-select: none
			}
			.sex{
				width:32px;
				height:15px;
				margin-top:10px;
			}
		</style>
	</head>

	<body>
		
		
		<!--header start-->
		<jsp:include page="navigation.jsp"></jsp:include>
		<!--header end-->
		
		<!--content start-->
		<div id="content">
			<!--left-->
			<div class="left" id="about_me">
				<div class="weizi">
					<div class="wz_text">当前位置：
						<a href="#">首页</a>>
						<h1>关于我</h1></div>
				</div>
				<div class="about_content">
					<form action="UserServlet" method="post" enctype="multipart/form-data">
						<label class="layui-form-label">昵称:</label>
						<input class="layui-input" type="text" name="name" value="${requestScope.user.user_rname }"><br>
	
						<label class="layui-form-label">性别:</label>
						<c:choose>
							<c:when test="${requestScope.user.user_sex=='男' }">
								<input class="sex" type="radio" name="sex" value="男" title="男" checked="checked">男
								<input class="sex" type="radio" name="sex" value="女" title="女">女
							</c:when>
							<c:when test="${requestScope.user.user_sex=='女' }">
								<input class="sex" type="radio" name="sex" value="男" title="男" >男
								<input class="sex" type="radio" name="sex" value="女" title="女" checked="checked">女
							</c:when>
						</c:choose>
						
						<br><br>
						<label class="layui-form-label">职业:</label>
						<input class="layui-input" type="text" name="job" value="${requestScope.user.user_job }"><br>
						
						<label class="layui-form-label">座右铭:</label>
						<input class="layui-input" type="text" name="motto" value="${requestScope.user.user_motto }"><br>
						
						<label class="layui-form-label">头像:</label>
						<div id="myfileup">
							<input class="layui-input" type="file" name="filename"><br>
						</div>
						<input type="hidden" name="userid" value="${requestScope.user.user_id }">
						<input class="layui-btn" type="submit" value="完成"><br>
					</form>			
				</div>
			</div>
			<!--end left -->

			<!--right-->
			<div class="right" id="c_right">
				<div class="s_about">
					<h2>关于博主</h2>
					<img src="upload/${requestScope.user.user_image }" width="230" height="230" alt="博主" />
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
						<ul>
							<li>精心推荐</li>
							<li>最新文章</li>
							<li class="hd_3">随机文章</li>
						</ul>
					</div>
					<div class="bd">
						<ul>
							<li>
								<a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a>
							</li>
							<li>
								<a href="#" title="关于响应式布局">关于响应式布局</a>
							</li>
							<li>
								<a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a>
							</li>
							<li>
								<a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a>
							</li>
							<li>
								<a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a>
							</li>
						</ul>
						<ul>
							<li>
								<a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a>
							</li>
							<li>
								<a href="#" title="关于响应式布局">关于响应式布局</a>
							</li>
							<li>
								<a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a>
							</li>
							<li>
								<a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a>
							</li>
							<li>
								<a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a>
							</li>
						</ul>
						<ul>
							<li>
								<a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a>
							</li>
							<li>
								<a href="#" title="关于响应式布局">关于响应式布局</a>
							</li>
							<li>
								<a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a>
							</li>
							<li>
								<a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a>
							</li>
							<li>
								<a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a>
							</li>
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
		<script type="text/javascript">
			jQuery(".lanmubox").slide({
				easing: "easeOutBounce",
				delayTime: 400
			});
		</script>
		<script type="text/javascript" src="js/nav.js"></script>
	</body>

</html>