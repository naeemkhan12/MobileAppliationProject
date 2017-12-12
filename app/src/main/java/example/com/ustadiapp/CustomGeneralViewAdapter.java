package example.com.ustadiapp;

/**
 * Created by naeem on 12/12/17.
 */

//public class CustomGeneralViewAdapter {

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.ustadiapp.model.GeneralCardModel;
import example.com.ustadiapp.model.MyCardModel;
public class CustomGeneralViewAdapter extends RecyclerView.Adapter<CustomGeneralViewAdapter.CustomGeneralViewHolder> {


    private static final String LOG="TESTLOG";
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GeneralCardModel> list;

    public CustomGeneralViewAdapter(Context context, ArrayList<GeneralCardModel> list){
        this.context=context;
        this.list=list;
        this.inflater= LayoutInflater.from(context);

    }

    @Override
    public CustomGeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomGeneralViewHolder(inflater.inflate(R.layout.general_card,parent,false));
    }

    @Override
    public void onBindViewHolder(CustomGeneralViewHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.venu.setText(list.get(position).getVenu());
        holder.subject.setText(list.get(position).getSubject());
        holder.day.setText(list.get(position).getDay());
        holder.slot.setText(list.get(position).getSlot()+"");
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class CustomGeneralViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView venu;
        private TextView subject;
        private TextView day;
        private TextView slot;
        private TextView name;
        public CustomGeneralViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            venu = (TextView) itemView.findViewById(R.id.name);
            subject = (TextView) itemView.findViewById(R.id.subj);
            day = (TextView) itemView.findViewById(R.id.day);
            slot = (TextView) itemView.findViewById(R.id.slot);
            name=(TextView)itemView.findViewById(R.id.name);
        }


    }
}

