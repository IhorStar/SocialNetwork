package service;

import dao.DAOException;
import entity.RelationType;

import java.sql.SQLException;

public interface RelationTypeService {
    void addRelationType(RelationType relationType) throws DAOException, SQLException;
    RelationType getRelationTypeById(int relationTypeId) throws DAOException, SQLException;
    void updateRelationType(RelationType relationType) throws DAOException, SQLException;
    void deleteRelationTypeById(int relationTypeId) throws DAOException, SQLException;
}
