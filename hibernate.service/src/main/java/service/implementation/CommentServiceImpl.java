package service.implementation;

import entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CommentDAO;
import service.CommentService;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentDAO dao;

    public void setDao(CommentDAO dao) {
        this.dao = dao;
    }
    public void addComment(Comment comment) {
        dao.addComment(comment);
    }

    public Comment getCommentById(int commentId) {
        return dao.getCommentById(commentId);
    }

    public void updateComment(Comment comment) {
        dao.updateComment(comment);
    }

    public void deleteComment(Comment comment) {
        dao.deleteComment(comment);
    }

    public List<Comment> getAllBy(int newsId) {
        return dao.getAllBy(newsId);
    }
}
