package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/11/17.
 */

public class Day {
    private ArrayList<Duty> duties;
    private Date date;
    private String dayName;

    public Day(ArrayList<Duty> duties, String name,Date date) {
        this.duties = duties;
        this.date=date;
        this.dayName=name;

    }
    public Day(){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return dayName;
    }

    public void setName(String dayName) {
        this.dayName = dayName;
    }

    public ArrayList<Duty> getDuties() {
        return duties;
    }

    public void setDuties(ArrayList<Duty> duties) {
        this.duties = duties;
    }
}
