<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link rel="stylesheet" href="common.css">
<script src="./resources/js/jquery-3.5.1.min.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<title>MyLittleBar</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Courgette&family=Ruda:wght@500&family=Signika:wght@500&display=swap');
h3{
	font-family: "Courgette", sans-serif;
}
div{
	font-family: "Courgette", sans-serif;
}
</style>
</head>
<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>

	<article>
		<div class="container" style="color: white;">
			<div class="jumbotron mt-5 mh-100">
				<form method="post"
					action="/MyLittleCocktail/userController?action=signUp">
					<h3 class="text-dark" style="text-align: center;">Sign up</h3>
					<div class="form-gruop mt-5">
						<input type="text" class="form-control" placeholder="id"
							name="email" maxlength="20" required>
					</div>
					<div class="form-gruop mt-2">
						<input type="password" class="form-control" placeholder="Password"
							name="password" maxlength="20" required>
					</div>
					<div class="form-gruop mt-2">
						<input type="password" class="form-control" placeholder="Re-enter Password"
							name="rePassword" maxlength="20" required>
					</div>
					<div class="form-gruop mt-2">
						<input type="text" class="form-control" placeholder="Name"
							name="name" maxlength="20" required>
					</div>
					<input type="submit" class="btn btn-warning form-control mt-2"
						value="Sign up">
				</form>


				<c:if test="${error != null}">
					<div class="alert alert-danger text-center mt-2" role="alert">${error}</div>
				</c:if>


			</div>
		</div>
	</article>
	<footer>
		<%@ include file="../footer.jsp"%>
	</footer>
</body>

</html>
