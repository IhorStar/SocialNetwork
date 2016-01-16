package service;


import entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);
    Comment getCommentById(int commentId);
    void updateComment(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> getAllBy(int newsId);
}
