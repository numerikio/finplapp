<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <title>Main Page</title>
    <link href="static/chartist/dist/chartist.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>

<body>
    <nav class="navbar-lg navbar-inverse navbar-fixed-top">
        <div class="container-fluid">

            <div class="btn-group-lg btn-group-justified">
                <a href="Cost" class="btn btn-primary"><i class="glyphicon glyphicon-minus-sign"></i></a>
                <a href="Income" class="btn btn-primary"><i class="glyphicon glyphicon-plus-sign"></i></a>
                <a href="getEvents" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></a>
                <a href="/logout" class="btn btn-primary">${loggedinuser} <i class="glyphicon glyphicon-log-out"></i></a>
            </div>

        </div>
    </nav>
    <br>
    <br>

    <sec:authorize access="hasRole('ADMIN')">
        <td><a href="<c:url value='/userslist' />" class="btn btn-success custom-width">userslist</a></td>
    </sec:authorize>

    <sec:authorize access="hasRole('ANALYST')">
        <td><a href="<c:url value='/statistics' />" class="btn btn-success custom-width">statistics</a></td>
    </sec:authorize>

    <div class="ct-chart ct-major-tenth"></div>


    <script src="static/chartist/dist/chartist.js"></script>
    <script src="static/chartist/dist/chartist-plugin-threshold.js"></script>

    <script>
        var data = ${data};
        new Chartist.Line('.ct-chart', data, {
            showArea: true,
            axisY: {
                onlyInteger: true
            },
            plugins: [
                Chartist.plugins.ctThreshold({
                    threshold: 0,
                }),
            ]
        });

    </script>

</body>

</html>
