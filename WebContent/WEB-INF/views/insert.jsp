<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enter Character data</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" 
integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" 
crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="introPretty.css">
<link rel="stylesheet" type="text/css" href="updatePretty.css">
</head>
<body>
<div class="container">
	<h3>Add New Character</h3>
	<form:form action="updateCharacter.do" method="post" modelAttribute="character">
		Name	:	<form:input path="name"/><form:errors path="name"/>	<br>
		Age:	 <form:input path="age"/>	<form:errors path="age"/>	<br>
		Gender: <form:select path="gender">
		    <option value="female">Female</option>
		    <option value="male">Male</option>
		    <option value="agender">Agender</option>
		    <option value="Non-gender conforming">Non-gender conforming</option>
		</form:select><form:errors path="gender"/>		<br>
		Role: <form:select path="gender">
			<option value="protagonist">Protagonist</option>
		    <option value="antagonist">Antagonist</option>
		    <option value="sidekick">Sidekick</option>
		    <option value="love interest">Love interest</option>
		    <option value="guide">Guide</option>
		    <option value="helper">Helper</option>
		</form:select><form:errors path="role"/>		<br>
		Backstory: 
		<form:input path="backstory"/>
		<form:errors path="backstory"/>	<br>
		<form:hidden path="id"/>
		<input type="submit" value="Update"/>
	</form:form>
</div>
</body>
</html>