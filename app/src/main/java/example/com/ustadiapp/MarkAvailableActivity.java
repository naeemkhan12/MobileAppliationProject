package example.com.ustadiapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import example.com.ustadiapp.model.GeneralCardModel;

/**
 * Created by naeem on 12/13/17.
 */

public class MarkAvailableActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_available_activity);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ArrayList<GeneralCardModel> models = new ArrayList<>();
        models.add(new GeneralCardModel("0:00 0:00","WEDNESDAY",1,true));
        models.add(new GeneralCardModel("0:00 0:00","WEDNESDAY",2,true));
        models.add(new GeneralCardModel("0:00 0:00","WEDNESDAY",3,true));
        models.add(new GeneralCardModel("0:00 0:00","WEDNESDAY",4,true));
        models.add(new GeneralCardModel("0:00 0:00","WEDNESDAY",5,true));
        recyclerView.setAdapter(new CustomAvailableViewAdapter(this,models));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
