package example.com.ustadiapp;

/**
 * Created by naeem on 12/12/17.
 */

//public class CustomGeneralViewAdapter {

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        holder.date.setText(list.get(position).getDate().substring(0,2));
        holder.venu.setText(list.get(position).getVenu());
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
    public ArrayList<AvailableListModel> searchAvailables(int slot,String date,String userId){
        ArrayList<AvailableListModel> availables = new ArrayList<>();
        int tDate = Integer.parseInt(date.substring(0,2));
        for (GeneralCardModel item: list) {
            if (!item.getId().equals(userId) && item.getSlot()!=slot && tDate<=Integer.parseInt(item.getDate().substring(0,2))){
                availables.add(new AvailableListModel(item.getId(),item.getName(),item.getVenu(),item.getSlot()));
            }
        }
        return availables;
    }

    public class CustomGeneralViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView date;
        private TextView venu;
        private TextView name;
        private ImageView imageView;
        public CustomGeneralViewHolder(final View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            venu = (TextView) itemView.findViewById(R.id.venu);
            date = (TextView) itemView.findViewById(R.id.date);
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
                                    AvailableListAdapter adapter = new AvailableListAdapter(context,searchAvailables(list.get(getAdapterPosition()).getSlot(),list.get(getAdapterPosition()).getDate(),list.get(getAdapterPosition()).getId()),inflater);
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                                            Log.i(LOG,"userId: "+values.get(position).getUserId());
                                        }
                                    });
                                    showDialog(listView);

                                    Log.i(LOG,"swap radio clicked");
                                    return true;
                            }
                            return false;
                        }
                    });

                }
            });
        }

    }

}

