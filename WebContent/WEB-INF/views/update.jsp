<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enter Character data</title>
</head>
<body>
	<h3>Update your Character</h3>
	<form:form action="updateCharacter.do" method="post" modelAttribute="character">
		Name	:	<form:input path="name"/><form:errors path="name"/>	<br>
		Age:	 <form:input path="age"/>	<form:errors path="age"/>	<br>
		Gender: <form:input path="gender"/><form:errors path="gender"/>		<br>
		<form:hidden path="id"/>

		<input type="submit" value="Update"/>
	</form:form>
</body>
</html>