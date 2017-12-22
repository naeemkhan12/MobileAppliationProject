package example.com.ustadiapp.randomData;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import example.com.ustadiapp.model.AvailableModel;
import example.com.ustadiapp.model.Date;
import example.com.ustadiapp.model.Day;
import example.com.ustadiapp.model.Duty;
import example.com.ustadiapp.model.SampleModel;
import example.com.ustadiapp.model.Schedule;
import example.com.ustadiapp.model.Slot;
import example.com.ustadiapp.model.User;

/**
 * Created by naeem on 12/12/17.
 */

public class RandomDutyGenerator {
    User[] users = {new User("X6g0wikHVASspueiFUoBLZct5t03","Atif Aslam","someone@example.com",true),new User("X6g0wikHVASspueiFUoBLZct5t03","Ahmed Jhanzed","someone@example.com",true),new User("jCpxldgig4cfTWHeMKtZUlqG5GA2","Jawad Ahmed","someone@example.com",true),new User("qqKz3v39ocZ38OS5uIrp6f1gyvs2","Abrar-ul-Haq","someone@example.com",true),new User("5","Ali Zafar","someone@example.com",true)};
    Slot[] slots= {new Slot(1,"9:00","10:15"),new Slot(2,"10:30","11:45"),new Slot(3,"12:00","1:15"),new Slot(4,"2:15","3:30"),new Slot(5,"3:45","5:00")};
    String[] subjects = {"Physics","Math","Chem","Bio","SD"};
    String[] venus ={"Lab1","Lab2","LH1","LH2","LH3"};
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    Date[] dates = {new Date("01","01","2011"),new Date("02","01","2011"),new Date("03","01","2011"),new Date("04","01","2011"),new Date("05","01","2011")};

    public Schedule getRandomSchedule(){
        ArrayList<Duty> duties = new ArrayList<>();
        for (int i=0;i<5;i++){
            double random= Math.random()*5;
            int randInt = (int) random;
            duties.add(new Duty(venus[randInt],slots[randInt],subjects[randInt],users[randInt],dates[randInt],days[randInt]));
            Log.i("TESTLOG",randInt+"");
        }
        return new Schedule(5,duties);
    }
    public SampleModel populateData(){
        ArrayList<AvailableModel> list = new ArrayList<>();

        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                list.add(new AvailableModel(slots[j],days[i],dates[i],true));
            }
        }
        return new SampleModel("John Doe","useremail@example.com",list);
    }
}