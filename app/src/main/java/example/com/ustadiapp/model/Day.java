package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/11/17.
 */

public class Day {
    private ArrayList<Duty> duties;
    private int id;

    public Day(ArrayList<Duty> duties, int id) {
        this.duties = duties;
        this.id=id;
    }
    public Day(){

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
