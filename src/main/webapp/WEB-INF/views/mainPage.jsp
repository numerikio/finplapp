<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <title>Main Page</title>
    <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
    <link href="static/chartist/dist/chartist.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script type="text/javascript" src="static/chartist/dist/chartist.js"></script>
    <script type="text/javascript" src="static/chartist/dist/chartist-plugin-threshold.js"></script>
</head>

<body>
    <sec:authorize access="hasRole('USER')">
        <nav class="navbar-lg navbar-inverse navbar-fixed-top">
            <div class="container-fluid">

                <div class="btn-group-lg btn-group-justified">
                    <a href="Expenditure" class="btn btn-primary"><i class="glyphicon glyphicon-minus"></i><i class="glyphicon glyphicon-usd"></i></a>
                    <a href="Income" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i><i class="glyphicon glyphicon-usd"></i></a>
                    <a href="getEvents" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></a>
                    <a href="logout" class="btn btn-primary">${loggedinuser} <i class="glyphicon glyphicon-log-out"></i></a>
                </div>

            </div>
        </nav>
        <br>
        <br>
        <br>

        <div class="container">
            <form action="mainRedact">
                <div class="row row-centered">
                    <div class="col-sm-6 col-md-2">
                        <div class="form-group-lg input-group">
                            <span class="input-group-addon" id="addon-type"><i class="glyphicon glyphicon-arrow-left"></i></span>
                            <select name="measureTypeP" id="measureTypeP" class="form-control" aria-describedby="addon-type">
                                <option>${measureTypeP}</option>
                                <c:forEach items="${dateMeasure}" var="value">
                                    <c:if test="${value ne measureTypeP}">
                                        <option>${value}</option>
                                    </c:if>

                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-2">
                        <div class="form-group-lg input-group-fluid">
                            <input type="number" min=0 step=1 value=${beforeNow} autocomplete="off" class="form-control" name="beforeNow" id="beforeNow" placeholder="before now" />
                        </div>
                    </div>
                    <div class="col-sm-2 col-sm-offset-5 col-md-1 col-md-offset-0">
                        <button type="submit" class="btn-lg btn-primary btn-block"><i class="glyphicon glyphicon-sunglasses"></i></button>
                    </div>
                    <div class="col-sm-6 col-md-2">
                        <div class="form-group-lg input-group-fluid">
                            <input type="number" min=0 step=1 value=${afterNow} autocomplete="off" class="form-control" name="afterNow" id="afterNow" placeholder="after now" />
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-2">
                        <div class="form-group-lg input-group">
                            <select name="measureTypeF" id="measureTypeF" class="form-control" aria-describedby="addon-type">
                                <option>${measureTypeF}</option>
                               <c:forEach items="${dateMeasure}" var="value">
                                  <c:if test="${value ne measureTypeF}">
                                     <option>${value}</option>
                                  </c:if>
                               </c:forEach>
                            </select>
                            <span class="input-group-addon" id="addon-type"><i class="glyphicon glyphicon-arrow-right"></i></span>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        </div>
        <div class="ct-chart ct-major-tenth"></div>
    </sec:authorize>

    <sec:authorize access="hasRole('ADMIN')">
        <script language="javascript" type="text/javascript">
            window.location = window.location.href = 'userslist';

        </script>
    </sec:authorize>

    <sec:authorize access="hasRole('ANALYST')">
        <script language="javascript" type="text/javascript">
            window.location = window.location.href = 'analyst';

        </script>
    </sec:authorize>


    <script>
        var data = ${data};
        new Chartist.Line('.ct-chart', data, {
            showArea: true,
            axisY: {
                onlyInteger: true
            },
            axisX: {
                labelInterpolationFnc: function(value) {
                if(value=='${dateNow}'){
                return '> '+value + ' <';
                }
                  return value;
                },
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
