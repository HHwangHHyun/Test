<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="imgbbs.*"%>

<%@ page import="utility.*"%>
<jsp:useBean id="idao" class="imgbbs.BbsDAO" />
<%
	request.setCharacterEncoding("utf-8");
	String root = request.getContextPath();
	//--업로드용 변수선언(폴더형)

%>
