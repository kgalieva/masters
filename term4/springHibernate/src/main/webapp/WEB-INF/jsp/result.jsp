<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Student Information</h2>
<table>
    <c:forEach items="${contacts}" var="contact">
    <tr>
        <td>Name</td>
        <td>${contact.name}</td>
        <td>Phone</td>
        <td>${contact.phone}</td>
    </tr>
    </c:forEach>
</table>
<c:url var="root" value="/"/>
<a href="${root}">Add more contacts</a>

</body>
</html>