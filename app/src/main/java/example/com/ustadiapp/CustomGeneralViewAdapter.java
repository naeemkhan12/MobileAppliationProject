package example.com.ustadiapp;

/**
 * Created by naeem on 12/12/17.
 */

//public class CustomGeneralViewAdapter {

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import example.com.ustadiapp.model.AvailableListModel;
import example.com.ustadiapp.model.Duty;

public class CustomGeneralViewAdapter extends RecyclerView.Adapter<CustomGeneralViewAdapter.CustomGeneralViewHolder> {
    private static final String LOG="TESTLOG";
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Duty> list;
    private FragmentManager manager;
    private String userId;
    private PositionCallback mCallback;

    public CustomGeneralViewAdapter(Context context, ArrayList<Duty> list, FragmentManager manager,String userId){
        this.context=context;
        this.manager=manager;
        this.list=list;
        this.userId=userId;
        this.inflater= LayoutInflater.from(context);

    }
    public void setPositionCallback(PositionCallback mCallback){
        this.mCallback=mCallback;
    }
    @Override
    public CustomGeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomGeneralViewHolder(inflater.inflate(R.layout.general_card,parent,false));
    }

    @Override
    public void onBindViewHolder(CustomGeneralViewHolder holder, int position) {

            holder.time.setText(list.get(position).getSlot().getStartTime()+" "+list.get(position).getSlot().getEndTime());
            holder.date.setText(list.get(position).getDate().getDay()+"");
            holder.venu.setText(list.get(position).getVenu());
            holder.name.setText(list.get(position).getUser().getUserName());

//        visibility checks
        if (!list.get(position).getUser().getUserId().equals(userId)){
            holder.imageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public AlertDialog showDialog(View listView,String title){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setCancelable(true)
                .setTitle(title)
                .setView(listView).
                create();
        return builder.show();
    }
    public ArrayList<AvailableListModel> searchAvailables(int position){
        ArrayList<AvailableListModel> availables = new ArrayList<>();

        for (int i=0;i<list.size();i++) {
            String userId = list.get(position).getUser().getUserId();
            int slotId = list.get(position).getSlot().getId();
            Duty item = list.get(i);
            if (!item.getUser().getUserId().equals(userId) && item.getSlot().getId()!=slotId){
                availables.add(new AvailableListModel(item.getUser(),item.getVenu(),item.getSlot().getId(),item.getDate(),i));
            }
        }
        return availables;
    }
    public ListView availablesList(int position){
        ListView listView = new ListView(context);
        ListAdapter adapter = new ListAdapter(context,searchAvailables(position),inflater);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                                            LogModel.i(LOG,"userId: "+values.get(position).getUserId());
            }
        });
        return listView;
    }
    public long dateToMillis(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date =sdf.parse(dateStr);
       return date.getTime();
    }
    public void setOnClickListener(View view , final int position){
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.radio_menu,popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.available_radio:
                        View view = inflater.inflate(R.layout.reason_pop,null);
                        Button ok = (Button)view.findViewById(R.id.ok);
                        Button cancel = (Button)view.findViewById(R.id.cancel);
                        final TextView message = (TextView) view.findViewById(R.id.reason);
                        final AlertDialog dialog = showDialog(view,"Reason for Unavaiablity");
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i(LOG,"Message: "+message.getText());
                                dialog.dismiss();
                                if (mCallback!=null){
                                    mCallback.getPosition(position,message.getText().toString());
                                }
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        return true;
                    case R.id.swap_radio:
                        showDialog(availablesList(position),"Select Person to swap with");
                        Log.i(LOG,"swap radio clicked");
                        return true;
                    case R.id.set_alarm:
                        Duty duty = list.get(position);
                        String dateStr = duty.getDate().toString()+" "+duty.getSlot().getStartTime()+":00";
                        String testStr = "2017/12/20 01:25:00";
                        Log.i(LOG,"Date: "+dateStr);

                        try {
                            long timeInMills = dateToMillis(dateStr);
//                                        new SetAlarm(context).setAlarm(timeInMills);
                        } catch (ParseException e) {
                            Log.i(LOG,"parse error");
                            e.printStackTrace();
                        }
                }
                return false;
            }
        });


    }
//    return position to main activity
    public interface PositionCallback{
    public void getPosition(int position,String flag);
    }


    public class CustomGeneralViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView date;
        private TextView venu;
        private TextView name;
        private ImageView imageView;
        public CustomGeneralViewHolder( View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            venu = (TextView) itemView.findViewById(R.id.venu);
            date = (TextView) itemView.findViewById(R.id.date);
            name=(TextView)itemView.findViewById(R.id.name);
                imageView=(ImageView)itemView.findViewById(R.id.launcher);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setOnClickListener(v,getAdapterPosition());
                    }
                });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId()!=imageView.getId()){
                        if (mCallback!=null){
                            mCallback.getPosition(getAdapterPosition(),null);
                        }
                    }

                }
            });
        }
    }

}

