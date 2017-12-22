package example.com.ustadiapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import example.com.ustadiapp.database.FirebaseCRUD;
import example.com.ustadiapp.model.AvailableModel;
import example.com.ustadiapp.model.GeneralCardModel;
import example.com.ustadiapp.model.SampleModel;
import example.com.ustadiapp.randomData.RandomDutyGenerator;

/**
 * Created by naeem on 12/13/17.
 */

public class MarkAvailableActivity extends AppCompatActivity {
    private FirebaseCRUD crud;
    private Context context;
    private RecyclerView recyclerView;
    private FirebaseAuth firebaseAuth;
    private ArrayList<AvailableModel> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_available_activity);
        this.context=this;
        list = new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        crud = new FirebaseCRUD();
        crud.postSampleSchedule(new RandomDutyGenerator().populateData());
        crud.getSampleRefrence().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SampleModel dataset = dataSnapshot.getValue(SampleModel.class);
                list= dataset.getList();
                recyclerView.setAdapter(new CustomAvailableViewAdapter(context,list));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater= getMenuInflater();
            inflater.inflate(R.menu.options_menu,menu);

            return true;

        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_update:
                FirebaseUser user = firebaseAuth.getCurrentUser();
                SampleModel model = new SampleModel(user.getDisplayName(),user.getEmail(),list);
                crud.postUserSchedule(user.getUid(),model);
                Toast.makeText(this,"Uploaded",Toast.LENGTH_LONG);
                break;
            case R.id.action_logout:
                firebaseAuth.signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
