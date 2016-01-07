
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h3>Hello ${user.getName()}</h3>
<strong>Your email</strong>: ${user.getEmail()}
<br>
<br>
<c:url var="logoutUrl" value="j_spring_security_logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<br>
<br>
<div class="allUser-container">
    <c:forEach var="user" items="${allUsers}">
        <div class="user">
            ${user.getName()}
            <a href="<c:url value='/deleteUser' />">delete</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
