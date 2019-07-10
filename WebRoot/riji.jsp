<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>个人日记-个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/animate.css" />
<script type="text/javascript" src="js/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="js/uedit/ueditor.all.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="js/paging.js"></script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<style type="text/css">
.deleteMessage{
	float: right;
	display:none;
	margin-right:10px;
}
</style>
<script type="text/javascript">
	function addDiary() {
		var content = ue.getContent();
		$.ajax({
			type : "post",
			url : "DiaryServlet",
			data : {
				content : content,
				flag : "addDiary"
			},
			success : function(data) {
				if (data == 2) {
					if (confirm("您尚未登录 立即登录？")) {
						location.href = "login.jsp";
					}
				} else if (data == 1) {
					pageIndex = 1;
					getAllCount();
					getContent();
				} else if (data == 0) {
					
				} else {
					alert("错误");
				}
				ue.setContent("");
				$('#editArea').css("display", "none");
			}
		});
	}
	function getAllCount() {
		$.ajax({
			type : "post",
			url : "DiaryServlet",
			data : {
				flag : "getAllCount"
			},
			dataType : "text",
			success : function(data) {
				// 获得总留言数，
				allCount = parseInt(data);
				console.log("ss" + allCount);
				updatePaging();
			}
		});
	}
	function mouseover(div){
		div.children[1].children[1].style.display = 'inline';
	}
	function mouseout(div){
		div.children[1].children[1].style.display = 'none';
	}
	function getContent(){
		$.ajax({
			type : "post",
			url : "DiaryServlet",
			data : {
				flag : "getContent",
				pageIndex : pageIndex
			},
			dataType : "json",
			success : function(data) {
				$("#messageArea").html("");
				if(data != null)
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
		var mess = "<div class='shiguang animated bounceIn'>"
					   	+"<div class='left sg_ico'>"
					   		+"<img src='upload/"+data[i].diary_auther.user_image+"' width='120' height='120' alt='' />"
					   	+"</div>"
						+"<div class='right sg_text' onmouseover='mouseover(this)' onmouseout='mouseout(this)'>"
							+"<div style='min-height:90px; margin-top:10px'>"
								+"<img class='littleTriangle' src='images/left.png' width='13' height='16' alt='左图标' />"
								+ data[i].diary_content
							+"</div>"
							+"<div>"
								+ "<span class='timeSpan'>"
								+ data[i].diary_time + "</span>"
								+"<div class ='deleteMessage'><a href='javascript:deleteMessage("+data[i].diary_id+")'>删除</a></div>"
							+"</div>"
						+"</div>"
						+"<div class='clear'></div>"
					+"</div>"
					$("#messageArea").html($("#messageArea").html() + mess);
				}
				updatePaging();
			}
		});
	}
	function deleteMessage(id){
		if(confirm("确定删除？"))
		$.ajax({
			type : "post",
			url : "DiaryServlet",
			data : {
				id : id,
				flag : "delete"
			},
			success : function(data) {
				getContent();
				getAllCount();
			}
		});
	}
	function showEditArea() {
		updatePaging();
		console.log(1);
		$('#editArea').css("display", "block");
	}
	$(document).ready(function() {
		getAllCount();
		getContent();
	});
</script>
<style type="text/css">
.submitButton{
	float: right;
}
.editArea{
	display: none;
	margin: 10px 5px 20px;
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
		<div class="left" id="riji">
			<div class="weizi">
				<div class="wz_text">
					当前位置：<a href="#">首页</a>>
					<h1>个人日记</h1>
					&nbsp; <a href="javascript:showEditArea()">写日记</a>
				</div>
			</div>
			<div id="editArea" class="editArea">
				<script id="editor" name="editor" type="text/plain"></script>
				<div class="submitButton"><a href="javascript:addDiary()">发表</a></div>
			</div>
			<script type="text/javascript">
					//var editor = new baidu.editor.ui.Editor();
					//editor.render("mytext");
					var ue = UE.getEditor('editor', {
						toolbars : [ [ 'emotion','bold','italic','underline','fontfamily','fontsize','forecolor','insertimage', 'link','fullscreen','scrawl', ] ],
						autoHeightEnabled : true,
						autoFloatEnabled : true
					});
			</script>
			<div id="messageArea" class="rj_content">
				<!--时光-->
				<div class="shiguang animated bounceIn">
					<div class="left sg_ico">
						<img src="images/my_1.jpg" width="120" height="120" alt="" />
					</div>
					<div class="right sg_text">
						<img src="images/left.png" width="13" height="16" alt="左图标" />
						时间好象一把尺子，它能衡量奋斗者前进的进程。时间如同一架天平，它能称量奋斗者成果的重量；时间就像一把皮鞭，它能鞭策我们追赶人生的目标。时间犹如一面战鼓，它能激励我们加快前进的脚步。
					</div>
					<div class="clear"></div>
				</div>
				<!--时光 end-->
			</div>
			<div id="paging"> </div>
		</div>
		<!--end left -->
		<!--right-->
		<div class="right" id="c_right">
			<div class="s_about">
				<h2>关于博主</h2>
				<img src="upload/${sessionScope.user.user_image }" width="230" height="230" alt="博主" />
				<p>博主：${sessionScope.user.user_rname }</p>
				<p>职业：${sessionScope.user.user_job }</p>
				<p>简介：${sessionScope.user.user_info }</p>
				<p>
					<a href="#" title="联系博主"><span class="left b_1"></span></a><a href="#" title="加入QQ群，一起学习！"><span
						class="left b_2"></span></a>
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
		<p>Design by:段乐 2018-9-21</p>
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

