package dao.implementation;

import dao.DAOException;
import dao.RelationTypeDAO;
import entity.RelationType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RelationTypeDAOImpl implements RelationTypeDAO {
    private PostgresqlDAOFactory postgresqlDaoFactory = new PostgresqlDAOFactory();


    public void addRelationType(RelationType relationType) throws DAOException {
        String query = "insert into relation_type  values (?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, relationType.getRelationTypeId());
            statement.setString(2, relationType.getRelationTypeName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public RelationType getRelationTypeById(int relationTypeId) throws  DAOException {
        String query = "select * from relation_type where relation_type_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        RelationType relationType = new RelationType();
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                relationType.setRelationTypeId(resultSet.getInt("relation_type_id"));
                relationType.setRelationTypeName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return relationType;
    }

    public void updateRelationType(RelationType relationType) throws DAOException {
        String query = "update relation_type set name = ? where relation_type_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, relationType.getRelationTypeId());
            statement.setString(2, relationType.getRelationTypeName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteRelationType(RelationType relationType) throws DAOException {
        String query = "delete from relation_type where relation_type_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, relationType.getRelationTypeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
