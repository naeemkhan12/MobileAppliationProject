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
    User[] users = {new User("X6g0wikHVASspueiFUoBLZct5t03","Namal User","naeemr2014@namal.edu.pk",true),new User("qqKz3v39ocZ38OS5uIrp6f1gyvs2","Bradford User","naeemb7070@gmail.com",true)};
    Slot[] slots= {new Slot(1,"9:00","10:15"),new Slot(2,"10:30","11:45"),new Slot(3,"12:00","13:15"),new Slot(4,"14:15","15:30"),new Slot(5,"15:45","16:00")};
    String[] subjects = {"Physics","Math","Chem","Bio","SD"};
    String[] venus ={"Lab-1","Lab-2","LH-1","LH-2","LH-3"};
    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    Date[] dates = {new Date("21","01","2018"),new Date("22","01","2018"),new Date("23","01","2018"),new Date("24","01","2018"),new Date("25","01","2018")};

    public Schedule getRandomSchedule(){
        ArrayList<Duty> duties = new ArrayList<>();
        for (int i=0;i<5;i++){
            double random= Math.random()*5;
            double randUser = Math.random()*2;
            int randInt = (int) random;
            int randUserint = (int) randUser;
            for(int j=0; j<dates.length;j++){
                duties.add(new Duty(venus[randInt],slots[randInt],subjects[randInt],users[randUserint],dates[i],days[i]));
                Log.i("TESTLOG",randInt+"");
            }

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