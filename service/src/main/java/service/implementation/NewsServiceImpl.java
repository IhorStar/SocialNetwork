package service.implementation;


import dao.DAOException;
import dao.NewsDAO;
import entity.News;
import org.springframework.stereotype.Service;
import service.NewsService;

import java.sql.SQLException;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    private NewsDAO dao;

    public void setDao(NewsDAO dao) {
        this.dao = dao;
    }

    public void addNews(News news) throws DAOException, SQLException {
        dao.addNews(news);
    }

    public News getNewsById(int newsId) throws DAOException, SQLException {
        return dao.getNewsById(newsId);
    }

    public void updateNews(News news) throws DAOException, SQLException {
        dao.updateNews(news);
    }

    public void deleteNewsById(int newsId) throws DAOException, SQLException {
        dao.deleteNewsById(newsId);
    }

    public List<News> getAllNews(int userId) throws DAOException, SQLException {
        return dao.getAllNews(userId);
    }
}
