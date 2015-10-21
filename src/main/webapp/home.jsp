
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<div class="i18n">
    <a href="<c:url value='/I18N' />">En</a>
    <a href="<c:url value='/I18N' />">Ru</a>
</div>
<h3>${messages.get("greetings")} ${user.getName()}</h3>
<strong>Your email</strong>: ${user.getEmail()}
<br>
<br>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
<br>
<br>
<div class="news-container">
    <c:forEach var="news" items="${allNews}">
            ${news.getDescription()}
            <a href="<c:url value='/deleteNews' />">delete</a>
            <br>
            <c:forEach var="commentNews" items="${allComments}">
                ${commentNews.getText()}
                <a href="<c:url value='/deleteComment'/>">delete</a>
            </c:forEach>
    </c:forEach>
</div>
<br>
<br>
<h3>Field for new news</h3>
<form action="addNews" method="get">
    <p>Enter your news</p>
    <p><textarea rows="10" cols="50"></textarea></p>
    <p><input type="submit" value="Add News"></p>
</form>
<br>
<br>
<h3>Fields for edit news</h3>
<form action="updateNews" method="get">
    <p><b>Select your news which you wants to edit:</b></p>
    <select name="newsId">
        <c:forEach var="news" items="${allNews}">
            <option value="${news.getNewsId()}">${news.getDescription()}</option>
        </c:forEach>
    </select>
    <br><br>
    <p><b>Edit your news:</b></p>
    <p><textarea rows="10" cols="50" name="newsDescription"></textarea></p>
    <p><input type="submit" value="Update News"></p>
</form>
<br>
<br>
<h3>Field for add comment to news</h3>
<form action="addComment" method="get">
    <p>Select news to add a comment:</p>
    <select name="newsId">
        <c:forEach var="news" items="${allNews}">
            <option value="${news.getNewsId}">${news.getDescription()}</option>
        </c:forEach>
    </select>
    <br>
    <p>Add comment to news:</p>
    <p><textarea rows="10" cols="50"></textarea> </p>
    <p><input type="submit" value="Add Comment"></p>
</form>
<br>
<br>
<h3>This is list of all friends</h3>
<br>
<div class="friends-container">
    <c:forEach var="friend" items="allRelation">
        ${user.getName()}
        <a href="<c:url value='addNews'/>">add to friend</a>
        <a href="<c:url value='deleteFriend'/>">cancel the friendship</a>
    </c:forEach>
</div>
</body>
</html>
