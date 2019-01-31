<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <title>Events</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

    <div class="container">
        <form action="getAllEventOfDate">
            <div class="row">
                <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">
                    <h2>View events</h2>
                </div>

                <div class="col-md-1 col-md-offset-8 col-lg-1 col-lg-offset-8 col-sm-1 col-sm-offset-8 col-xs-1 col-xs-offset-8">
                    <br>
                    <a href="/fpapp/" class="btn-lg btn-primary pull-right"><i class="glyphicon glyphicon-home"></i></a>

                </div>
            </div>

            <%@include file="calendarWithPeriod.jsp" %>
            <br>
            <button type="submit" class="btn-lg btn-primary"><i class="glyphicon glyphicon-search"></i></button>

        </form>

    </div>
</body>

</html>

