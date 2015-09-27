package dao;

import entity.News;

public interface NewsDAO {
    public void addNews(News news) throws DAOException;
    public News getNewsById(int newsId) throws DAOException;
    public void updateNews(News news) throws DAOException;
    public void deleteNews(News news) throws DAOException;
}
