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
<body style="background-color: #230312;" class="min-vh-100">
<%@ include file="../navbar.jsp" %>

 <div class="container">
 		<div class="jumbotron mt-5 mh-100">
 			<form method="post" action="/MyLittleCocktail/userController?action=signUp">
 				<h3 style="text-align:center;">회원가입</h3> 
 				<div class="form-gruop mt-5">
 					<input type="text" class="form-control" placeholder="아이디" name="email" maxlength="20" required>
 				</div> 
 				<div class="form-gruop mt-2">
 					<input type="password" class="form-control" placeholder="비밀번호" name="password" maxlength="20" required>
 				</div>
 				<div class="form-gruop mt-2">
 					<input type="password" class="form-control" placeholder="비밀번호 재입력" name="rePassword" maxlength="20" required>
 				</div>
 				<div class="form-gruop mt-2">
 					<input type="text" class="form-control" placeholder="성명" name="name" maxlength="20" required>
 					
 				</div>
 				<input type="submit" class="btn btn-secondary form-control mt-2" value="회원가입">
 			</form>
 		
		<% if("${error}" != null) {%>
 			<div class="alert alert-danger text-center mt-2" role="alert">${error}</div>
 			<% } %>
 	</div>
 	
 	
 </div>
</body>

<footer class="fixed-bottom">
<%@ include file="../footer.jsp" %>
</footer>

</html>
