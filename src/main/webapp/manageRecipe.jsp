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

<style>

	
	.imgSize {
	
    top: 0;
    left: 0;
    transform: translate(50, 50);
    width: 100%;
    height: 100%;
    object-fit: fill;
	}
	.image{

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
				 <img class=" imgSize" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnleqeY06WPRQG9isU963b92HCCrXCL6fAvg&usqp=CAU"/> 
				 <a class="btn btn-primary mx-1 mt-2" data-toggle="modal" href="#registerModal">베이스 등록</a>
				</div>

				<div class="col-4 image align align-self-center ">
			  	<img class=" imgSize" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3QgK52TAjJR3EIKgwo3UJDRsqwJMBkIGyfg&usqp=CAU"/> 			
				<a class="btn btn-primary mx-1 mt-2" data-toggle="modal" href="#registerModal">재료 등록</a>
				</div>
			
				<div class="col-4 image align align-self-center ">
			    <img class=" imgSize" src="https://media.timeout.com/images/105631937/750/422/image.jpg"/> 
				<a class="btn btn-primary mx-1 mt-2" data-toggle="modal" href="#registerModal">칵테일 레시피 등록</a>
				</div>
			
		
			
		</div>
  	
  		
  		<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true"> <!-- tabindex는 레이아웃 느낌 , modal은 새로 창 띄우는거 -->
   	<div class="modal-dialog"> <!-- 전반적으로 모달 창 임을 알려줌. -->
   		<div class="modal-content"> <!-- 모달 내용이 들어감 -->
   			<div class="modal-header"> <!-- 모달 내용의 맨 윗부분 -->
	   			<h5 class="modal-title" id="modal">기본 재료 등록</h5>
	   			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	   				<span aria-hidden="true">&times;</span> <!-- x기호 -->
	   			</button>
	   		</div>
	   		<div class="modal-body">
		   			<form action="./rec_writeAction.jsp" method="post">
		   				<div class="form-row"> <!-- 한개의 행은 12열인데 6과 6으로 나눔 -->
		   					
		   		        </div>
		   		        <div class="modal-footer">
		   		        	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
		   		        	<button type="submit" class="btn btn-primary" >등록하기</button>
		   		        </div>
		   			</form>
		   			
	   		</div>
	   	</div>
   	</div>
  </div>


			
	</div>
</body>

<footer class="">
	<%@ include file="../footer.jsp"%>
</footer>


</html>
