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

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

<script src="./resources/js/jquery-3.5.1.min.js"></script>
<script src="./resources/js/bootstrap.bundle.min.js"></script>
<title>SearchList</title>
</head>
<body style="background-color: #230312;">

	<style>
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

	<%@ include file="../navbar.jsp"%>
	<%@ include file="../checkValid.jsp"%>



	<div class="container main-wrapper">
		<c:if test="${error != null}">
			<div class="alert alert-danger text-center mt-2" role="alert">${error}</div>
		</c:if>




		<div class="row text-center main-wrapper">

			<div class="col-4 image align align-self-center ">
				<img class=" imgSize"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnleqeY06WPRQG9isU963b92HCCrXCL6fAvg&usqp=CAU" />
				<a class="btn btn-primary mx-1 mt-2" data-toggle="modal"
					href="#registerBase">베이스 등록</a>
			</div>

			<div class="col-4 image align align-self-center ">
				<img class=" imgSize"
					src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3QgK52TAjJR3EIKgwo3UJDRsqwJMBkIGyfg&usqp=CAU" />
				<a class="btn btn-primary mx-1 mt-2" data-toggle="modal"
					href="#registerIngredient">재료 등록</a>
			</div>

			<div class="col-4 image align align-self-center ">
				<img class=" imgSize"
					src="https://media.timeout.com/images/105631937/750/422/image.jpg" />
				<a class="btn btn-primary mx-1 mt-2" data-toggle="modal"
					href="#registerCocktail">칵테일 레시피 등록</a>
			</div>



		</div>


		<div class="modal fade text-center" id="registerIngredient" tabindex="-1"
			role="dialog" aria-labelledby="modal" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 전반적으로 모달 창 임을 알려줌. -->
				<div class="modal-content">
					<!-- 모달 내용이 들어감 -->
					<div class="modal-header">
						<!-- 모달 내용의 맨 윗부분 -->
						<h5 class="modal-title" id="modal">재료 등록</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
							<!-- x기호 -->
						</button>
					</div>
					<div class="modal-body">
						<form method="post" action="#"
							enctype="multipart/form-data">
							<label class="form-label fs-5">재료명</label> <input type="text"
								name="title" class="form-control"> <label
								class="form-label fs-5">이미지</label> <input type="file" name="file"
								class="form-control">
							<button type="submit" class="btn btn-success mt-3">등록하기</button>
						</form>

					</div>
				</div>
			</div>
		</div>


		<div class="modal fade text-center" id="registerBase" tabindex="-1" role="dialog"
			aria-labelledby="modal" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 전반적으로 모달 창 임을 알려줌. -->
				<div class="modal-content">
					<!-- 모달 내용이 들어감 -->
					<div class="modal-header">
						<!-- 모달 내용의 맨 윗부분 -->
						<h5 class="modal-title" id="modal">베이스 등록</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
							<!-- x기호 -->
						</button>
					</div>
					<div class="modal-body">
						<form method="post" action="/MyLittleCocktail/adminRecipeController?action=uploadBase"
							enctype="multipart/form-data">
							<label class="form-label fs-5">베이스명</label> <input type="text"
								name="name" class="form-control"> <label
								class="form-label fs-5">이미지</label> <input type="file" name="file"
								class="form-control">
							<button type="submit" class="btn btn-success mt-3">등록하기</button>
						</form>

					</div>
				</div>
			</div>
		</div>


		<div class="modal fade text-center" id="registerCocktail" tabindex="-1"
			role="dialog" aria-labelledby="modal" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 전반적으로 모달 창 임을 알려줌. -->
				<div class="modal-content">
					<!-- 모달 내용이 들어감 -->
					<div class="modal-header">
						<!-- 모달 내용의 맨 윗부분 -->
						<h5 class="modal-title" id="modal">칵테일 레시피 등록</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
							<!-- x기호 -->
						</button>
					</div>

					<div class="modal-body text-center">

						<form method="post" action="#"
							enctype="multipart/form-data">
							<label class="form-label fs-5">칵테일명</label> <input type="text"
								name="title" class="form-control"> <label
								class="form-label fs-5">이미지</label> <input type="file" name="file"
								class="form-control"> <br> <label class="fs-5">베이스 선택</label> <select
								name="rec_type" class="form-control">
								<option value="--">--</option>
								<option value="1">콜라</option>
								<option value="2">레몬</option>
								<option value="3">재료2</option>
								<option value="4">재료3</option>

							</select> <br>
							
							<label class="fs-5">재료 선택</label>
							
							<table id="insertTable" class="table"
								style="text-align: center; border: 1px solid #dddddd">

								<tbody>
								
									<tr>
										<td><select name="base_type" class="form-control">
												<option value="--">--</option>
												<option value="1">콜라</option>
												<option value="2">레몬</option>
												<option value="3">재료2</option>
												<option value="4">재료3</option>

										</select></td>

									</tr>
								</tbody>

							</table>
						
							<input type="button" class="btn btn-success  mt-3" value="재료 추가"
								onClick="addIngredient()">
							<button type="submit" class="btn btn-success mt-3">등록하기</button>
						

						</form>

					</div>
				</div>
			</div>
		</div>

	</div>

	<script>
		var rowIndex = 1;
		function addIngredient() {
			var oCurrentRow, oCurrentCell;
			var sAddingHtml;
			oCurrentRow = insertTable.insertRow();
			rowIndex = oCurrentRow.rowIndex;
			oCurrentCell = oCurrentRow.insertCell();
			rowIndex++;
			oCurrentCell.innerHTML = "<select name='ingredient" + rowIndex + "' class='form-control'> <option value='--'>--</option> <option value='수필'>콜라</option> <option value='소설'>ㅇ</option>		</select>"
			form.rowCount.value = rowIndex;

		}
	</script>
</body>

<footer class="">
	<%@ include file="../footer.jsp"%>
</footer>


</html>
