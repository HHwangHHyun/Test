<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String root = request.getContextPath();

	String id = (String)session.getAttribute("id");
%>

<html>
<head>
<style type="text/css">
ul#menu li {
	display: inline;
	margin: 0 auto;
	background-color: #533d3f;
}

ul#menu li a {
	background-color: #533d3f;
	color: #fff;
	pading: 10px 20px;
	text-decoration: none;
	border-radius: 4px 4px 0 0;
}

ul#menu li a:hover {
	background-color: #fff000;
	color: #533d3f;
	font-weight: bold;
}

ul#menu li a:SELECTION {
	background-color: #fff000;
	color: #533d3f;
	font-weight: bold;
}

ul#menu li a:CHECKED {
	background-color: #fff000;
	color: #533d3f;
	font-weight: bold;
}

.table {
	width: 100%;
}

.table, .td {
	border-style: none;
	padding: 0px;
	background-color: #533d3f;
}

.img {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<!-- 상단 메뉴 -->
	<div>
		<table class="table">
			<tr>
				<td class="td">
					<img src="<%=root%>/menu/images/main02.jpg" class="img">
				</td>
			</tr>

			<tr>
				<td class="td">
					<ul id="menu">
						<li><a href="<%=root%>/index.jsp">홈</a></li>
						<% if(id==null) {%>
						<li><a href="<%=root%>/member/agreement.jsp">회원가입</a></li>
						<li><a href="<%=root%>/member/loginForm.jsp">로그인</a></li>
						<%}else{%>
						<li><a href="<%=root%>/member/read.jsp">나의정보</a></li>
						<li><a href="<%=root%>/member/updateForm.jsp">정보수정</a></li>
						<li><a href="<%=root%>/member/deleteForm.jsp">회원탈퇴</a></li>
						<li><a href="<%=root%>/member/logout.jsp">로그아웃</a></li>
						<%}%>	
						<li><a href="<%=root%>/member/">메뉴</a></li>
						<li><a href="<%=root%>/member/list.jsp">회원목록</a></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
	<!-- 상단 메뉴 끝 -->

	<!-- 내용 시작 -->
	<div style="width: 100%; padding-top: 10px;">