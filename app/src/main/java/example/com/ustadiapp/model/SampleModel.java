package example.com.ustadiapp.model;

import java.util.ArrayList;

/**
 * Created by naeem on 12/21/17.
 */

public class SampleModel {
    private String name;
    private String email;
    ArrayList<AvailableModel> list;

    public SampleModel(String name, String email, ArrayList<AvailableModel> list) {
        this.name = name;
        this.email = email;
        this.list = list;
    }

    public SampleModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<AvailableModel> getList() {
        return list;
    }

    public void setList(ArrayList<AvailableModel> list) {
        this.list = list;
    }
}
