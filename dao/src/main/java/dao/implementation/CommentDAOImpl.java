package dao.implementation;

import dao.CommentDAO;
import dao.DAOException;
import dao.mapper.CommentMapper;
import entity.Comment;
import entity.News;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {

    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addComment(Comment comment) throws DAOException {
        String query = "insert into comment values (?, ?, ?, ?, ?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[] {comment.getCommentId(), comment.getText(),
                comment.getDate(), comment.getTime(), comment.getNewsId(), comment.getUserId()});
    }

    public Comment getCommentById(int commentId) throws DAOException {
        String query = "select * from comment where comment_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Comment comment = (Comment) jdbcTemplate.queryForObject(query, new Object[]{commentId}, new CommentMapper());
        return  comment;
    }

    public void updateComment(Comment comment) throws DAOException {
        String query = "update comment set  text = ?, date = ?, time = ? where comment_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[] {comment.getCommentId(), comment.getText(),
                comment.getDate(), comment.getTime()});
    }

    public void deleteCommentById(int commentId) throws DAOException {
        String query = "delete from comment where comment_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{commentId});
    }

    public List<Comment> getAllBy(int newsId) throws DAOException {
        String query = "select * from comment where news = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        List comment = jdbcTemplate.query(query, new Object[]{newsId}, new CommentMapper());
        return comment;
    }

    public List<List<Comment>> getAllBy(List<News> allNews) throws DAOException {
        List<List<Comment>> list = new ArrayList<List<Comment>>();
        for(News news : allNews) {
            List<Comment> commentList = getAllBy(news.getNewsId());
            list.add(commentList);
        }
        return list;
    }
}
