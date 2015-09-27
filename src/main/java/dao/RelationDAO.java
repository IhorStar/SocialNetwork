package dao;


import entity.Relation;

public interface RelationDAO {
    public void addRelation(Relation relation) throws DAOException;
    public Relation getRelationById(int relationId) throws DAOException;
    public void updateRelation(Relation relation) throws DAOException;
    public void deleteRelation(Relation relation) throws DAOException;
}
