package dao.implementation;

import dao.DAOException;
import dao.RelationDAO;
import entity.Relation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RelationDAOImpl implements RelationDAO {
    private PostgresqlDAOFactory postgresqlDaoFactory = new PostgresqlDAOFactory();

    public void addRelation(Relation relation) throws DAOException {
        String query = "insert into relation values (?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, relation.getRelationId());
            statement.setInt(2, relation.getUser1Id());
            statement.setInt(3, relation.getUser2Id());
            statement.setInt(4, relation.getRelationTypeId());
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

    public Relation getRelationById(int relationId) throws  DAOException {
        String query = "select * from relation_type where relation_type = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Relation relation = new Relation();
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                relation.setRelationId(resultSet.getInt("relation_id"));
                relation.setUser1Id(resultSet.getInt("user1"));
                relation.setUser2Id(resultSet.getInt("user2"));
                relation.setRelationTypeId(resultSet.getInt("relation_type"));
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
        return relation;
    }

    public void updateRelation(Relation relation) throws  DAOException {
        String query = "update relation_type set user1 = ?, user2 = ?, relation_type = ? where relation_type = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, relation.getRelationTypeId());
            statement.setInt(2, relation.getUser1Id());
            statement.setInt(3, relation.getUser2Id());
            statement.setInt(4, relation.getRelationTypeId());
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

    public void deleteRelation(Relation relation) throws DAOException {
        String query = "delete from relation_type where relation_type = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, relation.getRelationId());
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
