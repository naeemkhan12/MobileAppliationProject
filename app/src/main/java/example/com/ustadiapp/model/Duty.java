package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/7/17.
 */

public class Duty {
    private String venu;
    private Slot slot;
    private String subject;
    private boolean isAvailable;
    private User user;

    public Duty(String venu,  Slot slot,String subject, boolean isAvailable) {
        this.venu = venu;
        this.slot = slot;
        this.subject = subject;

        this.isAvailable=isAvailable;
    }
    public Duty(String venu, Slot slot,String subject, boolean isAvailable,User user) {
        this.venu = venu;
        this.slot = slot;
        this.subject = subject;
        this.user=user;
        this.isAvailable=isAvailable;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Duty() {
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
