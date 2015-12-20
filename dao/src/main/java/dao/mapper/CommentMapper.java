package dao.mapper;

import entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt("comment_id"));
        comment.setText(resultSet.getString("text"));
        comment.setDate(resultSet.getString("date"));
        comment.setTime(resultSet.getString("time"));
        comment.setNewsId(resultSet.getInt("news"));
        comment.setUserId(resultSet.getInt("user"));
        return comment;
    }
}
