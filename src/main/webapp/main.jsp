<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link href="/docs/5.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script src="./resources/js/jquery-3.5.1.min.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<title>SearchList</title>
</head>
<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>
		<div class="container" style="color: white;">
			<a href="drinkController?action=getSearchList&base_id=1">리스트 이동</a>
		</div>
	<%@ include file="../footer.jsp"%>
</body>

<footer class="fixed-bottom">
<%@ include file="../footer.jsp" %>
</footer>


</html>
