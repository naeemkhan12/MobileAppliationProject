package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/7/17.
 */

public class Schedule {
    private String date;
    private int slots;
    private ArrayList<Day> list;

    public Schedule(String date, ArrayList<Day> list, int slots) {
        this.date = date;
        this.list = list;
        this.slots=slots;
    }

    public Schedule() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Day> getList() {
        return list;
    }

    public void setList(ArrayList<Day> list) {
        this.list = list;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    @Override
    public String toString(){
        return "Finally ....DAz DAz DAz";
    }
}
