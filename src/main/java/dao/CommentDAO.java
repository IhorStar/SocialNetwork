package dao;


import entity.Comment;

public interface CommentDAO {
    public void addComment(Comment comment) throws DAOException;
    public Comment getCommentById(int commentId) throws DAOException;
    public void updateComment(Comment comment) throws DAOException;
    public void deleteComment(Comment comment) throws DAOException;
}
