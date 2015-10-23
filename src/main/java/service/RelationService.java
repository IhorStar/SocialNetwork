package service;


import dao.DAOException;
import entity.Relation;

import java.util.List;

public interface RelationService {
    void addRelation(int user1Id, int user2Id, int relationTypeId) throws DAOException;
    Relation getRelationById(int relationId) throws DAOException;
    void updateRelation(Relation relation) throws DAOException;
    void deleteRelationBy(int user1Id, int user2Id) throws DAOException;
    List<Relation> getAllRelationBy(int userId) throws DAOException;
}
