package dao.implementation;

import dao.CommentDAO;
import dao.DAOException;
import entity.Comment;
import entity.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CommentDAOImpl implements CommentDAO {
    private static  final Logger log = LogManager.getLogger(CommentDAOImpl.class);
    private PostgresqlDAOFactory postgresqlDaoFactory = new PostgresqlDAOFactory();

    private Timestamp getDate() {
        Date date = new Date();
        return new Timestamp(date.getDate());
    }

    private Timestamp getTime() {
        Date date = new Date();
        return  new Timestamp(date.getTime());
    }

    public void addComment(Comment comment) throws DAOException, SQLException {
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
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
            connection.rollback();
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

    public Comment getCommentById(int commentId) throws DAOException, SQLException {
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
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
            connection.rollback();
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

    public void updateComment(Comment comment) throws DAOException, SQLException {
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
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
            connection.rollback();
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

    public void deleteCommentById(int commentId) throws DAOException, SQLException {
        String query = "delete from comment where comment_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, commentId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
            connection.rollback();

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

    public List<Comment> getAllBy(int newsId) throws DAOException, SQLException {
        String query = "select * from comment where news = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Comment> commentList = null;

        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newsId);
            resultSet = statement.executeQuery();
            commentList = new ArrayList<Comment>();
            while (resultSet.next()) {
                int commentId = resultSet.getInt("comment_id");
                int id = resultSet.getInt("news");
                int userId = resultSet.getInt("user");
                String text = resultSet.getString("text");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                Comment comment = new Comment();
                comment.setCommentId(commentId);
                comment.setNewsId(id);
                comment.setUserId(userId);
                comment.setText(text);
                comment.setDate(date);
                comment.setTime(time);
                commentList.add(comment);
            }
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
            connection.rollback();
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

        return commentList;
    }

    public List<List<Comment>> getAllBy(List<News> allNews) throws DAOException, SQLException {
        List<List<Comment>> list = new ArrayList<List<Comment>>();
        for(News news : allNews) {
            List<Comment> commentList = getAllBy(news.getNewsId());
            list.add(commentList);
        }
        return list;
    }
}
