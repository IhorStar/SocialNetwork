package dao.implementation;

import dao.CommentDAO;
import dao.DAOException;
import entity.Comment;

import java.sql.*;
import java.util.Date;


public class CommentDAOImpl implements CommentDAO {
    private PostgresqlDAOFactory postgresqlDaoFactory = new PostgresqlDAOFactory();

    private Timestamp getDate() {
        Date date = new Date();
        return new Timestamp(date.getDate());
    }

    private Timestamp getTime() {
        Date date = new Date();
        return  new Timestamp(date.getTime());
    }

    public void addComment(Comment comment) throws DAOException {
        String query = "insert into comment values (?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getCommentId());
            statement.setString(2, comment.getText());
            statement.setTimestamp(3, getDate());
            statement.setTimestamp(4, getTime());
            statement.setInt(5, comment.getNewsId());
            statement.setInt(6, comment.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Comment getCommentById(int commentId) throws DAOException {
        String query = "select * from comment where comment_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Comment comment = new Comment();
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setText(resultSet.getString("text"));
                comment.setDate(resultSet.getString("date"));
                comment.setTime(resultSet.getString("time"));
                comment.setNewsId(resultSet.getInt("news"));
                comment.setUserId(resultSet.getInt("user"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return comment;
    }

    public void updateComment(Comment comment) throws  DAOException{
        String query = "update comment set  text = ?, date = ?, time = ? where comment_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getCommentId());
            statement.setString(2, comment.getText());
            statement.setString(3, comment.getDate());
            statement.setString(4, comment.getTime());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteComment(Comment comment) throws  DAOException{
        String query = "delete from comment where comment_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getNewsId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
