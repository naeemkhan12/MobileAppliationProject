package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/11/17.
 */

public class Day {
    private ArrayList<Duty> duties;
    private String date;
    private int id;

    public Day(ArrayList<Duty> duties, int id,String date) {
        this.duties = duties;
        this.id=id;
        this.date=date;

    }
    public Day(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Duty> getDuties() {
        return duties;
    }

    public void setDuties(ArrayList<Duty> duties) {
        this.duties = duties;
    }
}
