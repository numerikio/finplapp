<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <title>Analyst Page</title>
    <link rel="shortcut icon" href="favicon.ico?ver=2" type="image/x-icon">
    <link href="static/chartist/dist/chartist.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
    .fig {
        text-align: center;
        }
    </style>
</head>

<body>

    <nav class="navbar-lg navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="btn-group-lg btn-group-justified">
               <a href="logout" class="btn btn-success">${loggedinuser} <i class="glyphicon glyphicon-log-out"></i></a>
            </div>
        </div>
    </nav>
    <br>
    <br>

  <p class="fig"><img src="static/images/under.png"></p>

</body>

</html>