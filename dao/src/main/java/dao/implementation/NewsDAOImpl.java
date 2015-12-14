package dao.implementation;

import dao.DAOException;
import dao.NewsDAO;
import entity.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("newsDAO")
public class NewsDAOImpl implements NewsDAO {
    private static final Logger log = LogManager.getLogger(NewsDAOImpl.class);
    private DriverManagerDataSource dataSource;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Timestamp getDate() {
        Date date = new Date();
        return new Timestamp(date.getDate());
    }

    private Timestamp getTime() {
        Date date = new Date();
        return  new Timestamp(date.getTime());
    }
    public void addNews(News news) throws DAOException, SQLException {
        String query = "insert into news values (?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, news.getNewsId());
            statement.setString(2, news.getDescription());
            statement.setTimestamp(3, getDate());
            statement.setTimestamp(4, getTime());
            statement.setInt(5, news.getUserId());
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

    public News getNewsById(int newsId) throws DAOException, SQLException {
        String query = "select * from news where news_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        News news = new News();
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                news.setNewsId(resultSet.getInt("newsId"));
                news.setDescription(resultSet.getString("description"));
                news.setDate(resultSet.getString("date"));
                news.setTime(resultSet.getString("time"));
                news.setUserId(resultSet.getInt("user"));
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

        return news;
    }

    public void updateNews(News news) throws DAOException, SQLException {
        String query = "update news set  description = ?, date = ?, time = ? where news_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, news.getNewsId());
            statement.setString(2, news.getDescription());
            statement.setString(3, news.getDate());
            statement.setString(4, news.getTime());
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

    public void deleteNewsById(int newsId) throws DAOException, SQLException {
        String query = "delete from news where news_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newsId);
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

    public List<News> getAllNews(int userId) throws DAOException, SQLException {
        String query = "select * from news where user = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<News> newsList = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            newsList = new ArrayList<News>();
            while (resultSet.next()) {
                int id = resultSet.getInt("news_id");
                int user = resultSet.getInt("user");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                News news = new News();
                news.setNewsId(id);
                news.setUserId(user);
                news.setDescription(description);
                news.setDate(date);
                news.setTime(time);
                newsList.add(news);
            }
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
        return newsList;
    }
}
