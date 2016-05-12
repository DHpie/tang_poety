<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jkxy.model.Poet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bottom.css"></link>

<title>Insert title here</title>
<script src="js/jquery-2.2.3.min.js"></script>
</head>
<script type="text/javascript">
	var pageSize = 15; //每页显示的记录条数
	var curPage = 0; //当前页
	var lastPage; //最后页
	var direct = 0; //方向
	var len; //总行数
	var page; //总页数
	var begin;
	var end;

	$(document).ready(
			function display() {
				len = $("#mytable tr").length - 1; // 求这个表的总行数，剔除第一行介绍
				page = len % pageSize == 0 ? len / pageSize : Math.floor(len
						/ pageSize) + 1;//根据记录条数，计算页数
				curPage = 1; // 设置当前为第一页
				displayPage(1);//显示第一页

				document.getElementById("btn0").innerHTML = "当前 " + curPage
						+ "/" + page + " 页    每页 "; // 显示当前多少页
				document.getElementById("sjzl").innerHTML = "数据总量 " + len + ""; // 显示数据量
				document.getElementById("pageSize").value = pageSize;

				$("#btn1").click(function firstPage() { // 首页
					curPage = 1;
					direct = 0;
					displayPage();
				});
				$("#btn2").click(function frontPage() { // 上一页
					direct = -1;
					displayPage();
				});
				$("#btn3").click(function nextPage() { // 下一页
					direct = 1;
					displayPage();
				});
				$("#btn4").click(function lastPage() { // 尾页
					curPage = page;
					direct = 0;
					displayPage();
				});
				$("#btn5").click(function changePage() { // 转页
					var temp = document.getElementById("changePage").value * 1;
					if (!/^[1-9]\d*$/.test(temp)) {
						alert("请输入正整数");
						return;
					}
					if (temp > page) {
						alert("超出数据页面");
						return;
					}
					curPage = temp;
					direct = 0;
					displayPage();
				});

				$("#pageSizeSet").click(function setPageSize() { // 设置每页显示多少条记录
					pageSize = document.getElementById("pageSize").value; //每页显示的记录条数
					if (!/^[1-9]\d*$/.test(pageSize)) {
						alert("请输入正整数");
						return;
					}
					len = $("#mytable tr").length - 1;
					page = len % pageSize == 0 ? len / pageSize
							: Math.floor(len / pageSize) + 1;//根据记录条数，计算页数
					curPage = 1; //当前页
					direct = 0; //方向
					displayPage();
				});
			});

	function displayPage() {
		if (curPage <= 1 && direct == -1) {
			direct = 0;
			alert("已经是第一页了");
			return;
		} else if (curPage >= page && direct == 1) {
			direct = 0;
			alert("已经是最后一页了");
			return;
		}

		lastPage = curPage;

		// 修复当len=1时，curPage计算得0的bug
		if (len > pageSize) {
			curPage = ((curPage + direct + len) % len);
		} else {
			curPage = 1;
		}

		document.getElementById("btn0").innerHTML = "当前 " + curPage + "/"
				+ page + " 页    每页 "; // 显示当前多少页

		begin = (curPage - 1) * pageSize + 1;// 起始记录号
		end = begin + 1 * pageSize - 1; // 末尾记录号

		if (end > len)
			end = len;
		$("#mytable tr").hide(); // 首先，设置这行为隐藏
		$("#mytable tr").each(function(i) { // 然后，通过条件判断决定本行是否恢复显示
			if ((i >= begin && i <= end) || i == 0)//显示begin<=x<=end的记录
				$(this).show();
		});

	}
</script>

<body>
 <img src="img/bg.jpg">
	<%
		List poets = (List) session.getAttribute("poets");
		Iterator iter = poets.iterator();
	%>
	<a id="btn0"></a>
	<input id="pageSize" type="text" size="1" maxlength="2"
		value="getDefaultValue()" />
	<a> 条 </a>
	<a href="#" id="pageSizeSet">设置</a>
	<a id="sjzl"></a>
	
	<a href="#" id="btn1">首页</a>
	<a href="#" id="btn2">上一页</a>
	<a href="#" id="btn3">下一页</a>
	<a href="#" id="btn4">尾页</a>
	<a>转到 </a>
	<input id="changePage" type="text" size="1" maxlength="4" />
	<a>页 </a>
	<a href="#" id="btn5">跳转</a>
	<table id="mytable" align="center">
		<tr>
			<th width="100px">作者名</th>
			<th width="200px">诗词名</th>
			<th>诗词内容</th>
		</tr>
		<%
			while (iter.hasNext()) {
				Poet poet = (Poet) iter.next();
		%>
		<tr>
			<td><%=poet.getName()%></td>
			<td><%=poet.getTitle()%></td>
			<td><%=poet.getContent()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>