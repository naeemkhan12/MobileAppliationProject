package example.com.ustadiapp.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import example.com.ustadiapp.model.Schedule;

/**
 * Created by naeem on 12/7/17.
 */

public  class FirebaseCRUD {
    private FirebaseDatabase databaseInstance;
    public FirebaseCRUD(FirebaseDatabase databaseInstance){
        this.databaseInstance=databaseInstance;
    }
    public void postSchedule(String userId, Schedule schedule){
        DatabaseReference databaseReference= databaseInstance.getReference("schedule/").child(userId);
       databaseReference.setValue(schedule);
    }
    public DatabaseReference getRefrence(String userId){
        return databaseInstance.getReference("schedule/").child(userId);
    }

}
