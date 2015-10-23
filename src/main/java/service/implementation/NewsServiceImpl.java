package service.implementation;


import dao.DAOException;
import dao.NewsDAO;
import dao.implementation.NewsDAOImpl;
import entity.News;
import service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    NewsDAO dao = new NewsDAOImpl();

    public void addNews(News news) throws DAOException {
        dao.addNews(news);
    }

    public News getNewsById(int newsId) throws DAOException {
        return dao.getNewsById(newsId);
    }

    public void updateNews(News news) throws DAOException {
        dao.updateNews(news);
    }

    public void deleteNewsById(int newsId) throws DAOException {
        dao.deleteNewsById(newsId);
    }

    public List<News> getAllNews(int userId) throws DAOException {
        return dao.getAllNews(userId);
    }
}
