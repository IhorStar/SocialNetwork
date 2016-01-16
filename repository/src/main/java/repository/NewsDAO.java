package repository;

import entity.News;

import java.util.List;

public interface NewsDAO {
    void addNews(News news);
    News getNewsById(int newsId);
    void updateNews(News news);
    void deleteNews(News news);
    List<News> getAllNews(int userId);
}
