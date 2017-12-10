package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/7/17.
 */

public class Duty {
    private String venu;
    private int slotNumber;
    private String subject;
    private String startTime;
    private String endTime;
    private boolean isAvailable;

    public Duty(String venu, int slotNumber, String subject, String startTime, String endTime, boolean isAvailable) {
        this.venu = venu;
        this.slotNumber = slotNumber;
        this.subject = subject;
        this.startTime=startTime;
        this.endTime=endTime;
        this.isAvailable=isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Duty() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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


}
