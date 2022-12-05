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
<style>
html, body {
	height: 100%
}

#wrapper {
	min-height: 90%;
	position: relative;
}

.footer {
	position: relative; 
	transform: translatY(-100%);
}
</style>
</head>

<body style="background-color: #230312;">

	<div id='wrapper'>
		<%@ include file="../navbar.jsp"%>
		<div class='container'>
			<a href="drinkController?action=getSearchList&base_id=1">리스트 이동</a>
		</div>
	</div>
	<footer style='footer'>
		<%@ include file="../footer.jsp"%>
	</footer>

</body>



<%-- <footer class="relative-bottom">
<%@ include file="../footer.jsp" %>
</footer>
 --%>

</html>
