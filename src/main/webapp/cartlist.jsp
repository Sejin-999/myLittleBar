<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384- b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script> -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link rel="stylesheet" href="common.css">
<title>likeList</title>

</head>
<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>
	<div id='body-wrapper'>
		<div id="body-content">
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
											<c:when test="${cartlist != null && fn:length(cartlist)>0}">
												<c:forEach var="cart" items="${cartlist}" varStatus="status">
													<c:if test="{i%j==0}">
														<tr style="padding-right: 5%; padding-left: 5%;">
													</c:if>
													<td
														style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
														<a href="#">
															<figure>
																<img src="${drink.image}" style="width: 100%">
																<figcaption>${drink.name}</figcaption>
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
												<div
													class="alert alert-danger alert-dismissible fade show mt-3">
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
		</div>
		<footer class='footer'>
			<%@ include file="../footer.jsp"%>
		</footer>
	</div>

</body>

</html>