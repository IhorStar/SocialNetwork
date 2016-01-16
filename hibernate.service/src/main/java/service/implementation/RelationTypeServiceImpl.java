package service.implementation;

import entity.Relation;
import entity.RelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RelationTypeDAO;
import service.RelationTypeService;

@Service
@Transactional
public class RelationTypeServiceImpl implements RelationTypeService {

    private RelationTypeDAO dao;

    @Autowired
    public void setDao(RelationTypeDAO dao) {
        this.dao = dao;
    }

    public void addRelationType(RelationType relationType) {
        dao.addRelationType(relationType);
    }

    public RelationType getRelationTypeById(int relationTypeId) {
        return dao.getRelationTypeById(relationTypeId);
    }

    public void updateRelationType(RelationType relationType) {
        dao.updateRelationType(relationType);
    }

    public void deleteRelationType(RelationType relationType) {
        dao.deleteRelationType(relationType);
    }
}
