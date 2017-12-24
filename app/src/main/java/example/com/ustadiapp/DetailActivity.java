package example.com.ustadiapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import example.com.ustadiapp.model.Detail;

/**
 * Created by naeem on 12/24/17.
 */

public class DetailActivity extends AppCompatActivity{
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
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_detail);
    }

    public class DetailHolder extends RecyclerView.Adapter<DetailHolder.DetailView>{
        private ArrayList<Detail> modelList;
        public DetailHolder(ArrayList<Detail> modelList){
            this.modelList=modelList;
        }
        @Override
        public DetailView onCreateViewHolder(ViewGroup parent, int viewType) {

            return null;

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
                icon = (ImageView) itemView.findViewById(R.id.detail_icon_profile);

            }
        }
    }
}
