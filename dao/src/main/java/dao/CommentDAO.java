package dao;


import entity.Comment;
import entity.News;

import java.util.List;

public interface CommentDAO {
    void addComment(Comment comment) throws DAOException;
    Comment getCommentById(int commentId) throws DAOException;
    void updateComment(Comment comment) throws DAOException;
    void deleteCommentById(int commentId) throws DAOException;
    List<Comment> getAllBy(int newsId) throws DAOException;
    List<List<Comment>> getAllBy(List<News> allNews) throws DAOException;
}
