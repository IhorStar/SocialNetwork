package dao;

import entity.News;

import java.util.List;

public interface NewsDAO {
    void addNews(News news) throws DAOException;
    News getNewsById(int newsId) throws DAOException;
    void updateNews(News news) throws DAOException;
    void deleteNewsById(int newsId) throws DAOException;
    List<News> getAllNews(int userId) throws DAOException;
}
