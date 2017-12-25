package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/24/17.
 */

public class LogModel {
    private String username;
    private String userEmail;
    private String reason;

    public LogModel(String username, String userEmail, String reason) {
        this.username = username;
        this.userEmail = userEmail;
        this.reason = reason;
    }

    public LogModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
