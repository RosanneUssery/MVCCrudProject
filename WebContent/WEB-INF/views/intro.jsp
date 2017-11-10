<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CharacterTracker</title>
</head>
<body>

<form action="getCharacter.do" method="post">
	<input type="text" name="characterName">
	<input type="submit" value="Name Your Character">
</form>
<c:if test="${not empty selectCharacter }">
The character is ${selectCharacter }
</c:if>
</body>
</html>