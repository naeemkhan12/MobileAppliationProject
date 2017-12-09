package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/9/17.
 */

public class CardModel {
    private String time;
    private String venu;
    private String subject;
    public CardModel(){

    }

    public CardModel(String time, String venu, String subject) {
        this.time = time;
        this.venu = venu;
        this.subject = subject;
    }

    public String getTime() {
        return time;
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
