<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film</title>
</head>
<body>
<h2>You Have Chosen the Film</h2>
	<c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>${film.title}</li>
        <li>${film.description}</li>
        <li>${film.releaseYear}</li>
        <li>${film.language}</li>
        <li>${film.rentalDuration}</li>
        <li>${film.rentalRate}</li>
        <li>${film.length}</li>
        <li>${film.replacementCost}</li>
        <li>${film.rating}</li>
        <li>${film.specialFeatures}</li>
      </ul>
      <h3>Actors</h3>
      <ul>
        <c:forEach var="actor" items="${film.actors}">
        <li>${actor.firstName} ${actor.lastName}</li>
        </c:forEach>
        </ul>
      <h3>Categories:</h3>
      <ul>
        <c:forEach var="category" items="${film.categories}">
        <li>${category.name}</li>
        </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
  <c:if test="${! empty film}">
	<form action="modifyFilm.do" method="GET">
		<input type="hidden" name="filmId" value="${film.id}" />
		<input type="submit" name="Edit" value="Edit" />
	</form>
	<form action="deleteFilm.do" method="POST">
		<input type="hidden" name="filmId" value="${film.id}" />
		<input type="submit" name="Delete" value="Delete" />
	</form>
	</c:if>
	<br>
	<form action="home.do" method="GET">
		<input type="submit" value="Home" />
	</form>
</body>


</html>