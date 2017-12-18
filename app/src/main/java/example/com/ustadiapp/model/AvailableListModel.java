package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/18/17.
 */

public class AvailableListModel extends User {

    private String venu;
    private int slot;
    public AvailableListModel(String userId,String userName,String venu,int slot ){
        super();
        setUserId(userId);
        setUserName(userName);
        this.venu=venu;
        this.slot=slot;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getVenu() {
        return venu;
    }

    public void setVenu(String venu) {
        this.venu = venu;
    }
}
