<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %> <!-- 자바 스크립트 문장의 작성을 위해 사용-->
<meta charset="UTF-8">
<% 
if(session.getAttribute("isAdmin") == null)
{
	 PrintWriter script = response.getWriter();
	 script.println("<script>");
	 script.println("alert('비정상적인 접근 입니다.')");
	 script.println("location.href = 'main.jsp'");
	 script.println("</script>");
}

%>
