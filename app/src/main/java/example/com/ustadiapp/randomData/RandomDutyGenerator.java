package example.com.ustadiapp.randomData;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import example.com.ustadiapp.model.Day;
import example.com.ustadiapp.model.Duty;
import example.com.ustadiapp.model.Schedule;
import example.com.ustadiapp.model.Slot;
import example.com.ustadiapp.model.User;

/**
 * Created by naeem on 12/12/17.
 */

public class RandomDutyGenerator {

    public Schedule getRandomSchedule(){
//        dutyArrayList.add(new Duty("",5,"","3:45","5:00",true));
        ArrayList<Day> days = new ArrayList<>();

        User[] randomUser = {new User("1","Atif Aslam"),new User("2","Ahmed Jhanzed"),new User("3","Jawad Ahmed"),new User("4","Abrar-ul-Haq"),new User("5","Ali Zafar")};
        Slot[] getRandomSlot= {new Slot(1,"9:00","10:15"),new Slot(2,"10:30","11:45"),new Slot(3,"12:00","1:15"),new Slot(4,"2:15","3:30"),new Slot(5,"3:45","5:00")};
        String[] subject = {"Physics","Math","Chem","Bio","SD"};
        String[] venu={"Lab1","Lab2","LH1","LH2","LH3"};


        for (int i=0;i<5;i++){

            ArrayList<Duty> duties = new ArrayList<>();
            double randInt= Math.random()*5;
            Log.i("TESTLOG",randInt+"");
            for (int j=0;j<=randInt;j++){
                Duty duty = new Duty(venu[(int) randInt],getRandomSlot[j],subject[(int) randInt],true,randomUser[(int) randInt]);
                duties.add(duty);
            }
            Day day = new Day(duties,i);
            days.add(day);
        }

        return new Schedule("12-5-2017",days,5);
    }

}