package service.implementation;

import dao.DAOException;
import dao.NewsDAO;
import entity.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.NewsService;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private NewsDAO dao;

    public void setDao(NewsDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void addNews(News news) throws DAOException {
        dao.addNews(news);
    }

    @Transactional
    public News getNewsById(int newsId) throws DAOException {
        return dao.getNewsById(newsId);
    }

    @Transactional
    public void updateNews(News news) throws DAOException {
        dao.updateNews(news);
    }

    @Transactional
    public void deleteNewsById(int newsId) throws DAOException {
        dao.deleteNewsById(newsId);
    }

    @Transactional
    public List<News> getAllNews(int userId) throws DAOException {
        return dao.getAllNews(userId);
    }
}
