package service.implementation;

import dao.DAOException;
import dao.RelationTypeDAO;
import entity.RelationType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RelationTypeService;

@Service
@Transactional
public class RelationTypeServiceImpl implements RelationTypeService {

    private RelationTypeDAO dao;

    public void setDao(RelationTypeDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void addRelationType(RelationType relationType) throws DAOException {
        dao.addRelationType(relationType);
    }

    @Transactional
    public RelationType getRelationTypeById(int relationTypeId) throws DAOException {
        return dao.getRelationTypeById(relationTypeId);
    }

    @Transactional
    public void updateRelationType(RelationType relationType) throws DAOException {
        dao.updateRelationType(relationType);
    }

    @Transactional
    public void deleteRelationTypeById(int relationTypeId) throws DAOException {
        dao.deleteRelationTypeById(relationTypeId);
    }
}
