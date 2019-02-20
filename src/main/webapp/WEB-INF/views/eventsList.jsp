<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Events List</title>
     <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="static/app_css.css" rel="stylesheet">
</head>

<body>
    <%@include file="navbar.jsp" %>
    <br>
    <br>
    <c:forEach items="${periodOfTimes}" var="periodOfTime">
    <br>
        <div class="container event-list">
            <h3>${periodOfTime.localDate}</h3>
            <table class="table table-condensed">
                <thead>
                    <tr>
                        <th width="20%">amount</th>
                        <th width="50%">messege</th>
                        <th width="20%">type</th>
                        <th width="10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty periodOfTime.costList}">

                        <c:forEach items="${periodOfTime.costList}" var="cost">
                            <tr class="danger">
                                <td vertical-align="middle">${cost.amount}</td>
                                <td>${cost.message}</td>
                                <td>${cost.getCostType().getType()}</td>
                                <td>
                                    <form action="delete-cost">
                                        <input type="hidden" name="dates" value="${dates}">
                                        <input type="hidden" name="date" value=${periodOfTime.localDate}>
                                        <input type="hidden" name="cost.id" value=${cost.id}>
                                        <button type="submit" class="btn-lg btn-light"><i class="glyphicon glyphicon-trash"></i></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty periodOfTime.incomeList}">

                        <c:forEach items="${periodOfTime.incomeList}" var="income">
                            <tr class="success">
                                <td valign="middle">${income.amount}</td>
                                <td valign="middle">${income.message}</td>
                                <td valign="middle">${income.getTypeIncome().getType()}</td>
                                <td valign="middle">
                                    <form action="delete-income">
                                        <input type="hidden" name="dates" value="${dates}">
                                        <input type="hidden" name="date" value=${periodOfTime.localDate}>
                                        <input type="hidden" name="income.id" value=${income.id}>
                                        <button type="submit" class="btn-lg btn-light"><i class="glyphicon glyphicon-trash"></i></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>

    </c:forEach>
</body>

</html>


