<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CharacterTracker</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" 
integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" 
crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="introPretty.css">
</head>
<body>
<div class="container">
<div class="row">
<div class="column">
<div class="container1">
<c:if test="${not empty characterList}">
<c:forEach items="${characterList}" var="characters">
${characters.name}
${characters.age}
${characters.gender}
${characters.position}
${characters.backstory}
</c:forEach>
</c:if>

<c:if test="${not empty selectCharacter }">
${selectCharacter.name }
${selectCharacter.age }
${selectCharacter.gender }
${selectCharacter.position }
${selectCharacter.backstory }
</c:if>

</div>
</div>
</div>
<div class="container">
<div class="column">
<div class="container2">
<form action="getCharacter.do" method="post" modelAttribute="id">
	Your character's name: <br>
	<input type="text" name="name">
	<br>
	Their age: <br>
	<input type="number" name="age">
	<br>
	Their gender: <br>
	<input type="text" name="gender">
	<br>
	Their role in the novel: <br>
	<input type="text" name="position">
	<br>
	A short backstory: <br>
	<textarea type="text" name="backstory" rows="10" columns="1" 
	placeholder="Enter a short backstory for your character">
	</textarea>
	<br>
	<input type="submit" value="Submit your character">
	
	
</form>
</div>
</div>
</div>
<div class="container">
<div class="column">
<div class="container3">
	<p>
	All characters: <br >
	<c:forEach var="character" items="${characterList}">
	
		<a href=" info.do?id=${character.id}">${character.name} </a> <%--  <img src=${gir.imageUrl}/> --%>
		<form method="post" action="delete.do">
			<input type="submit" value="Delete">
			<input type="hidden" name="id" value="${character.id }">
		</form>
		<form method="post" action="update.do">
			<input type="submit" value="Update">
			<input type="hidden" name="id" value="${character.id }">
		</form>
		<br>
	</c:forEach>
	</p>


</div>
</div>
</div>
</div>
</body>
</html>