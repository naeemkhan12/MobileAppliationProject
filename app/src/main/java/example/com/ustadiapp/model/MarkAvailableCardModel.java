package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/12/17.
 */

public class MarkAvailableCardModel {
    private String day;
    private int slot;
    private String time;
    private boolean isAvaiable;

    public MarkAvailableCardModel(String day, int slot, String time, boolean isAvaiable) {
        this.day = day;
        this.slot = slot;
        this.time = time;
        this.isAvaiable = isAvaiable;
    }

    public MarkAvailableCardModel() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAvaiable() {
        return isAvaiable;
    }

    public void setAvaiable(boolean avaiable) {
        isAvaiable = avaiable;
    }
}
