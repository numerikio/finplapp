<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Analyst Page</title>
    <link rel="shortcut icon" href="favicon.ico?ver=2" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


</head>

<body>

    <nav class="navbar-lg navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="btn-group-lg btn-group-justified">
                <a href="/" class="btn btn-success"><i class="glyphicon glyphicon-home"></i></a>
                <a href="logout" class="btn btn-success">${loggedinuser} <i class="glyphicon glyphicon-log-out"></i></a>
            </div>
        </div>
    </nav>
    <br>
    <br>
    <br>
    <br>
    <div class="container" style="text-align:center">
        <form action="useCategories">

            <br>
            <div class="form-group-lg input-group">
                <span class="input-group-addon" id="addon-message"><i class="glyphicon glyphicon-pencil"></i></span>
                <input type="text" autocomplete="off" class="form-control" name="name" aria-describedby="addon-message" placeholder="please text type" style="text-align:center" id="name" required="required" pattern="[A-Za-z0-9]{1,20}" />
            ${errorMessage}
            </div>
            <br>
            <div class="row row-centered">
                <div class="col-sm-6">
                    <br>
                    <button type="submit" class="btn-lg btn-success" name="action" value="addCostType">Add to list of cost type </button>
                    <br>
                    <button type="submit" class="btn-lg btn-danger" name="action" value="deleteCostType">Delete cost type </button>
                    <br>
                    <br>
                    <c:forEach items="${costsType}" var="value">
                        <option>${value.getType()}</option>
                    </c:forEach>
                </div>
                <div class="col-sm-6">
                    <br>
                    <button type="submit" class="btn-lg btn-success" name="action" value="addIncomeType">Add to list of income type </button>
                    <br>
                    <button type="submit" class="btn-lg btn-danger" name="action" value="deleteIncomeType">Delete income type </button>
                    <br>
                    <br>
                    <c:forEach items="${incomesType}" var="value">
                        <option>${value.getType()}</option>
                    </c:forEach>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
