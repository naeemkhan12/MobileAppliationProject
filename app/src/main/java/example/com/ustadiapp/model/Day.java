package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/7/17.
 */

public class Day {
    private String venu;
    private int slotNumber;
    private String subject;
    private String time;

    public Day(String venu, int slotNumber, String subject, String time) {
        this.venu = venu;
        this.slotNumber = slotNumber;
        this.subject = subject;
        this.time = time;
    }

    public Day() {
    }

    public String getVenu() {
        return venu;
    }

    public void setVenu(String venu) {
        this.venu = venu;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
