package service.implementation;


import dao.DAOException;
import dao.RelationDAO;
import dao.implementation.RelationDAOImpl;
import dao.implementation.RelationTypeDAOImpl;
import entity.Relation;
import service.RelationService;

import java.sql.SQLException;
import java.util.List;

public class RelationServiceImpl implements RelationService {

    RelationDAO dao = new RelationDAOImpl();

    public void setDao(RelationDAO dao) {
        this.dao = dao;
    }

    public void addRelation(int user1Id, int user2Id, int relationTypeId) throws DAOException, SQLException {
        dao.addRelation(user1Id, user2Id, relationTypeId);
    }

    public Relation getRelationById(int relationId) throws DAOException, SQLException {
        return dao.getRelationById(relationId);
    }

    public void updateRelation(Relation relation) throws DAOException, SQLException {
        dao.updateRelation(relation);
    }

    public void deleteRelationBy(int user1Id, int user2Id) throws DAOException, SQLException {
        dao.deleteRelationBy(user1Id, user2Id);
    }

    public List<Relation> getAllRelationBy(int userId) throws DAOException, SQLException {
        return dao.getAllRelationBy(userId);
    }
}
