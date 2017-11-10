<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" 
integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" 
crossorigin="anonymous">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CharacterTracker</title>
<link rel="stylesheet" type="text/css" href="introPretty.css">
</head>
<body>

<form action="getCharacter.do" method="post">
	Your character's name: <br>
	<input type="text" name="characterName">
	<br>
	Their age: <br>
	<input type="number" name="characterAge">
	<br>
	Their gender: <br>
	<input type="text" name="characterGender">
	<br>
	Their role in the novel: <br>
	<input type="text" name="characterPosition">
	<br>
	A short backstory: <br>
	<textarea type="text" name="characterBackstory" rows="10" columns="1" 
	placeholder="Enter a short backstory for your character">
	</textarea>
	<br>
	<input type="submit" value="Submit your character">
	
	
</form>

<c:if test="${not empty selectCharacter }">
The character is ${selectCharacter }
</c:if>
</body>
</html>