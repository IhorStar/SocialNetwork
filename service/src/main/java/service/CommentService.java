package service;


import dao.DAOException;
import entity.Comment;
import entity.News;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws DAOException, SQLException;
    Comment getCommentById(int commentId) throws DAOException, SQLException;
    void updateComment(Comment comment) throws DAOException, SQLException;
    void deleteCommentById(int commentId) throws DAOException, SQLException;
    List<Comment> getAllBy(int newsId) throws DAOException, SQLException;
    List<List<Comment>> getAllBy(List<News> allNews) throws DAOException, SQLException;
}
