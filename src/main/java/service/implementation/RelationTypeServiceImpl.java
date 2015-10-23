package service.implementation;


import dao.DAOException;
import dao.RelationTypeDAO;
import dao.implementation.RelationTypeDAOImpl;
import entity.RelationType;
import service.RelationTypeService;


public class RelationTypeServiceImpl implements RelationTypeService {

    RelationTypeDAO dao = new RelationTypeDAOImpl();


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
