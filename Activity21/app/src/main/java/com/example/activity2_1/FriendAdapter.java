package com.example.activity2_1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendAdapter extends BaseAdapter {

    private ArrayList<Friend> source;
    private Activity activity;

    public FriendAdapter(ArrayList<Friend> source, Activity activity) {
        this.source = source;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Object getItem(int position) {
        return source.get(position);
    }

    @Override
    public long getItemId(int position) {
        return source.get(position).getAge();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView friendName = convertView.findViewById(R.id.friendName);
        TextView friendHobby = convertView.findViewById(R.id.friendHobby);

        friendName.setText(source.get(position).getName());
        friendHobby.setText(source.get(position).getHobby());
        return convertView;
    }
}
