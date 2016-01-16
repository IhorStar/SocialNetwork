package service;

import entity.Relation;

import java.util.List;

public interface RelationService {
    void addRelation(Relation relation);
    Relation getRelationById(int relationId);
    void updateRelation(Relation relation);
    void deleteRelation(Relation relation);
    List<Relation> getAllRelationBy(int userId);
}
