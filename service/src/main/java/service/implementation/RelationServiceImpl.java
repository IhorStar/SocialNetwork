package service.implementation;

import dao.DAOException;
import dao.RelationDAO;
import entity.Relation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RelationService;

import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class RelationServiceImpl implements RelationService {

    private RelationDAO dao;

    public void setDao(RelationDAO dao) {
        this.dao = dao;
    }

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
