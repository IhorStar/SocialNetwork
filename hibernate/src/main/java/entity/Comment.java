package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMMENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "COMMENT_ID")})
public class Comment implements Serializable {

    @Id
    @Column(name = "COMMENT_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comentId;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "DATE", nullable = false)
    private String date;

    @Column(name= "TIME", nullable = false)
    private String time;

    public Integer getComentId() {
        return comentId;
    }

    public void setComentId(Integer comentId) {
        this.comentId = comentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
