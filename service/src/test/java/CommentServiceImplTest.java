import dao.DAOException;
import dao.implementation.CommentDAOImpl;
import entity.Comment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.implementation.CommentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {
    private CommentServiceImpl commentService;
    private CommentDAOImpl commentDAO;

    Comment comment1 = new Comment();
    Comment comment2 = new Comment();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        commentDAO = mock(CommentDAOImpl.class);

        commentService = new CommentServiceImpl();
        commentService.setDao(commentDAO);

        comment1.setCommentId(1);
        comment1.setText("first comment");
        comment1.setDate("17/11/2015");
        comment1.setTime("19:13:50");
        comment1.setNewsId(1);
        comment1.setUserId(1);

        comment2.setCommentId(2);
        comment2.setText("first comment");
        comment2.setDate("17/11/2015");
        comment2.setTime("19:14:20");
        comment2.setNewsId(1);
        comment2.setUserId(1);
    }

    @Test
    public void testAddComment() throws Exception {
        commentService.addComment(comment1);
        verify(commentDAO).addComment(comment1);
    }

    @Test
    public void testGetCommentById() throws Exception {
        when(commentDAO.getCommentById(1)).thenReturn(comment1);
        Comment comment = commentService.getCommentById(1);
        verify(commentDAO).getCommentById(1);
        assertEquals("first comment", comment.getText());
    }

    @Test
    public void testGetCommentById2() throws Exception {
        doThrow(new DAOException("cannot find comment with id = 0")).when(commentDAO).getCommentById(0);
        exception.expect(DAOException.class);
        exception.expectMessage("cannot find comment with id = 0");
        Comment comment = commentDAO.getCommentById(0);
    }

    @Test
    public void testUpdateComment() throws Exception {
        commentService.updateComment(comment1);
        verify(commentDAO).updateComment(comment1);
    }

    @Test
    public void testDeleteCommentById() throws Exception {
        commentService.deleteCommentById(1);
        verify(commentDAO).deleteCommentById(1);
    }

    @Test
    public void testGetAllCommentById() throws Exception {
        List<Comment> allComment = new ArrayList<Comment>();
        int counter = 0;
        allComment.add(comment1);
        allComment.add(comment2);

        when(commentDAO.getAllBy(1)).thenReturn(allComment);
        List<Comment> result = commentService.getAllBy(1);
        for(Comment comment : result) {
            counter++;
        }
        verify(commentDAO).getAllBy(1);
        assertEquals(2, counter);
    }
}
