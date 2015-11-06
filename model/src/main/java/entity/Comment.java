package entity;


public class Comment {
    private int commentId;
    private String text;
    private String date;
    private String time;
    private int newsId;
    private int userId;

    public Comment(int commentId, String text, String date, String time, int newsId, int userId) {
        this.commentId = commentId;
        this.text = text;
        this.date = date;
        this.time = time;
        this.newsId = newsId;
        this.userId = userId;
    }

    public Comment() {

    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
