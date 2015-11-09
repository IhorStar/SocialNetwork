package service;

import dao.DAOException;
import entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {
    void addNews(News news) throws DAOException, SQLException;
    News getNewsById(int newsId) throws DAOException, SQLException;
    void updateNews(News news) throws DAOException, SQLException;
    void deleteNewsById(int newsId) throws DAOException, SQLException;
    List<News> getAllNews(int userId) throws DAOException, SQLException;
}
