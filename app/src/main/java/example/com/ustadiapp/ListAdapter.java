package example.com.ustadiapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.ustadiapp.model.AvailableListModel;
public class ListAdapter extends ArrayAdapter<AvailableListModel>{
    private final Context context;
    private final ArrayList<AvailableListModel> values;
    private final LayoutInflater inflater;
    public ListAdapter(@NonNull Context context, ArrayList<AvailableListModel> values, LayoutInflater inflater) {
        super(context, -1);
        this.context=context;
        this.values=values;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.popup_user,parent,false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.username);
        TextView venu = (TextView) convertView.findViewById(R.id.assigned_at);
        TextView slot = (TextView) convertView.findViewById(R.id.date);
        name.setText(values.get(position).getUser().getUserName());
        venu.setText(values.get(position).getVenu());
        slot.setText(values.get(position).getSlot()+"");
        return convertView;
    }
    @Override
    public int getCount() {
        return values.size();
    }
    @Nullable
    @Override
    public AvailableListModel getItem(int position) {
        return values.get(position);
    }
}
