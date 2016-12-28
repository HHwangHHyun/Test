<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
<% 	
	int no = Integer.parseInt(request.getParameter("no"));
	boolean flag = idao.checkRefno(no);
%> 
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>글 삭제 페이지</title> 
<script type="text/javascript">
function incheck(f) {
	if(f.passwd.value==""){
		alert("패스워드를 입력하세요.");
		f.passwd.focus();
		return false;
	}
}

</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
<link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
<jsp:include page="/menu/top.jsp" flush="false"/>
<!-- *********************************************** -->
 
<DIV class="title">삭제</DIV>

<div class="content">
<%if(flag){ 
	out.print("답변있는 글이므로 삭제를 할 수 없습니다.<br>");
	out.print("<input type='button' value='다시시도' onclick='history.go(-2)'>");
}else{ %>
</div>
<div>
 
<FORM name='frm' method='POST' action='./deleteProc.jsp'onsubmit="return incheck(this)">
		<input type ="hidden" name="no" value="<%=request.getParameter("no") %>">
		<input name="col" value="<%=request.getParameter("col") %>" type="hidden">
		<input name="word" value="<%=request.getParameter("word") %>" type="hidden">
		<input name="nowPage" value="<%=request.getParameter("nowPage") %>" type="hidden">
  <TABLE>
    <TR>
      <TH>패스워드</TH>
      <TD><input type="password" name="passwd"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='삭제'>
    <input type='button' value='목록' onclick="history.back()">
  </DIV>
</FORM>
<%}%>
</div>
 
<!-- *********************************************** -->
<jsp:include page="/menu/bottom.jsp" flush="false"/>
</body>
<!-- *********************************************** -->
</html> 