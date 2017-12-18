package example.com.ustadiapp;

/**
 * Created by naeem on 12/12/17.
 */

//public class CustomGeneralViewAdapter {

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.ustadiapp.model.AvailableListModel;
import example.com.ustadiapp.model.GeneralCardModel;

public class CustomGeneralViewAdapter extends RecyclerView.Adapter<CustomGeneralViewAdapter.CustomGeneralViewHolder> {



    private static final String LOG="TESTLOG";
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GeneralCardModel> list;
    private FragmentManager manager;

    public CustomGeneralViewAdapter(Context context, ArrayList<GeneralCardModel> list, FragmentManager manager){
        this.context=context;
        this.list=list;
        this.manager=manager;
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
        Log.i(LOG,list.get(position).getName());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showDialog(View listView){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setCancelable(true).setView(listView).create().show();
    }

    public class CustomGeneralViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView venu;
        private TextView subject;
        private TextView day;
        private TextView slot;
        private TextView name;
        private ImageView imageView;
        public CustomGeneralViewHolder(final View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            venu = (TextView) itemView.findViewById(R.id.name);
            subject = (TextView) itemView.findViewById(R.id.subj);
            day = (TextView) itemView.findViewById(R.id.day);
            slot = (TextView) itemView.findViewById(R.id.slot);
            name=(TextView)itemView.findViewById(R.id.name);
             imageView=(ImageView)itemView.findViewById(R.id.launcher);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popupMenu=new PopupMenu(context,v);
                    popupMenu.getMenuInflater().inflate(R.menu.radio_menu,popupMenu.getMenu());
                    popupMenu.show();
                    Log.i(LOG,"clicked: "+getAdapterPosition());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.available_radio:
//                                    itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.material_lime_a700));
                                    Log.i(LOG,"available radio clicked");
                                    return true;
                                case R.id.swap_radio:
                                    ListView listView = new ListView(context);
                                    ArrayList<AvailableListModel> values = new ArrayList<AvailableListModel>();
                                    values.add(new AvailableListModel("1","Naeem","LH3",2));
                                    values.add(new AvailableListModel("1","Bashir","LH4",1));
                                    values.add(new AvailableListModel("1","Raza","LH5",3));
                                    values.add(new AvailableListModel("1","Jhanzeb","LH6",4));
                                    AvailableListAdapter adapter = new AvailableListAdapter(context,values,inflater);
                                    listView.setAdapter(adapter);
                                    listView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Log.i(LOG,"Item clicked: ");
                                        }
                                    });
                                    showDialog(listView);

                                    Log.i(LOG,"swap radio clicked");
                                    return true;
                            }
                            return false;
                        }
                    });
//                    DialogFragment fragment= new CustomAlertDialog();
//                    fragment.show(manager,"ATTENTION LADIES AND GENTLEMEN !!");

                }
            });
        }

    }

}

