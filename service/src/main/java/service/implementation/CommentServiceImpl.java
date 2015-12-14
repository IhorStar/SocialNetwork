package service.implementation;

import dao.CommentDAO;
import dao.DAOException;
import entity.Comment;
import entity.News;
import org.springframework.stereotype.Service;
import service.CommentService;

import java.sql.SQLException;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private CommentDAO dao;

    public void setDao(CommentDAO dao) {
        this.dao = dao;
    }

    public void addComment(Comment comment) throws DAOException, SQLException {
        dao.addComment(comment);
    }

    public Comment getCommentById(int commentId) throws DAOException, SQLException {
        return dao.getCommentById(commentId);
    }

    public void updateComment(Comment comment) throws DAOException, SQLException {
        dao.updateComment(comment);
    }

    public void deleteCommentById(int commentId) throws DAOException, SQLException {
        dao.deleteCommentById(commentId);
    }

    public List<Comment> getAllBy(int newsId) throws DAOException, SQLException {
        return dao.getAllBy(newsId);
    }

    public List<List<Comment>> getAllBy(List<News> allNews) throws DAOException, SQLException {
        return dao.getAllBy(allNews);
    }
}
