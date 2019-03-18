<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <title>Events</title>
     <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <nav class="navbar-lg navbar-inverse navbar-fixed-top">
        <div class="container-fluid">

            <div class="btn-group-lg btn-group-justified">
                <a href="Expenditure" class="btn btn-primary"><i class="glyphicon glyphicon-minus"></i><i class="glyphicon glyphicon-usd"></i></a>
                <a href="Income" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i><i class="glyphicon glyphicon-usd"></i></a>
                <a href="userStatistics" class="btn btn-primary"><i class="glyphicon glyphicon-stats"></i></a>
                <a href="/" class="btn btn-primary"><i class="glyphicon glyphicon-home"></i></a>
            </div>

        </div>
    </nav>
   <br>
   <br>

    <div class="container">
        <form class="form-inline" action="getAllEventOfDate">

            <h2>View Ledger</h2>
            <%@include file="calendarWithPeriod.jsp" %>

            <button type="submit" class="btn-lg btn-primary"><i class="glyphicon glyphicon-search"></i></button>

        </form>

    </div>
</body>

</html>
