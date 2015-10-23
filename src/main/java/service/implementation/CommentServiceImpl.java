package service.implementation;


import dao.CommentDAO;
import dao.DAOException;
import dao.implementation.CommentDAOImpl;
import entity.Comment;
import entity.News;
import service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    CommentDAO dao = new CommentDAOImpl();

    public void addComment(Comment comment) throws DAOException {
        dao.addComment(comment);
    }

    public Comment getCommentById(int commentId) throws DAOException {
        return dao.getCommentById(commentId);
    }

    public void updateComment(Comment comment) throws DAOException {
        dao.updateComment(comment);
    }

    public void deleteCommentById(int commentId) throws DAOException {
        dao.deleteCommentById(commentId);
    }

    public List<Comment> getAllBy(int newsId) throws DAOException {
        return dao.getAllBy(newsId);
    }

    public List<List<Comment>> getAllBy(List<News> allNews) throws DAOException {
        return dao.getAllBy(allNews);
    }
}
