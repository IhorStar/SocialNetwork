import dao.DAOException;
import dao.implementation.NewsDAOImpl;
import entity.News;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.implementation.NewsServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NewsServiceImplTest {
    private NewsServiceImpl newsService;
    private NewsDAOImpl newsDAO;

    News news1 = new News();
    News news2 = new News();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        newsDAO = mock(NewsDAOImpl.class);

        newsService = new NewsServiceImpl();
        newsService.setDao(newsDAO);

        news1.setNewsId(1);
        news1.setDescription("first news");
        news1.setDate("17/11/2015");
        news1.setTime("18:40:20");
        news1.setUserId(1);

        news2.setNewsId(2);
        news2.setDescription("second news");
        news2.setDate("17/11/2015");
        news2.setTime("18:40:40");
        news2.setUserId(1);
    }

    @Test
    public void testAddNews() throws Exception {
        newsService.addNews(news1);
        verify(newsDAO).addNews(news1);
    }

    @Test
    public void testGetNewsById() throws Exception {
        when(newsDAO.getNewsById(1)).thenReturn(news1);
        News news = newsService.getNewsById(1);
        verify(newsDAO).getNewsById(1);
        assertEquals("first news", news.getDescription());
    }

    @Test
    public void testGetNewsById2() throws Exception {
        doThrow(new DAOException("cannot find news with id = 0")).when(newsDAO).getNewsById(0);
        exception.expect(DAOException.class);
        exception.expectMessage("cannot find news with id = 0");
        News news = newsDAO.getNewsById(0);
    }

    @Test
    public void testUpdateNews() throws Exception {
        newsService.updateNews(news1);
        verify(newsDAO).updateNews(news1);
    }

    @Test
    public void testDeleteNewsById() throws Exception {
        newsService.deleteNewsById(1);
        verify(newsDAO).deleteNewsById(1);
    }

    @Test
    public void testGetAllNews() throws Exception {
        List<News> allNews = new ArrayList<News>();
        int counter = 0;

        allNews.add(news1);
        allNews.add(news2);

        when(newsDAO.getAllNews(1)).thenReturn(allNews);
        List<News> result = newsService.getAllNews(1);
        for(News news : result) {
            counter++;
        }
        verify(newsDAO).getAllNews(1);
        assertEquals(2, counter);
    }
}
