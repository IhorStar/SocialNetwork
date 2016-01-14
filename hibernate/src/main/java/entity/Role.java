package entity;

import javax.persistence.*;

@Entity
@Table(name = "ROLE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ROLE_ID"),
        @UniqueConstraint(columnNames = "ROLE_TYPE")})
public class Role {

    @Id
    @Column(name = "ROLE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleID;

    @Column(name = "ROLE_TYPE", nullable = false, unique = true)
    private String roleType;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
