package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/7/17.
 */

public class Schedule {
    private int slots;
    private ArrayList<Duty>list;

    public Schedule() {
    }

    public Schedule(int slots, ArrayList<Duty> list) {
        this.slots = slots;
        this.list = list;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public ArrayList<Duty> getList() {
        return list;
    }

    public void setList(ArrayList<Duty> list) {
        this.list = list;
    }
}
