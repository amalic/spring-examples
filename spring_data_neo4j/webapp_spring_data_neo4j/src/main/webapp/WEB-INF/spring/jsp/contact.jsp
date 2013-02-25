<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Contact Manager</h2>
	
	<form:form method="post" action="add.html" commandName="contact">
		<table>
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
				<td colspan="2"><input type="submit" value="Add Contact"/></td>
			</tr>
		</table>
	</form:form>
	
	<h3><a href="./rest/contacts">Contacts</a></h3>
	
	<c:if test="${!empty contactList}">
		<table class="data" border="1">
			<tr>
				<th>id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Telephone</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${contactList}" var="contact">
				<tr>
					<td><a href="./rest/contact/${contact.id}">${contact.id}</a></td>
					<td>${contact.lastName}, ${contact.firstName}</td>
					<td>${contact.email}</td>
					<td>${contact.telephone}</td>
					<td><a href="delete/${contact.id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>