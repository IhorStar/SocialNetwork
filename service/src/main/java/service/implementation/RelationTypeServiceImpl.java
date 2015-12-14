package service.implementation;


import dao.DAOException;
import dao.RelationTypeDAO;
import entity.RelationType;
import org.springframework.stereotype.Service;
import service.RelationTypeService;

import java.sql.SQLException;

@Service("relationTypeService")
public class RelationTypeServiceImpl implements RelationTypeService {

    private RelationTypeDAO dao;

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
