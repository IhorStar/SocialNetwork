package service.implementation;

import dao.CommentDAO;
import dao.DAOException;
import entity.Comment;
import entity.News;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CommentService;

import java.util.List;

@Service
@Scope("singleton")
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentDAO dao;

    public void setDao(CommentDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public static CommentServiceImpl getCommentServiceImpl() {
        return new CommentServiceImpl();
    }

    @Transactional
    public void addComment(Comment comment) throws DAOException {
        dao.addComment(comment);
    }

    @Transactional
    public Comment getCommentById(int commentId) throws DAOException {
        return dao.getCommentById(commentId);
    }

    @Transactional
    public void updateComment(Comment comment) throws DAOException {
        dao.updateComment(comment);
    }

    @Transactional
    public void deleteCommentById(int commentId) throws DAOException {
        dao.deleteCommentById(commentId);
    }

    @Transactional
    public List<Comment> getAllBy(int newsId) throws DAOException {
        return dao.getAllBy(newsId);
    }

    @Transactional
    public List<List<Comment>> getAllBy(List<News> allNews) throws DAOException {
        return dao.getAllBy(allNews);
    }
}
