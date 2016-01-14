package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "RELATION", uniqueConstraints = {
        @UniqueConstraint(columnNames = "RELATION_ID")})
public class Relation implements Serializable{

    @Id
    @Column(name = "RELATION_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer relationId;

    @Column(name = "USER_1",nullable = false)
    private User user1;

    @Column(name = "USER_2", nullable = false)
    private User user2;

    @OneToMany
    private Set<RelationType> relationType;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Set<RelationType> getRelationType() {
        return relationType;
    }

    public void setRelationType(Set<RelationType> relationType) {
        this.relationType = relationType;
    }
}
