package repository.implementation;

import entity.Comment;
import entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repository.CommentDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addComment(Comment comment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(comment);
    }

    public Comment getCommentById(int commentId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Comment.class, commentId);
    }

    public void updateComment(Comment comment) {
        Session currentSession = sessionFactory.getCurrentSession();
        Comment existingComment = currentSession.get(Comment.class, comment.getComentId());
        existingComment.setText(comment.getText());
        currentSession.save(existingComment);
    }

    public void deleteComment(Comment comment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(comment);
    }

    public List<Comment> getAllBy(int newsId) {
        Session currentSession = sessionFactory.getCurrentSession();
        News news = currentSession.get(News.class, newsId);
        return new ArrayList<Comment>(news.getComment());
    }
}
