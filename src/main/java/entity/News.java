package entity;

public class News {
    private int newsId;
    private String description;
    private String date;
    private String time;
    private int userId;

    public News() {

    }

    public News(int newsId, String description, String date, String time, int userId) {
        this.newsId = newsId;
        this.description = description;
        this.date = date;
        this.time = time;
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int id) {
        this.newsId = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
