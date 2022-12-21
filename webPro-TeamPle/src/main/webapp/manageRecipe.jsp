<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384- BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<link href="/docs/5.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link rel="stylesheet" href="common.css">
<script src="./resources/js/jquery-3.5.1.min.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<title>SearchList</title>

<style>
@import url('https://fonts.googleapis.com/css2?family=Courgette&family=Ruda:wght@500&family=Signika:wght@500&display=swap');
a,h5,label,button{
	font-family: "Courgette", sans-serif;
}
label{
	font-family: "Courgette", sans-serif;
}
div{
	font-family: "Courgette", sans-serif;
}



.imgSize {
	top: 0;
	left: 0;
	transform: translate(50, 50);
	width: 100%;
	height: 100%;
	object-fit: fill;
}

.image {
	width: 300px;
	height: 300px;
}

.main-wrapper {
	height: 70vh;
}
</style>
</head>


<body style="background-color: #230312;">
	<%@ include file="../checkValid.jsp"%>
	<%@ include file="../navbar.jsp"%>
	<article>
		<div class="container" style="color: white;">

			<c:if test="${error != null}">
				<div class="alert alert-danger text-center mt-2" role="alert">${error}</div>
			</c:if>


			<div class="row text-center main-wrapper">

				<div class="col-4 image align align-self-center ">
					<img class="imgSize"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnleqeY06WPRQG9isU963b92HCCrXCL6fAvg&usqp=CAU" />
					<a class="btn btn-warning mx-1 mt-2" data-toggle="modal"
						href="#registerBase">Base Register</a>
				</div>

				<div class="col-4 image align align-self-center ">
					<img class=" imgSize"
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3QgK52TAjJR3EIKgwo3UJDRsqwJMBkIGyfg&usqp=CAU" />
					<a class="btn btn-warning mx-1 mt-2" data-toggle="modal"
						href="#registerIngredient">Ingredient Register</a>
				</div>

				<div class="col-4 image align align-self-center ">
					<img class=" imgSize"
						src="https://media.timeout.com/images/105631937/750/422/image.jpg" />
					<a class="btn btn-warning mx-1 mt-2" data-toggle="modal"
						href="#registerCocktail">Cocktail Recipe Register</a>
				</div>



			</div>


			<div class="modal fade text-center" id="registerIngredient"
				tabindex="-1" role="dialog" aria-labelledby="modal"
				aria-hidden="true">
				<div class="modal-dialog">
					<!-- 전반적으로 모달 창 임을 알려줌. -->
					<div class="modal-content">
						<!-- 모달 내용이 들어감 -->
						<div class="modal-header">
							<!-- 모달 내용의 맨 윗부분 -->
							<h5 class="modal-title text-dark" id="modal">Ingredient Register</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
								<!-- x기호 -->
							</button>
						</div>
						<div class="modal-body">
							<form method="post"
								action="/MyLittleCocktail/adminRecipeController?action=uploadIngredient"
								enctype="multipart/form-data">
								<label class="form-label fs-5 text-dark">Ingredient Name</label> <input
									type="text" name="name" class="form-control"> <label
									class="form-label fs-5 text-dark">image</label> <input
									type="file" name="file" accept="image/*" class="form-control">
								<button type="submit" class="btn btn-success mt-3">Register</button>
							</form>

						</div>
					</div>
				</div>
			</div>

			<div class="modal fade text-center" id="registerBase" tabindex="-1"
				role="dialog" aria-labelledby="modal" aria-hidden="true">
				<div class="modal-dialog">
					<!-- 전반적으로 모달 창 임을 알려줌. -->
					<div class="modal-content">
						<!-- 모달 내용이 들어감 -->
						<div class="modal-header">
							<!-- 모달 내용의 맨 윗부분 -->
							<h5 class="modal-title text-dark" id="modal">Base Register</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
								<!-- x기호 -->
							</button>
						</div>
						<div class="modal-body">
							<form method="post"
								action="/MyLittleCocktail/adminRecipeController?action=uploadBase"
								enctype="multipart/form-data">
								<label class="form-label fs-5 text-dark">Base Name</label> <input
									type="text" name="name" class="form-control"> <label
									class="form-label fs-5 text-dark">image</label> <input
									type="file" name="file" accept="image/*" class="form-control">
								<button type="submit" class="btn btn-success mt-3">Register</button>
							</form>

						</div>
					</div>
				</div>
			</div>

			<div class="modal fade text-center" id="registerCocktail"
				tabindex="-1" role="dialog" aria-labelledby="modal"
				aria-hidden="true">
				<div class="modal-dialog">
					<!-- 전반적으로 모달 창 임을 알려줌. -->
					<div class="modal-content">
						<!-- 모달 내용이 들어감 -->
						<div class="modal-header">
							<!-- 모달 내용의 맨 윗부분 -->
							<h5 class="modal-title text-dark" id="modal">Cocktail Recipe Register</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
								<!-- x기호 -->
							</button>
						</div>

						<div class="modal-body text-center">


							<form method="post"
								action="/MyLittleCocktail/adminRecipeController?action=uploadCocktail"
								enctype="multipart/form-data">
								<label class="form-label fs-5 text-dark">Cocktail Name</label> <input
									type="text" name="title" class="form-control"> <label
									class="form-label fs-5 text-dark">image</label> <input
									type="file" name="file" accept="image/*" class="form-control ">
								<br> <label class="fs-5 text-dark">Select Base</label> <select
									name="base_type" class="form-control">

									<option value="0">--</option>

									<c:if test="${baseList != null}">
										<c:forEach var="base" items="${baseList}" varStatus="status">
											<option value="${base.base_id}">${base.name}</option>
										</c:forEach>
									</c:if>

								</select> <br> <label class="fs-5 text-dark">Select Ingredient</label>

								<table id="insertTable" class="table"
									style="text-align: center; border: 1px solid #dddddd">

									<tbody>

										<tr>
											<td><select name="ingredient" class="form-control">
													<option value="0">--</option>
													<c:if test="${baseList != null}">
														<c:forEach var="ingredient" items="${ingredientList}"
															varStatus="status">
															<option value="${ingredient.ingredient_id}">${ingredient.name}</option>
														</c:forEach>
													</c:if>

											</select></td>

										</tr>
									</tbody>

								</table>

								<input type="button" class="btn btn-success  mt-3" value="Add Ingredient"
									onClick="addIngredient()">
								<button type="submit" class="btn btn-success mt-3">Register</button>


							</form>

						</div>
					</div>
				</div>
			</div>


		</div>

		<script>
			function addIngredient() {
				var oCurrentRow, oCurrentCell;
				var sAddingHtml;
				oCurrentRow = insertTable.insertRow();
				rowIndex = oCurrentRow.rowIndex;
				oCurrentCell = oCurrentRow.insertCell();
				rowIndex++;
				oCurrentCell.innerHTML = "<select name='ingredient' class='form-control'> <option value='0'>--</option><c:if test='${baseList != null}'><c:forEach var='ingredient' items='${ingredientList}' varStatus='status'>	<option value='${ingredient.ingredient_id}'>${ingredient.name}</option></c:forEach>	</c:if></select>"

			}
		</script>

	</article>
	<footer class="">
		<%@ include file="../footer.jsp"%>
	</footer>
</body>




</html>
