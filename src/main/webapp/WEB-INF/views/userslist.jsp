<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Users </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Email</th>
				        <th>SSO ID</th>
				        <th width="100"></th>
				        <th width="100"></th>

					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.email}</td>
						<td>${user.ssoId}</td>
					    <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success custom-width">edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Add New User</a>
		 	</div>
	 	</sec:authorize>
   	</div>
</body>
</html>