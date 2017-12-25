package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/19/17.
 */

public class Date {
    private String year;
    private String month;
    private String day;

    public Date(String day, String month, String year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString(){
        return this.year+"/"+this.month+"/"+this.day;
    }
}
