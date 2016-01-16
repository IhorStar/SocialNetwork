package repository;


import entity.Comment;
import entity.News;

import java.util.List;

public interface CommentDAO {
    void addComment(Comment comment);
    Comment getCommentById(int commentId);
    void updateComment(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> getAllBy(int newsId);
}
