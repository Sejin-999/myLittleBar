<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>navbar</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Courgette&family=Ruda:wght@500&display=swap')
	;

nav {
	font-family: "Courgette", sans-serif;
}
</style>
<body>
	<%
	String userEmail = null;
	boolean isAdmin = false;
	int userId = 0;
	if (session.getAttribute("userEmail") != null) {
		userEmail = (String) session.getAttribute("userEmail");
	}
	if (session.getAttribute("isAdmin") != null) {
		isAdmin = (boolean) session.getAttribute("isAdmin");
	}
	if (session.getAttribute("userId") != null) {
		userId = (Integer) session.getAttribute("userId");
	}
	%>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark"
		style="color: black; margin-bottom: 1.5rem;">
		<a class="navbar-brand" href="drinkController" style="color: #FFE071">My Little Bar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<%
		if (isAdmin) {
		%>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav text-center ml-auto">
				<li class="nav-link pr-5" style="color:#40A940">${userEmail}</li>
				<li class="nav-item"><a class="nav-link"
					href="/MyLittleCocktail/adminRecipeController?action=defaultView">
Recipe</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/MyLittleCocktail/userController?action=manageUser">Users</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="/MyLittleCocktail/userController?action=logout">LogOut</a></li>
			</ul>
		</div>
		<%
		} else if (userEmail != null) {
		%>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">

			<ul class="navbar-nav text-center ml-auto">
				<li class="nav-link pr-5" style="color:#40A940">${userEmail}</li>

				<li class="nav-item"><a class="nav-link"
					href="drinkController?action=getCartAll&user_id=${userId}">
			
				Carts</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/MyLittleCocktail/userController?action=logout">LogOut</a></li>
			</ul>
		</div>

		<%
		} else {
		%>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav text-center ml-auto">
				<li class="nav-item"><a class="nav-link" href=signIn.jsp>Sign
						in </a></li>
				<li class="nav-item"><a class="nav-link" href="signUp.jsp">Sign
						up</a></li>
			</ul>
		</div>

		<%
		}
		%>

	</nav>
</body>