<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CharacterTracker</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="introPretty.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<br>
		</div>
		<div class="row">
			<div class="col-12">
				<h1>Character Tracker Website</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<c:if test="${not empty characters}">
					 <c:forEach items="${characters}" var="x"> 
								${x.name}
								${x.age}
								${x.gender}
								${x.role}</br>
								${x.backstory}<br>
						<br>
				 </c:forEach>
				</c:if>

			</div>
			<div class="col-sm-3">
				<div class="entryBox">
					<form action="getCharacter.do" method="post" modelAttribute="id">
						Your character's name: <br> <input type="text" name="name">
						<br> Their age: <br> <input type="number" name="age">
						<br> Their gender: <br> <select name="gender">
							<option value="female">Female</option>
							<option value="male">Male</option>
							<option value="agender">Agender</option>
							<option value="non-gender-conforming">Non-gender
								conforming</option>
							<option value="other">Other</option>
						</select> <br> Their role in the novel: <br> <select
							name="position">
							<option value="protagonist">Protagonist</option>
							<option value="antagonist">Antagonist</option>
							<option value="sidekick">Sidekick</option>
							<option value="love interest">Love interest</option>
							<option value="guide">Guide</option>
							<option value="helper">Helper</option>
						</select> <br> A short backstory: <br>
						<textarea type="text" name="backstory" rows="10" columns="1"
							placeholder="Enter a short backstory for your character">
	</textarea>
						<br> <input type="submit" value="Submit your character">
					</form>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="updateBoxes">
					<p>
						All characters: <br>
						<c:forEach var="character" items="${characters}">

							<a href=" info.do?id=${character.id}">${character.name} </a>
							<%--  <img src=${gir.imageUrl}/> --%>
							<form method="post" action="delete.do">
								<input type="submit" value="Delete"
									onclick="return confirm('Are you sure you want to delete?')">
								<input type="hidden" name="id" value="${character.id }">

							</form>
							<form method="post" action="update.do">
								<input type="submit" value="Update"> <input
									type="hidden" name="id" value="${character.id }">
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