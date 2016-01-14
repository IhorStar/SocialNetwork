package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_ID"),
        @UniqueConstraint(columnNames = "USER_NAME"),
        @UniqueConstraint(columnNames = "EMAIL")})
public class User implements Serializable{

    @Id
    @Column(name = "USER_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @OneToMany
    private Set<Role> role;

    @OneToMany
    private Set<News> news;

    @OneToMany
    private Set<Comment> comment;

    @OneToMany
    private Set<Relation> relation;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public Set<Relation> getRelation() {
        return relation;
    }

    public void setRelation(Set<Relation> relation) {
        this.relation = relation;
    }
}
