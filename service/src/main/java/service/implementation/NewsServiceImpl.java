package service.implementation;

import dao.DAOException;
import dao.NewsDAO;
import entity.News;
import org.springframework.stereotype.Service;
import service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsDAO dao;

    public void setDao(NewsDAO dao) {
        this.dao = dao;
    }

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
