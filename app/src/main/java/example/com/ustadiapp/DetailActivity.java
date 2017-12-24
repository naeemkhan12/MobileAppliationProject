package example.com.ustadiapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import example.com.ustadiapp.model.Detail;

/**
 * Created by naeem on 12/24/17.
 */

public class DetailActivity extends AppCompatActivity{
    private static final String LOG = "TESTLOG";
    private RecyclerView recyclerView;
    private int[] icons = {R.drawable.ic_action_identity,R.drawable.ic_action_email,
            R.drawable.ic_action_subject,R.drawable.ic_action_location,
            R.drawable.ic_action_time,R.drawable.ic_action_date,
            };
    private String[] heading={"Name","Email","Subject","Venu","Time Duration","Date"};
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.detail);
        recyclerView = (RecyclerView) findViewById(R.id.detail_recyclerview);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            ArrayList<String> extrasList= extras.getStringArrayList("Detail");
            Log.i(LOG,"size: "+extrasList.size());
            ArrayList<Detail> detailArrayList = new ArrayList<>();
            for(int i=0;i<heading.length;i++){
                detailArrayList.add(new Detail(heading[i],extrasList.get(i),icons[i]));
            }
            recyclerView.setAdapter(new DetailHolder(detailArrayList,getLayoutInflater()));
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }



    }

    public class DetailHolder extends RecyclerView.Adapter<DetailHolder.DetailView>{
        private ArrayList<Detail> modelList;
        private LayoutInflater inflater;
        public DetailHolder(ArrayList<Detail> modelList, LayoutInflater inflater){
            this.modelList=modelList;
            this.inflater = inflater;
        }
        @Override
        public DetailView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DetailView(inflater.inflate(R.layout.my_card,parent,false));

        }

        @Override
        public void onBindViewHolder(DetailView holder, int position) {
            holder.heading.setText(modelList.get(position).getHeading());
            holder.value.setText(modelList.get(position).getValue());
            holder.icon.setImageResource(modelList.get(position).getIcon());

        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }


        public class DetailView extends RecyclerView.ViewHolder{

            private TextView heading;
            private TextView value;
            private ImageView icon;
            public DetailView(View itemView) {
                super(itemView);
                heading = (TextView) itemView.findViewById(R.id.detail_head);
                value = (TextView) itemView.findViewById(R.id.detail_value);
                icon = (ImageView) itemView.findViewById(R.id.icon);

            }
        }
    }
}
