<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>

    <title>Cost Page</title>
     <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>

<body>

    <%@include file="navbar.jsp" %>
    <br>
    <br>

    <div class="container">
        <form action="saveCost">
            <h2>Add cost</h2>

            <%@include file="calendar.jsp" %>

            <br>
            <div class="form-group-lg input-group">
                <span class="input-group-addon" id="addon-type"><i class="glyphicon glyphicon-list-alt"></i></span>
                <select name="costTypes" id="costTypes" class="form-control" aria-describedby="addon-type">
                    <option value="NOT_SELECTED"></option>
                    <c:forEach items="${costTypes}" var="value">
                       <c:if test="${value.type ne 'NOT_SELECTED'}">
                         <option>${value.type}</option>
                       </c:if>
                    </c:forEach>
                </select>
            </div>
            <br>
            <div class="form-group-lg input-group">
                <span class="input-group-addon" id="addon-amount"><i class="glyphicon glyphicon-usd"></i></span>
                <input type="number" autocomplete="off" class="form-control" aria-describedby="addon-amount" name="amount" id="amount" placeholder="amount" pattern="^[ 0-9]+$" required title="only numbers please" />
            </div>
            <br>
            <div class="form-group-lg input-group">
                <span class="input-group-addon" id="addon-message"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" autocomplete="off" class="form-control" name="message" aria-describedby="addon-message" placeholder="message" id="message" />
            </div>
            <br>

            <button type="submit" class="btn-lg btn-primary"><i class="glyphicon glyphicon-ok"></i></button>

        </form>

    </div>
</body>

</html>
