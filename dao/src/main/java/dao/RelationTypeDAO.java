package dao;


import entity.RelationType;

public interface RelationTypeDAO {
    void addRelationType(RelationType relationType) throws DAOException;
    RelationType getRelationTypeById(int relationTypeId) throws DAOException;
    void updateRelationType(RelationType relationType) throws DAOException;
    void deleteRelationTypeById(int relationTypeId) throws DAOException;
}
