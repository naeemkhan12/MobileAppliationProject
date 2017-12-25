package example.com.ustadiapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by naeem on 12/24/17.
 */

public class Detail{
    private String heading;
    private String value;
    private int icon;

    public Detail(String heading, String value, int icon) {
        this.heading = heading;
        this.value = value;
        this.icon = icon;
    }

    public Detail() {
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }



    }
