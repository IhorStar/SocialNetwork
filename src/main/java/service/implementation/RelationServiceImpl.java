package service.implementation;


import dao.DAOException;
import dao.RelationDAO;
import dao.implementation.RelationDAOImpl;
import entity.Relation;
import service.RelationService;

import java.util.List;

public class RelationServiceImpl implements RelationService {

    RelationDAO dao = new RelationDAOImpl();

    public void addRelation(int user1Id, int user2Id, int relationTypeId) throws DAOException {
        dao.addRelation(user1Id, user2Id, relationTypeId);
    }

    public Relation getRelationById(int relationId) throws DAOException {
        return dao.getRelationById(relationId);
    }

    public void updateRelation(Relation relation) throws DAOException {
        dao.updateRelation(relation);
    }

    public void deleteRelationBy(int user1Id, int user2Id) throws DAOException {
        dao.deleteRelationBy(user1Id, user2Id);
    }

    public List<Relation> getAllRelationBy(int userId) throws DAOException {
        return dao.getAllRelationBy(userId);
    }
}
