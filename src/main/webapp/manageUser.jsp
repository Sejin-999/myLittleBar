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
<script src="./resources/js/jquery-3.5.1.min.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<title>SearchList</title>
</head>
<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-dark">
						<thead class="">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Email</th>
								<th scope="col"><span>Name</span></th>
								<th scope="col"><span>Role</span></th>
								<th scope="col"><span>Setting</span></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${userList}" varStatus="status">
								<tr class="align-center">
									<td class="align-middle">${user.user_id}
									</th>
									<td class="align-middle">${user.email}</td>
									<td class="align-middle">
									${user.name}
							</td>
									<td class="align-middle">
									<c:if test="${user.is_admin}">
										ADMIN
									</c:if> 
									<c:if test="${!user.is_admin}">
										USER
									</c:if>
									</td>
									
									
									<c:if test="${!user.is_admin}">
										<td class="align-middle"><a href="#link"
										class="btn btn-info" role="button">X</a></td>
									</c:if> 
									
									<c:if test="${user.is_admin}">
										<td class="align-middle"></td>
									</c:if> 
									
									
								</tr>

							</c:forEach>



						</tbody>
					</table>


				</div>
			</div>
</body>

<footer class="fixed-bottom">
	<%@ include file="../footer.jsp"%>
</footer>


</html>
