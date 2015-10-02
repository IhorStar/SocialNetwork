package dao.implementation;

import dao.DAOException;
import dao.RelationDAO;
import entity.Relation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RelationDAOImpl implements RelationDAO {
    private PostgresqlDAOFactory postgresqlDaoFactory = new PostgresqlDAOFactory();

    public void addRelation(int user1Id, int user2Id, int relationTypeId) throws DAOException {
        String query = "insert into relation(user1, user2, relation_type) values (?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, user1Id);
            statement.setInt(2, user2Id);
            statement.setInt(3, relationTypeId);
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
        String query = "select * from relation where relation_id = ?;";
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
        String query = "update relation set user1 = ?, user2 = ?, relation_type = ? where relation_id = ?;";
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

    public void deleteRelationBy(int user1id, int user2Id) throws DAOException {
        String query = "delete from relation where user1 = ? and user2 = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = postgresqlDaoFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, user1id);
            statement.setInt(2, user2Id);
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

    public List<Relation> getAllRelationBy(int userId) throws DAOException {
        String query = "select * from relation where user1 = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Relation> relationList = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            relationList = new ArrayList<Relation>();
            while(resultSet.next()) {
                int relationId = resultSet.getInt("relation_id");
                int user1Id = resultSet.getInt("user1");
                int user2Id = resultSet.getInt("user2");
                int relationTypeId = resultSet.getInt("relation_type");
                Relation relation = new Relation();
                relation.setRelationId(relationId);
                relation.setUser1Id(user1Id);
                relation.setUser2Id(user2Id);
                relation.setRelationTypeId(relationTypeId);
                relationList.add(relation);

            }
        }
        catch (SQLException e) {
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
        return relationList;
    }
}
