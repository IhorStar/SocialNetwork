<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<div class="i18n">
    <a href="?lang=eng">En</a>
    <a href="?lang=ru>">Ru</a>
</div>
<h3><spring:message code="greetings"/>${user.getName()}</h3>
<strong><spring:message code="yourEmail"/></strong>: ${user.getEmail()}
<br>
<br>
<c:url var="logoutUrl" value="j_spring_security_logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<br>
<br>
<h3><spring:message code="newsContainer"/></h3>
<div class="news-container">
    <c:forEach var="news" items="${allNews}">
            ${news.getDescription()}
            <a href="<c:url value='/deleteNews' />"><spring:message code="delete"/></a>
            <br>
            <c:forEach var="commentNews" items="${allComments}">
                ${commentNews.getText()}
                <a href="<c:url value='/deleteComment'/>"><spring:message code="delete"/></a>
            </c:forEach>
    </c:forEach>
</div>
<br>
<br>
<h3><spring:message code="fieldForNews"/></h3>
<form action="addNews" method="get">
    <div style="color: #FF0000;">${errorMessage}</div>
    <p><spring:message code="enterNews"/></p>
    <p><textarea rows="10" cols="50"></textarea></p>
    <p><input type="submit" value="Add News"></p>
</form>
<br>
<br>
<h3><spring:message code="fieldForEditNews"/></h3>
<form action="updateNews" method="get">
    <div style="color: #FF0000;">${errorMessage}</div>
    <p><b><spring:message code="selectNews"/>:</b></p>
    <select name="newsId">
        <c:forEach var="news" items="${allNews}">
            <option value="${news.getNewsId()}">${news.getDescription()}</option>
        </c:forEach>
    </select>
    <br><br>
    <p><b><spring:message code="editNews"/>:</b></p>
    <p><textarea rows="10" cols="50" name="newsDescription"></textarea></p>
    <p><input type="submit" value="Update News"></p>
</form>
<br>
<br>
<h3><spring:message code="fieldForAddComment"/></h3>
<form action="addComment" method="get">
    <div style="color: #FF0000;">${errorMessage}</div>
    <p><spring:message code="selectNewsToAddComment"/>:</p>
    <select name="newsId">
        <c:forEach var="news" items="${allNews}">
            <option value="${news.getNewsId}">${news.getDescription()}</option>
        </c:forEach>
    </select>
    <br>
    <p><spring:message code="addComment"/>:</p>
    <p><textarea rows="10" cols="50"></textarea> </p>
    <p><input type="submit" value="Add Comment"></p>
</form>
<br>
<br>
<h3><spring:message code="friendList"/></h3>
<br>
<div class="friends-container">
    <c:forEach var="friend" items="allRelation">
        ${user.getName()}
        <a href="<c:url value='addNews'/>"><spring:message code="addToFriend"/></a>
        <a href="<c:url value='deleteFriend'/>"><spring:message code="cancelFriendship"/></a>
    </c:forEach>
</div>
</body>
</html>
