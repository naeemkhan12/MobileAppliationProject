package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/7/17.
 */

public class Duty {
    private String venu;
    private Slot slot;
    private String subject;
    private User user;
    private Date date;
    private String day;

    public Duty() {
    }

    public Duty(String venu, Slot slot, String subject, User user, Date date, String day) {
        this.venu = venu;
        this.slot = slot;
        this.subject = subject;
        this.user = user;
        this.date = date;
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Slot getSlot() {
        return slot;
    }
    public void setSlot(Slot slot) {
        this.slot = slot;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getVenu() {
        return venu;
    }
    public void setVenu(String venu) {
        this.venu = venu;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
