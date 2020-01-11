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
	
	<h3>Film</h3>
	<form action="home.do" method="GET">
		Film ID:
		<input type="text" name="ID" size="4"/> 
		<input type="submit" value="Get Film Data" />
	</form>
</body>
</html>