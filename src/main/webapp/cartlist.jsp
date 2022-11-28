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
<title>likeList</title>
</head>
<body style="background-color: #230312;">
	<%@ include file="../navbar.jsp"%>
	<div class="container" style="color: white;">
		<table style="width: 100%">
			<tbody>
				<tr>
					<td style="width: 50%; vertical-align: top; text-align: center;">
						<h1 style="margin-top: 2%; margin-bottom: 5%;">찜 목록</h1>
					</td>
				</tr>
				<tr>
					<td>
						<table style="width: 100%; text-align: center;">
							<tbody>
								<!-- <c:forEach var="like" items="%{like}"> -->
								<tr style="padding-right: 5%; padding-left: 5%;">
									<td style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>

								</tr>
								<!-- </c:forEach> -->

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