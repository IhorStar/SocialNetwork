package repository;

import entity.Relation;

import java.util.List;

public interface RelationDAO {
    void addRelation(Relation relation);
    Relation getRelationById(int relationId);
    void updateRelation(Relation relation);
    void deleteRelation(Relation relation);
    List<Relation> getAllRelationBy(int userId);
}
