package example.com.ustadiapp;


import android.app.AlarmManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.com.ustadiapp.database.FirebaseCRUD;
import example.com.ustadiapp.model.Day;
import example.com.ustadiapp.model.Duty;
import example.com.ustadiapp.model.GeneralCardModel;
import example.com.ustadiapp.model.SampleModel;
import example.com.ustadiapp.model.Schedule;
import example.com.ustadiapp.randomData.RandomDutyGenerator;




//set alarm: https://stackoverflow.com/questions/34517520/how-to-give-notifications-on-android-on-specific-time

public class MainActivity extends AppCompatActivity {
    private static final String LOG="TESTLOG";
    private static final String TAG = "TESTLOG";
    private static final String[] DAYS={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private RecyclerView recyclerView;
    private Context context;
    private String userId;
    private ArrayList<Duty> duties;
    private FirebaseCRUD crud;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=this;
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        crud = new FirebaseCRUD();
        crud.updateDutyTable(new RandomDutyGenerator().getRandomSchedule());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        crud.dutyTableRefrence().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Schedule schedule = dataSnapshot.getValue(Schedule.class);
                duties=schedule.getList();
                recyclerView.setAdapter(new CustomGeneralViewAdapter(context,duties,getFragmentManager(),userId));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;

    }
    public ArrayList<Duty> singleUserList(){
        ArrayList<Duty> dutyArrayList = new ArrayList<>();
        for(Duty duty:duties){
            if (userId.equals(duty.getUser().getUserId())){
                dutyArrayList.add(duty);
            }
        }
        return dutyArrayList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_user:
                recyclerView.setAdapter(new CustomGeneralViewAdapter(context,singleUserList(),getFragmentManager(),userId));
                break;
            case R.id.action_all:
                recyclerView.setAdapter(new CustomGeneralViewAdapter(context,duties,getFragmentManager(),userId));
                break;
            case R.id.action_setting:
                break;
            case R.id.action_logout:
                AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(context, LoginActivity.class));
                            finish();
                        }
                    });
                break;
        }
        Log.i(LOG,"menu item id: "+item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}





