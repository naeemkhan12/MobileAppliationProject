package example.com.ustadiapp.model;

/**
 * Created by naeem on 12/19/17.
 */

public class Date {
    private int day;
    private int year;
    private int month;

    public Date(int day, int month, int year) {
        this.day = day;
        this.year = year;
        this.month = month;
    }

    public Date() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
