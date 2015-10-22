
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
<strong>${messages.get("yourEmail")}</strong>: ${user.getEmail()}
<br>
<br>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
<br>
<br>
<h3>${messages.get("newsContainer")}</h3>
<div class="news-container">
    <c:forEach var="news" items="${allNews}">
            ${news.getDescription()}
            <a href="<c:url value='/deleteNews' />">${messages.get("delete")}</a>
            <br>
            <c:forEach var="commentNews" items="${allComments}">
                ${commentNews.getText()}
                <a href="<c:url value='/deleteComment'/>">${messages.get("delete")}</a>
            </c:forEach>
    </c:forEach>
</div>
<br>
<br>
<h3>${messages.get("fieldForNews")}</h3>
<form action="addNews" method="get">
    <div style="color: #FF0000;">${errorMessage}</div>
    <p>${messages.get("enterNews")}</p>
    <p><textarea rows="10" cols="50"></textarea></p>
    <p><input type="submit" value="Add News"></p>
</form>
<br>
<br>
<h3>${messages.get("fieldForEditNews")}</h3>
<form action="updateNews" method="get">
    <div style="color: #FF0000;">${errorMessage}</div>
    <p><b>${messages.get("selectNews")}:</b></p>
    <select name="newsId">
        <c:forEach var="news" items="${allNews}">
            <option value="${news.getNewsId()}">${news.getDescription()}</option>
        </c:forEach>
    </select>
    <br><br>
    <p><b>${messages.get("editNews")}:</b></p>
    <p><textarea rows="10" cols="50" name="newsDescription"></textarea></p>
    <p><input type="submit" value="Update News"></p>
</form>
<br>
<br>
<h3>${messages.get("fieldForAddComment")}</h3>
<form action="addComment" method="get">
    <div style="color: #FF0000;">${errorMessage}</div>
    <p>${messages.get("selectNewsToAddComment")}:</p>
    <select name="newsId">
        <c:forEach var="news" items="${allNews}">
            <option value="${news.getNewsId}">${news.getDescription()}</option>
        </c:forEach>
    </select>
    <br>
    <p>${message.get("addComment")}:</p>
    <p><textarea rows="10" cols="50"></textarea> </p>
    <p><input type="submit" value="Add Comment"></p>
</form>
<br>
<br>
<h3>${messages.get("friendList")}</h3>
<br>
<div class="friends-container">
    <c:forEach var="friend" items="allRelation">
        ${user.getName()}
        <a href="<c:url value='addNews'/>">${message.get("addToFriend")}</a>
        <a href="<c:url value='deleteFriend'/>">${message.get("cancelFriendship")}</a>
    </c:forEach>
</div>
</body>
</html>
