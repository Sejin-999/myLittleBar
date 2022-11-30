<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>likeList</title>
</head>
<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>
	<div class="container" style="color: white;">
		<table style="width: 100%">
			<tbody>
				<tr>
					<td style="width: 50%; vertical-align: top; text-align: center;">
						<h1 style="margin-top: 2%; margin-bottom: 5%;">Likes</h1>
					</td>
				</tr>
				<tr>
					<td>
						<table style="width: 100%; text-align: center;">
							<tbody>
								<c:set var="i" value="0" />
								<c:set var="j" value="4" />
								<c:choose>
									<c:when test="${likedrink != null && fn:length(likedrink)>0}">
										<c:forEach var="like" items="${likedrink}"
											varStatus="status">
											<c:if test="{i%j==0}">
												<tr style="padding-right: 5%; padding-left: 5%;">
											</c:if>
											<td
												style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
												<a href="#">
													<figure>
														<img src="${like.image}" style="width: 100%">
														<figcaption>${like.name}</figcaption>
													</figure>
											</a>
											</td>
											<c:if test="${i%j == j-1}">
				</tr>
				</c:if>
				<c:set var="i" value="${i+1}" />
				</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger alert-dismissible fade show mt-3">
						에러 발생: ${error}</div>
				</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		</td>


		</tr>
		</tbody>
		</table>

	</div>
	<%@ include file="../footer.jsp"%>
</body>
</html>