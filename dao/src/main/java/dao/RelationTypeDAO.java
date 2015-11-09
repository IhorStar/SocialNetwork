package dao;


import entity.RelationType;

import java.sql.SQLException;

public interface RelationTypeDAO {
    void addRelationType(RelationType relationType) throws DAOException, SQLException;
    RelationType getRelationTypeById(int relationTypeId) throws DAOException, SQLException;
    void updateRelationType(RelationType relationType) throws DAOException, SQLException;
    void deleteRelationTypeById(int relationTypeId) throws DAOException, SQLException;
}
