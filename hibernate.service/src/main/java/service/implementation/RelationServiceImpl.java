package service.implementation;

import entity.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RelationDAO;
import service.RelationService;

import java.util.List;

@Service
@Transactional
public class RelationServiceImpl implements RelationService {

    private RelationDAO dao;

    @Autowired
    public void setDao(RelationDAO dao) {
        this.dao = dao;
    }

    public void addRelation(Relation relation) {
        dao.addRelation(relation);
    }

    public Relation getRelationById(int relationId) {
        return dao.getRelationById(relationId);
    }

    public void updateRelation(Relation relation) {
        dao.updateRelation(relation);
    }

    public void deleteRelation(Relation relation) {
        dao.deleteRelation(relation);
    }

    public List<Relation> getAllRelationBy(int userId) {
        return dao.getAllRelationBy(userId);
    }
}
