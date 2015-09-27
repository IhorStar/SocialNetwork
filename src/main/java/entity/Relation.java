package entity;


public class Relation {
    private int relationId;
    private int user1Id;
    private int user2Id;
    private int relationTypeId;

    public Relation() {

    }
    public Relation(int relationId, int user1Id, int user2Id, int relationTypeId) {
        this.relationId = relationId;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.relationTypeId = relationTypeId;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public int getRelationTypeId() {
        return relationTypeId;
    }

    public void setRelationTypeId(int relationTypeId) {
        this.relationTypeId = relationTypeId;
    }
}
