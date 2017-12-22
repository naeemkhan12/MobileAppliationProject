package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/21/17.
 */

public class AvailableModel {
   private  Slot slot;
    private String day;
    private Date date;
    private boolean isAvailable;

    public AvailableModel() {
    }

    public AvailableModel(Slot slot, String day, Date date, boolean isAvailable) {
        this.slot = slot;
        this.day = day;
        this.date = date;
        this.isAvailable = isAvailable;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
