package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/12/17.
 */

public class Slot {
    private int id;
    private String startTime;
    private String endTime;

    public Slot(int id,String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Slot() {
    }

    public String getStartTime() {
        return startTime+":00";
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime+":00";
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
