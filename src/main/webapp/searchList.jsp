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
		<table style="width: 100%">
			<tbody>
				<tr>
					<td style="width: 35%; text-align: center; padding-right: 5%;">
						<h1 style="margin-top: 2%; margin-bottom: 5%;">GIN</h1>
					</td>
					<td style="width: 50%; vertical-align: top; text-align: center;">
						<h1 style="margin-top: 2%; margin-bottom: 5%;">Drinks</h1>
					</td>
				</tr>
				<tr>
					<td style="width: 35%; vertical-align: top; padding-right: 5%;">
						<img
						src="data:image/jpeg;base64,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"
						style="width: 100%">
					</td>
					<td>
						<table style="width: 100%; text-align: center;">
							<tbody>
								<tr style="padding-right: 5%; padding-left: 5%;">
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>

								</tr>
								<tr style="padding-right: 5%; padding-left: 5%;">
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>

								</tr>
								<tr style="padding-right: 5%; padding-left: 5%;">
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>
									<td
										style="vertical-alight: top; padding-right: 2.5%; padding-left: 2.5%;">
										<figure>
											<img
												src="https://www.thecocktaildb.com/images/media/drink/mr30ob1582479875.jpg/preview"
												style="width: 100%">
											<figcaption>Abbey Cocktail</figcaption>
										</figure>
									</td>

								</tr>

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
