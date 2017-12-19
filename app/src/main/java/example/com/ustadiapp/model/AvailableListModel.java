package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/18/17.
 */

public class AvailableListModel {

    private String venu;
    private int slot;
    private User user;
    public AvailableListModel(User user,String venu,int slot ){
        this.venu=venu;
        this.slot=slot;
        this.user=user;
    }

    public AvailableListModel() {
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
