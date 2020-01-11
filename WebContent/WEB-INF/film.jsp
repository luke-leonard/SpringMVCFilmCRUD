<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>You Have Chosen the Film</h2>
	<c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>${film.title}</li>
        <li>${film.description}</li>
        <li>${film.releaseYear}</li>
        <li>${film.languageId}</li>
        <li>${film.rentalDuration}</li>
        <li>${film.rentalRate}</li>
        <li>${film.length}</li>
        <li>${film.replacementCost}</li>
        <li>${film.rating}</li>
        <li>${film.specialFeatures}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
  <c:if test="${! empty film}">
  <h3>Edit This Film</h3>
	<form action="modifyFilm.do" method="GET">
		Film Editor:
		<input type="submit" name="filmId" value="${film.id}" />
	</form>
	</c:if>
	
	<form action="home.do" method="GET">
		<input type="submit" value="Home" />
	</form>
</body>


</html>