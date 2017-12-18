package example.com.ustadiapp;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
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
import example.com.ustadiapp.model.Schedule;
import example.com.ustadiapp.randomData.RandomDutyGenerator;


public class MainActivity extends AppCompatActivity {

    private static final String LOG="TESTLOG";
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "TESTLOG";
    private static final String[] DAYS={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private RecyclerView recyclerView;
    private String userId;
    // Choose authentication providers
    private List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build());

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        if (mAuth.getCurrentUser()!=null){
            Log.i(TAG,"User has id "+mAuth.getCurrentUser().getUid());
            userId=mAuth.getCurrentUser().getUid();
//            myRef = database.getReference("schedule/").child(mAuth.getCurrentUser().getUid());
        }else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.signup_theme)
                            .build(),
                    RC_SIGN_IN);
            userId=mAuth.getCurrentUser().getUid();

        }

        if (getIntent().getBooleanExtra("FLAG",false)){
            Intent intent = new Intent(this,MarkAvailableActivity.class);
            startActivity(intent);
        }
        FirebaseCRUD crud = new FirebaseCRUD();
//        crud.updateDutyTable(new RandomDutyGenerator().getRandomSchedule());
        crud.dutyTableRefrence().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Schedule schedule = dataSnapshot.getValue(Schedule.class);
                UpdateUI task = new UpdateUI(getApplication(),userId,schedule);
                task.execute();
                Log.d(LOG,"Dataset Changed"+ schedule.getDate()+schedule.getList().size());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
//        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//            // Successfully signed in
//            if (resultCode == RESULT_OK) {
////                startActivity(SignedInActivity.createIntent(this, response));
//                finish();
//                return;
//            } else {
//                // Sign in failed
//                if (response == null) {
//                    // User pressed back button
////                    showSnackbar(R.string.sign_in_cancelled);
//                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
////                    showSnackbar(R.string.no_internet_connection);
////                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
////                    showSnackbar(R.string.unknown_error);
////                    return;
//                }
//            }
//
////            showSnackbar(R.string.unknown_sign_in_response);
//        }
//    }


    public class UpdateUI extends AsyncTask<Void,Void,Void> {
        private Context context;
        private String userId;
        private Schedule schedule;
        private ArrayList<GeneralCardModel> dataList;
        public UpdateUI(Context context,String userId,Schedule schedule){
            this.context=context;
            this.schedule=schedule;
            this.userId=userId;
            this.dataList = new ArrayList<>();
        }
        @Override
        protected Void doInBackground(Void... params) {
            if (schedule!=null) {
                for (Day day : schedule.getList()) {
                    String time;
                    String venu;
                    String subject;
                    String dayname = DAYS[day.getId()];
                    day.getId();
                    int slot;
                    String id;
                    String username;
                    boolean isAvailable;
                    for (Duty duty : day.getDuties()) {
                        time = duty.getSlot().getStartTime()+" "+duty.getSlot().getEndTime();
                        venu = duty.getVenu();
                        subject = duty.getSubject();
                        slot = duty.getSlot().getId();
                        id=duty.getUser().getUserId();
                        username=duty.getUser().getUserName();
                        isAvailable=duty.isAvailable();
//                        Log.i(LOG,username);
                        dataList.add(new GeneralCardModel(time, venu, subject, dayname, slot,isAvailable,id,username));
                    }

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setAdapter(new CustomGeneralViewAdapter(context,dataList,getFragmentManager()));
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(LOG,"menu item id: "+item.getItemId());
        return super.onOptionsItemSelected(item);
    }
}

 /* Alert Dialogue for cards */

//    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//// ...Irrelevant code for customizing the buttons and title
//dialogBuilder.setView(inflater.inflate(R.layout.alert_label_editor, null));
//        AlertDialog alertDialog = dialogBuilder.create();
//        LayoutInflater inflater = this.getLayoutInflater();
//        EditText editText = (EditText) alertDialog.findViewById(R.id.label_field);
//        editText.setText("test label");
//        alertDialog.show();

//ic boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//        case R.id.action_dropdown1:
//        ...
//        return true;
//
//        case R.id.action_dropdown2:
//        ...
//        return true;
//        ...
//
//default:
//        return super.onOptionsItemSelected(item);
//        }