package example.com.ustadiapp.model;

import java.util.*;

/**
 * Created by naeem on 12/18/17.
 */

public class AvailableListModel {

    private String venu;
    private int slot;
    private User user;
    private Date date;
    private int index;
    public AvailableListModel(User user,String venu,int slot ,Date date,int index){
        this.venu=venu;
        this.slot=slot;
        this.user=user;
        this.date=date;
        this.index=index;
    }

    public AvailableListModel() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenu() {
        return venu;
    }

    public void setVenu(String venu) {
        this.venu = venu;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
