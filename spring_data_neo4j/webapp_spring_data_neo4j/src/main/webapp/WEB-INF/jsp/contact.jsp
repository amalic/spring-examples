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
			$('.zebra').find('tr:odd').addClass('odd');
			$('#div1').load('./rest/contacts');
		});
	</script>
</head>
<body>
	<h2>Contact Manager</h2>
	
	<form:form method="post" action="save.html" commandName="contact">
		<form:input path="id" type="hidden"/>
		<table class="detailsTable">
			<tr>
				<th colspan="2">Details</th>
				<th>Reports To</th>
				<th>Direct Reports</th>
			</tr>
			<tr>
				<th>Username</th>
				<td><form:input path="userName"/></td>
				<td rowspan="8">
					<form:select path="reportsTo" multiple="true" size="13">
						<c:forEach var="ct" items="${contactList}">
							<c:if test="${contact.id!=ct.id}">
								<c:choose>
									<c:when test="${contact.reportsTo.contains(ct) eq true}">
										<form:option path="id" value="${ct.id}" selected="true"><c:out value="${ct.firstName} ${ct.lastName}"/></form:option>
									</c:when>
									<c:otherwise>
										<form:option path="id" value="${ct.id}"><c:out value="${ct.firstName} ${ct.lastName}"/></form:option>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</form:select>
				</td>
				<td rowspan="8">
					<form:select path="directReports" multiple="true" disabled="true" size="13">
						<form:options path="id" items="${contactList}" itemValue="id" itemLabel="userName"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th>First name</th>
				<td><form:input path="firstName"/></td>
			</tr>
			<tr>
				<th>Last name</th>
				<td><form:input path="lastName"/></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<th>Telephone</th>
				<td><form:input path="telephone"/></td>
			</tr>
			<tr>
				<th>Country</th>
				<td><form:input path="country"/></td>
			</tr>
			<tr>
				<th>Manager</th>
				<td>
					<form:select path="manager.id" multiple="false">
						<option value="">&nbsp;</option>
						<c:forEach var="mgr" items="${contactList}">
							<c:if test="${contact.id!=mgr.id}">
								<form:option value="${mgr.id}"><c:out value="${mgr.firstName} ${mgr.lastName}"/></form:option>
							</c:if>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th />
				<td><input type="submit" value="Save Contact"/></td>
			</tr>
		</table>
	</form:form>
	
	<h2><a href="./rest/contacts">Contacts</a></h2>
	
	<c:if test="${!empty contactList}">
		<table class="datatable zebra">
			<tr>
				<th>id</th>
				<th>&nbsp;</th>
				<th>Username</th>
				<th>Name</th>
				<th>Email</th>
				<th>Telephone</th>
				<th>Country</th>
				<th>manager</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${contactList}" var="contact">
				<tr>
					<td><a href="./rest/contact/${contact.id}">${contact.id}</a></td>
					<td><a href="${contact.id}">edit</a></td>
					<td>${contact.userName}</td>
					<td>${contact.lastName}, ${contact.firstName}</td>
					<td>${contact.email}</td>
					<td>${contact.telephone}</td>
					<td>${contact.country}</td>
					<td>${contact.manager}</td>
					<td><a href="delete/${contact.id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<h4>As JSON via jquery</h4>

	<div id="div1"></div>
	
</body>
</html>