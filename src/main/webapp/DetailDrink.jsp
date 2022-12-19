<%@page import="DrinkDetail.Ingredient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="detail.css">
    <link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="../navbar.jsp"%>
	 <div class="container">
        <div class="drink">
            <div class="imgBx">
                <img class="container__drink__img" src="${drink.image}">
            </div>
            <div class="title">
                <h2>${drink.name}</h2>
            </div>
            <div class="content">
            	<span>${info.getDrinkInfo_content()}</span>
            	<p><a href="${info.getDrinkInfo_URL()}"  target='_blank'>자세히 보기</a></p>
            </div>
        </div>
         
        <div class="content-box">
            <div class="drink">
                <div class="imgBx">
                    <img class="container__drink__img" src="${base.base_image}">
                </div>
                <div class="title">
                    <h2>${base.base_name}</h2>
                </div>
            </div>

            <div class="drink">
               <% List<Ingredient> getInList = (List<Ingredient>)request.getAttribute("ingredientList");
               
					for(Ingredient in:getInList){
						out.println(" <div class='imgBx'> <img  class='container__drink__img' src ='" +in.getIngredient_Image() +"'>" +"</div>");
						out.println("<div class='title'><h2>"+in.getIngredient_name()+"</h2></div>");
					}
				
				%>
            </div>
            
        </div>
    </div>

	<footer class="">
			<%@ include file="../footer.jsp"%>
	</footer>

</body>
</html>