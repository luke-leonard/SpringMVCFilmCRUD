<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Film</title>
</head>
<body>

	<form action="${redirectName}" method="POST">
	
		<label for="title">Title</label>
		<input type="text" name="title" value="${film.title}" > 
		<br>
		
		<label for="description">Description</label>
		<input type="text" name="description" value="${film.description}" >
		<br>
		
		<label for="releaseYear">Release Year</label> 
		<input type="text" name="releaseYear" value="${film.releaseYear}" >
		 <br>
		 
		<label for="languageId">Language ID</label>
		<input type="number" name="languageId" min="1" max="6" value="${film.languageId}" > 
		<br> 
		
		
		<label for="rentalDuration">Rental Duration</label>
		<input type="text" name="rentalDuration" value="${film.rentalDuration}" > 
		<br>
		
		<label for="rentalRate">Rental Rate</label>
		<input type="text" name="rentalRate" value="${film.rentalRate}" > 
		<br>
		
		<label for="length">Length</label>
		<input type="text" name="length" value="${film.length}" > 
		<br>
		
		<label for="replacementCost">Replacement Cost</label>
		<input type="text" name="replacementCost" value="${film.replacementCost}" >
		<br>
		
		<label for="rating">Rating</label> 
		<input type="number" name="rating" min="1" max="5" value="${film.rating}" > 
		<br>
		
		<label for="specialFeature">Special Features</label>
		<input type="number" name="specialFeatures" min="1" max="5" value="${film.specialFeatures}" > 
		<br>
		<input type="hidden" name="filmId" value="${film.id}" />
		<input type="submit" name="Submit" value="Submit" />
		
		
		<br><br><br>
	</form>
	<br>
	<form action="home.do" method="GET">
		<input type="submit" value="Home" />
	</form>




</body>
</html>