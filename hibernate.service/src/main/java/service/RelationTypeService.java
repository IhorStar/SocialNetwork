package service;


import entity.RelationType;

public interface RelationTypeService {
    void addRelationType(RelationType relationType);
    RelationType getRelationTypeById(int relationTypeId);
    void updateRelationType(RelationType relationType);
    void deleteRelationType(RelationType relationType);
}
