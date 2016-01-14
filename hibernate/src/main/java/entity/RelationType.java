package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "RELATION_TYPE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "RELATION_TYPE_ID"),
        @UniqueConstraint(columnNames = "RELATION_TYPE")})
public class RelationType implements Serializable {

    @Id
    @Column(name = "RELATION_TYPE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer relationTypeId;

    @Column(name = "RELATION_TYPE", nullable = false, unique = true)
    private String relationType;

    public Integer getRelationTypeId() {
        return relationTypeId;
    }

    public void setRelationTypeId(Integer relationTypeId) {
        this.relationTypeId = relationTypeId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}
