<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<p>Hello Film Buff</p>
	
	<h3>Search For A Film by ID</h3>
	<form action="getFilmData.do" method="GET">
		Film ID:
		<input type="text" name="filmID" size="4"/> 
		<input type="submit" value="Get Film Data" />
	</form>
	
	<h3>Add A New Film To The Database</h3>
	<form action="NewFilmPage.do" method="GET">
		Film:
		<input type="submit" value="Create New Film" />
	</form>
	
	
	
</body>
</html>