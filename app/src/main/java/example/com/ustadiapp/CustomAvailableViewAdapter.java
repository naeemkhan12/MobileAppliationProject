package example.com.ustadiapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.ustadiapp.model.AvailableModel;
import example.com.ustadiapp.model.GeneralCardModel;

/**
 * Created by naeem on 12/13/17.
 */

public class CustomAvailableViewAdapter extends RecyclerView.Adapter<CustomAvailableViewAdapter.CustomAvailableViewHolder>{

    private static final String LOG="TESTLOG";
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<AvailableModel> list;
    public CustomAvailableViewAdapter(Context context, ArrayList<AvailableModel> list){
        this.context=context;
        this.list=list;
        this.inflater= LayoutInflater.from(context);

    }
    @Override
    public CustomAvailableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new CustomAvailableViewHolder(inflater.inflate(R.layout.availabe_card,parent,false));

    }

    @Override
    public void onBindViewHolder(CustomAvailableViewHolder holder, int position) {
        holder.time.setText(list.get(position).getSlot().getStartTime()+" "+list.get(position).getSlot().getEndTime());
        holder.day.setText(list.get(position).getDay());
        holder.date.setText(list.get(position).getDate().getDay());
        holder.checkBox.setChecked(list.get(position).isAvailable());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class CustomAvailableViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView day;
        private TextView date;
        private CheckBox checkBox;
        private ImageView imageView;
        public CustomAvailableViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            day = (TextView) itemView.findViewById(R.id.venu);
            date = (TextView) itemView.findViewById(R.id.date);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            imageView=(ImageView)itemView.findViewById(R.id.imageView2);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    list.get(getAdapterPosition()).setAvailable(isChecked);
                }
            });
        }


    }
}
