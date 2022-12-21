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
<link rel="stylesheet" href="common.css">
<script src="./resources/js/jquery-3.5.1.min.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<title>MyLittleBar</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Courgette&family=Ruda:wght@500&family=Signika:wght@500&display=swap');
h1{
	font-family: "Courgette", sans-serif;
}

figcaption{
	font-family: "Signika", sans-serif;
}

.image {
	width: 250px;
	height: 250px;
	margin-top: 1.5rem;
	margin-bottom: 3rem;
}

.imageSize {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.a:hover {
	text-decoration: none;
}
</style>
</head>

<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>
	<%if(userId!=0){ %>
	<%} %>
	<article>
		<div class='container'>
			<br>
			<h1 style="text-align: center; color: #FFE071; font-size: 4rem;">My
				Little Bar</h1>
			<br>

			<table style="width: 100%">
				<tbody>
					<c:set var="i" value="0" />
					<c:set var="j" value="3" />
					<c:choose>
						<c:when test="${baseList != null && fn:length(baseList)>0}">
							<c:forEach var="base" items="${baseList}" varStatus="status">
								<c:if test="{i%j==0}">
									<tr
										style="padding-right: 5%; padding-left: 5%; padding-top: 3%;">
								</c:if>
								<td
									style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
									<a class="a"
									href="drinkController?action=getSearchList&base_id=${base.base_id}">
										<figure class="image">
											<img class="imageSize" src="${base.image}">
											<figcaption
												style="text-align: center; color: #FFFFFF; text-decoration: none; font-size: 2rem;">${base.name}</figcaption>
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
		</div>
	</article>

	<footer>
		<%@ include file="../footer.jsp"%>
	</footer>



</body>


</html>
