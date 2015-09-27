package dao;

import java.sql.Connection;

public interface DAOFactory {

    public Connection getConnection();
    public UserDAO getUserDAO();
    public NewsDAO getNewsDAO();
    public CommentDAO getCommentDAO();
    public RelationDAO getRelationDAO();
    public RelationTypeDAO getRelationTypeDAO();
}