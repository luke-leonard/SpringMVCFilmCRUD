<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Films</title>
</head>
<body>
	<h2>You Have Chosen the Film</h2>
	<c:if test="${! empty film}">
		<a href="showFilm.do?filmId=${film.id}">${film.title}</a><br>
	</c:if>
	<c:if test="${! empty films}">
		<ul>
		<c:forEach var="singleFilm" items="${films}">
		<li><a href="showFilm.do?filmId=${singleFilm.id}">${singleFilm.title}</a>
		</li>
		</c:forEach>
		</ul>
	</c:if>

	<form action="home.do" method="GET">
		<input type="submit" value="Home" />
	</form>
</body>


</html>