<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>浅谈：html5和html的区别-个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
</head>

<body>
	<!--header start-->
		<jsp:include page="navigation.jsp"></jsp:include>
		<!--header end-->
	<!--content start-->
	<div id="content">
		<!--left-->
		<div class="left" id="news">
			<div class="weizi">
				<div class="wz_text">
					当前位置：<a href="#">首页</a>><a href="#">学无止境</a>><span>文章内容</span>
				</div>
			</div>
			<div class="news_content">
				<div class="news_top">
					<h1>${requestScope.article.article_title }</h1>
					<p>
						<span class="left sj" style="width:205px;margin-left:75px;">时间:${requestScope.article.article_time }</span><span class="left fl">分类:${requestScope.article.article_type.getType_name() }</span>
						<span class="left author" style="padding-right:20px;">${requestScope.article.article_user.getUser_rname() }</span>
					</p>
					<div class="clear"></div>
				</div>
				<div class="news_text">
					${requestScope.article.article_content }
					
					
								
				</div>
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
					<a href="#" title="联系博主"><span class="left b_1"></span></a><a
						href="#" title="加入QQ群，一起学习！"><span class="left b_2"></span></a>
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
	<script type="text/javascript">
		jQuery(".lanmubox").slide({
			easing : "easeOutBounce",
			delayTime : 400
		});
	</script>
	<script type="text/javascript" src="js/nav.js"></script>
</body>
</html>
