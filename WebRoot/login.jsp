<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">

<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>登录页面</TITLE>
<SCRIPT src="js/jquery-1.9.1.min.js" type="text/javascript"></SCRIPT>

<STYLE>
body {
	background: #ebebeb;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei",
		"\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 12px;
}

* {
	padding: 0px;
	margin: 0px;
}

.top_div {
	background: #008ead;
	width: 100%;
	height: 400px;
}

.ipt {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 290px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.ipt:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.u_logo {
	background: url("images/username.png") no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 40px;
}

.p_logo {
	background: url("images/password.png") no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 12px;
	left: 40px;
}

a {
	text-decoration: none;
}

.tou {
	background: url("images/tou.png") no-repeat;
	width: 97px;
	height: 92px;
	position: absolute;
	top: -87px;
	left: 140px;
}

.left_hand {
	background: url("images/left_hand.png") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	left: 150px;
}

.right_hand {
	background: url("images/right_hand.png") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	right: -64px;
}

.initial_left_hand {
	background: url("images/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	left: 100px;
}

.initial_right_hand {
	background: url("images/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	right: -112px;
}

.left_handing {
	background: url("images/left-handing.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -24px;
	left: 139px;
}

.right_handinging {
	background: url("images/right_handing.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -21px;
	left: 210px;
}

.selectedButton{
	background: rgb(0, 142, 173); 
	padding: 7px 10px; 
	border-radius: 4px; 
	border: 1px solid rgb(26, 117, 152); 
	border-image: none; color: 
	rgb(255, 255, 255); 
	font-weight: bold;
}
.unSelectedButton{
	color: rgb(204, 204, 204); 
	margin-right: 10px;
}
</STYLE>

<SCRIPT type="text/javascript">
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
		});
	});
	function showRegisterArea(){
		$("#confirmPwdP").css("display","block");
		$("#top_div").css("height","350px");	
		$("#center_div").css("height","250px");
		$("#register").removeClass();
		$("#login").removeClass();
		$("#register").addClass("selectedButton");
		$("#login").addClass("unSelectedButton");
		$("#login").attr("href","javascript:hiddenRegisterArea()");
		$("#register").attr("href","javascript:mySubmit()");
		$("#myform").attr("action","RegisterServlet");
	}
	function hiddenRegisterArea(){
		$("#confirmPwdP").css("display","none");
		$("#top_div").css("height","400px");	
		$("#center_div").css("height","200px");
		$("#register").removeClass();
		$("#login").removeClass();
		$("#register").addClass("unSelectedButton");
		$("#login").addClass("selectedButton");
		$("#myform").attr("action","LoginServlet");
		$("#login").attr("href","javascript:mySubmit()");
		$("#register").attr("href","javascript:showRegisterArea()");
	}
	//表单提交用户名和密码登录
	function mySubmit(){
		document.getElementById("myform").submit();
	}
</script>
<META name="GENERATOR" content="MSHTML 11.00.9600.17496">
</HEAD>
<BODY>
	<DIV id="top_div" class="top_div"></DIV>
	<DIV id="center_div" 
		style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<DIV style="width: 165px; height: 96px; position: absolute;">
			<DIV class="tou"></DIV>
			<DIV class="initial_left_hand" id="left_hand"></DIV>
			<DIV class="initial_right_hand" id="right_hand"></DIV>
		</DIV>
		<form action="LoginServlet" method="post" id="myform" >	
			
			<P style="margin: 30px 0px 0px; position: relative;">
				<span class="u_logo" style="top:13px;left:35px;"></span> 
				<input class="ipt" type="text" placeholder="请输入用户名或邮箱" name="username" value="">
			</p>
			<P style="margin:10px 0px; position: relative;">
				<span class="p_logo" style="top:13px;left:35px;"></span> 
				<input class="ipt" id="password" type="password" name="password" placeholder="请输入密码" value="">
			</P>
			<P id= "confirmPwdP" style="position: relative;display:none ">
				<span class="p_logo" style="top:13px;left:35px;"></span> 
				<input class="ipt" id="confirmPassword" type="password" name="confirmPassword" placeholder="确认密码" value="">
			</P>
			<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
				<p style="margin: 0px 35px 20px 45px;">
					<span id="forgetPassword" style="float: left;"><a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a></span> 
					<span style="float: right;">
						<a id="register" class="unSelectedButton" href="javascript:showRegisterArea()">注册</a>
						<a id="login" class="selectedButton" href="javascript:mySubmit()">登录</a> 
					</span>
				</p>
			</div>
		</form>
	</DIV>
	
</BODY>
</HTML>

