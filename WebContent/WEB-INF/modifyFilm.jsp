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
	<form action="NewFilm.do" method="POST">
		<input type="text" name="title" value="${film.title}" > 
		<input type="submit" value="Create New Film" >
	</form>
	<br>




</body>
</html>