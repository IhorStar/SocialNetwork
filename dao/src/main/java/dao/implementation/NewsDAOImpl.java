package dao.implementation;

import dao.DAOException;
import dao.NewsDAO;
import dao.mapper.NewsMapper;
import entity.News;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class NewsDAOImpl implements NewsDAO {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addNews(News news) throws DAOException {
        String query = "insert into news values (?, ?, ?, ?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{news.getNewsId(), news.getDescription(), news. getDate(),
                news.getTime(), news.getUserId()});
    }

    public News getNewsById(int newsId) throws DAOException {
        String query = "select * from news where news_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        News news = (News) jdbcTemplate.queryForObject(query, new Object[]{newsId}, new NewsMapper());
        return news;
    }

    public void updateNews(News news) throws DAOException {
        String query = "update news set  description = ?, date = ?, time = ? where news_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{news.getNewsId(), news.getDescription(), news. getDate(),
                news.getTime()});
    }

    public void deleteNewsById(int newsId) throws DAOException {
        String query = "delete from news where news_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{newsId});
    }

    public List<News> getAllNews(int userId) throws DAOException {
        String query = "select * from news where user = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        List news = jdbcTemplate.query(query, new Object[]{userId}, new NewsMapper());
        return news;
    }
}
