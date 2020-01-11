<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>${film.title}</li>
        <li>${film.}</li>
        <li>${film.}</li>
        <li>${film.}</li>
        <li>${film.}</li>
        <li>${film.}</li>
        
      </ul>
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
</body>
</html>