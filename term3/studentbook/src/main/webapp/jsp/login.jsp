<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Signin</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <c:if test="${error}">
        <div class="alert alert-danger">Неверный логин или пароль</div>
    </c:if>
    <form class="form-signin" action="login" method="post">
        <h2 class="form-signin-heading">Форма авторизации</h2>
        <input type="text" name="username" class="form-control" placeholder="Логин" autofocus="">
        <input type="password" name="password" class="form-control" placeholder="Пароль">
        <button id="login-btn" class="btn btn-sm btn-primary" type="submit">Войти</button>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>

</body>
</html>