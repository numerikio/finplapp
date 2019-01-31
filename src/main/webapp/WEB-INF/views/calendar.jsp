<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>

<head>
    <title>Page Test</title>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

</head>

<body>
    <div class="form-group-lg input-group">
        <input type="text" autocomplete="off" name="date" class="form-control" readonly />
    </div>
    <script>
        $(function() {
            $('input[name="date"]').daterangepicker({
                singleDatePicker: true,
                minYear: 2018,
            });
        });

    </script>

</body>

</html>
