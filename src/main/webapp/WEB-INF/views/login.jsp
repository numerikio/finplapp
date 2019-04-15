<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="shortcut icon" href="favicon.ico?ver=0" type="image/x-icon">
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <div id="mainWrapper">
        <br>
        <div class="container">
            <c:url var="loginUrl" value="/login" />
            <form action="${loginUrl}" method="post" class="form-horizontal">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="username"><i class="glyphicon glyphicon-user"></i></label>
                    <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="password"><i class="glyphicon glyphicon-credit-card"></i></label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                </div>
                <div class="input-group input-sm">
                    <div class="checkbox">
                        <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <br>
                <div class="row">
                    <div class="col-md-3 col-lg-3 col-sm-3 col-xs-3">
                        <input type="submit" class="btn-lg  btn-primary btn-default" value="Log in">
                    </div>

                    <div class="col-md-1 col-md-offset-8 col-lg-1 col-lg-offset-8 col-sm-1 col-sm-offset-8 col-xs-1 col-xs-offset-8">
                        <a href="newuser" class="btn-lg btn-primary pull-right">registration</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <br>
    <br>

    <div class="container">
        <pre>ADMIN<br>
                login : admin<br>
                pass  : admin</pre>

        <pre>ANALYST<br>
                login : analyst<br>
                pass  : analyst</pre>
    </div>

    <br>
    <div class="container">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">

            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="static/images/carousel/3.JPG" alt="3">
                </div>

                <div class="item">
                    <img src="static/images/carousel/1.JPG" alt="1">
                </div>

                <div class="item">
                    <img src="static/images/carousel/2.JPG" alt="2">
                </div>

                <div class="item">
                    <img src="static/images/carousel/4.JPG" alt="4">
                </div>
            </div>

            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>