<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>留言板-个人博客</title>
<meta name="keywords" content="个人博客" />
<meta name="description" content="" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/style.css" />
<style type="text/css">
.messageDiv {
	border: 1px gray solid;
	color: black;
	margin: 2px;
}

.timeSpan {
	color: #5d5d5d;
}

.userhref {
	color: #2595B7;
}
.deleteMessage{
	float: right;
	display:none;
	margin-right:10px;
}
</style>
<script type="text/javascript" src="js/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="js/uedit/ueditor.all.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="js/paging.js"></script>
<script type="text/javascript">
	function showEditArea() {
		$('#editArea').css("display", "block");
	}
	function submit() {
		var content = ue.getContent();
		$.ajax({
			type : "post",
			url : "LeaveMessageServlet",
			data : {
				content : content,
				flag : "addLeaveMessage"
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
	function deleteMessage(id){
		if(confirm("确定删除？"))
		$.ajax({
			type : "post",
			url : "LeaveMessageServlet",
			data : {
				id : id,
				flag : "deleteLeaveMessage"
			},
			success : function(data) {
				getContent();
				getAllCount();
			}
		});
	}
	function getContent() {
		$.ajax({
			type : "post",
			url : "LeaveMessageServlet",
			data : {
				flag : "getContent",
				pageIndex : pageIndex
			},
			dataType : "json",
			success : function(data) {
				$("#messagearea").html("");
				if(data != null)
				for (var i = 0; i < data.length; i++) {
					var mess = "<div class = 'messageDiv' onmouseover='mouseover(this)' onmouseout='mouseout(this)'>"
							+ "<a class='userhref' href = 'index.jsp?user="
							+ data[i].leaveMessage_auther.user_lname + "'>"
							+ data[i].leaveMessage_auther.user_rname + "</a>"
							+ "<div class ='deleteMessage'><a href='javascript:deleteMessage("+data[i].leaveMessage_id+")'>删除</a></div>"
							+ "<div style='min-height:90px; margin-top:10px'>"
							+ data[i].leaveMessage_content + "</div>"
							+ "<span class='timeSpan'>"
							+ data[i].leaveMessage_time + "</span>" +
							
							"</div>"
					$("#messagearea").html($("#messagearea").html() + mess);
				}
				updatePaging();
			}
		});
		
	}
	function mouseover(div){
		div.children[1].style.display = 'inline';
	}
	function mouseout(div){
		div.children[1].style.display = 'none';
	}
	
	function getAllCount() {
		$.ajax({
			type : "post",
			url : "LeaveMessageServlet",
			data : {
				flag : "getAllCount"
			},
			dataType : "text",
			success : function(data) {
				// 获得总留言数，
				allCount = parseInt(data);
				updatePaging();
			}
		});
	}
	
	$(document).ready(function() {
		getAllCount();
		getContent();
	});
</script>

<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
</head>

<body>
	<!--header start-->
		<jsp:include page="navigation.jsp"></jsp:include>
		<!--header end-->
	<!--content start-->
	<div id="content" style="height: auto;">
		<!--left-->
		<div class="left" id="guestbook" style="height: auto;">
			<div class="weizi">
				<div class="wz_text">
					当前位置：<a href="#">首页</a>>
					<h1>留言板</h1>
				</div>
			</div>
			<div class="g_content" style="height: auto;">
				有什么想对我说的嘛？<a href="javascript:showEditArea()">我要留言</a>
				<div id="editArea" style="display: none;">
					<form action="LeaveMessageServlet" method="post">
						<a href="javascript:submit()" style="position: absolute; left: 62%; top: 200px; z-index: 1000">发表</a>
						<script id="editor" name="editor" type="text/plain"></script>
					</form>
				</div>
				<script type="text/javascript">
					//var editor = new baidu.editor.ui.Editor();
					//editor.render("mytext");
					var ue = UE.getEditor('editor', {
						toolbars : [ [ 'emotion' ] ],
						autoHeightEnabled : true,
						autoFloatEnabled : true
					});
				</script>

				<div id="messagearea"></div>
				<div id="paging"></div>
			</div>
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
		<p>Design by:段乐 2018-9-20</p>
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

