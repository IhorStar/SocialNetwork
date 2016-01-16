package service.implementation;

import entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.NewsDAO;
import service.NewsService;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private NewsDAO dao;

    @Autowired
    public void setDao(NewsDAO dao) {
        this.dao = dao;
    }

    public void addNews(News news) {
        dao.addNews(news);
    }

    public News getNewsById(int newsId) {
        return dao.getNewsById(newsId);
    }

    public void updateNews(News news) {
        dao.updateNews(news);
    }

    public void deleteNews(News news) {
        dao.deleteNews(news);
    }

    public List<News> getAllNews(int userId) {
        return dao.getAllNews(userId);
    }
}
