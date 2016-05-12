<%@ page language="java"  import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css"></link>

<script type="text/javascript">
	function validate(){
		var search_info = document.getElementById("search").value;
		var select_info = document.getElementById("select").value;
	
		//不能不包含特殊符号 
		var re=/[!@#$%^&*~<>',.]/;
		if(re.test(search_info) || search_info == ''){
			alert("不能为空或者含有特殊符号！");
		}else	
		{
			return true;
		}
		return false;
	}
</script>

<title>首页</title>
</head>
<body>
<center>

<%--onsubmit 事件会在表单中的确认按钮被点击时发生,即点击提交时，会执行rerutn validate() --%>
<img id="img" src="img/logo.jpg" />
<form onsubmit="return validate()" method="post" action="<%= request.getContextPath() %>/SearchServlet" >
<div id="content">
	
	
		<input id="search" name="search_info" />		
		<select id="select" name="select_info">
		  <option value="author">作者</option>
		  <option value="poetname">诗词名称</option>
		  <option value="quotes">诗歌名句</option>
		</select>
	
	    <input type="submit" id="buttonitem" value="唐诗搜索"></input>
</div>
</form>
</center>
</body>
</html>