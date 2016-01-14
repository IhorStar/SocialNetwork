package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "NEWS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "NEWS_ID")})
public class News implements Serializable {

    @Id
    @Column(name = "NEWS_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer newsId;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DATE", nullable = false)
    private String date;

    @Column(name = "TIME", nullable = false)
    private String time;

    @OneToMany
    private Set<Comment> comment;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }
}
