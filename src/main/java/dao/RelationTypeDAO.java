package dao;


import entity.RelationType;

public interface RelationTypeDAO {
    public void addRelationType(RelationType relationType) throws DAOException;
    public RelationType getRelationTypeById(int relationTypeId) throws DAOException;
    public void updateRelationType(RelationType relationType) throws DAOException;
    public void deleteRelationType(RelationType relationType) throws DAOException;
}
