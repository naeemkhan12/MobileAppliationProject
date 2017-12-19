package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/12/17.
 */

public class User {
    private String userId;
    private String userName;
    private String email;
    private boolean isAvailable;

    public User(String userId, String userName,String email,boolean isAvailable) {
        this.userId = userId;
        this.userName = userName;
        this.email=email;
        this.isAvailable=isAvailable;
    }
    public User() {
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
