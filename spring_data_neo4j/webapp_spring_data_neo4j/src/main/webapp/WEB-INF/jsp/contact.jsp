<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="./resources/site.css" />
	<script  type="text/javascript" src="./resources/jquery-1.9.1.min.js"></script>
	<script>
		$(document).ready(function(){
			$('#div1').load('./rest/contacts');
			$('.zebra').find('tr:odd').addClass('odd');
		});
	</script>
</head>
<body>
	<h2>Contact Manager</h2>
	
	<form:form method="post" action="save.html" commandName="contact">
		<form:input path="id" type="hidden"/>
		<table>
			<tr>
				<td>Username</td>
				<td><form:input path="userName"/></td>
			</tr>
			<tr>
				<td>First name</td>
				<td><form:input path="firstName"/></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><form:input path="lastName"/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<td>Telephone</td>
				<td><form:input path="telephone"/></td>
			</tr>
			<tr>
				<td>Country</td>
				<td><form:input path="country"/></td>
			</tr>
			<tr>
				<td>Manager</td>
				<td>
					<form:select path="manager.id">
						<form:options items="${managerList}" itemValue="id" itemLabel="lastName" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save Contact"/></td>
			</tr>
		</table>
	</form:form>
	
	<h2><a href="./rest/contacts">Contacts</a></h2>
	
	<c:if test="${!empty contactList}">
		<table class="datatable zebra">
			<tr>
				<th>id</th>
				<th>Username</th>
				<th>Name</th>
				<th>Email</th>
				<th>Telephone</th>
				<th>Country</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${contactList}" var="contact">
				<tr>
					<td><a href="./rest/contact/${contact.id}">${contact.id}</a></td>
					<td>${contact.userName}</td>
					<td>${contact.lastName}, ${contact.firstName}</td>
					<td>${contact.email}</td>
					<td>${contact.telephone}</td>
					<td>${contact.country}</td>
					<td><a href="${contact.id}">edit</a></td>
					<td><a href="delete/${contact.id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<h4>As JSON</h4>

	<div id="div1"></div>
	
</body>
</html>