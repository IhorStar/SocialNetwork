package service.implementation;


import dao.DAOException;
import dao.RelationTypeDAO;
import dao.implementation.RelationTypeDAOImpl;
import entity.RelationType;
import service.RelationTypeService;

import java.sql.SQLException;


public class RelationTypeServiceImpl implements RelationTypeService {

    RelationTypeDAO dao = new RelationTypeDAOImpl();

    public void setDao(RelationTypeDAO dao) {
        this.dao = dao;
    }
    public void addRelationType(RelationType relationType) throws DAOException, SQLException {
        dao.addRelationType(relationType);
    }

    public RelationType getRelationTypeById(int relationTypeId) throws DAOException, SQLException {
        return dao.getRelationTypeById(relationTypeId);
    }

    public void updateRelationType(RelationType relationType) throws DAOException, SQLException {
        dao.updateRelationType(relationType);
    }

    public void deleteRelationTypeById(int relationTypeId) throws DAOException, SQLException {
        dao.deleteRelationTypeById(relationTypeId);
    }
}
