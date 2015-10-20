package dao.implementation;

import dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlDAOFactory implements DAOFactory {
    private  static  final Logger log = LogManager.getLogger(PostgresqlDAOFactory.class);

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/social_network";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public Connection getConnection()  {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            log.error("Problem with connection", e);
        }
        catch (SQLException e) {
            log.error("Problem with connection", e);
        }

        return connection;
    }

    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    public NewsDAO getNewsDAO() {
        return new NewsDAOImpl();
    }

    public CommentDAO getCommentDAO() {
        return new CommentDAOImpl();
    }

    public RelationDAO getRelationDAO() {
        return new RelationDAOImpl();
    }

    public RelationTypeDAO getRelationTypeDAO() {
        return new RelationTypeDAOImpl();
    }
}
