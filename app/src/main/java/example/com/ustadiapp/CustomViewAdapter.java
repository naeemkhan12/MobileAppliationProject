package example.com.ustadiapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.ustadiapp.model.CardModel;

/**
 * Created by naeem on 12/9/17.
 */

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.CustomViewHolder> {

    private static final String LOG="TESTLOG";
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<CardModel> list;

    public CustomViewAdapter(Context context, ArrayList<CardModel> list){
        this.context=context;
        this.list=list;
        this.inflater= LayoutInflater.from(context);

    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new CustomViewHolder(inflater.inflate(R.layout.card,parent,false));

    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.venu.setText(list.get(position).getVenu());
        holder.subject.setText(list.get(position).getSubject());
        holder.day.setText(list.get(position).getDay());
        holder.slot.setText(list.get(position).getSlot()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView venu;
        private TextView subject;
        private TextView day;
        private TextView slot;
        public CustomViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            venu = (TextView) itemView.findViewById(R.id.venu);
            subject = (TextView) itemView.findViewById(R.id.subj);
            day = (TextView) itemView.findViewById(R.id.day);
            slot = (TextView) itemView.findViewById(R.id.slot);
        }


    }
}
