<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
    <title>Statistics</title>
    <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
    <link href="static/chartist/dist/chartist.css" rel="stylesheet">
    <script src="static/chartist/dist/chartist.js"></script>
    <link href="static/user_stat.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <nav class="navbar-lg navbar-inverse navbar-fixed-top">
        <div class="container-fluid">

            <div class="btn-group-lg btn-group-justified">
                <a href="Expenditure" class="btn btn-primary"><i class="glyphicon glyphicon-minus"></i><i class="glyphicon glyphicon-usd"></i></a>
                <a href="Income" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i><i class="glyphicon glyphicon-usd"></i></a>
                <a href="getEvents" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></a>
                <a href="/" class="btn btn-primary"><i class="glyphicon glyphicon-home"></i></a>
            </div>

        </div>
    </nav>
    <br>
    <br>

    <div class="container">
        <div class="container-background"></div>
        <div class="content">
            <form class="form-inline" action="getUserStatistics">
                <h2>View Statistics</h2>

                <%@include file="calendarWithPeriod.jsp" %>

                <button type="submit" class="btn-lg btn-primary"><i class="glyphicon glyphicon-search"></i></button>

            </form>
            <br>
            <br>

            <div id="chart1" class="ct-chart ct-minor-seventh"></div>

            <div id="chart2" class="ct-chart ct-minor-seventh"></div>

            <div id="chart3" class="ct-chart ct-minor-seventh"></div>

            <div id="chart4" class="ct-chart ct-minor-seventh"></div>

        </div>
    </div>
    <script>
        var data = ${data};
        var data2 = ${data2};

        var options = {
            labelInterpolationFnc: function(value) {
                return value[0]
            }
        };

        var responsiveOptions = [
            ['screen and (min-width: 640px)', {
                chartPadding: 30,
                labelOffset: 100,
                labelDirection: 'explode',
                labelInterpolationFnc: function(value) {
                    return value;
                }
            }],
            ['screen and (min-width: 1024px)', {
                labelOffset: 120,
                chartPadding: 90
            }]
        ];

        new Chartist.Pie('#chart1', data, options, responsiveOptions);
        new Chartist.Pie('#chart3', data2, options, responsiveOptions);

        new Chartist.Bar('#chart2', data, {
            distributeSeries: true
        });
        new Chartist.Bar('#chart4', data2, {
            distributeSeries: true
        });

    </script>


</body>
