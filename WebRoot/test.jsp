<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

	<script src="js/jquery-1.9.1.min.js"></script>
 	<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<style>
		#test{
			margin-left:50px;
		}
	</style>
</head>

<body>
<div id="test">
    <h1>完整demo</h1>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
    <h1>demo2</h1>
    <textarea id="mytext" name="mytext" ></textarea><br><br>
</div> 

<script type="text/javascript">
	var ue = UE.getEditor('editor');
	var editor = new baidu.editor.ui.Editor();
	editor.render("mytext");
	
</script>
</body>
</html>

