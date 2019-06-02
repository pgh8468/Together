package com.example.together_park;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter  {

    Context context;
    ArrayList<String> items;
    LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<String> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        RecyclerView.ViewHolder holder;
        if(v==null){
            v = inflater.inflate(R.layout.listitem,null);
        }
        TextView TextView_ID = (TextView)v.findViewById(R.id.TextView_ID);
        TextView TextView_Chat = (TextView)v.findViewById(R.id.TextView_Chat);
        TextView TextView_date = (TextView)v.findViewById(R.id.TextView_date);

        return v;
    }
}