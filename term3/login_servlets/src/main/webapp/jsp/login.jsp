<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>login</title>
  </head>
  <body>
        <c:forEach items="${credentials}" var="credential">
            <p>
                <c:out value="${credential.login}"/> -
                <c:out value="${credential.password}"/>
            </p>
        </c:forEach>
  </body>
</html>