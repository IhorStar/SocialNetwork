package repository;

import entity.RelationType;

public interface RelationTypeDAO {
    void addRelationType(RelationType relationType);
    RelationType getRelationTypeById(int relationTypeId);
    void updateRelationType(RelationType relationType);
    void deleteRelationType(RelationType relationType);
}
