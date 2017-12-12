package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/9/17.
 */

public class MyCardModel {
    private String time;
    private String venu;
    private String subject;
    private String day;
    private int slot;
    public MyCardModel(){

    }

    public MyCardModel(String time, String venu, String subject, String day, int slot) {
        this.time = time;
        this.venu = venu;
        this.subject = subject;
        this.day = day;
        this.slot=slot;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setTime(String time) {
        this.time = time;
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
