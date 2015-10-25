package dao.implementation;

import dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresqlDAOFactory implements DAOFactory {
    private  static  final Logger log = LogManager.getLogger(PostgresqlDAOFactory.class);

    private String driver = null;
    private String url = null;
    private String user = null;
    private String password = null;

    public Connection getConnection()  {

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("database.properties");
            properties.load(input);
            driver = properties.getProperty("databaseDriver");
            url = properties.getProperty("databaseUrl");
            user = properties.getProperty("databaseUser");
            password = properties.getProperty("databasePassword");

        } catch (FileNotFoundException e) {
            log.error("File not found", e);
        } catch (IOException e) {
            log.error("Problem with input stream", e);
        }
        finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("Could not close file", e);
                }
            }
        }

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
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
