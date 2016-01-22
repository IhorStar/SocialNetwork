package repository.implementation;

import entity.News;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repository.NewsDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addNews(News news) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(news);
    }

    public News getNewsById(int newsId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(News.class, newsId);
    }

    public void updateNews(News news) {
        Session currentSession = sessionFactory.getCurrentSession();
        News existingNews = currentSession.get(News.class, news.getNewsId());
        existingNews.setDescription(news.getDescription());
        currentSession.save(existingNews);
    }

    public void deleteNews(News news) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(news);
    }

    public List<News> getAllNews(int userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, userId);
        return new ArrayList<News>(user.getNews());
    }
}
