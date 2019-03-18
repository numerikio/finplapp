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

            <div class="row row-centered">
                <div class="col-sm-4 ">
                    <h4>${periodOfTime.localDate}</h4>
                </div>

                <div class="col-sm-8 col-xs-12">
                    <div class="row row-centered">
                        <div class="col-sm-4">
                            <b>total cash balance :</b>
                        </div>
                        <div class="col-sm-4">
                            <b>${periodOfTime.getBalance()}</b>
                        </div>
                    </div>
                    <div class="row row-centered">
                        <div class="col-sm-4">
                            on this day :
                            <c:choose>
                                <c:when test="${periodOfTime.getBalanceEndOfPeriod() gt 0}">
                                    <span class="label label-success"><i class="glyphicon glyphicon-arrow-up"></i></span>
                                </c:when>
                                <c:when test="${periodOfTime.getBalanceEndOfPeriod() lt 0}">
                                    <span class="label label-danger"><i class="glyphicon glyphicon-arrow-down"></i></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-default"><i class="glyphicon glyphicon-resize-small"></i></span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-sm-4">
                            ${periodOfTime.getBalanceEndOfPeriod()}
                        </div>
                    </div>
                </div>
            </div>

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
                    <c:if test="${not empty periodOfTime.expenditureList}">

                        <c:forEach items="${periodOfTime.expenditureList}" var="expenditure">
                            <tr class="danger">
                                <td vertical-align="middle">${expenditure.amount}</td>
                                <td>${expenditure.message}</td>
                                <td>${expenditure.getExpenditureType().getType()}</td>
                                <td>
                                    <form action="delete-expenditure">
                                        <input type="hidden" name="dates" value="${dates}">
                                        <input type="hidden" name="date" value=${periodOfTime.localDate}>
                                        <input type="hidden" name="expenditure.id" value=${expenditure.id}>
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