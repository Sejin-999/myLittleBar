<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import ="CockTailDetail.Cocktail" %>
<%@page import ="CockTailDetail.CockTailDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyLittleBar</title>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<% 
	 Cocktail ct = new Cocktail();
	 out.println(ct.getcName());
	%>
	<div class="container">
	
	</div>
<footer class="fixed-bottom">
<%@ include file="../footer.jsp" %>
</footer>
</body>
</html>