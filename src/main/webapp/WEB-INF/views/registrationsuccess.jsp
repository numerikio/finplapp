<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration Confirmation Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<div class="generic-container">
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
		    Successful registration.
			Go to <a href="<c:url value='/' />">login</a> page.
		</span>
	</div>
</body>

</html>