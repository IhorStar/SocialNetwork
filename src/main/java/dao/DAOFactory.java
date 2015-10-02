package dao;

import java.sql.Connection;

public interface DAOFactory {

    Connection getConnection();
    UserDAO getUserDAO();
    NewsDAO getNewsDAO();
    CommentDAO getCommentDAO();
    RelationDAO getRelationDAO();
    RelationTypeDAO getRelationTypeDAO();
}