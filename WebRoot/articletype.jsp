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
	padding: 0 20px;
	background-color: #009688;
	color: #fff;
	white-space: nowrap;
	text-align: center;
	font-size: 14px;
	border: none;
	border-radius: 2px;
	cursor: pointer;
	margin-left: -1px;
	
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
.typetable{
	height:500px;
}
.layui-table {
	width: 100%;
	background-color: #fff;
	color: #666;
}
thead {
    display: table-header-group;
    vertical-align: middle;
    border-color: inherit;
}
.layui-table tbody tr:hover,
.layui-table thead tr,
.layui-table-click,
.layui-table-header,
.layui-table-hover,
.layui-table-mend,
.layui-table-patch,
.layui-table-tool,
.layui-table-total,
.layui-table-total tr,
.layui-table[lay-even] tr:nth-child(even) {
	background-color: #f2f2f2
}
.layui-table td,
.layui-table th {
	position: relative;
	padding: 9px 15px;
	min-height: 20px;
	line-height: 20px;
	font-size: 14px
}
tbody {
    display: table-row-group;
    vertical-align: middle;
    border-color: inherit;
}
.layui-btn .layui-icon {
	margin-right: 3px;
	font-size: 18px;
	vertical-align: bottom;
	vertical-align: middle\9;
	
}
.layui-btn-group {
	display: inline-block;
	vertical-align: middle;
	font-size: 0
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
	           
           <div class="l_content typetable" > 
	          <table class="layui-table">
				  <colgroup>
				    <col width="150">
				    <col width="200">
				    <col>
				  </colgroup>
				  <thead>
				    <tr>
				      <th>序号</th>
				      <th>文章类型</th>
				      <th>操作</th>
				    </tr> 
				  </thead>
				  <tbody>
				  	<c:forEach items="${requestScope.list }" var="type" varStatus="status">
				  		<tr>
					      <th>${status.count }</th>
					      <th>${pageScope.type.type_name }</th>
					      <th>
					      <div class="layui-btn-group">
					      	<a href="TypeServlet?flag=querybyid&typeid=${pageScope.type.type_id }" ><button class="layui-btn">编辑</button></a>|
					      	<a href="TypeServlet?flag=deletetype&typeid=${pageScope.type.type_id }"><button class="layui-btn">删除</button></a>
					      </div>
					      	
					      </th>
					    </tr>
				  	</c:forEach>
				    
				   
				  </tbody>
				</table>    
           		
           		
     
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
