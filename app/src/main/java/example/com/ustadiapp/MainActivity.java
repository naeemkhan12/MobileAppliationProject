package example.com.ustadiapp;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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
import com.google.gson.internal.Streams;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import example.com.ustadiapp.database.FirebaseCRUD;
import example.com.ustadiapp.model.AvailableListModel;
import example.com.ustadiapp.model.Day;
import example.com.ustadiapp.model.Detail;
import example.com.ustadiapp.model.Duty;
import example.com.ustadiapp.model.GeneralCardModel;
import example.com.ustadiapp.model.LogModel;
import example.com.ustadiapp.model.SampleModel;
import example.com.ustadiapp.model.Schedule;
import example.com.ustadiapp.model.User;
import example.com.ustadiapp.randomData.RandomDutyGenerator;




//set alarm: https://stackoverflow.com/questions/34517520/how-to-give-notifications-on-android-on-specific-time

public class MainActivity extends AppCompatActivity{
    private static final String LOG="TESTLOG";
    private static final String TAG = "TESTLOG";
    private static final String[] DAYS={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private static final String PREFS_NAME = "TOKEN_SERVICE";
    private RecyclerView recyclerView;
    private Context context;
    private String userId;
    private ArrayList<Duty> duties = new ArrayList<>();
    private FirebaseCRUD crud;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    CustomGeneralViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=this;
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        userId=mAuth.getUid();
        database=FirebaseDatabase.getInstance();
        crud = new FirebaseCRUD();
        /*
        *
        * random duty generator to be called
        *
        * */

//        crud.updateDutyTable(new RandomDutyGenerator().getRandomSchedule());
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (settings.getString("email","").equals("")){
            String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String token = settings.getString("token","");
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("email",email);
            editor.commit();
            Log.i(LOG,"email: "+email+" token: "+token);
                SendtoServer server =new SendtoServer(email,token);
                server.start();
        }




        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        crud.dutyTableRefrence().addValueEventListener(new ValueEventListener() {
//            https://github.com/afollestad/material-dialogs
            MaterialDialog prgressDialog = new MaterialDialog.Builder(context)
                    .title(R.string.progress_dialog)
                    .content(R.string.please_wait)
                    .progress(true, 0)
                    .show();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Schedule schedule = dataSnapshot.getValue(Schedule.class);
                Log.i(LOG,"Dataset changed");
                setDuties(schedule.getList());
                adapter = new CustomGeneralViewAdapter(context,duties,getFragmentManager(),userId);
                prgressDialog.dismiss();
                adapter.setPositionCallback(new CustomGeneralViewAdapter.PositionCallback() {
                    @Override
                    public void getPosition(int position, String flag) {
                        if (flag!=null){
                            Log.i(LOG,"Inside logs");
                            duties.get(position).getUser().setAvailable(false);
                            crud.updateDuty(position,duties.get(position));
                            User user = duties.get(position).getUser();
                            LogModel log = new LogModel();
                            crud.insertLog(userId,log);

                        }else {
                            Intent intent = new Intent(context, DetailActivity.class);
                            ArrayList<String> paramsList = new ArrayList<String>();
                            paramsList.add(getDuties().get(position).getUser().getUserName());
                            paramsList.add(getDuties().get(position).getUser().getEmail());
                            paramsList.add(getDuties().get(position).getSubject());
                            paramsList.add(getDuties().get(position).getVenu());
                            paramsList.add(getDuties().get(position).getSlot().getStartTime() + " " + getDuties().get(position).getSlot().getEndTime());
                            paramsList.add(getDuties().get(position).getDate().toString());
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("Detail", paramsList);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                });
                recyclerView.setAdapter(adapter);



                Bundle extras= getIntent().getExtras();
                if (extras!=null){
                    String index = extras.get("index").toString();
                    try {
                        int indexInt = Integer.parseInt(index);
                        createListPopUp(indexInt);
                    } catch (ParseException e) {
                        Log.i(LOG,"parse exception");
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void createListPopUp(final int index) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        ListView listView = new ListView(this);
        final ArrayList<AvailableListModel> modelArrayList = new ArrayList<>();
        Date date1 = dateFormat.parse(getDuties().get(index).getDate().toString());
        for (int i=0;i<singleUserList().size();i++){
            Duty item = singleUserList().get(i);
            Date date2=dateFormat.parse(item.getDate().toString());
            if (date1.compareTo(date2)<=0){
                modelArrayList.add(new AvailableListModel(item.getUser(),item.getVenu(),item.getSlot().getId(),item.getDate(),i));
            }

        }
        listView.setAdapter(new ListAdapter(this,modelArrayList,getLayoutInflater()));
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setTitle("swap with ...")
                .setView(listView)
                .create()
                .show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 int index1= modelArrayList.get(position).getIndex();
                swapDuty(index1,index);
                finish();
            }
        });
    }
    public void swapDuty(int index1, int index){
        User user = getDuties().get(index).getUser();
        User user2 = getDuties().get(index1).getUser();
        getDuties().get(index).setUser(user2);
        getDuties().get(index1).setUser(user);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                crud.updateDutyTable(new Schedule(5,duties));
            }
        });
//        thread.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;

    }
    public ArrayList<Duty> singleUserList(){
        ArrayList<Duty> dutyArrayList = new ArrayList<>();
        Log.i(LOG,""+getDuties().size());
        for(Duty duty:getDuties()){
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
                if (adapter!=null){
                    adapter.setList(singleUserList());
                    adapter.notifyDataSetChanged();
                    Log.i(LOG,"Notify Dataset Changed");
                }
//                recyclerView.setAdapter(new CustomGeneralViewAdapter(context,singleUserList(),getFragmentManager(),userId));
                break;
            case R.id.action_all:
                if (adapter!=null){
                    adapter.setList(getDuties());
                    adapter.notifyDataSetChanged();
                    Log.i(LOG,"Notify Dataset Changed");
                }
//                recyclerView.setAdapter(new CustomGeneralViewAdapter(context,duties,getFragmentManager(),userId));
                break;
            case R.id.action_setting:
                Intent intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
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

    public ArrayList<Duty> getDuties() {
        return duties;
    }

    public void setDuties(ArrayList<Duty> duties) {
        this.duties = duties;
    }

}





