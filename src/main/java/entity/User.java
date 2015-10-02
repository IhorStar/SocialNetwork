package entity;


public class User {
    private int userId;
    private int roleId;
    private String name;
    private String password;
    private String email;

    public User() {

    }
    public User(int userId, int roleId, String name, String password, String email) {
        this.userId = userId;
        this.roleId = roleId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public  int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
