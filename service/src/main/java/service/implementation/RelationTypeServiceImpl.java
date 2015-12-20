package service.implementation;

import dao.DAOException;
import dao.RelationTypeDAO;
import entity.RelationType;
import org.springframework.stereotype.Service;
import service.RelationTypeService;

@Service
public class RelationTypeServiceImpl implements RelationTypeService {

    private RelationTypeDAO dao;

    public void setDao(RelationTypeDAO dao) {
        this.dao = dao;
    }
    public void addRelationType(RelationType relationType) throws DAOException {
        dao.addRelationType(relationType);
    }

    public RelationType getRelationTypeById(int relationTypeId) throws DAOException {
        return dao.getRelationTypeById(relationTypeId);
    }

    public void updateRelationType(RelationType relationType) throws DAOException {
        dao.updateRelationType(relationType);
    }

    public void deleteRelationTypeById(int relationTypeId) throws DAOException {
        dao.deleteRelationTypeById(relationTypeId);
    }
}
