package dao.mapper;

import entity.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        News news = new News();
        news.setNewsId(resultSet.getInt("newsId"));
        news.setDescription(resultSet.getString("description"));
        news.setDate(resultSet.getString("date"));
        news.setTime(resultSet.getString("time"));
        news.setUserId(resultSet.getInt("user"));
        return news;
    }
}
