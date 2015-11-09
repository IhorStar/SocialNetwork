package dao;


import entity.Relation;

import java.sql.SQLException;
import java.util.List;

public interface RelationDAO {
    void addRelation(int user1Id, int user2Id, int relationTypeId) throws DAOException, SQLException;
    Relation getRelationById(int relationId) throws DAOException, SQLException;
    void updateRelation(Relation relation) throws DAOException, SQLException;
    void deleteRelationBy(int user1Id, int user2Id) throws DAOException, SQLException;
    List<Relation> getAllRelationBy(int userId) throws DAOException, SQLException;
}
